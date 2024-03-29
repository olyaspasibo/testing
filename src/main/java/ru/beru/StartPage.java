package ru.beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StartPage {
    public static WebDriver driver;
    private WebDriverWait wait;
    private By headerButton = By.className("header2-nav__user");
    private By inputUsernameField = By.id("passp-field-login");
    private By logInButton = By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l " +
            "button2_theme_action " +
            "button2_width_max button2_type_submit passp-form-button\"]" +
            "[.//span[contains(text(), 'Войти')]]");
    private By inputPasswordField = By.id("passp-field-passwd");
    private By profileItem = By.xpath("//div[@class='header2-nav__user']//span[@title]");
    private By itemRegion = By.xpath("//span[contains(text(), 'Регион')]/span");
    private By city = By.xpath("//input[@class=\"input__control\"]");
    private By newCity = By.xpath("//div[@class=\"region-suggest__list-item suggestick-list__item suggest2-item " +
            "suggest2-item_type_text\"][.//strong]");
    private By continueWithNewRegionButton = By.xpath("//button[@type=\"submit\"]" +
            "[.//span[contains(text(), 'Продолжить с новым регионом')]]");
    private By personProfileItem = By.xpath("//a[@href=\"/my/settings?track=menu\"]");
    private By headerItem = By.className("header2__nav");

    public StartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sendKeysTo(WebElement input, String st) {
        input.click();
        input.sendKeys(st);
    }

    @Step("Check profile title")
    public void checkMyProfileTitle(String profileName) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(headerButton)));
        driver.findElement(headerButton).click();
        // Check that profile attribute was changed to "My profile"
        Assert.assertEquals( driver.findElement(profileItem).getAttribute("title"), profileName);
    }

    @Step("Loging in")
    public void logIn(String email, String password){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("header2-user")));
        driver.findElement(headerButton).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(inputUsernameField)));
        driver.findElement(inputUsernameField).sendKeys(email);
        driver.findElement(logInButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
        this.sendKeysTo(driver.findElement(inputPasswordField), password);
        driver.findElement(logInButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2-nav__user")));
    }

    @Step("Change city-name property")
    public void changeProperty_CityName(String cityTest) {
        driver.findElement(itemRegion).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@class=\"input__control\"]")));
        this.sendKeysTo(driver.findElement(city), cityTest);
        //Choose expected city in the city recommendations' list
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//div[@class=\"region-suggest__list-item suggestick-list__item suggest2-item " +
                        "suggest2-item_type_text\"]")));
        driver.findElement(newCity).click();
        driver.findElement(continueWithNewRegionButton).click();
        driver.navigate().refresh();
    }

    @Step("Go to Personal account")
    public void goToPersonalAccount(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(headerItem)));
        driver.findElement(headerItem).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                ("//a[@href=\"/my/settings?track=menu\"]")));
        driver.findElement(personProfileItem).click();
    }

    @Step("Check city-name property")
    public void checkCityValue(String city) {
        WebElement i = driver.findElement(By.className("link_pseudo_yes"));
        Assert.assertTrue(i.getText().contains(city));
    }
}
