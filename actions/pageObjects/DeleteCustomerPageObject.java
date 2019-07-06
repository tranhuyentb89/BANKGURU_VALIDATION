package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage{
	public DeleteCustomerPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;

}
