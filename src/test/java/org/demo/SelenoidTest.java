package org.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelenoidTest {
    public WebDriver driver;

    public String url = "https://rozetka.com.ua/ua/";

    @Test
    public void testTitleText() {

//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//
//        options.addArguments("--disable-dev-shm-usage");
//
//        options.addArguments("--headless");
//
//        driver = new ChromeDriver(options);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.get(url);

        String title = driver.getTitle();

        final String WORD = "razor";

        System.out.println("title: " + title);
        System.out.println("url from driver: " + driver.getCurrentUrl());

        WebElement webElement = driver.findElement(By.xpath("//*[@name='search']"));
        webElement.sendKeys(WORD);

//        System.out.println("value in field: " + webElement.getAttribute("value"));

        Assert.assertEquals(title, "Інтернет-магазин ROZETKA™: офіційний сайт найпопулярнішого онлайн-гіпермаркету в Україні", "title is not correct");
        Assert.assertEquals(webElement.getAttribute("value"), WORD, "value in input field is not equals passed value");

        webElement.clear();
        webElement.sendKeys(WORD + WORD);

        Assert.assertEquals(webElement.getAttribute("value"), WORD + WORD, "value in input field is not equals passed value");

        driver.close();
    }
}