package TestFramework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import TestFramework.ReusableClasses.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> cartlist;

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryname;

	@FindBy(css = ".ta-item")
	List<WebElement> country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".hero-primary")
	WebElement thankyoumsg;

	public List<WebElement> productList() {

		elelmentToAppear(cartlist);
		return cartlist;

	}

	public void elelmentToAppear(List<WebElement> cartlist) {
		// TODO Auto-generated method stub

	}

	public boolean veriyfyCartProduct(String ProductName) {

		boolean match = cartlist.stream().anyMatch(cartprouct -> cartprouct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	public void proceedToCart() {

		checkout.click();
		countryname.sendKeys("ind");

		// Search and select value from listed result
		for (WebElement list : country) {

			if (list.getText().equalsIgnoreCase("india")) {

				list.click();
				break;
			}

		}

		submit.click();

	}

	public String orderPage() {
		String msg = thankyoumsg.getText();
		return msg;

	}

}
