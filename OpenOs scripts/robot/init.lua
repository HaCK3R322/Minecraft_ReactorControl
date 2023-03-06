local r = require("robot_plus")
local pathes = require("pathes")
local sides = require("sides")
local component = require("component")
local parse = require("parse_response")
local internet = require("internet")
local urls = require("reactor_control_urls")
local ic = component.inventory_controller

local robot = {
    id = -1,
    name = r.name(),
    currentlyBusy = false
}

local function init()
    local postBody = ""
    postBody = postBody.."name="..robot.name
    postBody = postBody.."&currentlyBusy="..tostring(robot.currentlyBusy)

    robot.id = parse.parseInt(internet.request(urls.createRobot, postBody))
end

init()

return robot