package ca.mcgill.ecse428.email;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail {
	
    // Variables
    private WebDriver driver;
    private final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    private final String email = "ecse.428.test@gmail.com";
    private final String password = "asdf1234()_+";
    
	
	/**
	 * Set Chrome driver depending on user's OS
	 */
	public void setWebdriver() {
		if (this.driver == null) {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("mac")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver");
			} else {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			}
			this.driver = new ChromeDriver();
		}
	}
	
	/**
	 * get Chrome driver, initialize it not set.
	 */
	public void getWebdriver() {
		setWebdriver();
		this.driver = new ChromeDriver();
	}
	
	
    /**
	 * Sign in to Gmail using google account
	 * @param emailAddress
	 * @param password
	 * @return True - Success, False - failed 
	 */
	public Boolean signIn(String emailAddress, String password) {
		if(emailAddress.equals("") || password.equals("")) {
			System.out.println("Email or password can't be empty.");
			return false;
		}
		try {
			if (this.driver.getCurrentUrl().contains("https://accounts.google.com/signin/v2/identifier?")) {
				this.driver.findElement(By.id("identifierId")).sendKeys(emailAddress);
				this.driver.findElement(By.id("identifierNext")).click();

				WebDriverWait wait = new WebDriverWait(this.driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))); 
				wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
				this.driver.findElement(By.name("password")).sendKeys(password);
				this.driver.findElement(By.id("passwordNext")).click();
			}
			else {
				this.driver.findElement(By.name("Email")).sendKeys(emailAddress);
				this.driver.findElement(By.id("next")).click();
				WebDriverWait wait = new WebDriverWait(this.driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Passwd")));
				this.driver.findElement(By.name("Passwd")).sendKeys(password);
				this.driver.findElement(By.name("signIn")).click();
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sign in didn't work.");
			return false;
		}
	}

    /**
	 * Click Compose button
	 * 
	 */
	public void clickComposeButton() {
		//click compose button
		try {
			WebElement compose = (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")));
			compose.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * Click Send button
	 * 
	 */
	public void clickSendButton() {
		//send
		try {
			WebElement sendButton = this.driver.findElement(By.className("gU"));
			sendButton.click();
			(new WebDriverWait(this.driver, 10))
	        .until(ExpectedConditions.or(
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("span.ag.a8k")),
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("button.J-at1-auR"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * Click Reply button
	 * 
	 */
	public void clickReplyButton() {
		//send
		try {
			WebElement sendButton = (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='amn']/span")));
			sendButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * Look for an email with given address
	 * 
	 */
	public void lookForAnEmail(String fromAddress) {
		//send
		try {
			(new WebDriverWait(this.driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='yW']/span")));
			
			List<WebElement> a = driver.findElements(By.xpath("//*[@class='yW']/span/span"));
			System.out.println(a.size());
            for(int i=0;i<a.size();i++){
                System.out.println(a.get(i).getText());
                System.out.println(a.get(i).getAttribute("email"));
                if(a.get(i).getAttribute("email").equals(fromAddress)){  // if u want to click on the specific mail then here u can pass it
                    a.get(i).click();
                    break;
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
    /**
	 * input contents into email
	 * @param recipient
	 * @param subject
	 */
	public void inputContents(String recipient, String subject) {
		//enter recipient and subject
		try {
			WebElement recipientArea = (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='to']")));
			recipientArea.sendKeys(recipient);
			this.driver.findElement(By.className("aoT")).sendKeys(subject);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
    /**
	 * attach an image into email
	 * @param imageFilePath
	 */
	public void attachImage(String imageFilePath) {
		//attach file
		try {
			this.driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imageFilePath);
			(new WebDriverWait(this.driver, 20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.dO")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * input contents into email
	 * @param recipient
	 * @param imageFilePath
	 * @param subject
	 */
	public void inputContentsAlter(String recipient, String subject, String imageFilePath) {
		//enter recipient and subject
		try {
			WebElement recipientArea = (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='to']")));
			recipientArea.sendKeys(recipient);
			this.driver.findElement(By.className("aoT")).sendKeys(subject);
		} catch (Exception e) {
			e.printStackTrace();

		}
		//attach file
		try {
			this.driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imageFilePath);
			(new WebDriverWait(this.driver, 20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.dO")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
	 * get error message
	 * @return error message from gmail
	 */
	public String getErrorMessage() {
		(new WebDriverWait(this.driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Kj-JD']")));
		WebElement errorText = this.driver.findElement(By.xpath("//div[@class='Kj-JD-Jz']"));
		String errorMessage = errorText.getText();
		WebElement okButton = this.driver.findElement(By.xpath("//button[@name='ok']"));
		okButton.click();
		
		return errorMessage;	
	}
	
    /**
	 * Check if the email is sent
	 */
	public void checkIfEmailisSent() {
		//look for "Message sent" message
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("aT")));
	}
	
    /**
	 * go to given url
	 * @param url
	 */
	public void goToUrl(String url) {
		this.driver.get(url);
	}
	
	
    /**
	 * go back to Gmail main page
	 */
	public void resetInbox() {
		this.driver.get(this.GMAIL_URL);
	}

   /**
	 * kill chromedriver process 
	 */
	public void killDriver() {
		this.driver.quit();
	}

}
