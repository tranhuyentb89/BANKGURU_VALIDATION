package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.customers.Account_RegisterToSystem_Common;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;
import pageObjects.MiniStatementPageObject;
import pageObjects.NewAccountPageObject;

public class Verification_MiniStateStatement extends AbstractTest{
	WebDriver driver;
	String stringValue ="test", specialChar ="@#";
	String miniStatementUrl;
	
	
	LoginPageObjects loginPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	EditAccountPageObject editAccountPage;
	MiniStatementPageObject miniStatementPage;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		loginPage = PageFactoryManage.getLoginPage(driver);
	}
	
	@Test
	public void TC_01_Login() {
		loginPage.inputToUserIDTextbox(Account_RegisterToSystem_Common.USER_ID);
		loginPage.inputToPasswordTextbox(Account_RegisterToSystem_Common.PASSWORD);
		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_02_MiniStatement_AccountNoNotEmpty() {
		homePage.openMultiplePages(driver, "Mini Statement");
		miniStatementPage = PageFactoryManage.getMiniStatementPage(driver);
		miniStatementUrl = miniStatementPage.getCurrentUrl(driver);
		miniStatementPage.pressTab(driver, "accountno");
		verifyEquals(miniStatementPage.getDynamicMessageField(driver, "Account No"), "Account Number must not be blank");
	}
	
	@Test
	public void TC_03_MiniStatement_AccountNoShouldBeNumeric() {
		miniStatementPage.openUrl(driver, miniStatementUrl);
		miniStatementPage.inputToDynamicField(driver, stringValue, "accountno");
		verifyEquals(miniStatementPage.getDynamicMessageField(driver, "Account No"), "Characters are not allowed");

	}
	
	@Test
	public void TC_04_MiniStatement_SpecialChar() {
		miniStatementPage.openUrl(driver, miniStatementUrl);
		miniStatementPage.inputToDynamicField(driver, specialChar, "accountno");
		verifyEquals(miniStatementPage.getDynamicMessageField(driver, "Account No"), "Special characters are not allowed");

	}
	
	@Test
	public void TC_05_MiniStatement_FirstBlankSpace() {
		miniStatementPage.openUrl(driver, miniStatementUrl);
		miniStatementPage.pressSpace(driver, "accountno");
		verifyEquals(miniStatementPage.getDynamicMessageField(driver, "Account No"), "Special characters are not allowed");
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}


}
