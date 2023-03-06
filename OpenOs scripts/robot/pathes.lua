local sides = require("sides")

-- mapping for faster writing

local front = sides.front
local back = sides.back
local right = sides.right
local left = sides.left
local up = sides.up
local down = sides.down

-- path structure:
-- {{<side:sides>, <length:number>}, <other circuits>, ...}

local pathes = {}

pathes.to_hevea_plants = {
  {down, 4},
  {left, 23},
  {up, 2},
  {front, 1},
  {left, 8},
  {right, 7}
}

pathes.from_hevea_plants = {
  {back, 7},
  {right, 8},
  {right, 1},
  {down, 2},
  {front, 23},
  {up, 4},
  {left, 0},
}

pathes.to_west_out = {
  {down, 12},
  {right, 55},
  {up, 42},
  {front, 1}
}

pathes.from_west_out = {
  {back, 1},
  {down, 42},
  {back, 55},
  {left, 0},
  {up, 12}
}

pathes.to_uranium_rod_plant = {
  {back, 1},
  {up, 4},
  {right, 3},
  {right, 5},
  {left, 14},
  {down, 1},
  {left, 2},
  {right, 2},
  {down, 3}
}

pathes.from_uranium_rod_plant = {
  {up, 3},
  {back, 2},
  {right, 2},
  {up, 1},
  {right, 14},
  {right, 5},
  {left, 3},
  {down, 4},
  {right, 1}
}

pathes.to_reactors_enter_lever = {
  {front, 14},
  {left, 4},
  {up, 22},
  {front, 3},
  {right, 12}
}

pathes.from_reactors_enter_lever = {
  {back, 12},
  {right, 3},
  {down, 22},
  {front, 4},
  {right, 14},
  {right, 0},
  {right, 0}
}

pathes.to_the_front_reactor = {
  {front, 7},
  {right, 1},
  {left, 1},
  {down, 2},
  {right, 1},
  {down, 3},
  {left, 3},
  {left, 1}
}

pathes.from_the_front_reactor = {
  {back, 1},
  {left, 3},
  {up, 3},
  {right, 1},
  {up, 2},
  {left, 8},
  {right, 1},
  {right, 0}
}

pathes.to_left_reactor = {
  {left, 4},
  {down, 2},
  {left, 1},
  {right, 0},
  {down, 3},
  {front, 2}
}

pathes.from_left_reactor = {
  {back, 2},
  {up, 3},
  {right, 1},
  {up, 2},
  {right, 4},
  {left, 0}
}

pathes.to_right_reactor = {
  {right, 4},
  {down, 2},
  {right, 1},
  {left, 0},
  {down, 3},
  {front, 2}
}

pathes.from_right_reactor = {
  {back, 2},
  {up, 3},
  {left, 1},
  {up, 2},
  {left, 4},
  {right, 0}
}

return pathes