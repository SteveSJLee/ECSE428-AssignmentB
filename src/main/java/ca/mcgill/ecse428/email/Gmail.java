package ca.mcgill.ecse428.email;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmail {
	
    // Variables
    private WebDriver driver;

    private final String PATH_TO_CHROME_DRIVER = System.getenv("USERPROFILE") + "\\Desktop\\chromedriver";
    private final String GMAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
    //private final String email = "ecse.428.test@gmail.com";
    //private final String password = "asdf1234()_+";
    
    
	public static void main(String[] args) {
		String TEST_PATH_TO_CHROME_DRIVER_WIN = "chromedriver.exe";
		String TEST_PATH_TO_CHROME_DRIVER_MAC = "chromedriver";
	    String email = "ecse.428.test@gmail.com";
		String password = "asdf1234()_+";
		String os = System.getProperty("os.name").toLowerCase();
		String recipient = "annejuliecote@hotmail.com";
		String imageFilePath = System.getenv("USERPROFILE")+"Documents/"; //TODO Change to where image is
		
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
		Boolean sent = sendEmail(recipient, imageFilePath+"img.png", testDriver);
		System.out.println("Send email: " +sent);
		// reset to inbox
		resetInbox(testDriver);
	

	}
	
	private void setWebdriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
			WebDriver driver = new ChromeDriver();
		}
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

	private static boolean sendEmail(String recipient, String imageFilePath, WebDriver driver) {

		//click compose button
		try {
			WebElement compose = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")));
			compose.click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		//enter recipient
		try {
			WebElement recipientArea = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='to']")));
			recipientArea.sendKeys(recipient);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		//attach file
		try {
			driver.findElement(By.cssSelector("div.a1.aaA.aMZ")).click();
			Runtime.getRuntime().exec("osascript src/main/java/attachfile_mac.scpt " + imageFilePath); //TODO what
			(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.dO")));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		//send
		try {
			WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label='Send ‪(⌘Enter)‬']"));
			sendButton.click();
			(new WebDriverWait(driver, 10))
	        .until(ExpectedConditions.or(
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("span.ag.a8k")),
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("button.J-at1-auR"))));
		} catch (Exception e) {
			if(e instanceof UnhandledAlertException)
					return true;
					
			/*acceptPrompt();
			(new WebDriverWait(driver, 10))
	        .until(ExpectedConditions.or(
	        		ExpectedConditions.elementToBeClickable(By.cssSelector(MESSAGE_SENT_NOTIFICATION)),
	        		ExpectedConditions.elementToBeClickable(By.cssSelector("button.J-at1-auR"))));	*/
			
		}
		
		return true;

	}

	private static boolean sendEmail(String recipient, String imageFile, String message, String object) {

		//set to initial inbox
		//click compose button
		//enter recipient
		//enter object
		//enter message
		//attach file
		//send
		return false;

	}

	private static void resetInbox(WebDriver driver) {
		driver.get("https://mail.google.com/mail/#inbox");
	}

}
