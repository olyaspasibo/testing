package settings;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import page_object_classes.PageObject;
import org.testng.TestListenerAdapter;

public class CustomTestListener extends TestListenerAdapter implements StepLifecycleListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = PageObject.driver;
        TakeScreenshot.makeScreenshot(driver);
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void beforeStepStop(StepResult result) {
        WebDriver driver = PageObject.driver;
        TakeScreenshot.makeScreenshot(driver);
    }
}
