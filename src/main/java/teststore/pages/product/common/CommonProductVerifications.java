package teststore.pages.product.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import teststore.pages.product.filter.Color;
import teststore.pages.product.filter.Dimension;
import teststore.pages.product.filter.PaperType;
import teststore.pages.product.filter.Size;

import static teststore.DriverFactory.getChromeDriver;
import static teststore.DriverFactory.getWebDriverWait;

public class CommonProductVerifications {

    private final WebDriver driver = getChromeDriver();
    private final WebDriverWait wait = getWebDriverWait();

    public CommonProductVerifications sizeIs(Size size) {
        WebElement sizeAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Size\")]"));
        String actualSize = sizeAddedInQV.getText().substring(sizeAddedInQV.getText().indexOf(":")+2);
        Assert.assertEquals(size.toString(), actualSize);
        return this;
    }

    public CommonProductVerifications colorIs(Color color) {
        WebElement colorAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Color\")]"));
        String actualColor = colorAddedInQV.getText().substring(colorAddedInQV.getText().indexOf(":")+2);
        Assert.assertEquals(color.toString(), actualColor);
        return this;
    }

    public CommonProductVerifications paperTypeIs(PaperType paperType) {
        WebElement paperTypeAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Paper Type\")]"));
        String actualPaperType = paperTypeAddedInQV.getText().substring(paperTypeAddedInQV.getText().indexOf(":")+2);
        Assert.assertEquals(paperType.toString(), actualPaperType);
        return this;
    }

    public CommonProductVerifications dimensionIs(Dimension dimension) {
        WebElement dimensionAddedInQV = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Dimension\")]"));
        String actualDimension = dimensionAddedInQV.getText().substring(dimensionAddedInQV.getText().indexOf(":")+2);
        Assert.assertEquals(dimension.toString(), actualDimension);
        return this;
    }

    public CommonProductVerifications quantityIs(int expectedQuantity) {
        WebElement quantityCounter = driver.findElement(By.xpath("//h6/../span[contains(text(), \"Quantity\")]"));
        int actualQuantity = Integer.parseInt(quantityCounter.getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals(expectedQuantity, actualQuantity);
        return this;
    }



}
