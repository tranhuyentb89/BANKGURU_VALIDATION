package pageObjects;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.HomePageUI;

import commons.AbstractPage;

public class HomePageObject extends AbstractPage {
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public boolean isWelcomeMsgDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE);
	}

	public boolean isLoginFormUndisplayed() {
		waitToElementInvisible(driver, HomePageUI.LOGIN_FORM);
		return isControlUndisplayed(driver, HomePageUI.LOGIN_FORM);
	}
}
