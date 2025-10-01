package api.utilities;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * @author Ayanda Khaka
 * ExtentManager class is used for Extent Report
 */
public class ExtentManager {

    public static ExtentSparkReporter sparkReporter;  // ✅ use SparkReporter instead of HtmlReporter
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() throws IOException {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/extent-reports/MyReport.html");
        
        // load config if needed
        sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("HostName : ", "My Host");
        extent.setSystemInfo("ProjectName : ", "Rest Assured Pet Store API Automation");
        extent.setSystemInfo("Test Automation Engineer : ", "Ayanda Khaka");
        extent.setSystemInfo("OS : ", "Win11");
    }

    public static void endReport() {
        extent.flush();
    }
}