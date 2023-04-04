package teststore.ui.pages.home;

import org.openqa.selenium.WebDriver;

import static teststore.DriverFactory.getChromeDriver;

public class HomePage {

    private HomeStepsController steps;
    private HomeVerifyController verify;

    public HomeStepsController steps() {
        return steps;
    }

    public HomeVerifyController verify() {
        return verify;
    }

    private WebDriver driver = getChromeDriver();

    private HomePage() {
    }

    private HomePage(HomeStepsController steps, HomeVerifyController verify) {
        this.steps = steps;
        this.verify = verify;
    }

    public static HomePage getHomePage() {
        return new HomePage(new HomeStepsController(),
                            new HomeVerifyController());
    }



}
