local component = require("component")
local ic = component.inventory_controller
local sides = require("sides")

local function createComponent(_minecraftItemName, _damage, _maxDamage, _slot)
	local componentProperties = {
		minecraftItemName = _minecraftItemName,
		damage = _damage,
		maxDamage = _maxDamage,
		slot = _slot
	}
	
	return componentProperties
end

local reactorComponents = {}

function reactorComponents:getAll()
	local components = {}

	local j = 1
	for i=1,ic.getInventorySize(sides.up) do
		local item = ic.getStackInSlot(sides.up, i)

		if item ~= nil then
			components[j] = createComponent(
				item.name,
				item.damage,
				item.maxDamage,
				i
			)
			j = j + 1
		end
	end

	return components
end

function reactorComponents:getBySlots(slots)
	local components = {}

	local j = 1
	for i=1,#slots do
		local item = ic.getStackInSlot(sides.up, slots[i])
		if item ~= nil then
			components[j] = createComponent(
					item.name,
					item.damage,
					item.maxDamage,
					i
			)
			j = j + 1
		end
	end

	return components
end

return reactorComponents

