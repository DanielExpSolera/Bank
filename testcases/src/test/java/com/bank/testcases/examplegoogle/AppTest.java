package com.bank.testcases.examplegoogle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    private WebDriver driver;
    private Map<String, Object> vars;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        vars = new HashMap<String, Object>();
    }
    @After
    public void quit() {
        driver.quit();
    }
    @Test
    public void testGooglePlayStoreButton() {
        WebElement buttonGoogleApps = driver.findElement(By.cssSelector("a[aria-label='Google apps']"));
        WebElement buttonPlayStore = driver.findElement(By.cssSelector(""));
        String urlPlayStore = "https://play.google.com/";

        driver.get("https://google.com");
        buttonGoogleApps.click();
        buttonPlayStore.click();

        Assert.assertEquals(urlPlayStore, driver.getCurrentUrl());
    }
}
