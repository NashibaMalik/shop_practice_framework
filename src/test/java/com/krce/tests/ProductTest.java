package com.krce.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductTest {

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
    }



    @Test
    public void verifyProductDashboardLoads() {

        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void verifyProductNameAndPrice() {

        List<WebElement> productCards = driver.findElements(By.className("inventory_item"));

        for (WebElement product : productCards) {

            WebElement productName = product.findElement(By.className("inventory_item_name"));

            WebElement productPrice = product.findElement(By.className("inventory_item_price"));

            Assert.assertTrue(productName.isDisplayed());

            Assert.assertTrue(productPrice.isDisplayed());
        }
    }

    @Test
    public void verifyAddProductToCart() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        Assert.assertEquals(cartBadge.getText(), "1");
    }


    @Test
    public void verifyMultipleProductsAddToCart() {

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

        List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]"));

        addButtons.get(0).click();
        addButtons.get(1).click();
        addButtons.get(2).click();

        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();

        Assert.assertEquals(cartCount, "3");
    }
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}