package TestFramework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestFramework.pageobjects.LoginPage;

public class StandAlonTest {

	public static void main(String[] args) {

		// WebDriverManager.chromedriver().setup();

		String productname = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		LoginPage lp = new LoginPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("kcautotest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Auto@123");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		// Add to cart using Stream concept
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(
				ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("[routerlink*='cart']"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartname = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));

		boolean match = cartname.stream().anyMatch(cartprouct -> cartprouct.getText().equalsIgnoreCase(productname));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")))
				.sendKeys("ind");

		List<WebElement> countrylist = driver.findElements(By.cssSelector(".ta-item"));

		// Search and select value from listed result
		for (WebElement list : countrylist) {

			if (list.getText().equalsIgnoreCase("india")) {

				list.click();
				break;
			}

		}

		driver.findElement(By.cssSelector(".action__submit")).click();

		String thanks = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(thanks.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

		// Add to cart using For loop
//		for (int i = 0; i < products.size(); i++) {
//
//			String pname = products.get(i).getText().split("\n")[0];
//			if (pname.equalsIgnoreCase("zara coat 3")) {
//				
//				driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
//			}
//			
//		}

	}

}
