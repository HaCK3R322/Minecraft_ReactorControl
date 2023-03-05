local function parseInt(response)
	local result = ""
	for chunk in response do
		result = result..chunk
	end
	if tonumber(result) == nil then
		print("PARSE_RESPONSE.parseInt [ERROR]: cannot parse to number "..result)
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
	print("PARSE_RESPONSE.parseStr [INFO]: "..result)
	return result
end

local parse = {
	parseInt = parseInt,
	parseStr = parseStr
}

return parse
