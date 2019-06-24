package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage {
	public NewAccountPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
