package com.bank.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class GoogleTest {
    private WebDriver driver;
    private Map<String, Object> vars;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("lang=en-GB");
        driver = new ChromeDriver(options);
        vars = new HashMap<String, Object>();
    }
    @After
    public void quit() {
       driver.quit();
    }
    private int getIFrameIndexByWebElement(String selector) {
        int numberIFrames = driver.findElements(By.tagName("iframe")).size();
        int isInFrame = 0;
        int frameIndex = -1;
        for (int i = 0; i < numberIFrames; i++)
        {
            // jump to the frame dom
            driver.switchTo().frame(i);
            isInFrame = driver.findElements(By.cssSelector(selector)).size();
            if (frameIndex == -1 && isInFrame > 0)
            {
                frameIndex = i;
            }
            //com back to the default url dom content
        }
        driver.switchTo().defaultContent();
        System.out.println("The number of iframes in page: " + numberIFrames);

       // driver.switchTo().frame(0);
        return frameIndex
    }
    @Test
    public void testGooglePlayStoreButton() {
        String urlPlayStore = "https://play.google.com/store/games?device=windows";

        driver.get("https://google.com");

        WebElement buttonRejectCookies = driver.findElement(By.cssSelector("button[id='W0wltc'][class='tHlp8d']"));
        buttonRejectCookies.click();

        WebElement buttonGoogleApps = driver.findElement(By.cssSelector("a[aria-label='Google apps'].gb_d"));
        buttonGoogleApps.click();

        driver.switchTo().frame("app");
        WebElement buttonPlayStore = driver.findElement(By.cssSelector("#yDmH0d > c-wiz > div > div > c-wiz > div > div > div.v7bWUd > div.o83JEf > div:nth-child(1) > ul > li:nth-child(5) > a"));
        buttonPlayStore.click();
        driver.switchTo().defaultContent();

        Assert.assertEquals(urlPlayStore, driver.getCurrentUrl());
    }
}
