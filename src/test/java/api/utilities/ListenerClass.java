package api.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.response.Response;

public class ListenerClass extends ExtentManager implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    // -------------------- TestNG Listener Methods --------------------
    
    @Override
    public void onStart(ITestContext context) {
        try {
			setExtent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Initialize ExtentReports
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        endReport(); // Flush reports
        System.out.println("Test Suite finished: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName())
                     .assignCategory(result.getMethod().getGroups());
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, MarkupHelper.createLabel("Test Passed: " + result.getMethod().getMethodName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed: " + result.getMethod().getMethodName(), ExtentColor.RED));
        test.log(Status.FAIL, result.getThrowable());

        // Attempt to log response body if available
        Object testClass = result.getInstance();
        try {
            Method responseMethod = testClass.getClass().getDeclaredField("response").getType().getMethod("then");
            // For simplicity, here we can also attach screenshots if GUI tests exist
        } catch (Exception e) {
            // Ignore if no response object or reflection fails
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped: " + result.getMethod().getMethodName(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        test.log(Status.WARNING, "Test partially failed: " + result.getMethod().getMethodName());
    }

}
