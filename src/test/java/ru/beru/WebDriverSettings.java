package ru.beru;


import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class WebDriverSettings  {

    public ChromeDriver driver;

    //public JavascriptExecutor js;

    @BeforeEach
    public void setUp() throws IOException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://beru.ru");
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_1ZYDKa22GJ")));
        WebElement x = driver.findElement(By.className("_1ZYDKa22GJ"));
        takeScreenShot(x);
        x.click();
        WebElement but = driver.findElement(By.xpath(
                "//button[@class='_255V0g8dHJ _4qhIn2-ESi _3OWdR9kZRH C2YoejBGGj'][.//span[contains(text(), 'Нет, спасибо')]]"));
        takeScreenShot(but);
        but.click();
    }


    @AfterEach
    public void close() {
        //driver.quit();
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
            System.out.println("f");
            e.printStackTrace();
        }
    }
    @Attachment
    private void highLight(WebElement element) {
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);

    }

    public void logIn(String email, String password){

        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("header2-user")));
        WebElement headerButton = driver.findElement(By.className("header2-user"));
        takeScreenShot(headerButton);
        headerButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("passp-field-login")));
        WebElement inputUsernameField = driver.findElement(By.id("passp-field-login"));
        takeScreenShot(inputUsernameField);
        inputUsernameField.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class=\"control button2 " +
                "button2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")));
        WebElement logInButton = driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l " +
                "button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]"));
        takeScreenShot(logInButton);
        logInButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("passp-field-passwd")));
        WebElement inputPasswordField = driver.findElement(By.id("passp-field-passwd"));
        takeScreenShot(inputPasswordField);
        inputPasswordField.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("passp-field-passwd")));
        driver.findElement(By.id("passp-field-passwd")).sendKeys(password);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]")));
        WebElement loginButton = driver.findElement(By.xpath("//button[@class=\"control button2 button2_view_classic button2_size_l button2_theme_action " +
                "button2_width_max button2_type_submit passp-form-button\"]" +
                "[.//span[contains(text(), 'Войти')]]"));
        takeScreenShot(loginButton);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2__nav")));

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
