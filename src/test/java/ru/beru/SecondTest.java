package ru.beru;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondTest extends WebDriverSettings{
    @Test
    public void changeCity() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //driver.findElement(By.className("unique-selling-proposition-line__item")).click();
        //driver.findElement(By.id("uniq5362270749")).click();
        //driver.findElement(By.id("uniq2235017945")).sendKeys("Хвалынск");
        //driver.findElement(By.xpath("//button[@class=\"button2 button2_size_xl button2_theme_action " +
         //       "button2_width_max region-select-form__continue-with-new i-bem button2_js_inited\"]" +
           //     "[.//span[contains(text(), 'Продолжить с новым регионом')]]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Регион')]/span")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@class=\"input__control\"]")));
        WebElement city = driver.findElement(By.xpath("//input[@class=\"input__control\"]"));
        city.click();
        city.sendKeys("Хвалынск");
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"region-suggest__list-item " +
                "suggestick-list__item suggest2-item " +
                "suggest2-item_type_text\"][.//strong[contains(text(), 'Хвалынск')]]")));
        driver.findElement(By.xpath("//div[@class=\"region-suggest__list-item suggestick-list__item suggest2-item " +
                "suggest2-item_type_text\"][.//strong[contains(text(), 'Хвалынск')]]")).click();

        driver.findElement(By.xpath("//button[@type=\"submit\"][.//span[contains(text(), 'Продолжить с новым регионом')]]")).click();

        //для того чтобы убедиться, что страница перезагрузилась, привяжемся к полю вводу названий товаров
        //элемента нет на предыдущей странице, на новой он уникален и имеет id("header-search")
        // нельзя привязаться к элементу Регион-город - element is not attached to the page document
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("header-search")));
        //

        WebElement i =  driver.findElement(By.xpath("//span[@class=\"link__inner\"]"));
        //проверили, что город изменился на Хвалынск
        //Assert.assertTrue(i.getText().contains("Хвалынск"));
        String s = i.getText();
        //System.out.println(s);

        Assert.assertTrue(s.equals("Хвалынск"));
        //авторизация на сайте
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


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("header2__nav")));
        //
        driver.findElement(By.className("header2__nav")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@href=\"/my/settings?track=menu\"]")));
        driver.findElement(By.xpath("//a[@href=\"/my/settings?track=menu\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("personal")));

        WebElement d = driver.findElement(By.xpath("//h2[@class=\"n-headline__content title title_size_20\"]" +
                "[.//span[@class=\"link__inner\"]]"));
        String s2 = d.getText();
        Assert.assertEquals(s2, "Ваш город Хвалынск");

    }
}
