local function parseInt(response)
	local result = ""
	for chunk in response do
		result = result..chunk
	end
	if tonumber(result) == nil then
		return nil
	else
		return tonumber(result)
	end
end

local function parseStr(response)
	local result = ""
	for chunk in response do
		result = result..chunk
	end
	return result
end

local parse = {
	parseInt = parseInt,
	parseStr = parseStr
}

return parse
