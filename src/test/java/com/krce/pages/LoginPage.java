package com.krce.pages;

import com.krce.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    By email = By.id("userEmail");

    By password = By.id("userPassword");

    By loginButton = By.id("login");

    By errorMessage =
            By.cssSelector("[class*='flyInOut']");

    public void login(String user, String pass) {

        driver.findElement(email).clear();

        driver.findElement(password).clear();

        driver.findElement(email).sendKeys(user);

        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginButton).click();
    }

    public void clickLoginButton() {

        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {

        return driver.findElement(errorMessage).getText();
    }
}