package pageObjects;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.AbstractPageUI;

import commons.AbstractPage;
import commons.PageFactoryManage;

public class LoginPageObjects extends AbstractPage {
	WebDriver driver;

	public LoginPageObjects(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	public boolean isLoginFormDisplayed() {
		waitToElementVisible(driver, AbstractPageUI.LOGIN_FORM);
		return isControlDisplayed(driver, AbstractPageUI.LOGIN_FORM);
	}

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

}
