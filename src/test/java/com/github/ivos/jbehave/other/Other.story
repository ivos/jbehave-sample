Narrative:
As a developer
I want to see that JBehave works with multiple stories
So that I can make informed decision whether to use it on a project

Scenario: Other story test
Given text Hello
When I append World
Then result should be HelloWorld

Scenario: Second test on other story
Given text Java
When I append Behave
And I append Text
And I append Test
Then result should be JavaBehaveTextTest
