package com.krce.pages;

import com.krce.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    By products =
            By.cssSelector(".card-body");

    public int getProductCount() {

        return driver.findElements(products)
                .size();
    }
}
