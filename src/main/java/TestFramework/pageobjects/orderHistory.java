package TestFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.ReusableClasses.AbstractComponent;

public class orderHistory extends AbstractComponent {

	WebDriver driver;

	public orderHistory(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orders;
	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> productname;

	public void ordersPage() {

		orders.click();
	}
	
	public boolean verifyOrderDisplay(String ProductName) {
		boolean match = productname.stream().anyMatch(cartprouct -> cartprouct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	}


