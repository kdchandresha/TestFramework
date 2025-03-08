package TestFramework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestFramework.TestComponent.BaseMethods;
import TestFramework.pageobjects.AddToCart;
import TestFramework.pageobjects.CartPage;
import TestFramework.pageobjects.orderHistory;

public class PlaceOrdderTest extends BaseMethods {

	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "purchase")
	public void SubmitOrd(HashMap<String, String> input) throws IOException, InterruptedException {

		AddToCart ac = lp.login(input.get("email"), input.get("password"));
		ac.productList();
		ac.AddProducttoCart(input.get("productname"));
		CartPage cp = ac.goToCart();
		boolean match = cp.veriyfyCartProduct(input.get("productname"));
		Assert.assertTrue(match);
		cp.proceedToCart();
		Assert.assertTrue(cp.orderPage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// Verify that Zara coat 3 product present on order details page

	@Test(dataProvider = "getData", dependsOnMethods = { "SubmitOrd" }, groups = "purchase")
	public void orderVerification(HashMap<String, String> input) {

		lp.login(input.get("email"), input.get("password"));
		orderHistory oh = lp.accessOrderPage();
		oh.ordersPage();
		oh.verifyOrderDisplay(input.get("productname"));
		Assert.assertTrue(oh.verifyOrderDisplay(input.get("productname")));

	}
	//This is test Git commit Test 123
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//main//java//TestFramework//Data//PurchaseOrder.json");
		
		if (data.size() < 2) {
	        throw new IllegalStateException("Expected at least 3 data entries in PurchaseOrder.json, but found " + data.size());
	    }

		return new Object[][] {
			{ data.get(0)},
			{ data.get(1)}
		};
	}

//	@DataProvider
//	public Object[][] getData() {
//
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "kcautotest@gmail.com");
//		map.put("password", "Auto@123");
//		map.put("productname", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "jsondata@gmail.com");
//		map1.put("password", "Json@12345");
//		map1.put("productname", "ADIDAS ORIGINAL");
//	}

}
