package TestFramework.Tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestFramework.TestComponent.BaseMethods;
import TestFramework.pageobjects.AddToCart;
import TestFramework.pageobjects.CartPage;

public class ErrorValidation extends BaseMethods {

	@Test(groups = { "Error Handling" }, retryAnalyzer = TestFramework.TestComponent.Retry.class)
	public void loginError() throws IOException, InterruptedException {

		lp.login("kcautotest@gmail.com", "Auto@12345");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMsg());
		// div[@aria-label='Incorrect email or password.']
	}

	@Test(groups = { "Error Handling" })
	public void SubmitOrdValidationError() throws IOException, InterruptedException {

		String productname = "ZARA COAT 3";
		AddToCart ac = lp.login("jsondata@gmail.com", "Json@12345");
		List<WebElement> products = ac.productList();
		ac.AddProducttoCart(productname);
		CartPage cp = ac.goToCart();
		boolean match = cp.veriyfyCartProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
