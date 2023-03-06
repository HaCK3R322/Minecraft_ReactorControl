local internet = require("internet")
local urls = require("reactor_control_urls")
local parse = require("parse_response")

local reactor = {}

local reactor_body = ""
reactor_body = reactor_body.."reactorPos=".."right"
reactor_body = reactor_body.."&needReplacing=".."false"


reactor.id = parse.parseInt(internet.request(urls.createReactor, reactor_body))
reactor.reactorPos = "right"
reactor.needReplacing = false

print("CREATE_REACTOR [INFO]: Created reactor with id = "..tostring(reactor.id))
print("CREATE_REACTOR [INFO]: Reactor parameters:")
print("                 id: "..reactor.id)
print("                pos: "..reactor.reactorPos)
print("      needReplacing: "..tostring(reactor.needReplacing))

return reactor
