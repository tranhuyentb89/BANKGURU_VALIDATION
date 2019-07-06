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
	private String customerName, dateOfBirth, address, city, state, pin, mobile, email, passwordAddNew, addressEdit,
			cityEdit, stateEdit, pinEdit, mobileEdit, emailEdit, today;
	String currentAmount, SavevingsAmount, amountWithDraw, amountTransfer, amountToDeposit, currentAccountValue;
	public static String cusID, payerAccountID, payeeAccountID, AccountID;

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

		log.info("Precondition- Step 01: Input Username and Password ");
		loginPage.inputToDynamicField(driver, Account_RegisterToSystem_Common.USER_ID, "uid");
		loginPage.inputToDynamicField(driver, Account_RegisterToSystem_Common.PASSWORD, "password");
		log.info("Precondition- Step 02: Click to login button");
		homePage = loginPage.clickToLoginButton(driver, "btnLogin");
		today = getLocalDate();
		currentAmount = "50000";
		SavevingsAmount = "10000";
		amountWithDraw = "15000";
		amountTransfer = "10000";
		amountToDeposit = "5000";
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
		currentAccountValue = "Current";

		customerName = "Tran thi huyen";
	}

	@Test
	public void TC_01_CreateNewCustomerAccountAndCheckSuccessMessage() {
		log.info("TC_01_CreateNewCustomer - Step 01: Open new customer page");
		homePage.openMultiplePages(driver, "New Customer");
		newCustomerPage = PageFactoryManage.getNewCustomerPage(driver);

		log.info("TC_01_CreateNewCustomer - Step 02: Input info to all field");
		newCustomerPage.inputToDynamicField(driver, customerName, "name");
		newCustomerPage.inputToDynamicField(driver, dateOfBirth, "dob");
		newCustomerPage.inputToDynamicField(driver, address, "addr");
		newCustomerPage.inputToDynamicField(driver, city, "city");
		newCustomerPage.inputToDynamicField(driver, state, "state");
		newCustomerPage.inputToDynamicField(driver, pin, "pinno");
		newCustomerPage.inputToDynamicField(driver, mobile, "telephoneno");
		newCustomerPage.inputToDynamicField(driver, email, "emailid");
		newCustomerPage.inputToDynamicField(driver, passwordAddNew, "password");

		log.info("TC_01_CreateNewCustomer - Step 03: Click Submit button");
		newCustomerPage.clickToTextboxTextAreaButton(driver, "sub");

		log.info("TC01_Create New Customer- Step 04: Sleep 30s");
		newCustomerPage.sleepInSeconds(30);

		log.info("TC01_Create New Customer- Step 05: Verify message 'Customer Registered Successfully' is displayed");
		verifyTrue(newCustomerPage.isDynamicSuccessMessageDisplayed(driver, "Customer Registered Successfully!!!"));
		cusID = newCustomerPage.getDynamicTextInTable(driver, "Customer ID");
	}

	@Test
	public void TC_02_EditCustomerSuccessAndCheck() {

		log.info("TC_02_EditCustomer- Step 01: Open Edit Customer page");
		homePage.openMultiplePages(driver, "Edit Customer");
		editCustomerPage = PageFactoryManage.getEditCustomerPage(driver);

		log.info("TC_02 Edit Customer - Step 02: Input Customer ID into field");
		editCustomerPage.inputToDynamicField(driver, Payment.cusID, "cusid");

		log.info("TC_02 Edit Customer - Step 03: Click to Submit button");
		editCustomerPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("TC_02 Edit Customer - Step 04: Input edit value to editable field");
		editCustomerPage.inputToDynamicField(driver, addressEdit, "addr");
		editCustomerPage.inputToDynamicField(driver, cityEdit, "city");
		editCustomerPage.inputToDynamicField(driver, stateEdit, "state");
		editCustomerPage.inputToDynamicField(driver, pinEdit, "pinno");
		editCustomerPage.inputToDynamicField(driver, mobileEdit, "telephoneno");
		editCustomerPage.inputToDynamicField(driver, emailEdit, "emailid");

		log.info("TC_02_EditCustomer- Step 05: Click to Submit button");
		editCustomerPage.clickToTextboxTextAreaButton(driver, "sub");

		log.info("TC_02_EditCustomer- Step 06: Verify message 'Customer details updated Successfully!!!' is displayed");
		verifyTrue(
				newCustomerPage.isDynamicSuccessMessageDisplayed(driver, "Customer details updated Successfully!!!"));
	}

	@Test
	public void TC_03_CreateNewAccountSuccessfull() {
		log.info("-------------------------CREATE ACCOUNT 01----------------------------------------");
		log.info("TC_03 Create New Account - Step 01: Open New account  page");
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);

		log.info("TC_03 Create New Account - Step 02: Input value to all field in New Account form");
		newAccountPage.inputToDynamicField(driver, cusID, "cusid");
		newAccountPage.selectFromDropdown(driver, "Current", "selaccount");
		newAccountPage.inputToDynamicField(driver, currentAmount, "inideposit");

		log.info("TC_03 Create New Account - Step 03: Click To submit button");
		newAccountPage.clickToTextboxTextAreaButton(driver, "button2");

		log.info("TC_03 Create New Account - Step 04: Verify Customer ID is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer ID"), cusID);

		log.info("TC_03 Create New Account - Step 05: Verify Customer Name is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer Name"), customerName);

		log.info("TC_03 Create New Account - Step 06: Verify Email is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Email"), emailEdit);

		log.info("TC_03 Create New Account - Step 07: Verify Account Type is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Account Type"), currentAccountValue);

		log.info("TC_03 Create New Account - Step 08: Verify Date of Opening is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Date of Opening"), today);

		log.info("TC_03 Create New Account - Step 09: Verify Current Amount is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), currentAmount);
		payerAccountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");

		log.info("-------------------------CREATE ACCOUNT 02----------------------------------------");
		log.info("TC_03 Create New Account - Step 01: Open New account  page");
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);

		log.info("TC_03 Create New Account - Step 02: Input value to all field in New Account form");
		newAccountPage.inputToDynamicField(driver, cusID, "cusid");
		newAccountPage.selectFromDropdown(driver, "Current", "selaccount");
		newAccountPage.inputToDynamicField(driver, SavevingsAmount, "inideposit");

		log.info("TC_03 Create New Account - Step 03: Click To submit button");
		newAccountPage.clickToTextboxTextAreaButton(driver, "button2");

		log.info("TC_03 Create New Account - Step 04: Verify Customer ID is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer ID"), cusID);

		log.info("TC_03 Create New Account - Step 05: Verify Customer Name is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer Name"), customerName);

		log.info("TC_03 Create New Account - Step 06: Verify Email is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Email"), emailEdit);

		log.info("TC_03 Create New Account - Step 07: Verify Account Type is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Account Type"), currentAccountValue);

		log.info("TC_03 Create New Account - Step 08: Verify Date of Opening is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Date of Opening"), today);

		log.info("TC_03 Create New Account - Step 09: Verify Current Amount is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), SavevingsAmount);
		payeeAccountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");
		
		log.info("-------------------------CREATE ACCOUNT 03----------------------------------------");
		log.info("TC_03 Create New Account - Step 01: Open New account  page");
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManage.getNewAccountPage(driver);

		log.info("TC_03 Create New Account - Step 02: Input value to all field in New Account form");
		newAccountPage.inputToDynamicField(driver, cusID, "cusid");
		newAccountPage.selectFromDropdown(driver, "Current", "selaccount");
		newAccountPage.inputToDynamicField(driver, SavevingsAmount, "inideposit");

		log.info("TC_03 Create New Account - Step 03: Click To submit button");
		newAccountPage.clickToTextboxTextAreaButton(driver, "button2");

		log.info("TC_03 Create New Account - Step 04: Verify Customer ID is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer ID"), cusID);

		log.info("TC_03 Create New Account - Step 05: Verify Customer Name is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Customer Name"), customerName);

		log.info("TC_03 Create New Account - Step 06: Verify Email is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Email"), emailEdit);

		log.info("TC_03 Create New Account - Step 07: Verify Account Type is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Account Type"), currentAccountValue);

		log.info("TC_03 Create New Account - Step 08: Verify Date of Opening is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Date of Opening"), today);

		log.info("TC_03 Create New Account - Step 09: Verify Current Amount is matching");
		verifyEquals(newAccountPage.getDynamicTextInTable(driver, "Current Amount"), SavevingsAmount);
		AccountID = newAccountPage.getDynamicTextInTable(driver, "Account ID");

	}

	@Test
	public void TC_04_EditAccount_ChangeToOtherType() {

		log.info("TC_04 Edit Account - Step 01: Open Edit Account Page");
		homePage.openMultiplePages(driver, "Edit Account");
		editAccountPage = PageFactoryManage.getEditAccountPage(driver);

		log.info("TC_04 Edit Account - Step 02: Input to Account No field");
		editAccountPage.inputToDynamicField(driver, Payment.payerAccountID, "accountno");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		editAccountPage.selectFromDropdown(driver, "Savings", "a_type");

		log.info("TC_04 Edit Account - Step 03: Click to submit button");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("TC_04 Edit Account - Step 04: Verify Change Account Type is success");
		verifyEquals(editAccountPage.getDynamicTextInTable(driver, "Account Type"), "Savings");
	}

	@Test
	public void TC_05_DepositMoneyToCurrentAccount() {

		log.info("TC_05 Deposit - Step 01: Open Deposit Page");
		editAccountPage.openMultiplePages(driver, "Deposit");
		depositPage = PageFactoryManage.getDepositPage(driver);

		log.info("TC_05 Deposit - Step 02: Input data to deposit form");
		depositPage.inputToDynamicField(driver, payerAccountID, "accountno");
		depositPage.inputToDynamicField(driver, amountToDeposit, "ammount");
		depositPage.inputToDynamicField(driver, "Deposit", "desc");

		log.info("TC_05 Deposit - Step 03: Click to submit button");
		depositPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("TC_05 Deposit - Step 04: Verify Account No is matching");
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);

		log.info("TC_05 Deposit - Step 05: Verify Amount to deposit is matching with inputed value");
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Amount Credited"), amountToDeposit);

		log.info("TC_05 Deposit - Step 06: Verify Current balance is matching");
		verifyEquals(depositPage.getDynamicTextInTable(driver, "Current Balance"), "55000");
	}

	@Test
	public void TC_06_WithDrawFromCurrentAccount() {

		log.info("TC_06 WithDraw - Step 01: Open WithDraw Page");
		depositPage.openMultiplePages(driver, "Withdrawal");
		withdrawPage = PageFactoryManage.getWithDrawPage(driver);

		log.info("TC_06 WithDraw - Step 02: Input data to WithDraw form");
		withdrawPage.inputToDynamicField(driver, payerAccountID, "accountno");
		withdrawPage.inputToDynamicField(driver, amountWithDraw, "ammount");
		withdrawPage.inputToDynamicField(driver, "WITHDRAW", "desc");

		log.info("TC_06 WithDraw - Step 03: Click to submit button");
		withdrawPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("TC_06 WithDraw - Step 04: Verify Account No is matched");
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);

		log.info("TC_06 WithDraw - Step 04: Verify amount withdraw is matched");
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Amount Debited"), amountWithDraw);

		log.info("TC_06 WithDraw - Step 04: Verify Current Balance is matched");
		verifyEquals(withdrawPage.getDynamicTextInTable(driver, "Current Balance"), "40000");
	}

	@Test
	public void TC_07_TransferMoneyToOtherAccount() {

		log.info("TC_07 Transfer - Step 01: Open Fund Transfer Page");
		withdrawPage.openMultiplePages(driver, "Fund Transfer");
		fundTransferPage = PageFactoryManage.getFundTransferPage(driver);

		log.info("TC_07 Transfer - Step 02: Input data to Fund Transfer form");
		fundTransferPage.inputToDynamicField(driver, payerAccountID, "payersaccount");
		fundTransferPage.inputToDynamicField(driver, payeeAccountID, "payeeaccount");
		fundTransferPage.inputToDynamicField(driver, amountTransfer, "ammount");
		fundTransferPage.inputToDynamicField(driver, "Transfer", "desc");

		log.info("TC_07 Transfer - Step 03: Click to submit button");
		fundTransferPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		log.info("TC_07 Transfer - Step 04: Verify Account Number is matched");
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "From Account Number"), payerAccountID);

		log.info("TC_07 Transfer - Step 05: Verify PayeeAccount is matched");
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "To Account Number"), payeeAccountID);

		log.info("TC_07 Transfer - Step 06: verify Amount is matched");
		verifyEquals(fundTransferPage.getDynamicTextInTable(driver, "Amount"), amountTransfer);
	}

	@Test
	public void TC_08_CheckCurrentAmount() {

		log.info("TC_08 Transfer - Step 01: Open Balance Enquiry Page");
		fundTransferPage.openMultiplePages(driver, "Balance Enquiry");
		balaneEnquiryPage = PageFactoryManage.getBalaneEnquiryPage(driver);

		log.info("TC_08 Transfer - Step 02: Input to AccountNo field");
		balaneEnquiryPage.inputToDynamicField(driver, payerAccountID, "accountno");

		log.info("TC_08 Transfer - Step 03: Click to submit button");
		balaneEnquiryPage.clickToTextboxTextAreaButton(driver, "AccSubmit");

		verifyEquals(balaneEnquiryPage.getDynamicTextInTable(driver, "Account No"), payerAccountID);
		verifyEquals(balaneEnquiryPage.getDynamicTextInTable(driver, "Balance"), "30000");

	}

	@Test
	public void TC_09_DeleteAllAccountOfCustomer() {

		log.info("TC_09 DeleteAccount - Step 01: Open Delete Account page");
		homePage.openMultiplePages(driver, "Delete Account");
		deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);

		log.info("TC_09 DeleteAccount - Step 02: Input to accountNo field");
		deleteAccountPage.inputToDynamicField(driver, payerAccountID, "accountno");

		log.info("TC_09 DeleteAccount - Step 03: Click to submit button");
		deleteAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Do you really want to delete this Account?");

		log.info(
				"TC_09 DeleteAccount - Step 04: Click OK to accept alert 'Do you really want to delete this Account?'");
		deleteAccountPage.acceptAlert(driver);
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Account Deleted Sucessfully");

		log.info("TC_09 DeleteAccount - Step 05: Click OK to accept alert 'Account Deleted Sucessfully'");
		deleteAccountPage.acceptAlert(driver);

		log.info("TC_09 DeleteAccount - Step 06: Open Delete Account page");
		homePage.openMultiplePages(driver, "Delete Account");
		deleteAccountPage = PageFactoryManage.getDeleteAccountPage(driver);

		log.info("TC_09 DeleteAccount - Step 07: Input to accountNo field");
		deleteAccountPage.inputToDynamicField(driver, payeeAccountID, "accountno");

		log.info("TC_09 DeleteAccount - Step 08: Click to submit button");
		deleteAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Do you really want to delete this Account?");

		log.info(
				"TC_09 DeleteAccount - Step 09: Click OK to accept alert 'Do you really want to delete this Account?'");
		deleteAccountPage.acceptAlert(driver);
		deleteAccountPage.sleepInSeconds(3000);
		verifyEquals(deleteAccountPage.gettextAlert(driver), "Account Deleted Sucessfully");
		deleteAccountPage.acceptAlert(driver);

	}

	@Test
	public void TC_10_deleteCustomerSuccessfull() {
		
		log.info("TC_10 Delete Customer - Step 01: Open Delete Customer page");
		homePage.openMultiplePages(driver, "Delete Customer");
		deleteCustomerPage = PageFactoryManage.getDeleteCustomerPage(driver);

		log.info("TC_10 Delete Customer - Step 02: Input to CustomerID field");
		deleteCustomerPage.inputToDynamicField(driver, cusID, "cusid");

		log.info("TC_10 Delete Customer - Step 03: Click to submit button");
		deleteCustomerPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		deleteCustomerPage.sleepInSeconds(3000);
		verifyEquals(deleteCustomerPage.gettextAlert(driver), "Do you really want to delete this Customer?");

		log.info("TC_10 Delete Customer - Step 04: Click to OK to accept alert");
		deleteCustomerPage.acceptAlert(driver);
		deleteCustomerPage.sleepInSeconds(3000);
		verifyEquals(deleteCustomerPage.gettextAlert(driver), "Customer deleted Successfully");

		log.info("TC_10 Delete Customer - Step 04: Click to OK to accept alert 'Customer deleted Successfully'");
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
