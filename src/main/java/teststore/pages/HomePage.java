package teststore.pages;

import org.openqa.selenium.WebDriver;

import static teststore.DriverFactory.getChromeDriver;


public class HomePage {

    private WebDriver driver = getChromeDriver();

    private HomePage() {
    }

    public static HomePage getHomePage() {
        return new HomePage();
    }



}
