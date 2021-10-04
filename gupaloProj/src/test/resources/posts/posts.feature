@PostTest @FullRegression
Feature: Post feature

Background:
  Given User opens 'Home' page

  @R003
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser
  Scenario: R003 Check number of posts
    And Create 2 new posts via API for 'default' user and 'default' password
    When User clicks on 'Profile' button on 'Home' page
    Then User is redirect to Profile page
    And User sees 2 posts in Posts lost on Profile page



