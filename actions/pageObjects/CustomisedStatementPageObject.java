package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class CustomisedStatementPageObject extends AbstractPage{
	public CustomisedStatementPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;
}
