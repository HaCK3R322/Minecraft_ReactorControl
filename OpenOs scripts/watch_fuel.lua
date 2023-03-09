local parse = require("parse_response")
local initedComponents = require("init_components")
local urls = require("reactor_control_urls")
local reactorComponents = require("get_components_from_reactor")
local os = require("os")
local internet = require("internet")
local io = require("io")

local fuelsSchemeComponents = initedComponents.fuelsSchemeComponents
-- ROBOT ASSIGNING

-- reactor there is assigned reactor_id, okay?
local assignedRobotId  = parse.parseInt(internet.request(urls.askToAssignRobot, {reactor = initedComponents.reactorState.reactor}))
print("WATCH_FUEL [INFO]: assigned robot id = "..tostring(assignedRobotId))

-- updateComponentProperties
local function updateProperties(fuelsSchemeComponent, newProperties)
    newProperties.id = fuelsSchemeComponent.properties.id
    parse.parseInt(internet.request(urls.updateComponentProperties, newProperties))

    fuelsSchemeComponent.properties = newProperties
end

-- spring description of /askServing
--/**
--* By this link some reactor can ask for fuel replacement
--* @param reactor - reactor to serve
--* @param slotsToReplace - List of slots that need replacement
--* @return 0:number if no free robots assigned to this reactor, and 1 if some robot started its work
--*/
local function replaceDepletedInReactor(depletedComponents)
    local postBody = ""

    local reactor = initedComponents.reactorState.reactor
    postBody = postBody.."reactor="..tostring(reactor)

    for index, depletedComponent in ipairs(depletedComponents) do
        postBody = postBody.."&slotsToReplace="..tostring(depletedComponent.properties.slot)
    end

    while parse.parseInt(internet.request(urls.askServing, postBody)) ~= 1 do
        print("WATCH_FUEL [INFO]: waiting for free robot...")
        os.sleep(10)
    end
    print("WATCH_FUEL [INFO]: robot started his work")

    local time_of_waiting = 0
    while parse.parseStr(internet.request(urls.canIResumeWork, {reactor = reactor})) ~= "yes" do
        io.write("\rWATCH_FUEL [INFO]: waiting to robot ends replacing..."..tostring(time_of_waiting).."s")
        os.sleep(10)
        time_of_waiting = time_of_waiting + 10
    end
    print("\nWATCH_FUEL [INFO]: robot said that he's done.")
end

--/** /deleteFuelSchemeComponent
--* Gets fuels scheme id and removes it from DB
--* @param id - id in fuels scheme
--* @return ignored
--*/
local function deleteDepletedFromDB(depletedComponents)
    for index, depletedComponent in ipairs(depletedComponents) do
        local postBody = "id="..depletedComponent.id

        internet.request(urls.deleteFuelSchemeComponent, postBody)()

        print("WATCH_FUEL [INFO]: depleted fuel component id = "..tostring(depletedComponent.id).." removed from db")
    end
end


print("WATCH_FUEL [INFO]: starting watching for "..tostring(#fuelsSchemeComponents).." fuel components")
while(true) do
    local depletedComponents = {}

    -- iterate over all components and update their props
    -- if there will be some ic2:nuclear props, then fuel depleted
    for index, fuelsSchemeComponent in ipairs(fuelsSchemeComponents) do
        -- GET NEW PROPS FROM REACTOR
        local newProperties = reactorComponents.getFromSlot(fuelsSchemeComponent.properties.slot)

        local sameName = fuelsSchemeComponent.properties.minecraftItemName == newProperties.minecraftItemName
        local nameIsNuclear = fuelsSchemeComponent.properties.minecraftItemName == "ic2:nuclear"
        updateProperties(fuelsSchemeComponent, newProperties)

        if not sameName or nameIsNuclear then
            print("WATCH_FUEL [INFO]: found nuclear fuel scheme component. id = "..tostring(fuelsSchemeComponent.id))
            table.insert(depletedComponents, fuelsSchemeComponent)
        end

    end

    if #depletedComponents > 0 then
        print("WATCH_FUEL [INFO]: found depleted components. Started replacement.")

        replaceDepletedInReactor(depletedComponents)

        deleteDepletedFromDB(depletedComponents)

        -- INIT NEW COMPONENTS
        for index, depletedComponent in ipairs(depletedComponents) do
            local slot = depletedComponent.properties.slot

            local componentProperties = reactorComponents.getFromSlot(slot)
            componentProperties.id = parse.parseInt(internet.request(urls.createComponentProperties, componentProperties))

            local fuelComponent = {
                reactorState = initedComponents.reactorState.id,
                componentProperties = componentProperties.id,
                fuelType = initedComponents.typesIds.uraniumFuelTypeId
            }

            fuelComponent.id = parse.parseInt(internet.request(urls.createFuelsSchemeComponent, fuelComponent))
            fuelComponent.properties = reactorComponents.getFromSlot(slot)

            table.insert(fuelsSchemeComponents, fuelComponent)

            print("WATCH_FUEL.INIT_COMPONENTS [INFO]: inited fuel component \""..fuelComponent.properties.minecraftItemName.."\"")
        end

        -- delete depleted from watching
        for i, fuelsSchemeComponent in ipairs(fuelsSchemeComponents) do
            for j, depletedComponent in ipairs(depletedComponents) do
                if fuelsSchemeComponent.id == depletedComponent.id then
                    table.remove(fuelsSchemeComponents, i)
                end
            end
        end

        depletedComponents = {}
    end

    os.sleep(5)
end