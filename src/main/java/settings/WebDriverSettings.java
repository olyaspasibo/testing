package settings;


import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class WebDriverSettings  {

    public ChromeDriver driver;
    public WebDriverWait wait;

    //public JavascriptExecutor js;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://beru.ru");
        wait = new WebDriverWait(driver, 8);
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1ZYDKa22GJ")));
        //WebElement x = driver.findElement(By.xpath("//button[.//span]"));
        //takeScreenShot(x);
        //x.click();
        WebElement but = driver.findElement(By.xpath(
                "//button[@class='_255V0g8dHJ _4qhIn2-ESi _3OWdR9kZRH C2YoejBGGj'][.//span[contains(text(), 'Нет, спасибо')]]"));
        takeScreenShot(but);
        but.click();
    }


    @AfterMethod
    public void close() {

        driver.quit();
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


//

//    @Attachment
//    public void takeScreenShot1(WebElement element) throws IOException {
//        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        Point p = element.getLocation();
//        Dimension dimesions = element.getSize();
//        Rectangle rect = new Rectangle(0, 0, dimesions.getWidth(), dimesions.getHeight());
//        BufferedImage img = ImageIO.read(screen);
//        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
//        ImageIO.write(dest, "png", screen);
//        FileUtils.copyFile(screen, new File("screenshot" + System.nanoTime() + ".png"));
//    }


}
