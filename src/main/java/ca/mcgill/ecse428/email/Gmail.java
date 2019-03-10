package ca.mcgill.ecse428.email;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Gmail {
	
    // Variables
    private WebDriver driver;
    private final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    private final String email = "ecse.428.test@gmail.com";
    private final String password = "asdf1234()_+";
    
	
	
	/*
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
	
	/*
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
	
    /**TODO: Not working 
	 * Click Insert Image button
	 * 
	 */
	public void insertImageByURL(String image_url) {
		//send
		try {
			// First, click "insert image button"
			(new WebDriverWait(this.driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a2X aaA aMZ']")));
			WebElement insertImageButton = this.driver.findElement(By.xpath("//div[@class='a2X aaA aMZ']"));
			insertImageButton.click();
			
			
			// Second click upload button
			(new WebDriverWait(this.driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=':3']")));
			
			
			//WebElement hiddenTab = driver.findElement(By.xpath("//div[@id='Mf-ml-ni Mf-nl-ni']"));
			//((JavascriptExecutor) this.driver).executeScript("arguments[0].setAttribute('style','display: block')", hiddenTab);
			WebElement uploadButton = this.driver.findElement(By.xpath("//div[@id=':8']"));
			((JavascriptExecutor) this.driver).executeScript("document.getElementById(':8').setAttribute({'class':'a-Cf a-Cf-w', 'aria-selected':'true'})");
//			uploadButton.click();
//			
			
			
			// Lastly insert image url
			this.driver.findElement(By.xpath("//input[@id=':o']")).sendKeys(image_url);
			 WebElement insertButton = (new WebDriverWait(this.driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='picker:ap:2']")));
			
			// Click Insert
			insertButton.click();
			
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
     * TODO: Not working
	 * erase Recipient's address
	 */
	public void eraseRecipient() {
		//enter recipient and subject
		try {
			this.driver.findElement(By.xpath("//div[@id=':7b']")).click();
			(new WebDriverWait(this.driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vM']")));
			this.driver.findElement(By.xpath("//div[@class='vM']")).click();
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
	
	/// for testing
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir").toString());
		String TEST_PATH_TO_CHROME_DRIVER_WIN = "chromedriver.exe";
		String TEST_PATH_TO_CHROME_DRIVER_MAC = "chromedriver";
	    String email = "ecse.428.test@gmail.com";
		String password = "asdf1234()_+";
		
		String os = System.getProperty("os.name").toLowerCase();
		String recipient = "ecse.428.test@gmail.com";
		String recipient2 = "ecse.428.test@gmail.com";
		String imageFilePath1 = System.getProperty("user.dir").toString()+"/resources/img.png"; //TODO Change to where image is
		String imageFilePath2 = System.getProperty("user.dir").toString()+"/resources/img2.png";
		String message = "See attachment.";
		String object = "Test email with image";
		
		if(os.contains("mac")) {
			System.out.println(TEST_PATH_TO_CHROME_DRIVER_MAC);
			System.setProperty("webdriver.chrome.driver", TEST_PATH_TO_CHROME_DRIVER_MAC);
		} else {
			System.out.println(TEST_PATH_TO_CHROME_DRIVER_WIN);
			System.setProperty("webdriver.chrome.driver", TEST_PATH_TO_CHROME_DRIVER_WIN);
		}
		
		WebDriver testDriver = new ChromeDriver();
		
		testDriver.get("https://mail.google.com/");
		
		// login using email and password above
		Boolean signin = signIn(email, password, testDriver);
		System.out.println("Sign in: "+signin);
		
		// send email with image attachment
		Boolean sent = sendEmail(recipient, imageFilePath1, object, testDriver); //TODO Add image to the path named "img.png"
		System.out.println("Send email: " +sent);
		
		// reset to inbox
		resetInbox(testDriver);
	
		Boolean sent2 = sendEmail(recipient2, imageFilePath2, object, testDriver);
		System.out.println("Send email 2: "+sent2);

		resetInbox(testDriver);

	}
	
	
	
	private static boolean signIn(String emailAddress, String password, WebDriver testDriver) {
		if(emailAddress.equals("") || password.equals("")) {
			System.out.println("Email or password can't be empty.");
			return false;
		}
		try {
			if(testDriver.getCurrentUrl().contains("https://accounts.google.com/signin/v2/identifier?")){
			testDriver.findElement(By.id("identifierId")).sendKeys(emailAddress);
			testDriver.findElement(By.id("identifierNext")).click();

	        WebDriverWait wait = new WebDriverWait(testDriver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))); 
			wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
	        testDriver.findElement(By.name("password")).sendKeys(password);
	        testDriver.findElement(By.id("passwordNext")).click();
			}
			else{
				testDriver.findElement(By.name("Email")).sendKeys(emailAddress);
				testDriver.findElement(By.id("next")).click();
				WebDriverWait wait = new WebDriverWait(testDriver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Passwd")));
				testDriver.findElement(By.name("Passwd")).sendKeys(password);
				testDriver.findElement(By.name("signIn")).click();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sign in didn't work.");
			return false;
		}
		
	}

	private static boolean sendEmail(String recipient, String imageFilePath, String object, WebDriver driver) {

		//click compose button
		try {
			WebElement compose = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")));
			compose.click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//enter recipient and object
		try {
			WebElement recipientArea = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='to']")));
			recipientArea.sendKeys(recipient);
			driver.findElement(By.className("aoT")).sendKeys(object);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		//attach file
		try {
			driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imageFilePath);

			(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.dO")));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		//send
		try {
			WebElement sendButton = driver.findElement(By.className("gU"));
			sendButton.click();
			(new WebDriverWait(driver, 10))
	        .until(ExpectedConditions.or(
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("span.ag.a8k")),
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("button.J-at1-auR"))));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		//wait until "Message sent" appears
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("aT")));
		return true;

	}

	private static void resetInbox(WebDriver driver) {
		driver.get("https://mail.google.com/mail/#inbox");
	}

}
