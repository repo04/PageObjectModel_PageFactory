package com.intelegencia.tests.utility;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.intelegencia.tests.testLMS.Base;

/**
 *
 * @author Joshua.Perumalla
 */
public class TestUtil {
    // true- test has to be executed
    // false- test has to be skipped

    /**
     *
     *isExecutable() is a method which will check whether the test we are executing has a runmode Y(yes)
     * @param testName
     * @param xls
     * @return
     */
    public static boolean isExecutable(String testName, Xls_Reader xls) {
        for (int rowNum = 2; rowNum <= xls.getRowCount("Test Cases"); rowNum++) {
            if (xls.getCellData("Test Cases", "TCID", rowNum).equals(testName)) {
                if (xls.getCellData("Test Cases", "Runmode", rowNum).equals("Y")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     *takeScreenShot() is a method which takes screenshot for a particular test
     * @param fileName
     */
    public static void takeScreenShot(String fileName) {
        File srcFile = ((TakesScreenshot) (Base.driver)).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName + ".jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    /**
     * clickbyJavaScript() is a method which clicks the webelement when webdriver is unable to identify the 
     * @param fileName
     */
    public static void clickByJavaScript(String XPATH) {
        WebElement hiddenElement = Base.driver.findElement(By.xpath(XPATH));
        ((JavascriptExecutor) Base.driver).executeScript("arguments[0].click()", hiddenElement);
    }
    
  
    
    public static void verifyCurrentUrl(String textInUrl) {
    	try{
        if (!Base.driver.getCurrentUrl().contains(textInUrl));
        }catch(Throwable e){
			ErrorUtil.addVerificationFailure(e);         
        }
    }
    
    
    public static void actionBuilderClick(String path) {
        WebElement elm = Base.driver.findElement(By.xpath(path));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(Base.driver);
        builder.click(elm).perform();
    }
    
    public static void isElementPresentContainsTextByXPATH(String elementText) {
        new WebDriverWait(Base.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + elementText + "')]")));
    }
    
    
    public static void illegalStateException(String message) {
        throw new IllegalStateException(message);
    }
    
    
    public static void isElementPresentStartsWithTextByXPATH(String elementText) {
        new WebDriverWait(Base.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(text(),'" + elementText + "')]")));
    }
    
    public static void isElementClickableByXpath(String elementClickable, int time) {
        new WebDriverWait(Base.driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(elementClickable)));
    }
    
    public static void isElementPresentByXPATH(String elementByXPATH) {
        new WebDriverWait(Base.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementByXPATH)));
    }
    
    public static void isTextPresentByCSS(String css, String text) {
        new WebDriverWait(Base.driver, 60).until(ExpectedConditions.textToBePresentInElement(By.cssSelector(css), text));
    }
    
    public static void isTextPresentByXPATH(String path, String text) {
        new WebDriverWait(Base.driver, 60).until(ExpectedConditions.textToBePresentInElement(By.xpath(path), text));
    }
    
    /**
     *getData() is a method which will get data from the data provider and pas sthe data to our tests to get executed
     * @param testName
     * @param xls
     * @return
     */
    public static Object[][] getData(String testName, Xls_Reader xls) {
        // find the row num from which test starts
        // number of cols in the test
        // number of rows
        // put the data in hastable and put hastable in array

        int testStartRowNum = 0;
        // find the row num from which test starts
        for (int rNum = 1; rNum <= xls.getRowCount("Test Data"); rNum++) {
            if (xls.getCellData("Test Data", 0, rNum).equals(testName)) {
                testStartRowNum = rNum;
                break;
            }
        }
       // System.out.println("Test " + testName + " starts from " + testStartRowNum);

        int colStartRowNum = testStartRowNum + 1;
        int totalCols = 0;
        while (!xls.getCellData("Test Data", totalCols, colStartRowNum).equals("")) {
            totalCols++;
        }
       // System.out.println("Total Cols in test " + testName + " are " + totalCols);

        //rows
        int dataStartRowNum = testStartRowNum + 2;
        int totalRows = 0;
        while (!xls.getCellData("Test Data", 0, dataStartRowNum + totalRows).equals("")) {
            totalRows++;
        }
       // System.out.println("Total Rows in test " + testName + " are " + totalRows);

      //  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        // extract data
        Object[][] data = new Object[totalRows][1];
        int index = 0;
        Hashtable<String, String> table = null;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + totalRows); rNum++) {
            table = new Hashtable<String, String>();
            for (int cNum = 0; cNum < totalCols; cNum++) {
                table.put(xls.getCellData("Test Data", cNum, colStartRowNum), xls.getCellData("Test Data", cNum, rNum));
              //  System.out.print(xls.getCellData("Test Data", cNum, rNum) + " -- ");
            }
            data[index][0] = table;
            index++;
           // System.out.println();
        }
      //  System.out.println("done");
        return data;
    }
}
