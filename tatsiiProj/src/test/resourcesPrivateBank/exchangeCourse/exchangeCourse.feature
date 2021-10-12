@ExchangeCourseTest @FullRegression
Feature: ExchangeCourse Feature

  Background:
    Given Exchange Course API call

  @APIcall
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser
  Scenario: Exchange Course API call
    When Check course response

