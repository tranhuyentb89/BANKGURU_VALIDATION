package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MiniStatementPageObject extends AbstractPage{
	public MiniStatementPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;
}
