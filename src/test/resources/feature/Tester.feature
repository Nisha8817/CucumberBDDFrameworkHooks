Feature: About Testers
 
In order to get a high paid job in IT,
As a Tester I want to learn Automation concepts
 
@smoke
Scenario: I am a manual tester
  Given I am a "manual" tester
  When I apply for a job
  Then I got "less" paid job
  And I am "not" satisfied with my salary
  But my parents are "still" satisfied
 
@regression
Scenario: I am a automation tester
  Given I am a "Automation" tester
  When I apply for a job
  Then I got "high" paid job
  And I am "highly" satisfied with my salary
  But my parents are "also more" satisfied
 
@sanity
Scenario: I am a automation tester
  Given I am a "API" tester
  When I apply for a job
  Then I got "decent" paid job
  And I am "very much" satisfied with my salary
  But my parents are "also more" satisfied
