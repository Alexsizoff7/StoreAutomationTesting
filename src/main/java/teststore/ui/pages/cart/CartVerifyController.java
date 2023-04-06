package teststore.ui.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.ui.pages.product.filter.Color;
import teststore.ui.pages.product.filter.Dimension;
import teststore.ui.pages.product.filter.PaperType;
import teststore.ui.pages.product.filter.Size;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;
import static teststore.utils.TestUtils.getJSText;

public class CartVerifyController {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CartVerifyController productPriceIs(String productTitle, double expectedPrice) {
        String price = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']//span[@class='price']")).getText();
        double actualPrice = Double.parseDouble(price.replaceAll("[$]", ""));
        assertEquals(actualPrice, expectedPrice);
        return this;
    }

    public CartVerifyController productTotalPriceIs(String productTitle, double expectedTotalPrice) {
        String totalPrice = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']//span[@class='product-price']")).getText();
        double actualTotalPrice = Double.parseDouble(totalPrice.replaceAll("[$]", ""));
        assertEquals(actualTotalPrice, expectedTotalPrice);
        return this;
    }

    public CartVerifyController productQuantityIs(String productTitle, int expectedQuantity) {
        WebElement quantityLocator = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/ancestor::div[@class='product-line-grid']//input[@type='number']"));
        int actualQuantity = Integer.parseInt(getJSText(quantityLocator));
        assertEquals(actualQuantity, expectedQuantity);
        return this;
    }

    public CartVerifyController containsProduct(String productTitle) {
        assertThat(productTitles()).contains(productTitle);
        return this;
    }

    public CartVerifyController doesNotContainProduct(String productTitle) {
        assertThat(productTitles()).doesNotContain(productTitle);
        return this;
    }

    public CartVerifyController uniqueProductsInCart(int expectedCartSize) {
        assertEquals(productTitles().size(), expectedCartSize);
        return this;
    }

    public CartVerifyController totalProductsInCart(int expectedCartSize) {
        int actualCartSize = Integer.parseInt(driver.findElement(By.xpath
                ("//span[@class='cart-products-count']")).getText().replaceAll("[()]", ""));
        assertEquals(actualCartSize, expectedCartSize);
        return this;
    }

    public CartVerifyController sizeIs(String productTitle, Size size) {
        String actualSize = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/../following-sibling::div[@class='product-line-info']/span[@class='label']" +
                        "[text()='Size:']/following-sibling::span")).getText();
        assertEquals(actualSize, size.toString());
        return this;
    }

    public CartVerifyController colorIs(String productTitle, Color color) {
        String actualColor = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/../following-sibling::div[@class='product-line-info']/span[@class='label']" +
                        "[text()='Color:']/following-sibling::span")).getText();
        assertEquals(actualColor, color.toString());
        return this;
    }

    public CartVerifyController dimensionIs(String productTitle, Dimension dimension) {
        String actualDimension = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/../following-sibling::div[@class='product-line-info']/span[@class='label']" +
                        "[text()='Dimension:']/following-sibling::span")).getText();
        assertEquals(actualDimension, dimension.toString());
        return this;
    }

    public CartVerifyController paperTypeIs(String productTitle, PaperType paperType) {
        String actualPaperType = driver.findElement(By.xpath(
                "//div[@class='product-line-grid']//div[@class='product-line-info']/a[text()='" + productTitle
                        + "']/../following-sibling::div[@class='product-line-info']/span[@class='label']" +
                        "[text()='Paper Type:']/following-sibling::span")).getText();
        assertEquals(actualPaperType, paperType.toString());
        return this;
    }

    public CartVerifyController totalCartValueIs(double expectedTotalCartValue) {
        double actualTotalCartValue = Double.parseDouble(driver.findElement(By.xpath(
                "//div[@class='cart-summary-line cart-total']/span[@class='value']")).getText().replaceAll("[$]", ""));
        assertEquals(actualTotalCartValue, expectedTotalCartValue);
        return this;
    }

    public CartVerifyController shippingIs(double expectedShippingValue) {
        double actualShippingValue = Double.parseDouble(driver.findElement(By.xpath(
                "//div[@id='cart-subtotal-shipping']/span[@class='value']")).getText().replaceAll("[$]", ""));
        assertEquals(actualShippingValue, expectedShippingValue);
        return this;
    }


    private List<String> productTitles() {
        List<WebElement> productTitlesSelector = driver.findElements(By.xpath
                ("//div[@class='product-line-grid']//div[@class='product-line-info']/a"));
        return productTitlesSelector.stream()
                .map(WebElement::getText)
                .toList();
    }
}
