
package com.krce.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class FormValidationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Change URL based on your application
        driver.get("https://www.saucedemo.com/");
    }

    // =========================================================
    // TEST CASE 1
    // Submit login form with empty fields
    // =========================================================

    @Test(priority = 1)
    public void emptyLoginValidation() {

        // Click login without entering username/password
        driver.findElement(By.id("login-button")).click();

        // Capture error message
        WebElement errorMsg =
                driver.findElement(By.cssSelector("h3[data-test='error']"));

        // Validation
        Assert.assertTrue(
                errorMsg.isDisplayed(),
                "Error message is not displayed"
        );

        Assert.assertTrue(
                errorMsg.getText().contains("Username is required"),
                "Incorrect validation message"
        );
    }

    // =========================================================
    // TEST CASE 2
    // Duplicate email registration validation
    // =========================================================

    @Test(priority = 2)
    public void duplicateEmailValidation() {

        // Example registration page URL
        driver.get("https://automationexercise.com/signup");

        // Enter already registered email
        driver.findElement(By.name("name")).sendKeys("TestUser");

        driver.findElement(By.cssSelector("input[data-qa='signup-email']"))
                .sendKeys("testuser@gmail.com");

        // Click signup button
        driver.findElement(By.cssSelector("button[data-qa='signup-button']"))
                .click();

        // Capture duplicate email error
        WebElement duplicateError =
                driver.findElement(By.xpath("//*[contains(text(),'Email Address already exist')]"));

        // Validation
        Assert.assertTrue(
                duplicateError.isDisplayed(),
                "Duplicate email error not displayed"
        );
    }
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}