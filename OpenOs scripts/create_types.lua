local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local ids = {
	[0] = "uranuimFuelTypeId, componentHeatExchangerTypeId, overclockedHeatSinkerTypeId, componentHeatSinkerTypeId",
	uranuimFuelTypeId = nil,
	componentHeatExchangerTypeId = nil,
	overclockedHeatSinkerTypeId = nil,
	componentHeatSinkerTypeId = nil
}

-- TYPES CREATION
local uranuim_FuelType_body = {
	name = "Quadruple uranium rod",
	timeToDeplet = 20000,
	energyTick = 60,
	heatTick = 96
}

local Component_HeatExchangerType_body = {
	name = "Component heat exchanger",
	heatCapacity = 5000,
	closeExchangeTick = 24,
	corpusExchangeTick = 0
}

local Overclocked_HeatSinkerType_body = {
	name = "Overclocked heatsink",
	heatCapacity = 1000,
	closeExchangeTick = 0,
	corpusExchangeTick = 36,
	coolingTick = 20
}

local Component_HeatSinkerType_body = {
	name = "Component heatsink",
	heatCapacity = 0,
	closeExchangeTick = 16,
	corpusExchangeTick = 0,
	coolingTick = 16
}

print("INIT_TYPES [INFO]: started types initiation")

ids.uranuimFuelTypeId = parse.parse(internet.request(urls.createFuelType, uranuim_FuelType_body))
print("INIT_TYPES [INFO]: Created uranuim Fuel Type with id = "..tostring(ids.uranuimFuelTypeId))

ids.componentHeatExchangerTypeId = parse.parse(internet.request(urls.createHeatExchangerType, Component_HeatExchangerType_body))
print("INIT_TYPES [INFO]: Created Component Heat Exchanger Type with id = "..tostring(ids.componentHeatExchangerTypeId))

ids.overclockedHeatSinkerTypeId = parse.parse(internet.request(urls.createHeatSinkerType, Overclocked_HeatSinkerType_body))
print("INIT_TYPES [INFO]: Created Overclocked Heat Sinker Type with id = "..tostring(ids.overclockedHeatSinkerTypeId))

ids.componentHeatSinkerTypeId = parse.parse(internet.request(urls.createHeatSinkerType, Component_HeatSinkerType_body))
print("INIT_TYPES [INFO]: Created Component Heat Sinker Type with id = "..tostring(ids.componentHeatSinkerTypeId))

return ids
