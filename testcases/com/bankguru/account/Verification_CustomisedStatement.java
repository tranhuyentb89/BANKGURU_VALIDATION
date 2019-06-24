package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankguru.customers.Account_RegisterToSystem_Common;

import commons.AbstractTest;
import commons.PageFactoryManage;
import pageObjects.CustomisedStatementPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObjects;

public class Verification_CustomisedStatement extends AbstractTest{
	WebDriver driver;
	String stringValue ="test", specialChar ="@#";
	String customisedStatementUrl;
	
	
	LoginPageObjects loginPage;
	HomePageObject homePage;
	CustomisedStatementPageObject customisedStatementPage;
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
	public void TC_02_CustomeStatement_FieldNotEmpty() {
		homePage.openMultiplePages(driver, "Customised Statement");
		customisedStatementPage = PageFactoryManage.getCustomisedStatementPage(driver);
		customisedStatementUrl = customisedStatementPage.getCurrentUrl(driver);
		customisedStatementPage.pressTab(driver, "accountno");
		customisedStatementPage.pressTab(driver, "fdate");
		customisedStatementPage.pressTab(driver, "tdate");
		
		verifyEquals(customisedStatementPage.getDynamicMessageField(driver, "Account No"), "Account Number must not be blank");
		verifyEquals(customisedStatementPage.getDynamicMessageField(driver, "From Date"), "From Date Field must not be blank");
		verifyEquals(customisedStatementPage.getDynamicMessageField(driver, "To Date"), "To Date Field must not be blank");

	}
	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}


}
