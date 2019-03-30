package ru.beru;


import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondTest extends WebDriverSettings{
    @Test
    @DisplayName("Change city")
    @Description("Check the city is applied to the personal page")
    public void changeCity() {
        WebDriverWait wait = new WebDriverWait(driver, 8);

        WebElement itemRegion = driver.findElement(By.xpath("//span[contains(text(), 'Регион')]/span"));
        takeScreenShot(itemRegion);
        itemRegion.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@class=\"input__control\"]")));
        WebElement city = driver.findElement(By.xpath("//input[@class=\"input__control\"]"));
        takeScreenShot(city);

        city.click();
        city.sendKeys("Хвалынск");


        //Choose "Хвалынск" in the city recommendations' list
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//div[@class=\"region-suggest__list-item suggestick-list__item suggest2-item " +
                "suggest2-item_type_text\"]")));
        WebElement newCity = driver.findElement(By.xpath("//div[@class=\"region-suggest__list-item suggestick-list__item suggest2-item " +
                "suggest2-item_type_text\"][.//strong[contains(text(), 'Хвалынск')]]"));
        takeScreenShot(newCity);
        newCity.click();


        WebElement continueWithNewRegionButton = driver.findElement(By.xpath("//button[@type=\"submit\"]" +
                "[.//span[contains(text(), 'Продолжить с новым регионом')]]"));
        takeScreenShot(continueWithNewRegionButton);
        continueWithNewRegionButton.click();


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("header-search")));
        WebElement cityField =  driver.findElement(By.cssSelector("span.region-form-opener>span>span"));

        //Check the city field was change to "Хвалынск"
        assertEquals(cityField.getText(), "Хвалынск");

        logIn("o.spasibo2016", "wegtov-gezwa3-gasfeN");

        WebElement headerItem = driver.findElement(By.className("header2__nav"));
        takeScreenShot(headerItem);
        headerItem.click();

        // Go to personal account
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href=\"/my/settings?track=menu\"]")));
        WebElement personProfileItem = driver.findElement(By.xpath("//a[@href=\"/my/settings?track=menu\"]"));
        takeScreenShot(personProfileItem);
        personProfileItem.click();

        //Check the city field was changed here
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("personal")));
        WebElement cityFieldInProfile = driver.findElement(By.xpath("//h2[@class=\"n-headline__content title title_size_20\"]" +
                "[.//span[@class=\"link__inner\"]]"));
        assertEquals(cityFieldInProfile.getText(), "Ваш город Хвалынск");

    }
}
