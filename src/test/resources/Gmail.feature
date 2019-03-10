@all
Feature: Gmail

	@normal
	Scenario: Send an email with an image
		Given I am on Gmail page
		And I press "Compose" button
		And I input email address for recipient and attach an image
		When I press "Send" button
		Then the email sent is visible in Sent page
	
	Scenario Outline: 
	| image | recipient 				|
	| 1 	| ecse.428.test@gmail.com 	|
	| 2		| ecse.428.test@gmail.com 	|
	| 3		| ecse.428.test@gmail.com 	|
	| 4		| ecse.428.test@gmail.com 	|
	| 5		| ecse.428.test@gmail.com 	|
    
	@alternative
	Scenario: Reply an email with an image
		Given I am on Gmail page
		And I press an email to reply
		And I press "Reply" button
		And I attach an image
		When I press "Send" button
		Then the email sent is visible in Sent page
			
	@error
	Scenario: No recipient to send an email
		Given that I am on my current composed message
		And I input everything except the address of recipient 
		When I press "Send" button
		Then a window showing "Error - Please indicate at least one recipient" appears
