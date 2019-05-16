package ru.beru;


import io.qameta.allure.Description;
import org.testng.annotations.Test;

import page_object_classes.StartPage;
import settings.WebDriverSettings;

public class LogInTest extends WebDriverSettings {

    @Test
    @Description("Check that logging in is correct with correct password and username")
    public void firstTest() {

        StartPage pageObject = new StartPage(driver, wait);

        pageObject.logIn("o.spasibo2016", "wegtov-gezwa3-gasfeN");

        //Go to personal account
        //takeScreenShot(headerItem);
        pageObject.checkMyProfileTitle("Мой профиль");
    }
}