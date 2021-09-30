@PostsTest @FullRegression
Feature: Post feature

  Background:
# all in background will be executed for each scenario in this feature file
    Given User opens 'Home' page

  @R003
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser
  Scenario: R003 Check number of posts
    And Create 2 new post via API for 'default' user and 'default' password
    And User click on 'Profile' button on 'Home' page
    Then User is redirected to Profile page
    And User sees 2 posts in Posts list on Profile page

