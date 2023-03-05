local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local ids = {
	uraniumFuelTypeId = nil,
	nuclearFuelTypeId = nil,
	componentHeatExchangerTypeId = nil,
	overclockedHeatSinkerTypeId = nil,
	componentHeatSinkerTypeId = nil
}

-- TYPES CREATION
local uranium_FuelType_body = {
	name = "Quadruple uranium rod",
	timeToDeplet = 20000,
	energyTick = 60,
	heatTick = 96
}

local nuclear_FuelType_body = {
	name = "Depleted uranium",
	timeToDeplet = 0,
	energyTick = 0,
	heatTick = 0
}

local Component_HeatExchangerType_body = {
	name = "Component heat exchanger",
	heatCapacity = 5000,
	closeExchangeTick = 24,
	corpusExchangeTick = 0
}

local Overclocked_HeatSinkerType_body = {
	name = "Overclocked heat sink",
	heatCapacity = 1000,
	closeExchangeTick = 0,
	corpusExchangeTick = 36,
	coolingTick = 20
}

local Component_HeatSinkerType_body = {
	name = "Component heat sink",
	heatCapacity = 0,
	closeExchangeTick = 16,
	corpusExchangeTick = 0,
	coolingTick = 16
}

print("INIT_TYPES [INFO]: started types initiation")

ids.uraniumFuelTypeId = parse.parseInt(internet.request(urls.createFuelType, uranium_FuelType_body))
print("INIT_TYPES [INFO]: Created uranium Fuel Type with id = "..tostring(ids.uraniumFuelTypeId))

ids.nuclearFuelTypeId = parse.parseInt(internet.request(urls.createFuelType, nuclear_FuelType_body))
print("INIT_TYPES [INFO]: Created nuclear Fuel Type with id = "..tostring(ids.nuclearFuelTypeId))

ids.componentHeatExchangerTypeId = parse.parseInt(internet.request(urls.createHeatExchangerType, Component_HeatExchangerType_body))
print("INIT_TYPES [INFO]: Created Component Heat Exchanger Type with id = "..tostring(ids.componentHeatExchangerTypeId))

ids.overclockedHeatSinkerTypeId = parse.parseInt(internet.request(urls.createHeatSinkerType, Overclocked_HeatSinkerType_body))
print("INIT_TYPES [INFO]: Created Overclocked Heat Sinker Type with id = "..tostring(ids.overclockedHeatSinkerTypeId))

ids.componentHeatSinkerTypeId = parse.parseInt(internet.request(urls.createHeatSinkerType, Component_HeatSinkerType_body))
print("INIT_TYPES [INFO]: Created Component Heat Sinker Type with id = "..tostring(ids.componentHeatSinkerTypeId))

return ids
