package teststore.ui.pages.cart;

import org.openqa.selenium.WebDriver;

import static teststore.DriverFactory.getChromeDriver;

public class CartPage {

    private CartStepsController steps;
    private CartVerifyController verify;

    public CartStepsController steps() {
        return steps;
    }

    public CartVerifyController verify() {
        return verify;
    }

    private final WebDriver driver = getChromeDriver();

    private CartPage() {
    }

    private CartPage(CartStepsController steps, CartVerifyController verify) {
        this.steps = steps;
        this.verify = verify;
    }

    public static CartPage getCartPage() {
        return new CartPage(new CartStepsController(),
                new CartVerifyController());
    }
}
