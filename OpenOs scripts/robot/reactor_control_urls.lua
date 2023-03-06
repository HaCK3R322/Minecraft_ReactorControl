local urls = {}

local host = "http://localhost:8080"

urls.hostUrl = host

urls.createComponentProperties = host.."/createComponentProperties"
urls.createReactor = host.."/createReactor"
urls.createReactorState = host.."/createReactorState"
urls.createRobot = host.."/createRobot"

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

-- serving stuff
urls.askToAssignRobot = host.."/askToAssignRobot" -- reactor asks to assign robot for him
urls.askServing = host.."/askServing" -- reactor tells that he need to serve some of his slots
urls.isThereAJob = host.."/isThereAJob" -- robot asks if there is job for him
urls.getSlotsToReplace = host.."/getSlotsToReplace" -- if there is a job, then what will slots be?
urls.reactorServed = host.."/reactorServed" -- robot reports: job is done! ready to next
urls.canIResumeWork = host.."/canIResumeWork" -- reactor asks: am i served?
urls.deleteFuelSchemeComponent = host.."/deleteFuelSchemeComponent"

return urls
