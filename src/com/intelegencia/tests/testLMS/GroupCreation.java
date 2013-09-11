/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelegencia.tests.testLMS;

import com.intelegencia.tests.utility.Constants;
import com.intelegencia.tests.utility.TestUtil;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Joshua.Perumalla
 */
public class GroupCreation {

    WebDriver driver;
    Date now = new Date();
    String socialGroupName = "studentSocialGroup" + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
            DateFormat.SHORT).format(now);
    /**
     * constants is a java class in "utility package" which will fetch the
     * xpaths of all the elements in this page
     */
    @FindBy(xpath = Constants.startSocialGroup)
    public WebElement startSocialGroup;
    @FindBy(xpath = Constants.groupName)
    public WebElement groupName;
    @FindBy(xpath = Constants.shortName)
    public WebElement shortName;
    @FindBy(xpath = Constants.aboutGroup)
    public WebElement aboutGroup;
    @FindBy(xpath = Constants.whoShouldJoin)
    public WebElement whoShouldJoin;
    @FindBy(xpath = Constants.topics)
    public WebElement topics;
    @FindBy(xpath = Constants.submitGroup)
    public WebElement submitGroup;
    @FindBy(xpath = Constants.startWorkingGroup)
    public WebElement startWorkingGroup;
    @FindBy(xpath = Constants.findSocialGroup)
    public WebElement findSocialGroup;
    @FindBy(xpath = Constants.searchSocialGroup)
    public WebElement searchSocialGroup;
    @FindBy(xpath = Constants.searchField)
    public WebElement searchField;
    @FindBy(xpath = Constants.findSubmit)
    public WebElement findSubmit;
    @FindBy(xpath = Constants.joinButton)
    public WebElement joinButton;
    @FindBy(xpath = Constants.joinLink)
    public WebElement joinLink;
    @FindBy(xpath = Constants.asdLink)
    public WebElement asdLink;
    @FindBy(xpath = Constants.quizLink)
    public WebElement quizLink;
    @FindBy(xpath = Constants.searchTeacherLabel)
    public WebElement searchTeacherLabel;
    @FindBy(xpath = Constants.searchTextarea)
    public WebElement searchTextarea;
    @FindBy(xpath = Constants.searchClick)
    public WebElement searchClick;
    @FindBy(xpath = Constants.leftnav1Click)
    public WebElement leftnav1Click;
    @FindBy(xpath = Constants.leftnav2Click)
    public WebElement leftnav2Click;
    @FindBy(xpath = Constants.durationRadioBtn)
    public WebElement durationRadioBtn;
    @FindBy(xpath = Constants.deleteSocGrp)
    public WebElement deleteSocGrp;
    @FindBy(xpath = Constants.joinSocGrpLink)
    public WebElement joinSocGrpLink;
    @FindBy(xpath = Constants.mySocGrp)
    public WebElement mySocGrp;
    @FindBy(xpath = Constants.leavesocGrp)
    public WebElement leavesocGrp;
    @FindBy(xpath = Constants.leavesocGrplink)
    public WebElement leavesocGrplink;
    @FindBy(xpath = Constants.leaveGrp)
    public WebElement leaveGrp;

    /**
     * creating a constructor for initializing the weddriver
     *
     * @param dr
     */
    public GroupCreation(WebDriver dr) {
        driver = dr;
    }

    /**
     * creates a social group
     */
    public void createASocialGroup(String gName, String sName, String abtGroup, String whoJoin, String tpcs) {
        startSocialGroup.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        groupName.sendKeys(gName + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(now));
        shortName.sendKeys(sName + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(now));
        aboutGroup.sendKeys(abtGroup);
        whoShouldJoin.sendKeys(whoJoin);
        topics.sendKeys(tpcs);
        submitGroup.click();
    }

    /**
     * finds a social group
     */
    public void findASocialGroup(String gName) {
        findSocialGroup.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchSocialGroup.sendKeys(gName);
        searchField.sendKeys("All");
        findSubmit.click();
    }

    public void joinSocialGrp(String gName) {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"));
        driver.findElement(By.xpath("//*[contains(text(),'Join Now')]"))
                .click();
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        String btnID = buttons.get(1).getAttribute("id");
        driver.findElement(By.xpath("//button[@id='" + btnID + "']")).click();
        driver.findElement(By.xpath("//tr[2]/td[2]")).click();
    }

    public void leaveSocialGrp(String gName) {
        mySocGrp.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        TestUtil.isElementPresentStartsWithTextByXPATH(gName);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        leavesocGrp.click();
        leavesocGrplink.click();
        try {
            leaveGrp.click();
        } catch (Throwable t) {
        }
    }

    public void goToSocialGrp(String gName) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        TestUtil.isElementPresentStartsWithTextByXPATH(gName);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"))
                .click();

    }

    public void postOnGrpCrseWall(String gName) {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"))
                .click();

    }

    public void createLiveSession(String sesName, String sDescription,
            String sDate, String sTime) {
        try {
            leftnav1Click.click();
        } catch (Throwable t) {
            leftnav2Click.click();
        }
        TestUtil.isElementPresentByXPATH(Constants.createSession);
        TestUtil.isElementClickableByXpath(Constants.createSession, 60);
        driver.findElement(By.xpath(Constants.createSession)).click();
        WebElement sessionName = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.sesionName)));
        sessionName.clear();
        sessionName.sendKeys(sesName
                + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(now));
        WebElement description = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.sessionDescription)));
        description.clear();
        description.sendKeys(sDescription);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

        durationRadioBtn.click();
        WebElement startDate = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.endDate)));
        startDate.clear();
        startDate.sendKeys(sDate);
        WebElement startTime = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.endTime)));
        startTime.clear();
        startTime.sendKeys(sTime);

        if (sessionName.getText().isEmpty()) {
            sessionName.clear();
            sessionName.sendKeys(sesName
                    + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                    DateFormat.SHORT).format(now));
        }
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        WebElement submit = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath("//*[@id='ext-gen156']")));
        submit.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        try {
            TestUtil.isElementPresentContainsTextByXPATH(sesName);
            TestUtil.isTextPresentByXPATH(
                    Constants.textPresent,
                    sesName
                    + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                    DateFormat.SHORT).format(now));
        } catch (Throwable t) {
        }
    }

    public void deleteTeacherSocialGrp(String gName) {
        TestUtil.isElementPresentContainsTextByXPATH(gName);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"))
                .click();
        deleteSocGrp.click();
        Alert alert = driver.switchTo().alert();
        alert.getText();
        alert.accept();
    }

    public void deleteStudentSocialGrp(String gName) {
        TestUtil.isElementPresentContainsTextByXPATH(gName);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + gName + "')]"))
                .click();
        deleteSocGrp.click();
        Alert alert = driver.switchTo().alert();
        alert.getText();
        alert.accept();
    }
}
