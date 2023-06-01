package org.demo;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelenoidTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public String url = "https://rozetka.com.ua/ua/";

    @Test
    public void testTitleText() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.get(url);

        WebElement inputField = driver.findElement(By.xpath("//input[@name='search']"));


        Assert.assertTrue(inputField.isDisplayed(), "input field is not displayed");
        inputField.sendKeys("razor");

        Assert.assertTrue(inputField.isDisplayed(), "input field is not displayed");

        driver.quit();
    }
}