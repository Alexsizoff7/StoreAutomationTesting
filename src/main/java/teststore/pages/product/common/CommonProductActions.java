package teststore.pages.product.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.pages.product.filter.*;
import teststore.pages.product.sorting.SortBy;

import java.time.Duration;
import java.util.List;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;
import static teststore.utils.TestUtils.explicitWait;
import static teststore.utils.TestUtils.waitForJQueryToBeActive;

public class CommonProductActions {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CommonProductActions clickFilter(FilterType filter) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        WebElement filterCheckbox = driver.findElement(By.xpath
                ("//section[@class=\"facet clearfix\"]/p[text()=\"" + filter.getFilterType() + "\"]" +
                        "/following-sibling::ul//a[starts-with(normalize-space(text()),'" + filter + "')]/preceding-sibling::span/input"));
        filterCheckbox.click();
        waitForJQueryToBeActive();
        return this;
    }

    public CommonProductActions clickSorting(SortBy option) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebElement sortingDropdown = driver.findElement(By.xpath("//button[@class='btn-unstyle select-title']"));
        sortingDropdown.click();

        WebElement sortingOption = driver.findElement(By.xpath
                ("//div[@class='dropdown-menu']//a[starts-with(normalize-space(text()),'" + option + "')]"));
        sortingOption.click();
        waitForJQueryToBeActive();
        return this;
    }

    public List<WebElement> productLinks() {
        return driver.findElements(By.xpath("//h3[@class=\"h3 product-title\"]/a"));
    }

    public List<String> productTitlesPreview() {
        return productLinks().stream()
                .map(WebElement::getText)
                .toList();
    }

    public double getProductPrice(String productTitle) {
        String str = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/parent::h2/following-sibling::div/span[@class='price']")).getText();
        return Double.parseDouble(str.replaceAll("[$]", ""));
    }

    public double getProductDiscountedPrice(String productTitle) {
        String str = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/parent::h2/following-sibling::div/span[@class='regular-price']")).getText();
        return Double.parseDouble(str.replaceAll("[$]", ""));
    }

    public CommonProductActions clickProductQuickView(String productTitle) {
        WebElement openProductQuickView = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(openProductQuickView).perform();
        wait.until(ExpectedConditions.visibilityOf(openProductQuickView));
        openProductQuickView.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        return this;
    }

    public void clickQuickViewColor(String productTitle, Color color) {
        WebElement openProductQuickView = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(openProductQuickView).perform();
        wait.until(ExpectedConditions.visibilityOf(openProductQuickView));
        WebElement changeProductColorFromProductPreview = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/div/a[@class='color']/span[text()='" + color + "']/.."));
        changeProductColorFromProductPreview.click();
    }

    public CommonProductActions sizeChangeQuickView(Size size) {
        Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
        sizeDropdown.selectByVisibleText(String.valueOf(size));
        wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath("//option[text()='" + size + "']"))));
        return this;
    }

    public CommonProductActions paperTypeChangeQuickView(PaperType paperType) {
        Select paperTypeDropdown = new Select(driver.findElement(By.id("group_4")));
        paperTypeDropdown.selectByVisibleText(String.valueOf(paperType));
        wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath("//option[text()='" + paperType + "']"))));
        return this;
    }

    public CommonProductActions dimensionChangeQuickView(Dimension dimension) {
        Select dimensionDropdown = new Select(driver.findElement(By.id("group_3")));
        dimensionDropdown.selectByVisibleText(String.valueOf(dimension));
        wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath("//option[text()='" + dimension + "']"))));
        return this;
    }

    public CommonProductActions colorChangeQuickView(Color color) {
        WebElement colorOption = driver.findElement(By.xpath("//label/span/span[text()='" + color + "']/ancestor::label/input"));
        colorOption.click();
        wait.until(ExpectedConditions.elementToBeSelected(colorOption));
        return this;
    }

    public CommonProductActions quantityChangeQuickView(int quantity) {
        explicitWait(1000);
        WebElement quantityInputField = driver.findElement(By.id("quantity_wanted"));
        quantityInputField.clear();
        quantityInputField.sendKeys(String.valueOf(quantity));
        return this;
    }

    public void addToCartQuickView() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary add-to-cart\"]"));
        addToCartButton.click();
        cartModalWindowLoaded();
    }

    public void cartModalDismiss() {
        cartModalWindowLoaded();
        WebElement continueShoppingButton = driver.findElement(By.className("btn btn-secondary"));
        continueShoppingButton.click();
    }

    public void proceedToCheckout() {
        cartModalWindowLoaded();
        WebElement proceedToCheckoutButton = driver.findElement(By.className("btn btn-primary"));
        proceedToCheckoutButton.click();
    }

    private void cartModalWindowLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='modal-dialog']//h4[text()='Product successfully added to your shopping cart']")));
    }

}
