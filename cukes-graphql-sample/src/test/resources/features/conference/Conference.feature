Feature: Star Wars Universe

  Background:
    Given query from file "queries/Berlin2017Conference.graphql"

  Scenario: Should retrieve an amount of Universe heroes
    When the query is executed
    Then response contains property "conference.name" with value "GraphQL-Europe"
    And response contains an array "conference.schedule" of size 23
