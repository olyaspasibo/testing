package ru.beru;


import io.qameta.allure.Description;
import org.testng.annotations.Test;

import page_object_classes.BaseCatalogPage;
import settings.WebDriverSettings;


public class BuyToothbrushTest extends WebDriverSettings {

    @Test
    @Description("Check that delivery is free. Add to the cart. Check again.")

    public void searchInCart() {

        BaseCatalogPage baseCatalogPage = new BaseCatalogPage(driver, wait);

        baseCatalogPage.logIn("o.spasibo2016", "wegtov-gezwa3-gasfeN");

        driver.get("https://beru.ru/catalog/elektricheskie-zubnye-shchetki/79832/list?hid=278374&track=fr_ctlg");


        baseCatalogPage.setUpPriceRange("999", "1999");

        baseCatalogPage.showAllItems();

        baseCatalogPage.createListOfItemsAndCheckPriceRangeCorrect();

        baseCatalogPage.addPenultimateItemToCartAndGoToTheCart();

        baseCatalogPage.addItemToTheCartUntilFreeDelivery();



    }
}
