package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage{
	public EditCustomerPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
