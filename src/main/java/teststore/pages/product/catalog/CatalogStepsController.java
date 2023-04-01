package teststore.pages.product.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import teststore.pages.product.filter.FilterType;

import static teststore.DriverFactory.getChromeDriver;

public class CatalogStepsController {

    private WebDriver driver = getChromeDriver();

    public void clickCategoryOption(FilterType filter) {
        WebElement element = driver.findElement(By.xpath
                ("//section[@class=\"facet clearfix\"]/p[text()=\"" + filter.getFilterType().toString() + "\"]" +
                        "/following-sibling::ul//a[starts-with(normalize-space(text()),'" + filter + "')]/preceding-sibling::span/input"));
        element.click();
    }

}
