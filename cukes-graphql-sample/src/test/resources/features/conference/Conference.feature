Feature: Star Wars Universe

  Background:
    Given query from file "queries/Berlin2017Conference.graphql"

  Scenario: Should retrieve an amount of Universe heroes
    When the query is executed
    Then response contains property "conference.name" with value "GraphQL-Europe"
    And response contains an array "conference.tickets" of size 3
    And response contains property "conference.dateStart" with value "2017-05-21"
    And response contains property "conference.dateStart" with value "2017-05-21"
    And response contains an array "conference.sponsors" with object having property "name" with value "Facebook"
