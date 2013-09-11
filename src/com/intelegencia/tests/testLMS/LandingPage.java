/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intelegencia.tests.testLMS;

import com.intelegencia.tests.utility.Constants;
import com.intelegencia.tests.utility.TestUtil;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Joshua.Perumalla
 */
public class LandingPage {

    WebDriver driver;
    /**
     * constants is a java class in "utility package" which will fetch the
     * xpaths of all the elements in this page
     */
    @FindBy(xpath = Constants.profilePage)
    private WebElement profilePage;    
    @FindBy(linkText = Constants.grpCreation)
    public WebElement grpCreation;    
    @FindBy(linkText = Constants.Homelink)
    public WebElement Homelink;
    @FindBy(xpath = Constants.Courseslink)
    public WebElement Courseslink;
    @FindBy(xpath = Constants.myCourse)
    public WebElement myCourse;
    @FindBy(xpath = Constants.courseGroupName)
    public WebElement courseGroupName;
    @FindBy(linkText = Constants.contactName)
    public WebElement contactName;
    @FindBy(linkText = Constants.searchContactName)
    public WebElement searchContactName;
    @FindBy(xpath = Constants.clickOnCourse)
    public WebElement clickOnCourse;
    @FindBy(xpath = Constants.profile)
    public WebElement profile;
    @FindBy(xpath = Constants.searchContact)
    public WebElement searchContact;
    @FindBy(xpath = Constants.searchContactButton)
    public WebElement searchContactButton;
    @FindBy(xpath = Constants.finalLogoutBtn)
    public WebElement finalLogoutBtn;

    /**
     * creating a constructor for initializing the webdriver
     *
     * @param dr
     */
    public LandingPage(WebDriver dr) {
        driver = dr;
    }

    /**
     * clicks on My Wall and page factory will return to the mycoursepage class
     *
     * @return
     */
    public wallPage navigateToMyWall() {
        Homelink.click();
        TestUtil.clickByJavaScript(Constants.linkToWallXPATH);
        TestUtil.verifyCurrentUrl(Constants.homeUrl);
        return PageFactory.initElements(driver, wallPage.class);

    }

    public wallPage navigateToCourseWall(String courseGrpName) {
        Homelink.click();   
        Actions actions = new Actions(driver);
        WebElement myLink = driver.findElement(By.xpath("//*[@id='region-pre']/div/ul/li/a"));
        actions.moveToElement(myLink).build().perform();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[starts-with(text(),'" + courseGrpName + "')]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return PageFactory.initElements(driver, wallPage.class);
    }

    public wallPage teacherPostUrlOnStdWall(String Student) {
        Homelink.click();
        TestUtil.actionBuilderClick(Constants.profile);
        contactName.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        TestUtil.clickByJavaScript(Constants.searchContact);
        searchContact.sendKeys(Student);
        TestUtil.clickByJavaScript(Constants.searchContactButton);
        TestUtil.isElementPresentStartsWithTextByXPATH(Student);
        driver.findElement(
                By.xpath("//*[starts-with(text(),'" + Student + "')]")).click();
        TestUtil.clickByJavaScript(Constants.teacherPostOnStdWall);
        return PageFactory.initElements(driver, wallPage.class);

    }

    /**
     * clicks on the profile link and page factory will return/go to the
     * profilepage class
     *
     * @return
     */
    public profilepage goToProfile() {
        Homelink.click();
        profilePage.click();
        return PageFactory.initElements(driver, profilepage.class);
    }

    /**
     * clicks on the group link and page factory will return to the
     * groupCreation class
     *
     * @return
     */
    public GroupCreation groupCreation() {
        Homelink.click();
        grpCreation.click();
        return PageFactory.initElements(driver, GroupCreation.class);
    }

    public void testsignout() {
        Homelink.click();
        Actions actions = new Actions(driver);
        WebElement Link = driver.findElement(By
                .xpath("//*[@id='navright']/li[3]/a[@href='#']"));
        actions.moveToElement(Link).build().perform();
        driver.findElement(By.linkText("Sign Out")).click();
        try {
            WebElement Link1 = driver.findElement(By
                    .xpath("//*[@id='navright']/li[3]/a[@href='#']"));
            actions.moveToElement(Link1).build().perform();
            finalLogoutBtn.click();
        } catch (Throwable t) {
        }
    }
}