local urls = {}

local host = "http://localhost:8080"

urls.hostUrl = host

urls.createComponentProperties = host.."/createComponentProperties"
urls.createReactor = host.."/createReactor"
urls.createReactorState = host.."/createReactorState"

-- Types
urls.createFuelType = host.."/createFuelType"
urls.createHeatExchangerType = host.."/createHeatExchangerType"
urls.createHeatSinkerType = host.."/createHeatSinkerType"

-- Components
urls.createFuelsSchemeComponent = host.."/createFuelsSchemeComponent"
urls.createHeatExchangersSchemeComponent = host.."/createHeatExchangersSchemeComponent"
urls.createHeatSinkersSchemeComponent = host.."/createHeatSinkersSchemeComponent"

-- updating
urls.updateComponentProperties = host.."/updateComponentProperties"

return urls
