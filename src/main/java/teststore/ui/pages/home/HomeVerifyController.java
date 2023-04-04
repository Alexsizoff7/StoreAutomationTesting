package teststore.ui.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static teststore.DriverFactory.getChromeDriver;

public class HomeVerifyController {

    private final WebDriver driver = getChromeDriver();

    public List<WebElement> popularProductLinks() {
        return driver.findElements(By.xpath("//h3[@class='h3 product-title']/a"));
    }

    public List<String> popularProductTitles() {
        return popularProductLinks().stream()
                .map(WebElement::getText)
                .toList();
    }

}
