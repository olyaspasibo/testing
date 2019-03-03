package ru.beru;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ThirdTest extends WebDriverSettings {
    @Test
    public void searchInCard() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//button[.//span[contains(text(), 'Каталог товаров')]]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("n-navigation-vertical-category")));
        driver.findElement(By.xpath("//a[@data-id=\"77088\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("catalog-menu__item")));
        driver.findElement(By.xpath("//a" +
                "[@href=\"/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=fr_ctlg\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]")));
        WebElement in = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]"));
        in.click();
        in.sendKeys("999");
        WebElement out = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1f2usTwyAs\"]"));
        out.click();
        out.sendKeys("1999");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1PQIIOelRL")));

        List <WebElement> r = driver.findElements(By.xpath("//button[@class=\"_4qhIn2-ESi _3OWdR9kZRH THqSbzx07u\"]"));
        System.out.println(r.size());

    }
}
