package TestFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.ReusableClasses.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement lpassword;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(css = "[class*='flyInOut']")
	WebElement erroMsg;

	public String getErrorMsg() {
		waitforElelmentToAppear(erroMsg, null);
		return erroMsg.getText();
	}

	public AddToCart login(String email, String password) {

		userEmail.sendKeys(email);
		lpassword.sendKeys(password);
		login.click();
		AddToCart ac = new AddToCart(driver);
		return ac;
	}

	public orderHistory accessOrderPage() {

		orderHistory oh = new orderHistory(driver);
		oh.ordersPage();
		return oh;

	}

	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
