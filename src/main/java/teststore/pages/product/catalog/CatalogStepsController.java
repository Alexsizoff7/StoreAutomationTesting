package teststore.pages.product.catalog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import teststore.pages.product.common.CommonProductActions;
import teststore.pages.product.filter.FilterType;
import teststore.pages.product.sorting.SortBy;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class CatalogStepsController extends CommonProductActions {

    private WebDriver driver = getChromeDriver();
    private WebDriverWait wait = getWebDriverWait();

    public void clickFilter(FilterType filter) {
        WebElement filterCheckbox = driver.findElement(By.xpath
                ("//section[@class=\"facet clearfix\"]/p[text()=\"" + filter.getFilterType() + "\"]" +
                        "/following-sibling::ul//a[starts-with(normalize-space(text()),'" + filter + "')]/preceding-sibling::span/input"));
        filterCheckbox.click();
    }

    public void clickSorting(SortBy option) {
        WebElement sortingDropdown = driver.findElement(By.xpath("//button[@class='btn-unstyle select-title']"));
        sortingDropdown.click();

        WebElement sortingOption = driver.findElement(By.xpath
                ("//div[@class='dropdown-menu']//a[starts-with(normalize-space(text()),'" + option + "')]"));
        sortingOption.click();
    }



}
