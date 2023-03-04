local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactor = {}

local reactor_body = {
	reactorPos = "left"
}

local id = parse.parse(internet.request(urls.createReactor, reactor_body))
print("CREATE_REACTOR [INFO]: Created reactor with id = "..tostring(id))

reactor.id = id
reactor.reactorPos = reactor_body.reactorPos

return reactor
