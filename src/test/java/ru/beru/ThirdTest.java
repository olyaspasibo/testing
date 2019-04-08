package ru.beru;



import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ThirdTest extends WebDriverSettings {
    @Test
    @DisplayName("Delivery pressing test")
    @Description("Check that delivery is free. Add to the cart. Check again.")
    public void searchInCart() {
        WebDriverWait wait = new WebDriverWait(driver, 8); //Pavel: to parent class
        
        // Pavel: please remove the commmented code bellow
//
//        // Open items' catalog
//        WebElement itemsCatalog = driver.findElement(By.xpath("//button[.//span[contains(text(), 'Каталог товаров')]]"));
//        takeScreenShot(itemsCatalog);
//        itemsCatalog.click();
//
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("n-navigation-vertical-category")));
//        // Go to "Beauty And Hygiene" section
//        WebElement sectionOfBeautyAndHygiene = driver.findElement(By.xpath("//a[@data-id=\"77088\"]"));
//        takeScreenShot(sectionOfBeautyAndHygiene);
//        sectionOfBeautyAndHygiene.click();
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("catalog-menu__item")));
//
//        // Go to "Electronic Toothbrushes" section
//        WebElement sectionOfElectronicToothbrushes = driver.findElement(By.xpath("//a" +
//                "[@href=\"/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=fr_ctlg\"]"));
//        takeScreenShot(sectionOfElectronicToothbrushes);
//        sectionOfElectronicToothbrushes.click();
//
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
//                "//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]")));

    
        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki/79832/list?hid=278374&track=fr_ctlg"); //Pavel: this step too tricky)

        // Set up the price range
        WebElement in = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]")); //Pavel: do not use such classes because these ones can be generate dinamicly
        takeScreenShot(in);
        in.click();
        in.sendKeys("999");
        WebElement out = driver.findElement(By.xpath("//input[@class=\"_2yK7W3SWQ- _1f2usTwyAs\"]"));
        takeScreenShot(out);
        out.click();
        out.sendKeys("1999");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1PQIIOelRL")));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.n-pager-more__button")));
        //driver.findElement(By.cssSelector("div.n-pager-more__button")).click();

        WebElement showMoreItems = driver.findElement(By.cssSelector("div.n-pager-more__button>a"));
        while(showMoreItems.isDisplayed())
            showMoreItems.click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'В корзину')]")));
        // Create list for all brush items. List consist of buttons "Add to the cart"
        List <WebElement> new_p =  driver.findElements(By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz._3-NzCCibhA.AJD1X1j5bE")); // Pavel: new_p - it is not  good name for variable (check code conventions)

        // Check that items' price is in correct diapason
        for (int i = 1; i < new_p.size(); i++){
            String s = new_p.get(i).getText();
            String price_string = s.substring(0, s.length() - 2);
            price_string = price_string.replaceAll("\\s+","");
            assertTrue(Integer.parseInt(price_string) < 1999);
            assertTrue(Integer.parseInt(price_string) > 999);
        }

        // Add penultimate toothbrush to the cart
        List <WebElement> toothbrushesList = driver.findElements(By.xpath("//div[@data-zone-name='cartButton']//button"));
        WebElement penultimateToothbrush = toothbrushesList.get(toothbrushesList.size() - 2);
        takeScreenShot(penultimateToothbrush);
        penultimateToothbrush.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'В корзине')]")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти в корзину')]")));

        WebElement toCartButton = driver.findElement(By.cssSelector("button._4qhIn2-ESi.qAmx3n7Iqk.THqSbzx07u._39B7yXQbvm"));
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("header2-nav-item__icon_type_cart")));
        //WebElement toCartButton = driver.findElement(By.cssSelector("header2-nav-item__icon_type_cart"));
        takeScreenShot(toCartButton);
        toCartButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти к оформлению')]")));
        WebElement item_price = driver.findElement(By.cssSelector("span._2CJTlO2WW_"));

        String s = item_price.getText();

        String price_string = s.substring(0, s.length() - 2);
        price_string = price_string.replaceAll("\\s+","");
        Integer price = Integer.parseInt(price_string);


        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A" +
                "._18c2gUxCdP._3hWhO4rvmA")));
        Integer a = 2999 - price;
        WebElement addItemButton = driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA"));
        while (a > 0) {
            //takeScreenShot(addItemButton);
            addItemButton.click();
            a -= price;
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//b[contains(text(),'бесплатную доставку')]")));
        String freeDelivery = driver.findElement(By.cssSelector("._3yDgi6ylNe>span")).getAttribute("textContent");
        assertEquals(freeDelivery, "Поздравляем! Вы получили бесплатную доставку на ваш заказ");


    }
}
