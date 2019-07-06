package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.customers.Account_RegisterToSystem_Common;
import com.bankguru.payment.Payment;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.NewAccountPageObject;

public class Verification_Account extends AbstractTest {
	WebDriver driver;
	String stringValue ="test", specialChar ="@#";
	LoginPageObjects loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	EditAccountPageObject editAccountPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Login() {
		loginPage.inputToDynamicField(driver, Account_RegisterToSystem_Common.USER_ID, "uid");
		loginPage.inputToDynamicField(driver, Account_RegisterToSystem_Common.PASSWORD, "password");
		homePage = loginPage.clickToLoginButton(driver, "btnLogin");
	}
	
	@Test
	public void TC_02_AddNewAccount_VerificationEmptyMessage() {
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage= PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.clickToTextboxTextAreaButton(driver, "cusid");
		newAccountPage.clickToTextboxTextAreaButton(driver, "inideposit");
		newAccountPage.clickToTextboxTextAreaButton(driver, "cusid");
		
		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Customer id"), "Customer ID is required");
		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Initial deposit"), "Initial Deposit must not be blank");
	}
	
	@Test
	public void TC_03_AddNewAccount_FieldMustBeNumeric() {
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage= PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicField(driver, stringValue, "cusid");
		newAccountPage.inputToDynamicField(driver, stringValue, "inideposit");

		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Customer id"), "Characters are not allowed");
		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Initial deposit"), "Characters are not allowed");

	}
	
	@Test
	public void TC_04_AddNewAccount_FieldNotWithSpecial() {
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage= PageFactoryManage.getNewAccountPage(driver);
		newAccountPage.inputToDynamicField(driver, specialChar, "cusid");
		newAccountPage.inputToDynamicField(driver, specialChar, "inideposit");

		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Customer id"), "Special characters are not allowed");
		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Initial deposit"), "Special characters are not allowed");

	}

	@Test
	public void TC_05_AddNewAccount_FieldNotWithBlankSpace() {
		newAccountPage.refresh(driver);
		newAccountPage.pressSpace(driver, "cusid");
		newAccountPage.pressSpace(driver, "inideposit");

		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Customer id"), "First character can not have space");
		verifyEquals(newAccountPage.getDynamicMessageField(driver, "Initial deposit"), "First character can not have space");
	}

	@Test
	public void TC_06_EditAccount_ChangeToOtherType() {
		homePage.openMultiplePages(driver, "Edit Account");
		editAccountPage= PageFactoryManage.getEditAccountPage(driver);

		editAccountPage.inputToDynamicField(driver, Payment.AccountID, "accountno");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		editAccountPage.selectFromDropdown(driver, "Savings", "a_type");
		editAccountPage.clickToTextboxTextAreaButton(driver, "AccSubmit");
		verifyEquals(editAccountPage.getDynamicTextInTable(driver, "Account Type"), "Savings");
	}
	public int ramdomNumber() {
		Random radom = new Random();
		int number = radom.nextInt(9999999);
		return number;
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
