package ru.beru;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import settings.WebDriverSettings;


public class ChangeCityTest extends WebDriverSettings {
    @DataProvider(name = "SetUpCity")
    public static Object[][] citySetUp() {
        return new Object[][] {
                {"Хвалынск"},
                {"Самара"},
                {"Ялта"},
                {"Урус-Мартан"}};
    }

    @Test(dataProvider = "SetUpCity")
    @Description("Check the city is applied to the personal page")
    public void changeCity(String city_test) {
        StartPage pageObject = new StartPage(driver, wait);
        pageObject.changeProperty_CityName(city_test);
        pageObject.checkCityValue(city_test);
        pageObject.logIn("o.spasibo2016", "wegtov-gezwa3-gasfeN");
        pageObject.goToPersonalAccount();
        pageObject.checkCityValue(city_test);
    }
}
