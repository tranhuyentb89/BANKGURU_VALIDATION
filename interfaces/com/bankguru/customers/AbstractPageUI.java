package com.bankguru.customers;

public class AbstractPageUI {
	public static final String LOGIN_FORM ="//form[@name='frmLogin']";
	public static final String DYNAMIC_LINK ="//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA_BUTTON ="(//textarea|//input)[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE="//td[contains(text(),'%s')]/following-sibling::td/label";
	public static final String DYNAMIC_TABLE_TEXT ="//td[text()='%s']/following-sibling::td";
	public static final String DYNAMIC_PAGE_TITLE="//p[@class='heading3']";
	public static final String DYNAMIC_DROPDOWN ="//select[@name='%s']";
	public static final String DYNAMIC_TEXTBOX_TEXTAREA_BUTTON_TEST ="(//textarea|//input)[@name='txtcid']";
	public static final String DYNAMIC_TEXT_DISPLAYED="//p[text()='%s']";

}
