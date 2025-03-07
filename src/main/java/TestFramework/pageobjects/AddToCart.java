package TestFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestFramework.ReusableClasses.AbstractComponent;

public class AddToCart extends AbstractComponent {

	WebDriver driver;

	public AddToCart(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;

	By prodlist = By.cssSelector(".col-lg-4");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastmsg = By.id("toast-container");

	public List<WebElement> productList() {

		elelmentToAppear(prodlist, cart);
		return products;

	}

	public WebElement getProductByName(String ProductName) {

		WebElement prod = productList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void AddProducttoCart(String ProductName) throws InterruptedException {

		WebElement prod = getProductByName(ProductName);
		prod.findElement(addTocart).click();
		elelmentToAppear(toastmsg, cart);
		waitForElementToDisappear(spinner);

	}

}
