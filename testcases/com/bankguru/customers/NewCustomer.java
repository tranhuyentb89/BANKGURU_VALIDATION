package com.bankguru.customers;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.payment.Payment;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObjects;

public class NewCustomer extends AbstractTest {
	WebDriver driver;
	private String email, editCustomerPageUrl;
	private String numericValue = "1234", specialChar = "@" ;
	String valueToInput ="huyen";
	LoginPageObjects loginPage;
	RegisterPageObjects registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	DeleteCustomerPageObject deleteCustomerPage;
	EditCustomerPageObject editCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		loginPage = PageFactoryManage.getLoginPage(driver);
	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		log.info("Login - Step 01: Verify login form dislayed");
		verifyTrue(loginPage.isLoginFormDisplayed());

		log.info("Login - Step 02: Input username and password");
		loginPage.inputToUserIDTextbox(Account_RegisterToSystem_Common.USER_ID);
		loginPage.inputToPasswordTextbox(Account_RegisterToSystem_Common.PASSWORD);

		log.info("Login - Step 03: Click to login button");
		homePage = loginPage.clickToLoginButton();

	}

	@Test
	public void TC_03_VerifyEmptyValueAtNewCustomerForm() {
		
		log.info("TC_03_VerifyEmptyValueAtNewCustomerForm- Step 01: Open New Customer page ");
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		
		log.info("TC_03_VerifyEmptyValueAtNewCustomerForm- Step 02: Click to all field ");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "name");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "dob");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "addr");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "city");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "state");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "pinno");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "telephoneno");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "emailid");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "password");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "emailid");

		log.info("TC_03_VerifyEmptyValueAtNewCustomerForm- Step 03: Verify Empty message is displayed ");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Customer Name"), "Customer name must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Date of Birth"), "Date Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Address"), "Address Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "City"), "City Field must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "State"), "State must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "PIN"), "PIN Code must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Mobile Number"), "Mobile no must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "E-mail"), "Email-ID must not be blank");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Password"), "Password must not be blank");
	}

	@Test
	public void TC_04_VerifyNotInputNumericNewCustomerForm() {
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);
		newCustomerPage.inputToDynamicField(driver, numericValue, "name");
		newCustomerPage.inputToDynamicField(driver, numericValue, "city");
		newCustomerPage.inputToDynamicField(driver, numericValue, "state");

		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Customer Name"), "Numbers are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "City"), "Numbers are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "State"), "Numbers are not allowed");
	}

	@Test
	public void TC_05_VerifyInputSpecialCharactorNewCustomerForm() {
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

		log.info("New Customer - Step 01: Input special charactor into field");
		newCustomerPage.inputToDynamicField(driver, specialChar, "name");
		newCustomerPage.inputToDynamicField(driver, specialChar, "addr");
		newCustomerPage.inputToDynamicField(driver, specialChar, "city");
		newCustomerPage.inputToDynamicField(driver, specialChar, "state");
		newCustomerPage.inputToDynamicField(driver, specialChar, "pinno");
		newCustomerPage.inputToDynamicField(driver, specialChar, "telephoneno");

		log.info("New Customer - Step 02: Verify message cannot input special message displayed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Customer Name"), "Special characters are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Address"), "Special characters are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "City"), "Special characters are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "State"), "Special characters are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "PIN"), "Special characters are not allowed");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Mobile Number"), "Special characters are not allowed");
	}

	@Test
	public void TC_06_VerifyFirstCharactorWithSpaceNewCustomerForm() {
		log.info("New Customer - Step 01: ReloadPage");
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

		log.info("New Customer - Step 01: Input special charactor into field");
		newCustomerPage.pressSpace(driver, "name");
		newCustomerPage.pressSpace(driver, "addr");
		newCustomerPage.pressSpace(driver, "city");
		newCustomerPage.pressSpace(driver, "state");
		newCustomerPage.pressSpace(driver, "pinno");
		newCustomerPage.pressSpace(driver, "telephoneno");
		newCustomerPage.pressSpace(driver, "emailid");
		newCustomerPage.pressSpace(driver, "dob");

		log.info("New Customer - Step 01: Input special charactor into field");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Customer Name"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Address"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "City"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "State"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "PIN"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "Mobile Number"), "First character can not have space");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "E-mail"), "First character can not have space");
	}

	@Test
	public void TC_07_VerifyPinMustHave6DigitAddNewCusForm() {
		log.info("New Customer - Step 01: ReloadPage");
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

		log.info("New Customer - Step 02: Input lesser than 6 digit into field");
		newCustomerPage.inputToDynamicField(driver, numericValue, "pinno");

		log.info("New Customer - Step 03: Input lesser than 6 digit into field");
		verifyEquals(newCustomerPage.getDynamicMessageField(driver, "PIN"), "PIN Code must have 6 Digits");
	}

	@Test
	public void TC_08_EditCustomerForm_CusIDFieldCannotBeEmpty() {
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.pressTab(driver, "cusid");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "Customer ID"),"Customer ID is required");
	}
	
	@Test
	public void TC_09_EditCustomerForm_CusIDMustBeNumeric() {
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicField(driver, valueToInput, "cusid");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "Customer ID"), "Characters are not allowed");
	}
	
	@Test
	public void TC_10_EditCustomerForm_InputSpecialChar() {
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicField(driver, specialChar, "cusid");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "Customer ID"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_11_EditCustomerPage_InputValidData() {
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPageUrl = editCustomerPage.getCurrentUrl(driver);
		editCustomerPage.inputToDynamicField(driver, Payment.cusID, "cusid");
		editCustomerPage.clickToTextboxTextAreaButton(driver,"AccSubmit");
	}
	
	@Test
	public void TC_12_EditCustomerPage_VerifyEmptyValue() {
		editCustomerPage.clearValueOfTextboxAreabox(driver, "addr");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "city");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "state");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "pinno");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "telephoneno");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "emailid");

		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "Address"), "Address Field must not be blank");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "City"), "City Field must not be blank");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "State"), "State must not be blank");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "PIN"), "PIN Code must not be blank");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "Mobile Number"), "Mobile no must not be blank");
		verifyEquals(editCustomerPage.getDynamicMessageField(driver, "E-mail"), "Email-ID must not be blank");
	}
	
	@Test
	public void TC_13_EditCustomer_VerifyFieldCannotWithNumeric() {
		editCustomerPage.openUrl(driver, editCustomerPageUrl);
		editCustomerPage.inputToDynamicField(driver, Payment.cusID, "cusid");
		editCustomerPage.clickToTextboxTextAreaButton(driver,"AccSubmit");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "city");
		editCustomerPage.inputToDynamicField(driver, numericValue, "city");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "state");
		editCustomerPage.inputToDynamicField(driver, numericValue, "state");
		
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "City"), "Numbers are not allowed");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "State"), "Numbers are not allowed");

	}
	
	@Test
	public void TC_14_EditCustomer_VerifyFieldWithSpace() {
		editCustomerPage.openUrl(driver, editCustomerPageUrl);
		editCustomerPage.inputToDynamicField(driver, Payment.cusID, "cusid");
		editCustomerPage.clickToTextboxTextAreaButton(driver,"AccSubmit");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "addr");
		editCustomerPage.pressSpace(driver, "addr");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "city");
		editCustomerPage.pressSpace(driver, "city");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "state");
		editCustomerPage.pressSpace(driver, "state");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "pinno");
		editCustomerPage.pressSpace(driver, "pinno");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "telephoneno");
		editCustomerPage.pressSpace(driver, "telephoneno");
		editCustomerPage.clearValueOfTextboxAreabox(driver, "emailid");
		editCustomerPage.pressSpace(driver, "emailid");

		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "Address"), "First character can not have space");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "City"), "First character can not have space");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "State"), "First character can not have space");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "PIN"), "First character can not have space");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "Mobile Number"), "First character can not have space");
		verifyEquals(editCustomerPage.getDynamicCannotInputNumericMessage(driver, "E-mail"), "First character can not have space");
	}
	@Test
	public void TC_15_DeleteCustomerForm_CusIDFieldCannotBeEmpty() {
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.pressTab(driver, "cusid");
		verifyEquals(deleteCustomerPage.getDynamicMessageField(driver, "Customer ID"),"Customer ID is required");
	}
	
	@Test
	public void TC_16_DeleteCustomerForm_CusIDMustBeNumeric() {
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicField(driver, valueToInput, "cusid");
		verifyEquals(deleteCustomerPage.getDynamicMessageField(driver, "Customer ID"), "Characters are not allowed");
	}
	
	@Test
	public void TC_17_DeleteCustomerForm_InputSpecialChar() {
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicField(driver, specialChar, "cusid");
		verifyEquals(deleteCustomerPage.getDynamicMessageField(driver, "Customer ID"), "Special characters are not allowed");
	}
	
	@Test
	public void TC_18_DeleteCustomerPage_ClickSubmitButton() {
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteCustomerPage.gettextAlert(driver);
		verifyEquals(deleteCustomerPage.gettextAlert(driver), "Please fill all fields");
		deleteCustomerPage.acceptAlert(driver);
	}
	
	@Test
	public void TC_19_DeleteCustomerPage_InputInvalidCusID() {
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);
		deleteCustomerPage.inputToDynamicField(driver, numericValue, "cusid");
		deleteCustomerPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteCustomerPage.gettextAlert(driver);
		verifyEquals(deleteCustomerPage.gettextAlert(driver), "Do you really want to delete this Customer?");
		deleteCustomerPage.acceptAlert(driver);
		deleteCustomerPage.gettextAlert(driver);
		verifyEquals(deleteCustomerPage.gettextAlert(driver), "Customer does not exist!!");
		deleteCustomerPage.acceptAlert(driver);
	}
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	public int ramdomNumber() {
		Random radom = new Random();
		int number = radom.nextInt(9999999);
		return number;
	}

}
