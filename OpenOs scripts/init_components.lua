local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactorState = require("create_reactor_state")

local shemesComponentsIds = {}

local fuelsSchemeComponentsIds = {}
local heatSinkersSchemeComponentsIds = {}
local heatExchangersSchemeComponentsIds = {}

local reactorComponents = require("get_components_from_reactor")
local reactorTypesIds = require("create_types")

local QUFR_NAME = "ic2:quad_uranium_fuel_rod"
local CHS_NAME = "ic2:component_heat_vent"
local OHS_NAME = "ic2:overclocked_heat_vent"
local CHE_NAME = "ic2:component_heat_exchanger"

function getTypeIdComponentTypeByName(name)
	if name == QUFR_NAME then
		return reactorTypesIds.uranuimFuelTypeId
	elseif name == CHS_NAME then
		return reactorTypesIds.componentHeatSinkerTypeId
	elseif name == OHS_NAME then
		return reactorTypesIds.overclockedHeatSinkerTypeId
	elseif name == CHE_NAME then
		return reactorTypesIds.componentHeatExchangerTypeId
	else 
		return nil
	end
end

local fuel_i = 1
local exch_i = 1
local sink_i = 1
for i=1,#reactorComponents do
	local componentId = parse.parse(internet.request(urls.createComponentProperties, reactorComponents[i]))
	local name = reactorComponents[i].minecraftItemName
	
	if name == QUFR_NAME then -- FUEL
		local fuelComponentBody = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			fuelType = getTypeIdComponentTypeByName(name)
		}
		fuelsSchemeComponentsIds[fuel_i] = parse.parse(internet.request(urls.createFuelsSchemeComponent, fuelComponentBody))
		fuel_i = fuel_i + 1
		print("INIT_COMPONENTS [INFO]: inited fuel component \""..name.."\"")
	elseif name == CHS_NAME or name == OHS_NAME then -- SINKERS
		local sinkerComponentBody = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			heatSinkerType = getTypeIdComponentTypeByName(name)
		}
		heatSinkersSchemeComponentsIds[sink_i] = parse.parse(internet.request(urls.createHeatSinkersSchemeComponent, sinkerComponentBody))
		sink_i = sink_i + 1
		print("INIT_COMPONENTS [INFO]: inited heatSink component \""..name.."\"")
	elseif name == CHE_NAME then -- EXCHANGERS
		local exchComponentBody = {
			reactorState = reactorState.id,
			componentProperties = componentId,
			heatExchangerType = getTypeIdComponentTypeByName(name)
		}
		heatExchangersSchemeComponentsIds[exch_i] = parse.parse(internet.request(urls.createHeatExchangersSchemeComponent, exchComponentBody))
		exch_i = exch_i + 1
		print("INIT_COMPONENTS [INFO]: inited heatExch component \""..name.."\"")
	else 
		print("INIT_COMPONENTS [INFO]: could not recognize component \""..name.."\"")
	end
end

shemesComponentsIds.fuelsSchemeComponentsIds = fuelsSchemeComponentsIds
shemesComponentsIds.heatExchangersSchemeComponentsIds = heatExchangersSchemeComponentsIds
shemesComponentsIds.heatSinkersSchemeComponentsIds = heatSinkersSchemeComponentsIds

return shemesComponentsIds
