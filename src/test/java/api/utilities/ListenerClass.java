package api.utilities;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ListenerClass extends ExtentManager implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case PASSED: " + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Exception", ExtentColor.RED));

            // 👉 If you want screenshots, integrate like this:
            // String screenshotPath = Action.captureScreenshot(result.getName());
            // test.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public void onTestSkipped(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case SKIPPED: " + result.getName());
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used usually
    }

    public void onStart(ITestContext context) {
        try {
			setExtent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ✅ Initialize report at the start
    }

    public void onFinish(ITestContext context) {
        endReport(); // ✅ Flush report at the end
    }
}