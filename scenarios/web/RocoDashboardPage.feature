@dashboardpage
Feature: ROCO Web Application Dashboard Test Feature
    
    @dashboardpage01 @regression @endToend @prodReleased
    @dataFile:${env.resources}/data/pilotScenarioData.json
  	Scenario: 01 Login Page: Login to ROCO application with Valid  Credentials
  	Given user is on ROCO application
    When user verify sign on page with '<usernameORS>' and '<passwordORS>'
    #Then user verifies Channel Secure Sign On label is displayed in Channel Secure Sign On Page