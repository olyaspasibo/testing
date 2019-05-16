package settings;


import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class WebDriverSettings  {

    public static EventFiringWebDriver driver;
    public static WebDriverWait wait;

    private By profileeItem = By.cssSelector("span.header2-nav-item__icon_type_profile");
    private By logOutButton = By.cssSelector("li.header2-user-menu__item_type_logout");

    //public JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.get("https://beru.ru");
        wait = new WebDriverWait(driver, 8);
        CustomListener listener = new CustomListener();
        driver.register(listener);
        WebElement but = driver.findElement(By.xpath(
                "//button[@class='_255V0g8dHJ _4qhIn2-ESi _3OWdR9kZRH C2YoejBGGj'][.//span[contains(text(), 'Нет, спасибо')]]"));
        takeScreenShot(but);
        but.click();
    }


    @AfterMethod
    public void close() {
        logOut();
        driver.quit();
    }

    @Attachment
    public void logOut(){
        driver.findElement(profileeItem).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                ("//a[@href=\"/my/settings?track=menu\"]")));
        driver.findElement(logOutButton).click();



    }

    @Attachment
    public void takeScreenShot(WebElement element) {
        try {
        highLight(element);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = UUID.randomUUID().toString();
        File targetFile = new File("screenshots/" + fileName + ".png");
        FileUtils.copyFile(scrFile, targetFile);
        }
        catch(IOException e) {
            //System.out.println("f");
            e.printStackTrace();
        }
    }
    @Attachment
    private void highLight(WebElement element) {
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);

    }


}
