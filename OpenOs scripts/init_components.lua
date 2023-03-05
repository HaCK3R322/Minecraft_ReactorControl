local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactorState = require("create_reactor_state")

local reactorComponentsLib = require("get_components_from_reactor")
local reactorComponents = reactorComponentsLib.getAll()
local reactorTypesIds = require("create_types")

local QUFR_NAME = "ic2:quad_uranium_fuel_rod"
local N_NAME = "ic2:nuclear"
local CHS_NAME = "ic2:component_heat_vent"
local OHS_NAME = "ic2:overclocked_heat_vent"
local CHE_NAME = "ic2:component_heat_exchanger"

function getTypeIdComponentTypeByName(name)
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

local fuel_i = 1
local exch_i = 1
local sink_i = 1
for i=1,#reactorComponents do
	local componentId = parse.parseInt(internet.request(urls.createComponentProperties, reactorComponents[i]))
	local name = reactorComponents[i].minecraftItemName

	-- FUEL COMPONENT
	if name == QUFR_NAME or name == N_NAME then
		local fuelComponent = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			fuelType = getTypeIdComponentTypeByName(name)
		}

		fuelComponent.id = parse.parseInt(internet.request(urls.createFuelsSchemeComponent, fuelComponent))
		fuelComponent.properties = reactorComponents[i]
		fuelComponent.properties.id = componentId

		fuelsSchemeComponents[fuel_i] = fuelComponent
		fuel_i = fuel_i + 1

		print("INIT_COMPONENTS [INFO]: inited fuel component \""..name.."\"")

	-- HEAT SINKER COMPONENT
	elseif name == CHS_NAME or name == OHS_NAME then
		local sinkerComponent = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			heatSinkerType = getTypeIdComponentTypeByName(name)
		}

		sinkerComponent.id = parse.parseInt(internet.request(urls.createHeatSinkersSchemeComponent, sinkerComponent))
		sinkerComponent.properties = reactorComponents[i]
		sinkerComponent.properties.id = componentId

		heatSinkersSchemeComponents[sink_i] = sinkerComponent
		sink_i = sink_i + 1

		print("INIT_COMPONENTS [INFO]: inited heatSink component \""..name.."\"")

	-- HEAT EXCHANGER COMPONENT
	elseif name == CHE_NAME then -- EXCHANGERS
		local exchComponent = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			heatExchangerType = getTypeIdComponentTypeByName(name)
		}

		exchComponent.id = parse.parseInt(internet.request(urls.createHeatExchangersSchemeComponent, exchComponent))
		exchComponent.properties = reactorComponents[i]
		exchComponent.properties.id = componentId

		heatExchangersSchemeComponents[exch_i] = exchComponent
		exch_i = exch_i + 1

		print("INIT_COMPONENTS [INFO]: inited heatExch component \""..name.."\"")
	else
		print("INIT_COMPONENTS [INFO]: could not recognize component \""..name.."\"")
	end
end

schemesComponents.fuelsSchemeComponents = fuelsSchemeComponents
schemesComponents.heatSinkersSchemeComponents = heatSinkersSchemeComponents
schemesComponents.heatExchangersSchemeComponents = heatExchangersSchemeComponents

return schemesComponents
