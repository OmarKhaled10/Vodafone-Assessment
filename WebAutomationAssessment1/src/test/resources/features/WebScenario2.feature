Feature: feature to test Purchasing multiple items

  Scenario: Successful login with valid credentials

    Given the user is on demoblaze homepage
    And the user presses log in from header
    When the user enters an existing username and password
    And the user clicks on the login button
    Then the user is redirected to the home page
    When the user choose laptops from categories
    And the user chooses a product
    And the user presses add to cart
    Then product added pop up appears
    When the user is on demoblaze Home
    And the user chooses an item
    And presses add to cart
    Then product added pop up page appears
    When the user presses on cart
    Then The items are added correctly to cart
    And Total price is true
    When the user presses place order
    And fills the needed info
    And presses Purchase
    Then Thank you for purchase pop up appears


