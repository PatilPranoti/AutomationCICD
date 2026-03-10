
@tag
Feature: Error validation
I want to use this template for my feature file


@tag2 
Scenario Outline:
Error validate
Given I landed on Ecommerce Page
When Logged in with username <name> and password <password>
Then "Incorrect email or password" message is displayed

Examples:
| name				|	password		|	 productName	|
|pjadhav@gmail.com	|	@Panu123		|	ZARA COAT 3		|