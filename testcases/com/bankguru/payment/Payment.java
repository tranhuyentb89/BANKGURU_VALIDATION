package com.bankguru.payment;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.customers.Account_RegisterToSystem_Common;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.BalanceEnquiryPageObject;
import pageObjects.DeleteAccountPageObject;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.DepositPageObject;
import pageObjects.EditAccountPageObject;
import pageObjects.EditCustomerPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.WithDrawPageObject;

public class Payment extends AbstractTest {
	WebDriver driver;
	private String customerName, dateOfBirth, address, city, state, pin, mobile, email, passwordAddNew, today,
			addressEdit, cityEdit, stateEdit, pinEdit, mobileEdit, emailEdit;
	private String amountToDeposit,currentAmount, SavevingsAmount ;
	String currentAccountValue = "Current";
	public static String cusID, payerAccountID, payeeAccountID;

	LoginPageObjects loginPage;
	HomePageObject homePage;
	DeleteCustomerPageObject deleteCustomerPage;
	NewCustomerPageObject newCustomerPage;
	NewAccountPageObject newAccountPage;
	EditCustomerPageObject editCustomerPage;
	EditAccountPageObject editAccountPage;
	DepositPageObject depositPage;
	WithDrawPageObject withdrawPage;
	FundTransferPageObject fundTransferPage;
	BalanceEnquiryPageObject balaneEnquiryPage;
	DeleteAccountPageObject deleteAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
		loginPage.inputToUserIDTextbox(Account_RegisterToSystem_Common.USER_ID);
		loginPage.inputToPasswordTextbox(Account_RegisterToSystem_Common.PASSWORD);
		log.info("Login - Step 04: Click to login button");
		homePage = loginPage.clickToLoginButton();
		today = getLocalDate();
		currentAmount = "50000";
		customerName = "Tran thi huyen";
		dateOfBirth = "01/01/1989";
		address = "PO Box 9118331 Duis Avenue";
		city = "Tampa";
		state = "FL";
		pin = "466250";
		mobile = "478822211";
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		passwordAddNew = "123456";
		addressEdit = "1883 Cursus Avenue";
		cityEdit = "Houston";
		stateEdit = "Texas";
		pinEdit = "166455";
		mobileEdit = "3838819198";
		emailEdit = "testNG@gmail.com";
		
