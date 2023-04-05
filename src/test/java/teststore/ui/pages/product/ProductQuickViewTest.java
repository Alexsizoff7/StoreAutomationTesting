package teststore.ui.pages.product;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teststore.ui.base.BaseClass;
import teststore.ui.pages.home.HomePage;
import teststore.ui.pages.product.catalog.CatalogPage;
import teststore.ui.pages.product.filter.Color;
import teststore.ui.pages.product.filter.Dimension;
import teststore.ui.pages.product.filter.PaperType;
import teststore.ui.pages.product.filter.Size;
import teststore.ui.pages.product.sorting.SortBy;

import static org.testng.Assert.assertEquals;
import static teststore.ui.pages.home.HomePage.getHomePage;
import static teststore.ui.pages.product.catalog.CatalogPage.getCatalogPage;
import static teststore.utils.TestUtils.explicitWait;

public class ProductQuickViewTest extends BaseClass {

    CatalogPage catalog = getCatalogPage();
    HomePage home = getHomePage();

    @BeforeMethod
    public void openCatalogPage() {
        home.steps().clickCatalog();
    }

    @Test
    public void openQuickView_verifyModalOpened() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater");

        catalog.verify()
                .productQuickViewLoaded();
    }

    @Test
    public void closeQuickView_verifyModalClosed() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .quantityChangeQuickView(3)
                .closeProductQuickView();

        catalog.verify()
                .productQuickViewClosed();
    }

    @Test
    public void closeQuickViewWithESC_verifyModalClosed() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .quantityChangeQuickView(3);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        explicitWait(1000);

        catalog.verify()
                .productQuickViewClosed();
    }


    @Test
    public void changeSize_verifySize() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .sizeChangeQuickView(Size.XL)
                .addToCartClickQuickView();

        catalog.verify()
                .sizeIs(Size.XL);
    }

    @Test
    public void changeQuantity_verifyQuantity() {
        catalog.steps()
                .clickProductQuickView("Mountain fox cushion")
                .quantityChangeQuickView(10)
                .addToCartClickQuickView();

        catalog.verify()
                .quantityIs(10);
    }

    @Test
    public void changeColor_verifyColor() {
        catalog.steps()
                .clickProductQuickView("Hummingbird cushion")
                .colorChangeQuickView(Color.BLACK)
                .addToCartClickQuickView();

        catalog.verify()
                .colorIs(Color.BLACK);
    }

    @Test
    public void changeDimension_verifyDimension() {
        catalog.steps()
                .clickProductQuickView("Today is a good day Framed...")
                .dimensionChangeQuickView(Dimension._80X120CM)
                .addToCartClickQuickView();

        catalog.verify()
                .dimensionIs(Dimension._80X120CM);
    }

    @Test
    public void changePaperType_verifyPaperType() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_A_TO_Z)
                .clickProductQuickView("Brown bear notebook")
                .paperTypeChangeQuickView(PaperType.PLAIN)
                .addToCartClickQuickView();

        catalog.verify()
                .paperTypeIs(PaperType.PLAIN);
    }

    @Test
    public void changeColorQuantitySize_verifyColorQuantitySize() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .colorChangeQuickView(Color.BLACK)
                .sizeChangeQuickView(Size.L)
                .quantityChangeQuickView(3)
                .addToCartClickQuickView();

        catalog.verify()
                .colorIs(Color.BLACK)
                .sizeIs(Size.L)
                .quantityIs(3);
    }

    @Test
    public void addToCart_verifyCartModalOpened() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView();

        catalog.verify()
                .cartModalLoaded();
    }

    @Test
    public void addToCartModalDismiss_verifyCartModalClosed() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView()
                .cartModalDismiss();

        catalog.verify()
                .cartModalClosed();
    }

    @Test
    public void addToCartAfterModalDismiss_verifyProductAdded() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView()
                .cartModalDismiss()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .sizeChangeQuickView(Size.M)
                .addToCartClickQuickView();

        catalog.verify()
                .cartModalLoaded();
    }

    @Test
    public void addToCartModalProceedToCheckout_verifyCartPageOpened() {
        catalog.steps()
                .clickProductQuickView("Mountain fox - Vector graphics")
                .addToCartClickQuickView()
                .proceedToCheckout();

        String actualUrl = "http://teststore.automationtesting.co.uk/cart?action=show";
        assertEquals(driver.getCurrentUrl(), actualUrl);
    }

    @Test
    public void chooseColorInPreview_verifyItemPageOpensWithSelectedColor() {
        catalog.steps()
                .clickColorPreview("Hummingbird printed t-shirt", Color.BLACK);

        String actualUrl = "http://teststore.automationtesting.co.uk/men/1-2-hummingbird-printed-t-shirt.html#/1-size-s/11-color-black";
        assertEquals(driver.getCurrentUrl(), actualUrl);
    }
}
