package teststore.ui.pages.cart;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

import static teststore.ui.pages.cart.CartPage.getCartPage;
import static teststore.ui.pages.home.HomePage.getHomePage;
import static teststore.ui.pages.product.catalog.CatalogPage.getCatalogPage;
import static teststore.utils.TestUtils.explicitWait;

public class CartTest extends BaseClass {

    HomePage home = getHomePage();
    CartPage cart = getCartPage();
    CatalogPage catalog = getCatalogPage();

    @BeforeMethod
    public void openCatalogPage() {
        home.steps()
                .clickCatalog();
    }

    @AfterMethod
    public void cleanCart() {
        driver.get("http://teststore.automationtesting.co.uk/cart?action=show");

        cart.steps()
                .cleanCart();
    }


    @Test
    public void productAddedToCart_verifyPrice() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .productPriceIs("Mug The best is yet to come", 11.90);
    }

    @Test
    public void productAddedToCart_verifyQuantity() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .quantityChangeQuickView(4)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .productQuantityIs("Mug The best is yet to come", 4);
    }

    @Test
    public void productAddedToCart_verifyTotalPrice() {
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .quantityChangeQuickView(4)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .productTotalPriceIs("Mug The best is yet to come", 4 * 11.9);
    }

    @Test
    public void productAddedToCart_verifyPriceQuantityTotalPrice() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_A_TO_Z)
                .clickProductQuickView("Brown bear notebook")
                .quantityChangeQuickView(5)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .productPriceIs("Brown bear notebook", 12.90)
                .productQuantityIs("Brown bear notebook", 5)
                .productTotalPriceIs("Brown bear notebook", 5 * 12.90);
    }

    @Test
    public void productAddedToCart_verifySize() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .sizeChangeQuickView(Size.L)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .sizeIs("Hummingbird printed t-shirt", Size.L);
    }

    @Test
    public void productAddedToCart_verifyColor() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .colorChangeQuickView(Color.WHITE)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .colorIs("Hummingbird printed t-shirt", Color.WHITE);
    }

    @Test
    public void productAddedToCart_verifyDimension() {
        catalog.steps()
                .clickProductQuickView("Today is a good day Framed...")
                .dimensionChangeQuickView(Dimension._80X120CM)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .dimensionIs("Today is a good day Framed poster", Dimension._80X120CM);
    }

    @Test
    public void productAddedToCart_verifyPaperType() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_A_TO_Z)
                .clickProductQuickView("Brown bear notebook")
                .paperTypeChangeQuickView(PaperType.RULED)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .paperTypeIs("Brown bear notebook", PaperType.RULED);
    }

    @Test
    public void addMultipleProductsToCart_verifyCartContainsProducts() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Today is a good day Framed...")
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .containsProduct("Hummingbird printed t-shirt")
                .containsProduct("Today is a good day Framed poster")
                .containsProduct("Mug The best is yet to come");
    }

    @Test
    public void addMultipleProductsToCart_verifyUniqueProductCount() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(6)
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .quantityChangeQuickView(5)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .totalProductsInCart(11);
    }

    @Test
    public void addMultipleProductsToCart_verifyTotalCartSize() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Today is a good day Framed...")
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .uniqueProductsInCart(3);
    }

    @Test
    public void addMultipleProductsToCart_verifyTotalCartValue() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(6)
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Mug The best is yet to come")
                .quantityChangeQuickView(5)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.verify()
                .totalCartValueIs(181.22);
    }


    @Test
    public void changeProductQuantity_verifyQuantityChanged() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(10)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .changeProductQuantity("Hummingbird printed t-shirt", 4);

        cart.verify()
                .productQuantityIs("Hummingbird printed t-shirt", 4);
    }

    @Test
    public void incrementProductQuantity_verifyQuantityChanged() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(5)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .incrementProductQuantityBy1("Hummingbird printed t-shirt")
                .incrementProductQuantityBy1("Hummingbird printed t-shirt");

        cart.verify()
                .productQuantityIs("Hummingbird printed t-shirt", 7);
    }

    @Test
    public void decrementProductQuantity_verifyQuantityChanged() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(7)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .decrementProductQuantityBy1("Hummingbird printed t-shirt")
                .decrementProductQuantityBy1("Hummingbird printed t-shirt")
                .decrementProductQuantityBy1("Hummingbird printed t-shirt")
                .decrementProductQuantityBy1("Hummingbird printed t-shirt");

        cart.verify()
                .productQuantityIs("Hummingbird printed t-shirt", 3);
    }

    @Test
    public void changeProductQuantityMultipleTimes_verifyQuantityChanged() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(22)
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .changeProductQuantity("Hummingbird printed t-shirt", 8) // quantity: 8
                .decrementProductQuantityBy1("Hummingbird printed t-shirt") // quantity: 7
                .decrementProductQuantityBy1("Hummingbird printed t-shirt") // quantity: 6
                .incrementProductQuantityBy1("Hummingbird printed t-shirt") // quantity: 7
                .decrementProductQuantityBy1("Hummingbird printed t-shirt") // quantity: 6
                .decrementProductQuantityBy1("Hummingbird printed t-shirt"); // quantity: 5

        cart.verify()
                .productQuantityIs("Hummingbird printed t-shirt", 5);
    }

    @Test
    public void deleteProduct_verifyCartDoesNotContainProduct() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed t-shirt")
                .quantityChangeQuickView(2)
                .addToCartClickQuickView()
                .cartModalDismiss();
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .deleteProduct("Hummingbird printed sweater");

        cart.verify()
                .doesNotContainProduct("Hummingbird printed sweater");
    }

    @Test
    public void deleteProduct_verifyCartIsEmpty() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .deleteProduct("Hummingbird printed sweater");

        cart.verify()
                .doesNotContainProduct("Hummingbird printed sweater");
    }

    @Test
    public void clickCheckout_verifyCheckoutPageOpened() {
        catalog.steps()
                .clickProductQuickView("Hummingbird printed sweater")
                .addToCartClickQuickView()
                .proceedToCheckout();

        cart.steps()
                .proceedToCheckout();

        String expectedUrl = "http://teststore.automationtesting.co.uk/order";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

}
