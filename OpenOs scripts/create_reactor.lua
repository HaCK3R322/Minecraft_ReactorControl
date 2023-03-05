local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")
local function printParseTable(t)
	for k, v in pairs(t) do
		print(k, v)
	end
end

-- Call the printParseTable function with the parse table
printParseTable(parse)

local reactor = {}

local reactor_body = {
	reactorPos = "left"
}

local id = parse.parseInt(internet.request(urls.createReactor, reactor_body))
print("CREATE_REACTOR [INFO]: Created reactor with id = "..tostring(id))

reactor.id = id
reactor.reactorPos = reactor_body.reactorPos

return reactor
