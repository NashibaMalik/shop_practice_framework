package com.krce.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class OrderHistoryTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @Test
    public void createOrder() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("600001");

        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        Assert.assertEquals(msg.getText(), "Thank you for your order!");
    }


    @Test
    public void verifyOrderConfirmation() {

        createOrder();

        WebElement orderMsg = driver.findElement(By.className("complete-header"));

        Assert.assertTrue(orderMsg.isDisplayed());
        Assert.assertEquals(orderMsg.getText(), "Thank you for your order!");
    }


    @Test
    public void verifyOrderHistoryLikeBehavior() {

        createOrder();

        WebElement backHome = driver.findElement(By.id("back-to-products"));
        backHome.click();

        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}