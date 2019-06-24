package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteAccountPageObject extends AbstractPage{
	public DeleteAccountPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
