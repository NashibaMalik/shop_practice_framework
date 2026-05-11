package com.krce.base;

import com.krce.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;

    ConfigReader config = new ConfigReader();

    @BeforeMethod

    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get(config.getUrl());
    }

    @AfterMethod

    public void tearDown() {

        driver.quit();
    }
}