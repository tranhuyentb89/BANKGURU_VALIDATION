package pageObjects;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.AbstractPageUI;

import commons.AbstractPage;
import commons.Constants;

public class WithDrawPageObject extends AbstractPage{
	public WithDrawPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;
}
