package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage{
	public DeleteCustomerPageObject(WebDriver mappingDriver) {
		this.driver = mappingDriver;
	}

	WebDriver driver;

//	public boolean isEmptyMessageDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.CUS_ID_DYNAMIC_MSG, Constants.CUS_ID_BLANK_MSG);
//	}
//
//	public void inputCharToCustomerID(String value) {
//		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, value, "cusid");
//	}
//
//	public boolean isCharErrorDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.CUS_ID_DYNAMIC_MSG, Constants.CHAR_NOT_ALLOW_MSG);
//	}
}
