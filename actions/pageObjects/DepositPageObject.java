package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DepositPageObject extends AbstractPage{
	public DepositPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
