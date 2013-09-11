package com.intelegencia.tests.testLMS;

import com.intelegencia.tests.utility.Constants;
import com.intelegencia.tests.utility.ErrorUtil;
import com.intelegencia.tests.utility.TestUtil;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wallPage {

    WebDriver driver;
    WebElement textarea;
    WebElement buttonWallShare;
    Date now = new Date();
    /**
     * constants is a java class in "utility package" which will fetch the
     * xpaths of all the elements in this page
     */
    @FindBy(xpath = Constants.activityReport)
    public WebElement activityReport;
    @FindBy(id = Constants.fileUpload)
    public WebElement fileUpload;
    @FindBy(linkText = Constants.fileLink)
    public WebElement fileLink;
    @FindBy(xpath = Constants.fileButtonDeleteClk)
    public WebElement fileButtonDeleteClk;
    @FindBy(xpath = Constants.selectQuiz)
    public WebElement selectQuiz;
    @FindBy(xpath = Constants.attemptQuiz)
    public WebElement attemptQuiz;
    @FindBy(xpath = Constants.selectOption)
    public WebElement selectOption;
    @FindBy(xpath = Constants.endQuiz)
    public WebElement endQuiz;
    @FindBy(xpath = Constants.finalSubmitQuiz)
    public WebElement finalSubmitQuiz;
    @FindBy(xpath = Constants.dialogBoxData)
    public WebElement dialogBoxData;
    @FindBy(xpath = Constants.clickDialogBox)
    public WebElement clickDialogBox;
    @FindBy(xpath = Constants.leftnav1Click)
    public WebElement leftnav1Click;
    @FindBy(xpath = Constants.leftnav2Click)
    public WebElement leftnav2Click;
    @FindBy(xpath = Constants.durationRadioBtn)
    public WebElement durationRadioBtn;

    /**
     * creating a constructor for initializing the webdriver
     *
     * @param dr
     */
    public wallPage(WebDriver dr) {
        driver = dr;
    }

    public void postTextOnHomeWall(String text) {
        textarea = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.wallPublishPanelXPATH)));
        TestUtil.actionBuilderClick(Constants.wallPublishPanelXPATH);
        try {
            buttonWallShare = new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By
                    .xpath(Constants.btnWallShareXPATH)));
        } catch (Throwable t) {
            ErrorUtil.addVerificationFailure(t);
        }
        new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.linkButton)));
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : iframes) {
            driver.switchTo().frame(frame.getAttribute("name"));
            break;
        }
        WebElement editableTxtArea = driver.switchTo().activeElement();
        editableTxtArea.sendKeys(text
                + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(now));
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, 60).until(ExpectedConditions
                .invisibilityOfElementLocated(By.id(Constants.shareType)));
        buttonWallShare.click();
        TestUtil.isTextPresentByCSS(
                "div.post",
                text
                + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                DateFormat.SHORT).format(now));
    }

    public void postUrlOnHomeWall(String urlPost) {
        textarea = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.wallPublishPanelXPATH)));
        TestUtil.actionBuilderClick(Constants.wallPublishPanelXPATH);
        try {
            buttonWallShare = new WebDriverWait(driver, 60)
                    .until(ExpectedConditions.elementToBeClickable(By
                    .xpath(Constants.btnWallShareXPATH)));
        } catch (Throwable t) {
            ErrorUtil.addVerificationFailure(t);
        }
        WebElement linkButton = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.linkButton)));
        linkButton.click();
        WebElement linkTextBox = new WebDriverWait(driver, 60)
                .until(ExpectedConditions.elementToBeClickable(By
                .xpath(Constants.urlLink)));

        int i = 1;
        value:
        while (i < 6) {
            TestUtil.actionBuilderClick(Constants.urlPostpath);
            linkTextBox.sendKeys(urlPost);
            linkTextBox.clear();
            linkTextBox.sendKeys("http://" + urlPost);
            if (i < 5) {
                try {
                    new WebDriverWait(driver, 15).until(ExpectedConditions
                            .textToBePresentInElementValue(
                            By.xpath(Constants.urlLink), "http://"
                            + urlPost));
                    break value;
                } catch (TimeoutException e) {
                    System.out.println("i: " + i);
                    i++;
                }
            }
        }
        buttonWallShare.click();
        TestUtil.isElementPresentContainsTextByXPATH("http://" + urlPost);
        if (urlPost.contains(urlPost)) {
            String mainWindow = driver.getWindowHandle();
            driver.findElement(By.linkText("http://" + urlPost)).click();
            int j = 1;
            for (String handle : driver.getWindowHandles()) {
                System.out.println("window handle: " + handle);
                driver.switchTo().window(handle);
                if (j > 1) {
                    driver.close();
                }
                j++;
            }
            driver.switchTo().window(mainWindow);
        }
    }

    public void fileUploading(String fileName) {
        fileLink.click();
        TestUtil.isTextPresentByXPATH(Constants.fileNamePath, "File Name");
        TestUtil.isElementClickableByXpath(Constants.buttonsAvailable, 60);
        TestUtil.actionBuilderClick(Constants.buttonsAvailable);
        TestUtil.isTextPresentByXPATH(Constants.fileNamePresent, "File Name:");
        WebElement element = driver.findElement(By.xpath("//div/input[2]"));

        ((JavascriptExecutor) driver)
                .executeScript(
                "arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1",
                element);
        element.sendKeys(System.getProperty("user.dir") + "\\filedata\\"
                + fileName);
        driver.findElement(By.xpath(Constants.fileButtonClk)).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        try {
            driver.findElement(
                    By.xpath("//*[starts-with(text(),'" + fileName + "')]"))
                    .isDisplayed();
        } catch (Throwable t) {
        }
    }

    public void fileDeletion(String fileName) {
        fileLink.click();
        TestUtil.isElementClickableByXpath(Constants.fileDeleteButton, 60);
        WebElement c = driver.findElement(By.xpath("//*[starts-with(text(),'"
                + fileName + "')]"));
        c.findElement(By.xpath(Constants.fileDeleteButton)).click();
        fileButtonDeleteClk.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void isFilePresent(String fileName1, String fileName2,
            String fileName3) {
        fileLink.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElement(
                By.xpath("//*[starts-with(text(),'" + fileName1 + "')]"))
                .isDisplayed();
        driver.findElement(
                By.xpath("//*[starts-with(text(),'" + fileName2 + "')]"))
                .isDisplayed();
        driver.findElement(
                By.xpath("//*[starts-with(text(),'" + fileName3 + "')]"))
                .isDisplayed();
    }

    public void navigateToActivityReport() {
        activityReport.click();
        selectQuiz.click();
        attemptQuiz.click();
        selectOption.click();
        endQuiz.click();
        finalSubmitQuiz.click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        clickDialogBox.click();

    }

    public void commentOnTeachersCoursePost(String TeacherName,
            String TeacherPost, String Comment) {
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        WebElement b = driver.findElement(By.xpath("//a[contains(text(),'"
                + TeacherName + "')]"));
        b.findElement(By
                .xpath("//*[starts-with(text(),'" + TeacherPost + "')]"));
        b.findElement(By.xpath("//*[starts-with(@id,'lblComments')]")).click();
        b.findElement(By.xpath("//*[starts-with(@id,'add_comment_box_')]"))
                .sendKeys(Comment);
        b.findElement(By.xpath("//div[2]/a[2]")).click();
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
            driver.findElement(
                    By.xpath("//*[@id='ext-comp-1061']/tbody/tr[2]/td[2]"))
                    .click();
            TestUtil.isElementPresentContainsTextByXPATH(sesName);
            TestUtil.isTextPresentByXPATH(
                    Constants.textPresent,
                    sesName
                    + DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
                    DateFormat.SHORT).format(now));
        } catch (Throwable t) {
        }
    }
}
