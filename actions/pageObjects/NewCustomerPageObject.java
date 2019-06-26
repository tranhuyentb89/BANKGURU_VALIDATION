package pageObjects;

import org.openqa.selenium.WebDriver;

import com.bankguru.customers.AbstractPageUI;

import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage{
	public NewCustomerPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	WebDriver driver;

	public boolean isDynamicSuccessMessageDisplayed(WebDriver driver2, String dynamicValue) {
		return isControlDisplayed(driver2, AbstractPageUI.DYNAMIC_TEXT_DISPLAYED, dynamicValue);
	}

//	public boolean isEmptyMessageDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.CUSTOMER_NAME_DYNAMIC_MSG,Constants.CUS_NAME_BLANK_MSG);
//	}
//
//	public void inputNumericToCustomerName(String value) {
//		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, value, "name");
//	}
//
//	public boolean isNumericMessageDisplayed() {
//		// TODO Auto-generated method stub
//		return isControlDisplayed(driver, AbstractPageUI.CUSTOMER_NAME_DYNAMIC_MSG, Constants.NUMERIC_NOT_ALLOWED_MSG);
//	}
//
//	public void inputSpecialCharToCustomerName(String value) {
//		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, value	, "name");
//	}
//
//	public boolean isSpecialMessageDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.CUSTOMER_NAME_DYNAMIC_MSG, Constants.SPECIAL_CHAR_MSG);
//	}
//
//	public void pressSpace() {
//		sendKeyboardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, Keys.SPACE, "name");
//	}
//
//	public boolean isSpaceMessageDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.CUSTOMER_NAME_DYNAMIC_MSG, Constants.SPACE_MSG);
//	}
//
//	public void pressTab() {
//		sendKeyboardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, Keys.TAB, "addr");
//	}
//
//	public boolean isEmptyAddrMessageDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.ADDRESS_DYNAMIC_MSG,Constants.ADD_BLANK_MSG);
//	}
//
//	public void inputSpaceToAddress() {
//		sendKeyboardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA_BUTTON, Keys.SPACE, "addr");
//	}
//
//	public boolean isSpaceMessageAddDisplayed() {
//		return isControlDisplayed(driver, AbstractPageUI.ADDRESS_DYNAMIC_MSG, Constants.SPACE_MSG);
//	}
}
