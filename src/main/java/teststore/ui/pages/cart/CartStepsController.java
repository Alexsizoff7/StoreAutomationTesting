package teststore.ui.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;
import static teststore.utils.TestUtils.explicitWait;
import static teststore.utils.TestUtils.waitForJQueryToBeActive;

public class CartStepsController {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CartStepsController changeProductQuantity(String productTitle, int quantity) {
        WebElement quantityLocator = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']//input[@type='number']"));
        quantityLocator.sendKeys(Keys.chord(Keys.CONTROL + "a"));
        quantityLocator.sendKeys(Keys.DELETE);
        explicitWait(2000);
        quantityLocator.sendKeys(String.valueOf(quantity));
        wait.until(ExpectedConditions.textToBePresentInElementValue(quantityLocator, String.valueOf(quantity)));
        driver.findElement(By.xpath("//body")).click();
        return this;
    }

    public CartStepsController incrementProductQuantityBy1(String productTitle) {
        WebElement quantityIncrementLocator = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']" +
                        "//span[@class='input-group-btn-vertical']/button[contains(@class, 'touchspin-up')]"));
        quantityIncrementLocator.click();
        return this;
    }

    public CartStepsController decrementProductQuantityBy1(String productTitle) {
        WebElement quantityDecrementLocator = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']" +
                        "//span[@class='input-group-btn-vertical']/button[contains(@class, 'touchspin-down')]"));
        quantityDecrementLocator.click();
        return this;
    }

    public void deleteProduct(String productTitle) {
        WebElement deleteProductLocator = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']" +
                        "//div[@class='cart-line-product-actions']/a"));
        deleteProductLocator.click();
        waitForJQueryToBeActive();
    }

    public void cleanCart() {
        List<WebElement> productTitlesSelector = driver.findElements(By.xpath
                ("//div[@class='product-line-grid']//div[@class='product-line-info']/a"));
        List<String> productTitles = productTitlesSelector.stream()
                .map(WebElement::getText)
                .toList();
        for (String title : productTitles) {
            deleteProduct(title);
        }
    }

    public void proceedToCheckout() {
        driver.findElement(By.xpath("//div[@class='checkout cart-detailed-actions card-block']//a")).click();
    }

}
