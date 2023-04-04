package teststore.ui.pages.product.catalog;

import org.openqa.selenium.WebDriver;

import static teststore.DriverFactory.getChromeDriver;

public class CatalogPage {

    private CatalogStepsController steps;
    private CatalogVerifyController verify;

    public CatalogStepsController steps() {
        return steps;
    }

    public CatalogVerifyController verify() {
        return verify;
    }

    private WebDriver driver = getChromeDriver();

    private CatalogPage() {
    }

    private CatalogPage(CatalogStepsController steps, CatalogVerifyController verify) {
        this.steps = steps;
        this.verify = verify;
    }

    public static CatalogPage getCatalogPage() {
        return new CatalogPage(new CatalogStepsController(),
                               new CatalogVerifyController());
    }

}
