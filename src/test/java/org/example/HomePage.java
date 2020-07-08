package org.example;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class HomePage {
    static WebDriver driver;

    public static void waitUntillElementClickable(By by, int Time) {//Wait re-usable method, to wait untill element is live
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitUntillElementVisible(By by, int Time) {//Wait re-usable method, to wait untill element is visible
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void clickOnElement(By by) {// click on element re-usable method
        driver.findElement(by).click();
    }

    public static String getTextFromElement(By by) {// re-usable method to get text from element
        return driver.findElement(by).getText();
    }

    public static void typeText(By by, String text) {// re-usable method to type text in text-box
        driver.findElement(by).clear(); // Clears pre-existing text from the element if any existed
        driver.findElement(by).sendKeys(text);
    }

    public static void selectFromDropDownByVisiableText(By by, String value1) {// re-usable method to select option from DropDown box by visible option
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(value1);
    }

    public static void selectFromDropDownByIndex(By by, int n1) {// re-usable method to select option from DropDown box by index value of element
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(n1);
    }

    public static void selectFromDropDownByValue(By by, String value2) {// re-usable method to select option from DropDown box
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value2);
    }

    public static long timeStamp() {// re-usable method to get timestamp
        return (System.currentTimeMillis());
    }

    public static void javaScriptClick(By by){//re-usable method to click on element, some
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }

    @AfterMethod
    public static void closeBrowser() {//re-usable method to close web-browser
        driver.close();
    }

    @BeforeMethod
    public static void setUpWebBrowser() {// re-usable method to setup web browser for gui
        System.setProperty("webdriver.chrome.driver", "C:\\Soft\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void VerifyUserShouldRegisterSuccessfully() {
        //click on register link, on home page
        clickOnElement(By.linkText("Register"));
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //click on male
        waitUntillElementClickable(By.xpath("//input[@id=\"register-button\"]"), 30);
        clickOnElement(By.xpath("//input[@id=\"gender-male\"]"));

        //type First Name
        typeText(By.xpath("//input[@id=\"FirstName\"]"), "Nick");

        //type Last Name
        typeText(By.xpath("//input[@id=\"LastName\"]"), "Soloman");

        //click on Date of Birth
        clickOnElement(By.xpath("//label[text()=\"Date of birth:\"]"));

        //select Date of BirthDay
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "14");

        //select Date of BirthMonth
        selectFromDropDownByVisiableText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "June");

        //select Date of Year
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "1980");

        //type email id
        typeText(By.xpath("//input[@id=\"Email\"]"), "test+" + timeStamp() + "@gmail.com");

        //type Company Name
        typeText(By.xpath("//input[@id=\"Company\"]"), "ABCOnlineLTD");

        //click on  Newsletter
        clickOnElement(By.xpath("//input[@id=\"Newsletter\"]"));
        //type password
        typeText(By.xpath("//input[@id=\"Password\"]"), "P@ssw0rd");

        //type Confirm Password
        typeText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "P@ssw0rd");

        //click on Register Button
        javaScriptClick(By.xpath("//input[@id=\"register-button\"]"));


        //write expected result
        String expectedText = "Your registration completed";
        //waitUntillElementVisible(By.xpath("//div[@class=\"result\"]"),10);

        // path of expected result
        String actualText = getTextFromElement(By.xpath("//div[@class='result']"));

        //compare expected with actual
        Assert.assertEquals(actualText, expectedText, "expected match with actual");
    }

    @Test
    public void registerUserShouldBeAbelToReferProductSuccessfully() {

        //click on register link, on home page
        clickOnElement(By.linkText("Register"));
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //click on male
        waitUntillElementClickable(By.xpath("//input[@id=\"register-button\"]"), 30);
        clickOnElement(By.xpath("//input[@id=\"gender-male\"]"));

        //type First Name
        typeText(By.xpath("//input[@id=\"FirstName\"]"), "Nick");

        //type Last Name
        typeText(By.xpath("//input[@id=\"LastName\"]"), "Soloman");

        //click on Date of Birth
        clickOnElement(By.xpath("//label[text()=\"Date of birth:\"]"));

        //select Date of BirthDay
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "14");

        //select Date of BirthMonth
        selectFromDropDownByVisiableText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "June");

        //select Date of Year
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "1980");

        //type email id
        typeText(By.xpath("//input[@id=\"Email\"]"), "test+" + timeStamp() + "@gmail.com");

        //type Company Name
        typeText(By.xpath("//input[@id=\"Company\"]"), "ABCOnlineLTD");

        //click on  Newsletter
        clickOnElement(By.xpath("//input[@id=\"Newsletter\"]"));
        //type password
        typeText(By.xpath("//input[@id=\"Password\"]"), "P@ssw0rd");

        //type Confirm Password
        typeText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "P@ssw0rd");

        //click on Register Button
        javaScriptClick(By.xpath("//input[@id=\"register-button\"]"));

        //click on computer
        javaScriptClick(By.xpath("//ul[@class=\"top-menu notmobile\"]/li[1]/a[1]"));

        //click on desktop
        waitUntillElementVisible(By.cssSelector("h2.title>a[href='/desktops']"),30);
        clickOnElement(By.cssSelector("h2.title>a[href='/desktops']"));

        //click on digital Storm custom pc
        waitUntillElementClickable(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"), 30);
        clickOnElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"));

        //click on email a friend button
        waitUntillElementClickable(By.cssSelector("input[value='Email a friend']"), 40);
        javaScriptClick(By.cssSelector("input[value='Email a friend']"));


        //click on send email
        waitUntillElementClickable(By.xpath("//input[@name=\"send-email\"]"),30);

        //type friend email
        typeText(By.xpath("//input[@placeholder=\"Enter friend's email.\"]"), "test+" + timeStamp() + "@gmail.com");

        //type your email address
        typeText(By.xpath("//input[@placeholder=\"Enter your email address.\"]"), "test+" + timeStamp() + "@gmail.com");

        //type personal message
        typeText(By.xpath("//textarea[@name=\"PersonalMessage\"]"), "This is a blast");

        //click on send email
        clickOnElement(By.xpath("//input[@name=\"send-email\"]"));

        //write expected result
        String expectedEmailSentSuccessMessage = "Your message has been sent.";

        //path of expected result
        String actualEmailSentSuccessMessage = getTextFromElement(By.xpath("//div[@class=\"result\"]"));

        //compare expected with actual
        Assert.assertEquals(actualEmailSentSuccessMessage, expectedEmailSentSuccessMessage, "expected match with actual");
    }

    @Test
    public void UserShouldBeAbleToAddProductToBasketSuccessfully() {

        //click on electronics category
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]/li[2]/a[1]"));

        //click on cellphones
        javaScriptClick(By.xpath("//h2[@class=\"title\"]/a[contains(text(),'Cell phones')]"));

        //Click on HTC one Add to cart button
        javaScriptClick(By.xpath("//div[@class=\"item-grid\"]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]"));

        //click  on close
        javaScriptClick(By.cssSelector("span.close"));

        //Click on HTC one mini Add to cart button
        javaScriptClick(By.xpath("//div[@class=\"item-grid\"]/div[2]/div[1]/div[2]/div[3]/div[2]/input[1]"));

        //Click on shopping cart button
        javaScriptClick(By.xpath("//span[@class='cart-label']"));

        //Just wanted to confirm the shopping cart, if products are getting added ot not
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //write expected result
        String expectedShoppingCartText1 = "HTC One M8 Android L 5.0 Lollipop";
        String expectedShoppingCartText2 = "HTC One Mini Blue";

        //path of expected result
        String actualShoppingCartText1 = getTextFromElement(By.xpath("//a[@class='product-name'][contains(text(),'HTC One M8 Android L 5.0 Lollipop')]"));
        String actualShoppingCartText2=getTextFromElement(By.xpath("//a[@class='product-name'][contains(text(),'HTC One Mini Blue')]"));

        //compare expected with actual
        Assert.assertEquals(actualShoppingCartText1, expectedShoppingCartText1, "expected match with actual");
        Assert.assertEquals(actualShoppingCartText2, expectedShoppingCartText2, "expected match with actual");
    }
}