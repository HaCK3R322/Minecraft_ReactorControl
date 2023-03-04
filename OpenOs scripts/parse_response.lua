local parse = {}

function parse.parse(response)
	local result = ""
	for chunk in response do
		result = result..chunk
	end
	return tonumber(result)
end

return parse
