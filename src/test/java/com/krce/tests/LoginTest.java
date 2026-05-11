package com.krce.tests;

import com.krce.base.BaseTest;
import com.krce.config.ConfigReader;
import com.krce.pages.DashboardPage;
import com.krce.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    ConfigReader config = new ConfigReader();

    // Test Case 1
    @Test

    public void verifyValidLogin() {

        LoginPage lp = new LoginPage(driver);

        lp.login(config.getEmail(), config.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("dashboard"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());
    }

    // Test Case 2
    @Test

    public void verifyInvalidLogin() {

        LoginPage lp = new LoginPage(driver);

        lp.login(config.getEmail(), "WrongPassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("[class*='flyInOut']")));

        String error = lp.getErrorMessage();

        System.out.println(error);

        Assert.assertTrue(error.contains("Incorrect"));
    }

    // Test Case 3
    @Test

    public void verifyLogout() {

        LoginPage lp = new LoginPage(driver);

        lp.login(config.getEmail(), config.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("dashboard"));

        DashboardPage dp = new DashboardPage(driver);

        dp.logout();

        Assert.assertTrue(dp.isLoginPageDisplayed());
    }

    // Test Case 4
    @Test
    public void verifyEmptyLogin() {

        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Your username is invalid!')]")
                ));

        Assert.assertTrue(errorMessage.isDisplayed());
    }
}