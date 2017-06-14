Feature: Star Wars Universe

  Scenario: Should retrieve a list of Universe heroes

    Given query from file "queries/getAllPeople"
    When the query is executed
    Then response contains
