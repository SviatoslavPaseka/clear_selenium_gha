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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SelenoidTest {
    @BeforeTest
    public void intro(){
        System.out.println("Starting test");
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public String url = "https://rozetka.com.ua/ua/";

    @Test
    public void testTitleText() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", false);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.get(url);

        WebElement searchField = driver.findElement(By.xpath("//input[@name='search']"));
        System.out.println("Title: " + searchField.getAttribute("placeholder"));

        Assert.assertTrue(searchField.isDisplayed(),  "Search field are not displayed");
//        Assert.assertEquals(searchField.getAttribute("placeholder"),"Я шукаю...",  "Text in search field is wrong");
        driver.quit();
    }
}
