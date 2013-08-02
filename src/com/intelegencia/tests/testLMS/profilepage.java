/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelegencia.tests.testLMS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.intelegencia.tests.utility.Constants;
import com.intelegencia.tests.utility.TestUtil;

/**
 * 
 * @author Joshua.Perumalla
 */
public class profilepage {

	WebDriver driver;
	@FindBy(xpath = Constants.wallPage)
	public WebElement wallPage;
	@FindBy(xpath = Constants.personalInfo)
	public WebElement personalInfo;
	@FindBy(xpath = Constants.portfolio)
	public WebElement portfolio;
	@FindBy(xpath = Constants.notes)
	public WebElement notes;
	@FindBy(xpath = Constants.files)
	public WebElement files;
	@FindBy(xpath = Constants.contacts)
	public WebElement contacts;

	/**
	 * creating a constructor for initializing the weddriver
	 * 
	 * @param dr
	 */
	public profilepage(WebDriver dr) {
		driver = dr;
	}

	public void goToWallPage() {
		wallPage.click();
	}

	public void goToPersonalInfo() {
		personalInfo.click();
	}

	public void goToPortfolio(String fileName1, String fileName2,
			String fileName3) {
		portfolio.click();
		TestUtil.isElementPresentContainsTextByXPATH(fileName1);
		TestUtil.isElementPresentContainsTextByXPATH(fileName2);
		TestUtil.isElementPresentContainsTextByXPATH(fileName3);

	}

	public void goToNotes() {
		notes.click();
	}

	public void goToContacts() {
		contacts.click();
	}
}
