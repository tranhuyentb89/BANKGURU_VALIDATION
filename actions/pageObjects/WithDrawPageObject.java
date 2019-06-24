package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithDrawPageObject extends AbstractPage{
	public WithDrawPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;
}
