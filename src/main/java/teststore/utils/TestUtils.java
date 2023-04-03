package teststore.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static teststore.DriverFactory.getChromeDriver;

public class TestUtils {

    public static void explicitWait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForJQueryToBeActive() {
        Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) getChromeDriver())
                .executeScript("return (typeof(jQuery) != 'undefined')");
        if (isJqueryUsed) {
            while (true) {
                // JavaScript test to verify jQuery is active or not
                Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) getChromeDriver())
                        .executeScript("return jQuery.active == 0"));
                if (ajaxIsComplete)
                    break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static String getJSText(WebElement element) {
        return ((JavascriptExecutor) getChromeDriver())
                .executeScript("return arguments[0].value;", element).toString();
    }
}
