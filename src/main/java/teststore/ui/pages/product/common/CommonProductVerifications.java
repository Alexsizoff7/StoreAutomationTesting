package teststore.ui.pages.product.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.ui.pages.product.filter.Color;
import teststore.ui.pages.product.filter.Dimension;
import teststore.ui.pages.product.filter.PaperType;
import teststore.ui.pages.product.filter.Size;
import teststore.ui.pages.product.sorting.SortBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class CommonProductVerifications {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CommonProductVerifications sizeIs(Size size) {
        WebElement sizeAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Size\")]"));
        String actualSize = sizeAddedInQV.getText().substring(sizeAddedInQV.getText().indexOf(":") + 2);
        assertEquals(actualSize, size.toString());
        return this;
    }

    public CommonProductVerifications colorIs(Color color) {
        WebElement colorAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Color\")]"));
        String actualColor = colorAddedInQV.getText().substring(colorAddedInQV.getText().indexOf(":") + 2);
        assertEquals(actualColor, color.toString());
        return this;
    }

    public CommonProductVerifications paperTypeIs(PaperType paperType) {
        WebElement paperTypeAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Paper Type\")]"));
        String actualPaperType = paperTypeAddedInQV.getText().substring(paperTypeAddedInQV.getText().indexOf(":") + 2);
        assertEquals(actualPaperType, paperType.toString());
        return this;
    }

    public CommonProductVerifications dimensionIs(Dimension dimension) {
        WebElement dimensionAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Dimension\")]"));
        String actualDimension = dimensionAddedInQV.getText().substring(dimensionAddedInQV.getText().indexOf(":") + 2);
        assertEquals(actualDimension, dimension.toString());
        return this;
    }

    public CommonProductVerifications quantityIs(int expectedQuantity) {
        WebElement quantityCounter = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Quantity\")]"));
        int actualQuantity = Integer.parseInt(quantityCounter.getText().replaceAll("[^0-9]", ""));
        assertEquals(actualQuantity, expectedQuantity);
        return this;
    }

    public CommonProductVerifications activeFilters(String expectedFilter) {
        List<WebElement> activeFiltersList = driver.findElements(By.xpath("//section[@id='js-active-search-filters']/ul/li"));
        String actualFilter = activeFiltersList.stream()
                .map(WebElement::getText)
                .map(f -> f.replaceAll("[^A-Za-z0-9: ]", ""))
                .filter(f -> f.equals(expectedFilter))
                .findFirst().orElse("filter not found");
        assertEquals(actualFilter, expectedFilter);
        return this;
    }

    public CommonProductVerifications sortingOrderIs(SortBy expectedSortingOption) {
        WebElement sortByDropdown = driver.findElement(By.xpath("//button[@class='btn-unstyle select-title']"));
        assertEquals(sortByDropdown.getText().replaceAll("[^A-Za-z,\s]", ""), expectedSortingOption.toString());
        return this;
    }

    public CommonProductVerifications productsPerPage(int expectedProducts) {
        assertEquals(expectedProducts, productLinks().size());
        return this;
    }

    public CommonProductVerifications pageContainsProduct(String expectedProduct) {
        assertThat(productTitles()).contains(expectedProduct);
        return this;
    }

    public CommonProductVerifications pageDoesNotContainProduct(String expectedProduct) {
        assertThat(productTitles()).doesNotContain(expectedProduct);
        return this;
    }

    public CommonProductVerifications firstPriceIs(double expectedPrice) {
        String firstProductTitle = productTitles().get(0);
        assertEquals(getProductPrice(firstProductTitle), expectedPrice);
        return this;
    }

    // Product quick view modal window has all the same elements as cart modal window,
    // except for footer block - it is unique for product modal
    public CommonProductVerifications productQuickViewLoaded() {
        String modalXpath = "//div[@class='modal-footer']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(modalXpath)));
        assertTrue(driver.findElement(By.xpath(modalXpath)).isDisplayed());
        return this;
    }

    public CommonProductVerifications productQuickViewClosed() {
        assertFalse(driver.getPageSource().contains("modal-footer"));
        return this;
    }

    public CommonProductVerifications cartModalLoaded() {
        String cartModalXpath = "//div[@class='modal-dialog']//h4[text()='Product successfully added to your shopping cart']";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartModalXpath)));
        assertTrue(driver.findElement(By.xpath(cartModalXpath)).isDisplayed());
        return this;
    }

    public CommonProductVerifications cartModalClosed() {
        assertFalse(driver.findElement(By.xpath("//div[@id='blockcart-modal']")).isDisplayed());
        return this;
    }


    private List<WebElement> productLinks() {
        return driver.findElements(By.xpath("//h2[@class='h3 product-title']/a"));
    }

    private List<String> productTitles() {
        return productLinks().stream()
                .map(webElement -> webElement.getAttribute("text"))
                .toList();
    }

    private double getProductPrice(String productTitle) {
        String price = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/parent::h2/following-sibling::div/span[@class='price']")).getText();
        return Double.parseDouble(price.replaceAll("[$]", ""));
    }

    private double getProductDiscountedPrice(String productTitle) {
        String regularPrice = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/parent::h2/following-sibling::div/span[@class='regular-price']")).getText();
        return Double.parseDouble(regularPrice.replaceAll("[$]", ""));
    }

}
