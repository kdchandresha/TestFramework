package TestFramework.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import TestFramework.pageobjects.LoginPage;
import io.opentelemetry.sdk.metrics.internal.state.ObjectPool;

public class BaseMethods {

	public WebDriver driver;
	public LoginPage lp;

	public WebDriver initializeDriver() throws IOException {

		// Property class which can read property from Globle file

		Properties pr = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//TestFramework//Resources//GlobleData.properties");
		pr.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: pr.getProperty("browser");
		// pr.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\kdcha\\Documents\\geckodriver.exe");
//			FirefoxOptions options = new FirefoxOptions();
//			options.setCapability("webSocketUrl", true);
//			driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchBrwoser() throws IOException {

		driver = initializeDriver();
		lp = new LoginPage(driver);
		lp.Goto();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {

		// driver.close();
		driver.quit();
	}

	// Data reader method
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		// Read data json to string
		String JsonData = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// String to HashMap - need to use jackson data bind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException {

		TakesScreenshot sshot = (TakesScreenshot) driver;
		File source = sshot.getScreenshotAs(OutputType.FILE);
		File files = new File(System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png");
		FileUtils.copyFile(source, files);
		return System.getProperty("user.dir") + "//reports//" + TestCaseName + ".png";
	}

}
