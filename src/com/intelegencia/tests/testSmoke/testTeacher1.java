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
import com.intelegencia.tests.testLMS.wallPage;
import com.intelegencia.tests.utility.TestUtil;

public class testTeacher1 extends Base {

	@BeforeClass(groups = { "prerequisite" })
	public void testTeacherLogin1() {
		login("teacher");
	}

	@Test(dataProvider = "findSclGrp")
	public void testTeacherJoinStudentSocialGroup(Hashtable<String, String> data) {
		// data Provider will check whether the test has the runmode Y(yes) or
		// N(no) if it is Y then it will go into the TestData" folder in excel
		// sheet and fetch the data for this method
		if (!TestUtil.isExecutable("socialGroup_Student", xls)
				|| data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.groupCreation();
		GroupCreation gp = PageFactory
				.initElements(driver, GroupCreation.class);
		gp.findASocialGroup(data.get("GroupName"));
		gp.joinSocialGrp(data.get("GroupName"));
	}

	@DataProvider
	public Object[][] findSclGrp() {
		return TestUtil.getData("socialGroup_Student", xls);
	}

	@Test(dataProvider = "leaveSclGrp")
	public void testTeacherLeavesStudentSocialGroup(
			Hashtable<String, String> data) {
		// data Provider will check whether the test has the runmode Y(yes) or
		// N(no) if it is Y then it will go into the TestData" folder in excel
		// sheet and fetch the data for this method
		if (!TestUtil.isExecutable("socialGroup_Student", xls)
				|| data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.groupCreation();
		GroupCreation gp = PageFactory
				.initElements(driver, GroupCreation.class);
		gp.findASocialGroup(data.get("GroupName"));
		gp.leaveSocialGrp(data.get("GroupName"));
	}

	@DataProvider
	public Object[][] leaveSclGrp() {
		return TestUtil.getData("socialGroup_Student", xls);
	}

	@Test(dataProvider = "deleteSocialGrp")
	public void testTeacherDeletesOwnSocialGroup(Hashtable<String, String> data) {
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.groupCreation();
		// data Provider will check whether the test has the runmode Y(yes) or
		// N(no) if it is Y then it will go into the TestData" folder in excel
		// sheet and fetch the data for this method
		if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
				|| data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		GroupCreation socialGrpCrn = PageFactory.initElements(driver,
				GroupCreation.class);
		socialGrpCrn.deleteTeacherSocialGrp(data.get("GroupName"));
		TestUtil.takeScreenShot("socialGrp");
	}

	@DataProvider
	public Object[][] deleteSocialGrp() {
		return TestUtil.getData("socialGroup_Teacher", xls);
	}

	@Test(dataProvider = "teacherDeletesFile")
	public void testTeacherDeleteFiles(Hashtable<String, String> data) {
		// data Provider will check whether the test has the runmode Y(yes) or
		// N(no) if it is Y then it will go into the "TestData" folder in excel
		// sheet and fetch the data for this method
		if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
				|| data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.navigateToCourseWall(data.get("CourseName"));
		wallPage wall = PageFactory.initElements(driver, wallPage.class);
		wall.fileDeletion(data.get("FileName1"));
		// wall.fileDeletion(data.get("FileName2"));
		// wall.fileDeletion(data.get("FileName3"));
	}

	@DataProvider
	public Object[][] teacherDeletesFile() {
		return TestUtil.getData("socialGroup_Teacher", xls);
	}

	@AfterClass(groups = { "prerequisite" })
	public void testTeacherSignOut() {
		LandingPage landing = PageFactory.initElements(driver,
				LandingPage.class);
		landing.testsignout();
	}
}
