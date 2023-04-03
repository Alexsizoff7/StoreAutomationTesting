package teststore.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import teststore.pages.product.common.CommonProductActions;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class HomeStepsController extends CommonProductActions {

    private final WebDriver driver = getChromeDriver();

    public void search(String value) {
        WebElement search = driver.findElement(By.xpath("//input[@aria-label=\"Search\"]"));
        search.sendKeys(value);
        search.sendKeys(Keys.ENTER);
    }

    public void clickLogin() {
        WebElement signIn = driver.findElement(By.xpath
                ("//div[@class=\"user-info\"]/a[@href=\"http://teststore.automationtesting.co.uk/my-account\"]"));
        signIn.click();
    }

    public void clickCatalog() {
        WebElement catalog = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/2-home\"]"));
        catalog.click();
    }

    public void clickClothes() {
        WebElement clothes = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/3-clothes\"]"));
        clothes.click();
    }

    public void clickAccessories() {
        WebElement accessories = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/6-accessories\"]"));
        accessories.click();
    }

    public void clickArt() {
        WebElement art = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/9-art\"]"));
        art.click();
    }

    public void clickMen() {
        WebElement clothes = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/3-clothes\"]"));
        WebElement menClothes = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/4-men\"]"));
        Actions ac = new Actions(driver);
        ac.moveToElement(clothes).perform();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(menClothes));
        menClothes.click();
    }

    public void clickWomen() {
        WebElement clothes = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/3-clothes\"]"));
        WebElement womenClothes = driver.findElement(By.xpath("//a[@href=\"http://teststore.automationtesting.co.uk/5-women\"]"));
        Actions ac = new Actions(driver);
        ac.moveToElement(clothes).perform();
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(womenClothes));
        womenClothes.click();
    }


}
