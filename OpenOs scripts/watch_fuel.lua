local parse = require("parse_response")
local components = require("init_components")
local urls = require("reactor_control_urls")
local reactorComponents = require("get_components_from_reactor")
local os = require("os")
local internet = require("internet")

local fuelsSchemeComponents = components.fuelsSchemeComponents

function send(fuelsSchemeComponent)
    local componentProperties = fuelsSchemeComponent.properties
    local response = parse.parseStr(internet.request(urls.updateComponentProperties, componentProperties))
    print("WATCH_FUEL [INFO]: updated component properties with id = "..tostring(componentProperties.id).." : "..tostring(response))
end

while(true) do
    for i=1,#fuelsSchemeComponents do
        local slot = fuelsSchemeComponents[i].properties.slot
        print("WATCH_FUEL [INFO]: slot is "..tostring(slot))
        local newComponentProperties = reactorComponents.getFromSlot(slot)
        newComponentProperties.id = fuelsSchemeComponents[i].properties.id

        fuelsSchemeComponents[i].properties = newComponentProperties

        send(fuelsSchemeComponents[i])
    end

    os.sleep(5)
end