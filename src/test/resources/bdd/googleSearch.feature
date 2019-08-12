Feature: Open Chrome and search for 'Royal Schiphol Group'

  Scenario: Search Royal Schiphol Group

    Given Google page 'https://google.nl'
    When I search for 'Royal Schiphol Group'
    Then Open the first link and verify I am on the RSG website 'https://www.schiphol.nl/nl/schiphol-group/'