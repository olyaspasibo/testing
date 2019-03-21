package ru.beru;


import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings  {

    public ChromeDriver driver;


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://beru.ru/?ncrnd=9341");
        WebElement x = driver.findElement(By.className("_1ZYDKa22GJ"));
        x.click();
        WebElement but = driver.findElement(By.xpath(
                "//button[@class='_255V0g8dHJ _4qhIn2-ESi _3OWdR9kZRH C2YoejBGGj'][.//span[contains(text(), 'Нет, спасибо')]]"));
        but.click();
    }


    @AfterEach
    public void close() {
        //driver.quit();
    }

//    @Attachment
//    public Byte[] performedActions(WebElement element) {
//
//        //return element.toString();
//    }
}
