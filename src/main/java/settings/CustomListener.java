package settings;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;


public class CustomListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        makeText("Screenshot before click");
        makeScreenshot();
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        makeText("Screenshot after click");
        makeScreenshot();
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend) {
        makeText("Screenshot after change value");
        makeScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverSettings.driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Screenshot information")
    private static String makeText(String text) {
        return text;
    }
}