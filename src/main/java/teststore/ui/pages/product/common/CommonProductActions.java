package teststore.ui.pages.product.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.ui.pages.product.filter.*;
import teststore.ui.pages.product.sorting.SortBy;

import java.time.Duration;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;
import static teststore.utils.TestUtils.explicitWait;
import static teststore.utils.TestUtils.waitForJQueryToBeActive;

public class CommonProductActions {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CommonProductActions applyFilter(FilterType filter) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        WebElement filterCheckbox = driver.findElement(By.xpath
                ("//section[@class=\"facet clearfix\"]/p[text()=\"" + filter.getFilterType() + "\"]" +
                        "/following-sibling::ul//a[starts-with(normalize-space(text()),'" + filter + "')]/preceding-sibling::span/input"));
        filterCheckbox.click();
        waitForJQueryToBeActive();
        return this;
    }

    public CommonProductActions changeSortingOrder(SortBy option) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebElement sortingDropdown = driver.findElement(By.xpath("//button[@class='btn-unstyle select-title']"));
        sortingDropdown.click();

        WebElement sortingOption = driver.findElement(By.xpath
                ("//div[@class='dropdown-menu']//a[starts-with(normalize-space(text()),'" + option + "')]"));
        sortingOption.click();
        waitForJQueryToBeActive();
        return this;
    }

    public CommonProductActions clickProductQuickView(String productTitle) {
        WebElement openProductQuickView = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(openProductQuickView).perform();
        wait.until(ExpectedConditions.visibilityOf(openProductQuickView));
        openProductQuickView.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-footer")));
        return this;
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

    public void clickColorPreview(String productTitle, Color color) {
        WebElement openProductQuickView = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(openProductQuickView).perform();
        wait.until(ExpectedConditions.visibilityOf(openProductQuickView));
        WebElement changeProductColorFromProductPreview = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/div/a[@class='color']/span[text()='" + color + "']/.."));
        changeProductColorFromProductPreview.click();
    }

    public CommonProductActions closeProductQuickView() {
        String closeButtonXpath = "//div[@class='modal-content']//button[@class='close']";
        driver.findElement(By.xpath(closeButtonXpath)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(closeButtonXpath))));
        return this;
    }

    public CommonProductActions addToCartClickQuickView() {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class=\"btn btn-primary add-to-cart\"]"));
        addToCartButton.click();
        cartModalWindowLoaded();
        return this;
    }

    public CommonProductActions cartModalDismiss() {
        cartModalWindowLoaded();
        WebElement continueShoppingButton = driver.findElement(By.xpath("//button[@class='btn btn-secondary'][text()='Continue shopping']"));
        continueShoppingButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='blockcart-modal']"))));
        return this;
    }

    public void proceedToCheckout() {
        cartModalWindowLoaded();
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath
                ("//a[@href='//teststore.automationtesting.co.uk/cart?action=show'][@class='btn btn-primary']"));
        proceedToCheckoutButton.click();
    }

    private void cartModalWindowLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='modal-dialog']//h4[text()='Product successfully added to your shopping cart']")));
    }

}
