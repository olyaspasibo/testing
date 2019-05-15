package ru.beru;


import io.qameta.allure.Description;
import org.testng.annotations.Test;

import page_object_classes.BaseCatalogPage;
import settings.WebDriverSettings;


public class ThirdTest extends WebDriverSettings {

    @Test
    @Description("Check that delivery is free. Add to the cart. Check again.")

    public void searchInCart() {

        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki/79832/list?hid=278374&track=fr_ctlg");
        BaseCatalogPage baseCatalogPage = new BaseCatalogPage(driver, wait);

        // Set up the price range
        baseCatalogPage.setUpPriceRange("999", "1999");

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.n-pager-more__button")));
        //driver.findElement(By.cssSelector("div.n-pager-more__button")).click();
        baseCatalogPage.showAllItems();

        // Create list for all brush items. List consist of buttons "Add to the cart"
        baseCatalogPage.createListOfItemsAndCheckPriceRangeCorrect();


        // Add penultimate toothbrush to the cart
        baseCatalogPage.addPenultimateItemToCartAndGoToTheCart();

        baseCatalogPage.addItemToTheCartUntilFreeDelivery();

        //assertTrue(driver.findElement(By.xpath("//b[contains(text(),'бесплатную доставку')]")));
        //String freeDelivery = driver.findElement(By.cssSelector("._3yDgi6ylNe>span")).getAttribute("textContent");
        //assertEquals(freeDelivery, "Поздравляем! Вы получили бесплатную доставку на ваш заказ");


    }
}

