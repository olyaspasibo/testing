package page_object_classes;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BaseCatalogPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By inputPriceTextbox = By.xpath("//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]");
    private By outputPriceTextbox = By.xpath("//input[@class=\"_2yK7W3SWQ- _1f2usTwyAs\"]");
    private By showMoreItems = By.cssSelector("div.n-pager-more__button>a");
    private By itemElements = By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz._3-NzCCibhA.AJD1X1j5bE");
    private By addToTheCartButton = By.className("header2-nav-item__icon_type_cart");
    private By itemPrice = By.cssSelector("span._1JLs4_hnVR>span:first-child");
    private By addItemButton = By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA");
    private By freeDeliveryText = By.cssSelector("b.voCFmXKfcL");
    private By brushes = By.xpath("//div[@data-zone-name='cartButton']//button");
    private By headerButton = By.className("header2-nav__user");
    private By inputUsernameField = By.id("passp-field-login");
    private By logInButton = By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l " +
            "button2_theme_action " +
            "button2_width_max button2_type_submit passp-form-button\"]" +
            "[.//span[contains(text(), 'Войти')]]");
    private By inputPasswordField = By.id("passp-field-passwd");
    private By itemCatalog = By.xpath("//button[.//span[contains(text(), 'Каталог товаров')]]");
    private By sectionBeautyAndHygiene = By.xpath("//a[@data-id=\"77088\"]");
    private By electricBrushesSection = By.xpath("//a" +
            "[@href=\"/catalog/elektricheskie-zubnye-shchetki/80961/list?hid=278374&track=fr_ctlg\"]");

    public BaseCatalogPage (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sendKeysTo(WebElement input, String st) {
        input.click();
        input.sendKeys(st);
    }

    @Step("Loging in")
    public void logIn(String email, String password) {
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

    @Step("Set up price range")
    public void setUpPriceRange(String in, String out) {
        this.sendKeysTo(driver.findElement(inputPriceTextbox), in);
        this.sendKeysTo(driver.findElement(outputPriceTextbox), out);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1PQIIOelRL")));
    }

    @Step("Show all items")
    public  void showAllItems(){
        while(driver.findElement(showMoreItems).isDisplayed())
            driver.findElement(showMoreItems).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'В корзину')]")));
    }

    @Step("Create list of items and check price range is correct")
    public void createListOfItemsAndCheckPriceRangeCorrect(){
        List<WebElement> listOfItems =  driver.findElements(itemElements);
        // Check that items' price is in correct diapason
        for (int i = 1; i < listOfItems.size(); i++){
            String s = listOfItems.get(i).getText();
            String price = s.substring(0, s.length() - 2).replaceAll("\\s+","");
            Assert.assertTrue(Integer.parseInt(price) < 1999);
            Assert.assertTrue(Integer.parseInt(price) > 999);
        }
    }

    @Step("Add penultimate item into cart and go to the cart")
    public void addPenultimateItemToCartAndGoToTheCart(){
        List <WebElement> toothbrushesList = driver.findElements(brushes);
        WebElement penultimateToothbrush = toothbrushesList.get(toothbrushesList.size() - 2);
        penultimateToothbrush.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.header2-nav-item__icon_type_cart")));
        driver.findElement(addToTheCartButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти к оформлению')]")));

    }

    @Step("Add item into cart until free delivery")
    public void addItemToTheCartUntilFreeDelivery() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div._1D9zRlTN8r")));
        Integer price  = Integer.parseInt(driver.findElement(itemPrice).getText().replaceAll("\\s+",
                ""));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A" +
                "._18c2gUxCdP._3hWhO4rvmA")));
        Integer priceDeltaUntillFreeDelivery = 2999 - price;
        while (priceDeltaUntillFreeDelivery > 0) {
            driver.findElement(addItemButton).click();
            priceDeltaUntillFreeDelivery -= price;
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//b[contains(text()," +
                "'бесплатную доставку')]")));
        Assert.assertEquals(driver.findElement(freeDeliveryText).getAttribute("textContent"),
                "бесплатную доставку");
    }

    @Step("Go to electric brushes catalog")
    public void goToElectricBrushesCatalog() {
        driver.findElement(itemCatalog).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("n-navigation-vertical-category")));
        driver.findElement(sectionBeautyAndHygiene).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("catalog-menu__item")));
        driver.findElement(electricBrushesSection).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]")));
    }

}
