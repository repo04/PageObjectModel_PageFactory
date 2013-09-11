/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelegencia.tests.testSmoke;

import com.intelegencia.tests.testLMS.Base;
import com.intelegencia.tests.testLMS.GroupCreation;
import com.intelegencia.tests.testLMS.LandingPage;
import com.intelegencia.tests.testLMS.profilepage;
import com.intelegencia.tests.testLMS.wallPage;
import com.intelegencia.tests.utility.TestUtil;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Joshua.Perumalla
 */
public class testTeacher extends Base {

    /**
     *
     * before running your tests you need to call init() method which will call
     * properties files, initialize webdriver & login
     */
    @BeforeClass(groups = {"prerequisite"})
    public void testTeacherLogin() {
        login("teacher");
    }

    @Test(dataProvider = "teacherPostOnCourseWall")
    public void testTeacherPostOnCourseWall(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the "TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        wallPage wall = landing.navigateToCourseWall(data.get("CourseName"));
        wall.postTextOnHomeWall(data.get("HomeWallText"));
        wall.postUrlOnHomeWall(data.get("HomeWallUrl"));
    }

    @DataProvider
    public Object[][] teacherPostOnCourseWall() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "createSocialGrp")
    public void testTeacherCreatesASocialGroup(Hashtable<String, String> data) {
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.groupCreation();
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        GroupCreation socialGrpCrn = PageFactory.initElements(driver, GroupCreation.class);
        socialGrpCrn.createASocialGroup(data.get("GroupName"), data.get("ShortName"), data.get("AboutThisGroup"),
                data.get("WhoShouldJoin"), data.get("Topics"));
        TestUtil.takeScreenShot("socialGrp");
    }

    @DataProvider
    public Object[][] createSocialGrp() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "teacherPostOnStudentWall")
    public void testTeacherPostOnStudentWall(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the "TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage land = PageFactory.initElements(driver, LandingPage.class);
        land.teacherPostUrlOnStdWall(data.get("StudentName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.postTextOnHomeWall(data.get("HomeWallText"));
        wall.postUrlOnHomeWall(data.get("HomeWallUrl"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @DataProvider
    public Object[][] teacherPostOnStudentWall() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "TeacherCreateLiveSession")
    public void testTeacherCreateLiveSession(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("LiveSessionData", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.navigateToCourseWall(data.get("CourseName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.createLiveSession(data.get("SessionName"),
                data.get("Description"), data.get("StartDate"),
                data.get("StartTime"));
    }

    @DataProvider
    public Object[][] TeacherCreateLiveSession() {
        return TestUtil.getData("LiveSessionData", xls);
    }

    @Test(dataProvider = "teacherUploadFile")
    public void testTeacherUploadFiles(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the "TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.navigateToCourseWall(data.get("CourseName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.fileUploading(data.get("FileName1"));
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        wall.fileUploading(data.get("FileName2"));
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        wall.fileUploading(data.get("FileName3"));
    }

    @DataProvider
    public Object[][] teacherUploadFile() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "teacherVerifiesFile")
    public void testTeacherVerifyFilesInPortfolio(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the "TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.goToProfile();
        profilepage proPage = PageFactory.initElements(driver,
                profilepage.class);
        proPage.goToPortfolio(data.get("FileName1"), data.get("FileName2"),
                data.get("FileName3"));
    }

    @DataProvider
    public Object[][] teacherVerifiesFile() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "TeacherCreateLiveSessionWithSocGrp")
    public void testTeacherCreateLiveSessionWithSocGrp(
            Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.groupCreation();
        GroupCreation socialGrpCrn = PageFactory.initElements(driver,
                GroupCreation.class);
        socialGrpCrn.goToSocialGrp(data.get("GroupName"));
        socialGrpCrn.createLiveSession(data.get("SessionName"),data.get("Description"), 
                data.get("StartDate"), data.get("StartTime"));
    }

    @DataProvider
    public Object[][] TeacherCreateLiveSessionWithSocGrp() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @AfterClass(groups = {"prerequisite"})
    public void testTeacherSignOut() {
        LandingPage landing = PageFactory.initElements(driver, LandingPage.class);
        landing.testsignout();
    }
}
