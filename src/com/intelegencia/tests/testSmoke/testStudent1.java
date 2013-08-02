package com.intelegencia.tests.testSmoke;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intelegencia.tests.testLMS.Base;
import com.intelegencia.tests.testLMS.GroupCreation;
import com.intelegencia.tests.testLMS.LandingPage;
import com.intelegencia.tests.utility.TestUtil;

public class testStudent1 extends Base {

	
	 @BeforeClass(groups = {"prerequisite"})
	 public void testStudentLogin1() {
		 login("student");

	}

	@Test(dataProvider = "deleteStdSocialGrp")
	public void testStudentDeletesOwnSocialGroup(Hashtable<String, String> data) {
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.groupCreation();
		// data Provider will check whether the test has the runmode Y(yes) or
		// N(no) if it is Y then it will go into the TestData" folder in excel
		// sheet and fetch the data for this method
		if (!TestUtil.isExecutable("socialGroup_Student", xls)
				|| data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		GroupCreation socialGrpCrn = PageFactory.initElements(driver,
				GroupCreation.class);
		socialGrpCrn.deleteStudentSocialGrp(data.get("GroupName"));
		TestUtil.takeScreenShot("socialGrp");
	}

	@DataProvider
	public Object[][] deleteStdSocialGrp() {
		return TestUtil.getData("socialGroup_Student", xls);
	}

	 @AfterClass(groups = {"prerequisite"})
	 public void testStudentSignOut() {
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.testsignout();
	}

}
