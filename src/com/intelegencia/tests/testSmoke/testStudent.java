package com.intelegencia.tests.testSmoke;

import com.intelegencia.tests.testLMS.Base;
import com.intelegencia.tests.testLMS.GroupCreation;
import com.intelegencia.tests.testLMS.LandingPage;
import com.intelegencia.tests.testLMS.wallPage;
import com.intelegencia.tests.utility.TestUtil;
import java.util.Hashtable;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testStudent extends Base {

    @BeforeClass(groups = {"prerequisite"})
    public void testStudentLogin() {
        login("student");
    }

    @Test(dataProvider = "StudentJoinTeacherSocGrp")
    public void testStudentJoinTeacherSocGrp(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls) || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.groupCreation();
        GroupCreation gp = PageFactory
                .initElements(driver, GroupCreation.class);
        gp.findASocialGroup(data.get("GroupName"));
        gp.joinSocialGrp(data.get("GroupName"));
    }

    @DataProvider
    public Object[][] StudentJoinTeacherSocGrp() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "StudentPostOnTeacherSocGrp")
    public void testStudentPostOnTeacherSocGrp(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.groupCreation();
        GroupCreation gp = PageFactory
                .initElements(driver, GroupCreation.class);
        gp.findASocialGroup(data.get("GroupName"));
        gp.postOnGrpCrseWall(data.get("GroupName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.postTextOnHomeWall(data.get("HomeWallText"));
        wall.postUrlOnHomeWall(data.get("HomeWallUrl"));
    }

    @DataProvider
    public Object[][] StudentPostOnTeacherSocGrp() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "StudentCommentOnTeacherSocGrp")
    public void testStudentCommentOnTeacherSocGrp(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.navigateToCourseWall(data.get("CourseName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.commentOnTeachersCoursePost(data.get("FindTeacher"), data.get("HomeWallPost"), data.get("Comment"));

    }

    @DataProvider
    public Object[][] StudentCommentOnTeacherSocGrp() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "CreateLiveSessionWithinTeacherSocGrp")
    public void testStudentCreateLiveSessionWithinTeacherSocGrp(
            Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("LiveSessionData_student", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.groupCreation();
        GroupCreation socialGrpCrn = PageFactory.initElements(driver,
                GroupCreation.class);
        socialGrpCrn.goToSocialGrp(data.get("GroupName"));
        socialGrpCrn.createLiveSession(data.get("SessionName"),
                data.get("Description"), data.get("StartDate"), data.get("StartTime"));
    }

    @DataProvider
    public Object[][] CreateLiveSessionWithinTeacherSocGrp() {
        return TestUtil.getData("LiveSessionData_student", xls);
    }

    @Test(dataProvider = "createSocialGrp")
    public void testStudentCreatesASocialGroup(Hashtable<String, String> data) {
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.groupCreation();
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Student", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        GroupCreation socialGrpCrn = PageFactory.initElements(driver,
                GroupCreation.class);
        socialGrpCrn.createASocialGroup(data.get("GroupName"),
                data.get("ShortName"), data.get("AboutThisGroup"),
                data.get("WhoShouldJoin"), data.get("Topics"));
        TestUtil.takeScreenShot("socialGrp");
    }

    @DataProvider
    public Object[][] createSocialGrp() {
        return TestUtil.getData("socialGroup_Student", xls);
    }

    @Test(dataProvider = "StudentCheckTeacherFiles")
    public void testStudentVerifyTeacherFiles(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.navigateToCourseWall(data.get("CourseName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.isFilePresent(data.get("FileName1"), data.get("FileName2"),
                data.get("FileName3"));
    }

    @DataProvider
    public Object[][] StudentCheckTeacherFiles() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @Test(dataProvider = "studentAttemptQuiz")
    public void testStudentAttemptQuiz(Hashtable<String, String> data) {
        // data Provider will check whether the test has the runmode Y(yes) or
        // N(no) if it is Y then it will go into the TestData" folder in excel
        // sheet and fetch the data for this method
        if (!TestUtil.isExecutable("socialGroup_Teacher", xls)
                || data.get("Runmode").equals("N")) {
            throw new SkipException("Skipping the test");
        }
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.navigateToCourseWall(data.get("CourseName"));
        wallPage wall = PageFactory.initElements(driver, wallPage.class);
        wall.navigateToActivityReport();
    }

    @DataProvider
    public Object[][] studentAttemptQuiz() {
        return TestUtil.getData("socialGroup_Teacher", xls);
    }

    @AfterClass(groups = {"prerequisite"})
    public void testStudentSignOut() {
        LandingPage landing = PageFactory.initElements(driver,
                LandingPage.class);
        landing.testsignout();
    }
}
