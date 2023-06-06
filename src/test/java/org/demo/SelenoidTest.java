package org.demo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelenoidTest {

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

        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();

        System.out.println("title: " + title);
        System.out.println("currentUrl: " + currentUrl);
        WebElement webElement = driver.findElement(By.id("fat-menu"));
        Assert.assertEquals(title, "Just a moment...", "title is not correct");

        driver.quit();
    }
}