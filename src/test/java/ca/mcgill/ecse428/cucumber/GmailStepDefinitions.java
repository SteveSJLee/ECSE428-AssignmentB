package ca.mcgill.ecse428.cucumber;

import ca.mcgill.ecse428.email.Gmail;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Assert;


/*

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


 * 
 */
public class GmailStepDefinitions {
	
	Gmail gmail;
	String gmail_url = "https://mail.google.com/mail/#inbox";
	
	/* START Customizable variables */
    String email = "ecse.428.test@gmail.com";   // user's email
	String password = "asdf1234()_+";			// user's password
	
	String recipient = "ecse.428.test@gmail.com";
	String subject = "Email test";
	String imageFilePath = System.getProperty("user.dir").toString()+"/resources/img1.png";
	
	String imageURL = "https://github.com/SteveSJLee/ECSE428-AssignmentB/blob/master/resources/img.png?raw=true";
	/* END Customizable variable */
	
	
	@Before()
    public void beforeScenario() throws Throwable {
		// initialize gmail object
		if (gmail == null) {
			gmail = new Gmail();
			gmail.setWebdriver();
		}
	}
	
	@After()
    public void afterScenario() throws Throwable {
		// clean up the process
		// 3 second gap between each scenario
		Thread.sleep(3000);
		gmail.killDriver();
	}
	
	@Given("^I am on Gmail page$")
    public void givenOnGmailPage() throws Throwable {
		gmail.goToUrl(gmail_url);
		Boolean success = gmail.signIn(email, password);
		Assert.assertEquals(true, success);
	}
	
	@And("^I press \"Compose\" button$")
    public void pressComposeButton() throws Throwable {
		gmail.clickComposeButton();
	}
	
	@And("^I input email address for recipient and attach an image$")
    public void inputContentsForEmail() throws Throwable {
		gmail.inputContents(recipient, subject);
		gmail.attachImage(imageFilePath);
	}
	
	@When("^I press \"Send\" button$")
    public void pressSendButton() throws Throwable {
		gmail.clickSendButton();
	}
    
	@Then("^the email sent is visible in Sent page$")
    public void checkIfSent() throws Throwable {
		gmail.checkIfEmailisSent();
		gmail.goToUrl(new String("https://www.google.com"));
	}
	
	@Given("^that I am on my current composed message$")
    public void onCurrentComposedMessage() throws Throwable {
		gmail.goToUrl(gmail_url);
		Boolean success = gmail.signIn(email, password);
		Assert.assertEquals(true, success);
		gmail.clickComposeButton();
	}

	@And("^I input everything except the address of recipient$")
    public void inputExceptRecipientAddress() throws Throwable {
		gmail.inputContents("", subject);
		gmail.attachImage(imageFilePath);
	}
	
	@Then("^a window showing \"Error - Please indicate at least one recipient\" appears$")
    public void errorWindowAppears() throws Throwable {
		Assert.assertEquals(new String("Please specify at least one recipient."), gmail.getErrorMessage());
	}
	
	@And("^I input address for recipient and subject$")
    public void inputAddressAndSubject() throws Throwable {
		gmail.inputContents(recipient, subject);
	}
	
	@And("^I press an email to reply$")
    public void pressAnEmail() throws Throwable {
		gmail.lookForAnEmail(recipient);
	}
	
	@And("^I press \"Reply\" button$")
    public void pressReplyButton() throws Throwable {
		gmail.clickReplyButton();
	}
	
	@And("^I attach an image$")
    public void attachImage() throws Throwable {
		gmail.attachImage(imageFilePath);
	}	
	

}
