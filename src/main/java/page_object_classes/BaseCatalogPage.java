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
    public WebDriverWait wait;
    private By inputPriceTextbox = By.xpath("//input[@class=\"_2yK7W3SWQ- _1d02bPcWht\"]");
    private By outputPriceTextbox = By.xpath("//input[@class=\"_2yK7W3SWQ- _1f2usTwyAs\"]");
    private By showMoreItems = By.cssSelector("div.n-pager-more__button>a");
    private By itemElements = By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz._3-NzCCibhA.AJD1X1j5bE");
    private By addToTheCartButton = By.className("header2-nav-item__icon_type_cart");
    private By item_price = By.cssSelector("span._1JLs4_hnVR>span:first-child");
    private By addItemButton = By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA");


    public BaseCatalogPage (WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sendKeysTo(WebElement input, String st) {
        input.click();
        input.sendKeys(st);
    }

    @Step("Set up price range")
    public void setUpPriceRange(String in, String out) {

        //takeScreenShot(in);
        this.sendKeysTo(driver.findElement(inputPriceTextbox), in);
        this.sendKeysTo(driver.findElement(outputPriceTextbox), out);
        //takeScreenShot(out);
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
        List<WebElement> new_p =  driver.findElements(itemElements);

        // Check that items' price is in correct diapason
        for (int i = 1; i < new_p.size(); i++){
            String s = new_p.get(i).getText();
            String price_string = s.substring(0, s.length() - 2);
            price_string = price_string.replaceAll("\\s+","");
            Assert.assertTrue(Integer.parseInt(price_string) < 1999);
            Assert.assertTrue(Integer.parseInt(price_string) > 999);
        }
    }
    @Step("Add penultimate item into cart and go to the cart")
    public void addPenultimateItemToCartAndGoToTheCart(){
        List <WebElement> toothbrushesList = driver.findElements(By.xpath("//div[@data-zone-name='cartButton']//button"));
        WebElement penultimateToothbrush = toothbrushesList.get(toothbrushesList.size() - 2);
        //takeScreenShot(penultimateToothbrush);
        penultimateToothbrush.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'В корзине')]")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти в корзину')]")));

        //WebElement toCartButton = driver.findElement(addToTheCartButton);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.header2-nav-item__icon_type_cart")));
        //driver.findElement(By.className("_3wG1c71598")).click();
        //WebElement toCartButton = driver.findElement(By.cssSelector("header2-nav-item__icon_type_cart"));
        //takeScreenShot(toCartButton);
        driver.findElement(addToTheCartButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), " +
                "'Перейти к оформлению')]")));

    }
    @Step("Add item into cart until free delivery")
    public void addItemToTheCartUntilFreeDelivery() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div._1D9zRlTN8r")));

        String s = driver.findElement(item_price).getText();

        s = s.replaceAll("\\s+","");
        Integer price = Integer.parseInt(s);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A" +
                "._18c2gUxCdP._3hWhO4rvmA")));
        Integer a = 2999 - price;
        while (a > 0) {
            //takeScreenShot(addItemButton);
            driver.findElement(addItemButton).click();
            a -= price;
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//b[contains(text(),'бесплатную доставку')]")));
        WebElement freeDeliveryText = driver.findElement(By.cssSelector("b.voCFmXKfcL"));
        Assert.assertEquals(freeDeliveryText.getAttribute("textContent"), "бесплатную доставку");
    }


}
