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

	public RegisterPageObjects clickToHereLink(String hereLinkText) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, hereLinkText);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, hereLinkText);
		return PageFactoryManage.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userID) {
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, userID, "uid");
	}

	public void inputToPasswordTextbox(String password) {
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, password, "password");
	}

	public HomePageObject clickToLoginButton() {
		clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, "btnLogin");
		return PageFactoryManage.getHomePage(driver);
	}

}
