local component = require("component")
local sides = require("sides")
local robot = require("robot_plus")
local pathes = require("pathes")
local urls = require("reactor_control_urls")
local parse = require("parse_response")
local internet = require("internet")
local os = require("os")
local text = require("text")

local ic = component.inventory_controller



function take_new_uranium() -- add check number of uranium
  print("REACTOR_CONTROL [INFO]: taking uranium started")
  robot.move_path(pathes.to_uranium_rod_plant)
  
  local uranium = ic.getStackInSlot(sides.front, 1)
  if uranium ~= nil then
    ic.suckFromSlot(sides.front, 1)
  else
    print("REACTOR_CONTROL [ERROR]: no uranium found to take")
    return false
  end
  
  robot.move_path(pathes.from_uranium_rod_plant)

  return true
end

function drop_all()
  print("CHECK_REACTOR.DROP_ALL [INFO]: dropping started")
  robot.move_path(pathes.to_uranium_rod_plant)
  
  ic.dropIntoSlot(sides.front, 1, 64) -- left uranium
  robot.select(2)
  ic.dropIntoSlot(sides.front, 2, 64) -- collected moh
  robot.select(1)
  
  robot.move_path(pathes.from_uranium_rod_plant)
  
  print("CHECK_REACTOR.DROP_ALL [INFO]: end")
  return true
end

function is_depleted(rod_block)
  if rod_block == nil then
    return true
  end

  if rod_block.maxDamage - rod_block.damage < 1 then
    return true
  else
    return false
  end
end

function replace_depleted(slots)
  for index, slot in ipairs(slots) do
    ic.suckFromSlot(sides.front, slot)

    if ic.dropIntoSlot(sides.front, slot, 1) then
      print("REACTOR_CONTROL [INFO]: rod " .. tostring(slot) .. " was replaced")
    else
      print("REACTOR_CONTROL [ERROR]: not enough rods to replace rod " .. tostring(slot))
      return false
    end
  end
  
  return true
end

local thisRobot = require("init")

--  /** /isThereAJob
--  * There robot can ask if some reactors he assigned to needs to be served
--  * @param robot id of robot that asks
--  * @return "no" - if there is no one, position of reactor if he needs
--  */
local function getReactorPosToServe()
  local postBody = ""
  postBody = postBody.."robot="..tostring(thisRobot.id)

  print("REACTOR_CONTROL [INFO]: waiting for task...")

  local reactorPosToServe = "no"
  while(reactorPosToServe == "no") do
    os.sleep(15)
    reactorPosToServe = parse.parseStr(internet.request(urls.isThereAJob,postBody))
  end

  print("REACTOR_CONTROL [INFO]: Got a task to serve reactor "..reactorPosToServe)

  return reactorPosToServe
end

--/** /getSlotsToReplace
--* There, after started his serving for some reactor, he asks slots that needs to be replaced
--* @param reactorPos reactorPos of reactor with components to replace
--* @return slots, looks smthng like this: "1 2 3 4"
--*/
local function getSlotsToReplace(reactorPos)
  local postBody = ""
  postBody = postBody.."reactorPos="..reactorPos

  local slotsStr = parse.parseStr(internet.request(urls.getSlotsToReplace,postBody))

  local slotsStrTable = text.tokenize(slotsStr)

  local slots = {}

  for index, slot in ipairs(slotsStrTable) do
    table.insert(slots, tonumber(slot))
  end

  return slots
end

local function getReactorPaths(reactorPos)
  local paths = {}

  if reactorPos == "left" then
    paths.to_reactor = pathes.to_left_reactor
    paths.from_reactor = pathes.from_left_reactor
  elseif reactorPos == "right" then
    paths.to_reactor = pathes.to_right_reactor
    paths.from_reactor = pathes.from_right_reactor
  elseif reactorPos == "front" then
    paths.to_reactor = pathes.to_the_front_reactor
    paths.from_reactor = pathes.from_the_front_reactor
  else
    paths.to_reactor = nil
    paths.from_reactor = nil
  end

  return paths
end

--/** /reactorServed
--* By this link robot can say that he is done this his reactor and can serve, if this will be needed
--* @param reactorPos - just served reactor position to get reactor
--* @param robot - id of robot that just served reactor
--* @return can be ignored
--*/
local function indicateThatServingDone(reactorPos)
  local postBody = ""
  postBody = postBody.."reactorPos="..reactorPos
  postBody = postBody.."&robot="..tostring(thisRobot.id)

  internet.request(urls.reactorServed, postBody)
  print("REACTOR_CONTROL [INFO]: indicated that reactor served")
end

function main()
  print("REACTOR_CONTROL [INFO]: i am inited! My params:")
  print("                 id: "..tostring(thisRobot.id))
  print("               name: "..thisRobot.name)
  print("      currentlyBusy: "..tostring(thisRobot.currentlyBusy))

  while(true) do
    local reactorPosToServe = getReactorPosToServe()
    local slots = getSlotsToReplace(reactorPosToServe)
    local paths = getReactorPaths(reactorPosToServe)

    take_new_uranium()

    robot.move_path(pathes.to_reactors_enter_lever)
    robot.useDown()

    robot.move_path(paths.to_reactor)
    replace_depleted(slots)
    robot.move_path(paths.from_reactor)

    robot.useDown()
    robot.move_path(pathes.from_reactors_enter_lever)

    robot.move_path(pathes.to_uranium_rod_plant)
    drop_all()
    robot.move_path(pathes.from_uranium_rod_plant)

    print("REACTOR_CONTROL [INFO]: serving done")

    indicateThatServingDone(reactorPosToServe)
  end
end

main()