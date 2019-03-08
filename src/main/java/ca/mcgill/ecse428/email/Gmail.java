package ca.mcgill.ecse428.email;

import java.util.Set;

import org.openqa.selenium.By;
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
    private final String email = "ecse.428.test@gmail.com";
    private final String password = "asdf1234()_+";
    
    
	public static void main(String[] args) {
		String TEST_PATH_TO_CHROME_DRIVER_WIN = "chromedriver.exe";
		String TEST_PATH_TO_CHROME_DRIVER_MAC = "chromedriver";
	    String email = "ecse.428.test@gmail.com";
	    String password = "asdf1234()_+";
		String os = System.getProperty("os.name").toLowerCase();
		
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
		if(testDriver.getCurrentUrl().contains("https://accounts.google.com/signin/v2/identifier?")){
			testDriver.findElement(By.id("identifierId")).sendKeys(email);
			testDriver.findElement(By.id("identifierNext")).click();

	        WebDriverWait wait = new WebDriverWait(testDriver, 20);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password"))); 
	        testDriver.findElement(By.name("password")).sendKeys(password);
	        testDriver.findElement(By.id("passwordNext")).click();
	    }
	    else{
	    	testDriver.findElement(By.name("Email")).sendKeys(email);
	    	testDriver.findElement(By.id("next")).click();
	        WebDriverWait wait = new WebDriverWait(testDriver, 20);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Passwd")));
	        testDriver.findElement(By.name("Passwd")).sendKeys(password);
	        testDriver.findElement(By.name("signIn")).click();
	    }
		
		

	}
	
	private void setWebdriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER);
			WebDriver driver = new ChromeDriver();
		}
	}

}
