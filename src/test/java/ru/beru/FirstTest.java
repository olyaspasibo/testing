package ru.beru;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class FirstTest extends WebDriverSettings {


    @Test
    public void firstTest() {

        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("header2-user")));
        driver.findElement(By.className("header2-user")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("passp-field-login")));
        driver.findElement(By.id("passp-field-login")).sendKeys("o.spasibo2016");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class=\"control button2 b" +
                "utton2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")));
        driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l " +
                "button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("passp-form-field__input")));
        driver.findElement(By.className("passp-form-field__input")).click();


        driver.findElement(By.id("passp-field-passwd")).sendKeys("wegtov-gezwa3-gasfeN");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")));
        driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2__nav")));

        Actions actions = new Actions(driver);


        actions.moveToElement(driver.findElement(By.className("header2__nav"))).build().perform();
        //i.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"header2__nav\"][.//span[@class=" +
                "\"header2-nav-item__text\"]]")));
        System.out.println("wtww");
        WebElement i = driver.findElement(By.xpath("//div[@class=\"header2__nav\"][.//span[@class=" +
                "\"header2-nav-item__text\"]]"));
        System.out.println(i.getText());
        Assert.assertEquals(i.getText(), "Мой профиль");
        //driver.findElement(By.className("header2-user")).

        //WebElement header = driver.findElement(By.className("unique-selling-proposition-line__region"));
        //header.click();
        //WebElement header = driver.findElement(By.xpath("//a[@href='/login?retpath=https%3A%2F%2Fberu.ru%2F%3Fncrnd%3D6984%26loggedin%3D1']"));
        //WebElement header = driver.findElement(By.cssSelector
        //("[href='/login?retpath=https%3A%2F%2Fberu.ru
    }
}