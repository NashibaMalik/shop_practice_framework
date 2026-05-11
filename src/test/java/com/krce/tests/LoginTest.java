package com.krce.tests;

import com.krce.base.BaseTest;

import com.krce.config.ConfigReader;

import com.krce.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test

    public void verifyValidLogin() {

        ConfigReader config =
                new ConfigReader();

        LoginPage lp =
                new LoginPage(driver);

        lp.login(
                config.getEmail(),
                config.getPassword());

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("dashboard"));
    }
}
