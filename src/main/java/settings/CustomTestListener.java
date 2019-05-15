package settings;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import page_object_classes.StartPage;
import org.testng.TestListenerAdapter;

public class CustomTestListener extends TestListenerAdapter implements StepLifecycleListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = StartPage.driver;
        TakeScreenshot.makeScreenshot(driver);
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void beforeStepStop(StepResult result) {
        WebDriver driver = StartPage.driver;
        TakeScreenshot.makeScreenshot(driver);
    }
}
