package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class FundTransferPageObject extends AbstractPage{
	public FundTransferPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;

}
