local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactor = require("create_reactor")

local reactorState = {}

local reactorState_body = {
	reactor = reactor.id,
	maxOut = 420,
	currentOut = 0,
	coreHeat = 0,
	currentlyWorking = false
}

reactorState.id = parse.parse(internet.request(urls.createReactorState, reactorState_body))
reactorState.reactor = reactorState_body.reactor
reactorState.maxOut = reactorState_body.maxOut
reactorState.currentOut = reactorState_body.currentOut
reactorState.coreHeat = reactorState_body.coreHeat
reactorState.active = reactorState_body.active

print("CREATE_REACTOR_STATE [INFO]: Created Reactor state with id = "..tostring(reactorState.id))

return reactorState
