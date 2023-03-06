local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactorState = require("create_reactor_state")

local reactorComponentsLib = require("get_components_from_reactor")
local reactorComponents = reactorComponentsLib.getAll()
local reactorTypesIds = require("create_types")

local function getTypeIdComponentTypeByName(name)
	local QUFR_NAME = "ic2:quad_uranium_fuel_rod"
	local N_NAME = "ic2:nuclear"
	local CHS_NAME = "ic2:component_heat_vent"
	local OHS_NAME = "ic2:overclocked_heat_vent"
	local CHE_NAME = "ic2:component_heat_exchanger"

	if name == QUFR_NAME then
		return reactorTypesIds.uraniumFuelTypeId
	elseif name == CHS_NAME then
		return reactorTypesIds.componentHeatSinkerTypeId
	elseif name == OHS_NAME then
		return reactorTypesIds.overclockedHeatSinkerTypeId
	elseif name == CHE_NAME then
		return reactorTypesIds.componentHeatExchangerTypeId
	elseif name == N_NAME then
		return reactorTypesIds.nuclearFuelTypeId
	else
		return nil
	end
end

local schemesComponents = {}

local fuelsSchemeComponents = {}
local heatSinkersSchemeComponents = {}
local heatExchangersSchemeComponents = {}

-- if
-- im
-- here
-- then
-- by now all good

for i=1,#reactorComponents do -- ПЕРЕНЕСТИ INIT COMPONENT В ОТДЕЛЬНУЮ ФУНКЦИЮ
	local componentProperties = reactorComponents[i]
	componentProperties.id = parse.parseInt(internet.request(urls.createComponentProperties, reactorComponents[i]))

	local name = componentProperties.minecraftItemName
	local componentTypeId = getTypeIdComponentTypeByName(name)

	-- FUEL COMPONENT
	if componentTypeId == reactorTypesIds.uraniumFuelTypeId or componentTypeId == reactorTypesIds.nuclearFuelTypeId then
		local fuelComponent = {
			reactorState = reactorState.id,
			componentProperties = componentProperties.id,
			fuelType = getTypeIdComponentTypeByName(name)
		}

		fuelComponent.id = parse.parseInt(internet.request(urls.createFuelsSchemeComponent, fuelComponent))
		fuelComponent.properties = reactorComponents[i]

		table.insert(fuelsSchemeComponents, fuelComponent)

		print("INIT_COMPONENTS [INFO]: inited fuel component \""..name.."\"")

	-- HEAT SINKER COMPONENT
	elseif componentTypeId == reactorTypesIds.componentHeatSinkerTypeId or componentTypeId == reactorTypesIds.overclockedHeatSinkerTypeId then
		local sinkerComponent = {
			reactorState = reactorState.id,
			componentProperties = componentProperties.id,
			heatSinkerType = getTypeIdComponentTypeByName(name)
		}

		sinkerComponent.id = parse.parseInt(internet.request(urls.createHeatSinkersSchemeComponent, sinkerComponent))
		sinkerComponent.properties = reactorComponents[i]

		table.insert(heatSinkersSchemeComponents, sinkerComponent)

		print("INIT_COMPONENTS [INFO]: inited heatSink component \""..name.."\"")

	-- HEAT EXCHANGER COMPONENT
	elseif componentTypeId == reactorTypesIds.componentHeatExchangerTypeId then
		local exchComponent = {
			reactorState = reactorState.id,
			componentProperties = componentProperties.id,
			heatExchangerType = getTypeIdComponentTypeByName(name)
		}

		exchComponent.id = parse.parseInt(internet.request(urls.createHeatExchangersSchemeComponent, exchComponent))
		exchComponent.properties = reactorComponents[i]

		table.insert(heatExchangersSchemeComponents, exchComponent)

		print("INIT_COMPONENTS [INFO]: inited heatExch component \""..name.."\"")
	else
		print("INIT_COMPONENTS [INFO]: could not recognize component \""..name.."\"")
	end
end

schemesComponents.fuelsSchemeComponents = fuelsSchemeComponents
schemesComponents.heatSinkersSchemeComponents = heatSinkersSchemeComponents
schemesComponents.heatExchangersSchemeComponents = heatExchangersSchemeComponents
schemesComponents.typesIds = reactorTypesIds
schemesComponents.reactorState = reactorState

return schemesComponents
