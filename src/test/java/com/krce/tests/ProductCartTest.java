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
public class ProductCartTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");


        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
    }

    @Test(priority = 1)
    public void verifyProductsAppearInCart() {

        driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(0).click();

        driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(1).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));

        Assert.assertEquals(cartItems.size(), 2);

        System.out.println("Products displayed in cart successfully");
    }

    @Test(priority = 2)
    public void deleteProductFromCart() {

        driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(0).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        Assert.assertEquals(cartItems.size(), 0);

        System.out.println("Product removed successfully");
    }

    @Test(priority = 3)
    public void verifyTotalPrice() {

        driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(0).click();

        driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(1).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));

        double total = 0;

        for (WebElement price : prices) {

            String amount = price.getText().replace("$", "");

            total = total + Double.parseDouble(amount);
        }

        System.out.println("Total Cart Price = " + total);

        Assert.assertTrue(total > 0);
    }

    @Test
    public void checkoutSuccessfully() {

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("600001");

        driver.findElement(By.id("continue")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        Assert.assertEquals(successMsg.getText(), "Thank you for your order!");
    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}