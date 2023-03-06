local component = require("component")
local robot = require("robot")
local sides = require("sides")

function robot.move(side, length)
  if side == sides.left then
    robot.turnLeft()
    for i=1,length do
      if not robot.forward() then return false end
    end
  elseif side == sides.right then
    robot.turnRight()
    for i=1,length do
      if not robot.forward() then return false end
    end
  else
    for i=1,length do
      if not component.robot.move(side) then
        return false
      end
    end
  end
  
  return true
end

------- moving by given path
------- returns: true if success, false if one of the steps was ruined
------- accepts: path table like {{sides.front, 4}, {sides.back, 3}, ..., {<side listed in sides> <length>}}

function robot.move_path(path, attempts_per_step, sleep_time)
  local attempts = 1
  if attempts_per_step ~= nil then
    attempts = attempts_per_step
  end

  for i=1,#path do
    local side = path[i][1]
    local length = path[i][2]
    
    for attempt=1,attempts+1 do
      if robot.move(side, length) then break end
      if attempt == attempts + 1 then -- limit for attempts
        print("MOVE_PATH [ERROR]: attempts limit exceed on path section [" .. tostring(i) .. "]")
        return false
      end
      
      if sleep_time ~= nil then
        os.sleep(sleep_time)
      end
    end
  end

  return true
end

return robot