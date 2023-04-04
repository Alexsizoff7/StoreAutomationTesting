package teststore.ui.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void startUpBrowser() {
        driver = getChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = getWebDriverWait();
    }

    @BeforeMethod
    public void goToHomePage() {
        driver.get("http://teststore.automationtesting.co.uk/");
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
    }

}
