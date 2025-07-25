package Tasks;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Gmail_DDT_Register {
	WebDriver driver;

    @BeforeMethod
    public void setup() {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void registrationTest() throws IOException, InterruptedException {
        driver.get("https://accounts.google.com/signup");
        FileInputStream fis = new FileInputStream("./src/test/resources/snippet/Details.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		String firstName= book.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		
		String lastName =book.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		
		int day  = (int) book.getSheet("Sheet1").getRow(1).getCell(2).getNumericCellValue();  // E.g., 09
		int year = (int) book.getSheet("Sheet1").getRow(1).getCell(3).getNumericCellValue();  // E.g., 2001
		
		String username=book.getSheet("Sheet1").getRow(1).getCell(4).getStringCellValue();
		String pasword=book.getSheet("Sheet1").getRow(1).getCell(5).getStringCellValue();
		String confirm=book.getSheet("Sheet1").getRow(1).getCell(6).getStringCellValue();



		//String day =book.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		
	//	Date day = book.getSheet("Sheet1").getRow(1).getCell(2).getDateCellValue();
		System.out.println(firstName);
		System.out.println(lastName);
		
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        
//
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
        nextBtn.click();
      
        Thread.sleep(2000);
     

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"month\"]/div/div[1]/div")));
        js.executeScript("arguments[0].click();", monthDropdown);
        WebElement janOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"month\"]/div/div[2]/ul/li[3]")));
        janOption.click();
 		 driver.findElement(By.xpath("//*[@id=\"day\"]")).click();
		 driver.findElement(By.id("day")).sendKeys(String.valueOf(day));
		 driver.findElement(By.id("year")).sendKeys(String.valueOf(year));
		 WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gender']/div/div[1]/div")));
		 js.executeScript("arguments[0].click();", genderDropdown);
		 WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gender']/div/div[2]/ul/li[@data-value='1']")));
		 js.executeScript("arguments[0].click();", genderOption);
		 WebElement nextBtnAfterDOB = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='birthdaygenderNext']/div/button")));

		// Scroll into view
		js.executeScript("arguments[0].scrollIntoView(true);", nextBtnAfterDOB);
		Thread.sleep(1000); // Small wait to avoid animation issues

		// Click using JavaScript (bypasses any overlays)
		js.executeScript("arguments[0].click();", nextBtnAfterDOB);

		// Optional confirmation
		System.out.println("Clicked on the Next button after DOB and Gender.");
		Thread.sleep(3000);
	
		// Step 1: Click "Create your own Gmail address" radio button
		/*WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(6));
	WebElement customRadio = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//div[@role='radio' and contains(., 'Create your own Gmail address')]")
		));
		customRadio.click();*/

	// Step 2: Enter the email address in the input that appears
	WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div/form/span/section/div/div/div[2]/div[1]/div[1]/div[1]/div/div[1]/input")
	));
	emailInput.sendKeys(username); // Replace this with value read from Excel

       
			WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//*[@id='next']/div/button/div[3]")));
		// Scroll into view and click using JavaScript (more reliable on dynamic pages)
	 			js.executeScript("arguments[0].scrollIntoView(true);", nextButton);
				Thread.sleep(500); // brief wait if animation/loading occurs
				js.executeScript("arguments[0].click();", nextButton);
//WebElement customOption = wait.until(ExpectedConditions.elementToBeClickable(
				WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
					    By.xpath("//*[@id='passwd']/div[1]/div/div[1]/input")
					));

					// Send password from Excel or directly
					passwordInput.sendKeys(pasword);
					
					// Locate the confirm password input field
					WebElement confirmPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
					    By.xpath("//*[@id='confirm-passwd']/div[1]/div/div[1]/input")
					));

					// Send the same password (or from Excel again)
					confirmPasswordInput.sendKeys(confirm);
					System.out.println("✅ Confirm password entered.");
					
					 
					WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
					    By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div/form/span/section/div/div/div/div[3]/div/div[1]/div/div/div[1]/div/div/input")
					));

					js.executeScript("arguments[0].click();", checkbox);
					
					WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
						    By.xpath("//*[@id='createpasswordNext']/div/button")
						));

						next.click();
						System.out.println("✅ Clicked on 'Next' button after setting password.");
						
				Thread.sleep(15000);
				WebElement skipBtn = wait.until(ExpectedConditions.elementToBeClickable(
					    By.xpath("//*[@id='recoverySkip']/div/button")
					));

					skipBtn.click();
					System.out.println("✅ Clicked on 'Skip' button for recovery info.");
					
					WebElement nextBt = wait.until(ExpectedConditions.presenceOfElementLocated(
						    By.xpath("//*[@id='yDmH0d']/c-wiz/div/div[3]/div/div/div/div/button")
						));

						js.executeScript("arguments[0].click();", nextBt);
						System.out.println("✅ Clicked 'Next' using JavaScriptExecutor.");
						
						WebElement agreeBtn = wait.until(ExpectedConditions.elementToBeClickable(
							    By.xpath("//*[@id='yDmH0d']/c-wiz/div/div[3]/div/div[1]/div/div/button")
							));

							// Click the button
							agreeBtn.click();
							System.out.println("✅ Clicked on 'I agree' button.");
    }
							@AfterMethod
							public void tearDown() {
							    if (driver != null) {
							        driver.quit();
							        Thread.sleep(4000);
							        System.out.println("✅ Browser closed successfully.");
							    } else {
							        System.out.println("⚠️ WebDriver was not initialized.");
							    }


}
}