		amountToDeposit = "5000";
		currentAmount = "50000";
		SavevingsAmount = "10000";
	}

	@Test
	public void TC_01_CreateNewCustomerAccountAndCheckSuccessMessage() {
		log.info("New Customer - Step 01: Open new customer page");
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

		log.info("New Customer - Step 02: Input info to name field");
		newCustomerPage.inputToDynamicField(driver, customerName, "name");
		newCustomerPage.inputToDynamicField(driver, dateOfBirth, "dob");
		newCustomerPage.inputToDynamicField(driver, address, "addr");
		newCustomerPage.inputToDynamicField(driver, city, "city");
		newCustomerPage.inputToDynamicField(driver, state, "state");
		newCustomerPage.inputToDynamicField(driver, pin, "pinno");
		newCustomerPage.inputToDynamicField(driver, mobile, "telephoneno");
		newCustomerPage.inputToDynamicField(driver, email, "emailid");
		newCustomerPage.inputToDynamicField(driver, passwordAddNew, "password");

		log.info("New Customer - Step 11: Click sub button");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "sub");
		newCustomerPage.sleepInSeconds(30);

		verifyTrue(newCustomerPage.isDynamicSuccessMessageDisplayed(driver, "Customer Registered Successfully!!!"));
		// verifyEquals(newCustomerPage.getPageTitleSuccess(driver), "Customer Registered Successfully!!!");
		cusID = newCustomerPage.getDynamicTextInTable(driver, "Customer ID");
		System.out.println(cusID);
	}

	// @Test
	public void TC_02_EditCustomerSuccessAndCheck() {
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);
		editCustomerPage.inputToDynamicField(driver, Payment.cusID, "cusid");
		editCustomerPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		editCustomerPage.inputToDynamicField(driver, addressEdit, "addr");
		editCustomerPage.inputToDynamicField(driver, cityEdit, "city");
		editCustomerPage.inputToDynamicField(driver, stateEdit, "state");
		editCustomerPage.inputToDynamicField(driver, pinEdit, "pinno");
		editCustomerPage.inputToDynamicField(driver, mobileEdit, "telephoneno");
		editCustomerPage.inputToDynamicField(driver, emailEdit, "emailid");
		// editCustomerPage.clickToElement(driver, locator, values);

	}

	// @Test
	public void TC_03_CreateNewAccountSuccessfull() {
		log.info("-------------------------CREATE ACCOUNT 01----------------------------------------");
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);

		newAccountPage.inputToDynamicField(driver, cusID, "cusid");
		newAccountPage.selectFromDropdown(driver, "Current", "selaccount");
		newAccountPage.inputToDynamicField(driver, currentAmount, "inideposit");
		newAccountPage.clickToTextboxTextAreaButton(driver, "button2");

		// Verify info input and displayed is equal
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer ID"), cusID);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Email"), email);

		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Account Type"), currentAccountValue);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Date of Opening"), today);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), currentAmount);
		payerAccountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");

		log.info("-------------------------CREATE ACCOUNT 02----------------------------------------");
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);

		newAccountPage.inputToDynamicField(driver, cusID, "cusid");
		newAccountPage.selectFromDropdown(driver, "Current", "selaccount");
		newAccountPage.inputToDynamicField(driver, SavevingsAmount, "inideposit");
		newAccountPage.clickToTextboxTextAreaButton(driver, "button2");

		// Verify info input and displayed is equal
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer ID"), cusID);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer Name"), customerName);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Email"), email);

		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Account Type"), currentAccountValue);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Date of Opening"), today);
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), SavevingsAmount);
		payeeAccountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");
	}

	// @Test
	public void TC_04_EditAccount_ChangeToOtherType() {
		homePage.openMultiplePages(driver, "Edit Account");
		editAccountPage = PageFactoryManage.getEditAccountPage(driver);

		editAccountPage.inputToDynamicField(driver, Payment.payerAccountID, "accountno");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		editAccountPage.selectFromDropdown(driver, "Savings", "a_type");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		verifyEquals(editAccountPage.getDynamicTextInTable(driver, "Account Type"), "Savings");
	}

	// @Test
	public void TC_05_DepositMoneyToCurrentAccount() {
		editAccountPage.openMultiplePages(driver, "Deposit");
		depositPage = PageFactoryManage.getDepositPage(driver);

		log.info("Payment 05 - Step 01: Input data to deposit form");
		depositPage.inputToDynamicField(driver, payerAccountID, "accountno");
		depositPage.inputToDynamicField(driver, amountToDeposit, "ammount");
		depositPage.inputToDynamicField(driver, "Deposit", "desc");
		depositPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("Payment 05 - Step 02: Verify info input is correct");
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Amount Credited"), amountToDeposit);
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Current Balance"), "55000");
	}

	// @Test
	public void TC_06_WithDrawFromCurrentAccount() {
		depositPage.openMultiplePages(driver, "Withdrawal");
		withdrawPage = PageFactoryManage.getWithDrawPage(driver);

		log.info("Payment 06 - Step 01: Input data to withdraw form");
		withdrawPage.inputToDynamicField(driver, payerAccountID, "accountno");
		withdrawPage.inputToDynamicField(driver, "15000", "ammount");
		withdrawPage.inputToDynamicField(driver, "WITHDRAW", "desc");
		withdrawPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("Payment 06 - Step 02: Verify info input is correct");
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Amount Debited"), "15000");
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Current Balance"), "40000");
	}

	// @Test
	public void TC_07_TransferMoneyToOtherAccount() {
		withdrawPage.openMultiplePages(driver, "Fund Transfer");
		fundTransferPage = PageFactoryManage.getFundTransferPage(driver);

		log.info("Payment 07 - Step 01: Input data to Transfer form");
		fundTransferPage.inputToDynamicField(driver, payerAccountID, "payersaccount");
		fundTransferPage.inputToDynamicField(driver, payeeAccountID, "payeeaccount");
		fundTransferPage.inputToDynamicField(driver, "10000", "ammount");
		fundTransferPage.inputToDynamicField(driver, "Transfer", "desc");
		fundTransferPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("Payment 08 - Step 02: Verify info input is correct");
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "From Account Number"), payerAccountID);
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "To Account Number"), payeeAccountID);
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "Amount"), "10000");
	}

	// @Test
	public void TC_08_CheckCurrentAmount() {
		fundTransferPage.openMultiplePages(driver, "Balance Enquiry");
		balaneEnquiryPage = PageFactoryManage.getBalaneEnquiryPage(driver);

		balaneEnquiryPage.inputToDynamicField(driver, payerAccountID, "accountno");
		balaneEnquiryPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		verifyEquals(balaneEnquiryPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);
		// verifyEquals(balaneEnquiryPage.getDynamicTextInTable(driver, "Type of
		// Account"), currentAccountValue);
		verifyEquals(balaneEnquiryPage.getDynamicTextInTable(driver, "Balance"), "30000");

	}

	// @Test
	public void TC_09_DeleteAllAccountOfCustomer() {
		homePage.openMultiplePages(driver, "Delete Account");
		deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);

		deleteAccountPage.inputToDynamicField(driver, payerAccountID, "accountno");
		deleteAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Do you really want to delete this Account?");
		deleteAccountPage.acceptAlert(driver);
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Account Deleted Sucessfully");
		deleteAccountPage.acceptAlert(driver);

		homePage.openMultiplePages(driver, "Delete Account");
		deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);
		deleteAccountPage.inputToDynamicField(driver, payerAccountID, "accountno");
		deleteAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Do you really want to delete this Account?");
		deleteAccountPage.acceptAlert(driver);
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Account does not exist");
		deleteAccountPage.acceptAlert(driver);

		//
		// homePage.openMultiplePages(driver, "Delete Account");
		// deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);
		//
		// deleteAccountPage.inputToDynamicField(driver, payeeAccountID, "accountno");
		// deleteAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		// deleteAccountPage.acceptAlert(driver);
		// deleteAccountPage.acceptAlert(driver);
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
