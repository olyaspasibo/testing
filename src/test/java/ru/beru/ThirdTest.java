package ru.beru;



import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ThirdTest extends WebDriverSettings {
    @Test
    @DisplayName("Delivery pressing test")
    @Description("Check that delivery is free. Add to the cart. Check again.")
    public void searchInCard() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // открываем каталог товаров
        driver.findElement(By.xpath("//button[.//span[contains(text(), 'Каталог товаров')]]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("n-navigation-vertical-category")));

        // переходим в раздел "Красота и гигиена"
        driver.findElement(By.xpath("//a[@data-id=\"77088\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("catalog-menu__item")));

        // переходим в раздел Электрических зубных щеток
        driver.findElement(By.xpath("//a" +
                "[@href=\"/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=fr_ctlg\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]")));

        // Вводим значения ценового диапазона: 999 - 1999
        WebElement in = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]"));
        in.click();
        in.sendKeys("999");
        WebElement out = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1f2usTwyAs\"]"));
        out.click();
        out.sendKeys("1999");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1PQIIOelRL")));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.n-pager-more__button")));
        //driver.findElement(By.cssSelector("div.n-pager-more__button")).click();


        // Создаем список цен зубных щеток
        List <WebElement> new_p =  driver.findElements(By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz._3-NzCCibhA.AJD1X1j5bE"));

        // проверяем, что цена каждой щетки находится в заданном диапазоне
        for (int i = 1; i < new_p.size(); i++){
            String s = new_p.get(i).getText();
            String price_string = s.substring(0, s.length() - 2);
            price_string = price_string.replaceAll("\\s+","");
            //System.out.printf(price_string + "\n");
            assertTrue(Integer.parseInt(price_string) < 1999);
            assertTrue(Integer.parseInt(price_string) > 999);
        }

        // Добавляем в корзину предпоследнюю щетку
        List <WebElement> r = driver.findElements(By.xpath("//div[@data-zone-name='cartButton']//button"));
        r.get(r.size() - 2).click();

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a.header2-nav-item_type_cart")));
        //driver.findElement(By.cssSelector("a.header2-nav-item_type_cart")).click();
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1sjxYfIabK _26mXJDBxtH")));
        //driver.findElement(By.className("_1sjxYfIabK _26mXJDBxtH")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти в корзину')]")));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div._3UjOWy-LbN")));

        //driver.findElement(By.cssSelector("button._4qhIn2-ESi.qAmx3n7Iqk.THqSbzx07u._39B7yXQbvm")).click();

        driver.findElement(By.cssSelector("button._4qhIn2-ESi.qAmx3n7Iqk.THqSbzx07u._39B7yXQbvm")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти к оформлению')]")));
        //WebElement delivery = driver.findElement(By.cssSelector("span.voCFmXKfcL"));
        WebElement item_price = driver.findElement(By.cssSelector("span._2CJTlO2WW_"));

        String s = item_price.getText();

        String price_string = s.substring(0, s.length() - 2);
        price_string = price_string.replaceAll("\\s+","");
        Integer price = Integer.parseInt(price_string);

        // System.out.println(price);
        // итоговая цена = стоимость щетки + доставка
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A" +
                "._18c2gUxCdP._3hWhO4rvmA")));
        Integer a = 2999 / price;
        WebElement but = driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA"));
        while (a > 0) {
            but.click();
            a -= price;
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'бесплатно')]")));

        //Assert.ass
        //driver.findElement(By.cssSelector("span.personal-counter_provider_cart>span>span")).click();


    }
}
