Feature: Star Wars Universe

  Background:
    Given query from file "queries/getAllPeople"

  Scenario: Should retrieve an amount of Universe heroes
    When the query is executed
    Then response contains property "allPeople.totalCount" with value "82"
    And response contains an array "allPeople.people" of size 82

  Scenario: Should make sure that Luke Skywalker is in the response
    When the query is executed
    Then response contains property "name" in array "allPeople.people" with value "Luke Skywalker"
