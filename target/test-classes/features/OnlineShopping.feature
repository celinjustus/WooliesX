@SmokeTest
Feature:Online shopping feature

  Scenario: I want to be able to add 2 items to the cart and place an order
    Given I am on online Shopping app page
    When I login with email "celinautomation@gmail.com" and password "Welcome@123"
    Then I add 2 items to the cart
    And place an order