package com.krce.pages;

import com.krce.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    // Constructor
    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    // Logout button locator
    By logoutButton = By.xpath("//button[text()=' Sign Out ']");

    // Verify dashboard
    public boolean isDashboardDisplayed() {

        return driver.getCurrentUrl().contains("dashboard");
    }

    // Logout method
    public void logout() {

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));

        driver.findElement(logoutButton).click();
    }

    // Verify login page
    public boolean isLoginPageDisplayed() {

        wait.until(ExpectedConditions.urlContains("auth/login"));

        return driver.getCurrentUrl().contains("auth/login");
    }
}