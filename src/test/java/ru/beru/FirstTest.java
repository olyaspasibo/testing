package ru.beru;


import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;



public class FirstTest extends WebDriverSettings {

    @Test
    @DisplayName("Log in the account")
    @Description("Check that logging in is correct with correct password and username")
    public void firstTest() {
        WebDriverWait wait = new WebDriverWait(driver, 8);

        //Authorize in the website
        logIn("o.spasibo2016", "wegtov-gezwa3-gasfeN");  //Pavel: move this values into constants

        //Go to personal account
        WebElement headerItem = driver.findElement(By.className("header2__nav"));
        takeScreenShot(headerItem);  //Pavel: you have to use 2 steps (taking screenshot and clicking element) instead of simple click.
        // therefore you should create seperate function like "makeSreenshotAndClick" to avoid code duplitates
        // also you can use custom webdriver instance with overrided  functions Click() and getAttribute();
        headerItem.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"header2__nav\"]" +
                "[.//span[@class=\"header2-nav-item__text\"]]")));

        WebElement profileItem = driver.findElement(By.xpath("//div[@class='header2-nav__user']//span[@title]"));

        profileItem.getAttribute("title");

        // Check that profile attribute was changed to "My profile"
        assertEquals(profileItem.getAttribute("title"), "Мой профиль");

    }
}
