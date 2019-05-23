package settings;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        makeText("Screenshot on test failure");
        makeScreenshot();
    }

    @Attachment(value = "Screenshot information")
    private static String makeText(String text){
        return text;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverSettings.driver).getScreenshotAs(OutputType.BYTES);
    }
}