Feature: Gmail

Scenario: Send an email with an image
	Given I am on Gmail page
	And I press "Compose" button
	And I input recipient¡¯s email address and attach an image
	When I press "Send" button
	Then the email sent is visible in Sent page
	
Scenario: Reply an email with an image
	Given that I am on an existing email thread
	And I press the "Reply" button at the bottom
	And I attach an image
	When I press "Send" button
	Then the email sent is visible in Sent page

Scenario: Removing content of an email before sending
	Given that I am on my current composed message
	And I press the "X" next to the attached image
	And I erase the message
	Then there is no message and attached image to be sent

Scenario: No recipient to send an email
	Given that I am on my current composed message
	And I erase the recipient¡¯s email address
	Then there is no recipient to the email 
	And a window showing ¡°Error - Please indicate at least one recipient¡± appears
