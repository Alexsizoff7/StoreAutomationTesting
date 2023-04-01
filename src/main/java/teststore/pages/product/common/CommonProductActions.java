package teststore.pages.product.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.pages.product.filter.Color;

import java.util.List;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class CommonProductActions {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public List<WebElement> popularProductLinks() {
        return driver.findElements(By.xpath("//h3[@class=\"h3 product-title\"]/a"));
    }

    public List<String> popularProducts() {
        return popularProductLinks().stream()
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

    public void clickProductQuickView(String productTitle) {
        WebElement productQuickViewButton = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(productQuickViewButton).perform();
        wait.until(ExpectedConditions.visibilityOf(productQuickViewButton));
        productQuickViewButton.click();
    }

    public void clickQuickViewColor(String productTitle, Color color) {
        WebElement productQVButton = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/a[@class='quick-view']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(productQVButton).perform();
        wait.until(ExpectedConditions.visibilityOf(productQVButton));
        WebElement colorChoiceQVButton = driver.findElement(By.xpath("//article//h2/a[text()='" + productTitle
                + "']/ancestor::div[@class='product-description']/following-sibling::div/div/a[@class='color']/span[text()='" + color + "']/.."));
        colorChoiceQVButton.click();
    }


}
