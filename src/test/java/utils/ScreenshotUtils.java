package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";

        String screenshotsDir = "screenshots";
        String reportDir = "test-output";

        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(reportDir + "/" + screenshotsDir + "/" + screenshotName);

        dest.getParentFile().mkdirs();

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ✅ RETURN PATH RELATIVE TO ExtentReport.html
        return screenshotsDir + "/" + screenshotName;
    }

}
