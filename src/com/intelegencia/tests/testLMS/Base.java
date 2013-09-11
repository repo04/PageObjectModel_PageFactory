package com.intelegencia.tests.testLMS;

import com.intelegencia.tests.utility.Constants;
import com.intelegencia.tests.utility.Xls_Reader;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Joshua.Perumalla
 */
public class Base {

    public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
    public static Properties CONFIG = null;
    public static WebDriver driver = null;
    public static boolean isLoggedIn = false;
    public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")
            + "\\src\\com\\intelegencia\\tests\\XLdata\\lms.xlsx");
    public static String username;

    /**
     * initializing config.properties file to get the config data like url,
     * browser, username, password
     */
    @BeforeTest(groups = {"prerequisite"})
    public void initConfigurations() {

        if (CONFIG == null) {
            // Logging
            APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
            CONFIG = new Properties();
            try {
                FileInputStream fs = new FileInputStream(
                        Constants.CONFIG_FILE_PATH);
                CONFIG.load(fs);
                APPLICATION_LOGS
                        .debug("loading config file from config package");
            } catch (Exception e) {
            }
        }
    }

    /**
     * initializing the webdriver
     */
    @BeforeTest(groups = {"prerequisite"})
    public void initDriver() {

        if (driver == null) {
            switch (CONFIG.getProperty("browser")) {
                case "Mozilla":
                    driver = new FirefoxDriver();
                    APPLICATION_LOGS.debug("opening firefox browser");
                    break;
                case "IE":
                    System.getProperty(
                            "webdriver.ie.driver",
                            System.getProperty("user.dir")
                            + "\\lib\\IEDriverServer_x64_2.29.1\\IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    APPLICATION_LOGS.debug("opening IE browser");
                    break;
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir")
                            + "\\lib\\chromedriver_win\\chromedriver.exe");
                    driver = new ChromeDriver();
                    APPLICATION_LOGS.debug("opening chrome browser");
                    break;
            }
        }
    }

    /**
     * performing the login part
     *
     * @return
     */
    public void doLogin(String userName) {

        Base.username = userName;
        WebElement user = driver.findElement(By.xpath("//*[@id='username']"));
        WebElement passWord = driver.findElement(By
                .xpath("//*[@id='password']"));
        WebElement loginBtn = driver.findElement(By
                .xpath("//*[@id='loginpage_submit_btn']"));

        switch (userName) {
            case "contentAdmin":
                user.sendKeys(CONFIG.getProperty("contentAdminuName"));
                passWord.sendKeys(CONFIG.getProperty("password"));
                break;
            case "pesAdmin":
                user.sendKeys(CONFIG.getProperty("pesAdminName"));
                passWord.sendKeys(CONFIG.getProperty("password"));
                break;
            // Teacher/Student
            case "teacher":
                user.sendKeys(CONFIG.getProperty("teacherName"));
                passWord.sendKeys(CONFIG.getProperty("teacherPassword"));
                break;
            case "student":
                user.sendKeys(CONFIG.getProperty("studentName"));
                passWord.sendKeys(CONFIG.getProperty("studentPassword"));
                break;
        }
        loginBtn.click();
    }

    @Test
    public LandingPage login(String userName) {
        driver.get(CONFIG.getProperty("url"));
        doLogin(userName);
        LandingPage landingPage = PageFactory.initElements(driver,
                LandingPage.class);
        return landingPage;
    }

    public static String getUser() {
        return username;
    }

    /**
     * quiting the browser
     */
    public void quitDriver() {
        driver.quit();
        // driver = null;
    }
}
