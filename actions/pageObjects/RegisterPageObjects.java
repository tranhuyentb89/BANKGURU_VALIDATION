package pageObjects;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.AbstractPageUI;
import com.bankguru.customers.RegisterPageUI;

import commons.AbstractPage;
import commons.PageFactoryManage;

public class RegisterPageObjects extends AbstractPage {
	public RegisterPageObjects(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, "emailid");
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, email, "emailid");
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, "btnLogin");
		if (driver.toString().toLowerCase().contains("internet explorer")) {
			clickToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON ,"btnLogin");
			sleepInSeconds(5);
		} else {
			clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, "btnLogin");
		}
	}

	public String getUserIDInfo() {
		return getTextElement(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordInfo() {
		return getTextElement(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public LoginPageObjects openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
		return PageFactoryManage.getLoginPage(driver);
	}

//	public boolean isRegisterPageDisplayed() {
//		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,);
//		return false;
//	}

}
