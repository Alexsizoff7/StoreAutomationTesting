package teststore.ui.pages.product;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teststore.ui.base.BaseClass;
import teststore.ui.pages.home.HomePage;
import teststore.ui.pages.product.catalog.CatalogPage;
import teststore.ui.pages.product.filter.*;
import teststore.ui.pages.product.sorting.SortBy;

import static teststore.ui.pages.home.HomePage.getHomePage;
import static teststore.ui.pages.product.catalog.CatalogPage.getCatalogPage;

public class CatalogTest extends BaseClass {

    CatalogPage catalog = getCatalogPage();
    HomePage home = getHomePage();

    @BeforeMethod
    public void openCatalogPage() {
        home.steps().clickCatalog();
    }

    @Test
    public void filterSelection_verifyFilterIsApplied() {
        catalog.steps()
                .applyFilter(Categories.ART)
                .applyFilter(Dimension._40X60CM);

        catalog.verify()
                .activeFilters("Categories: Art")
                .activeFilters("Dimension: 40x60cm");
    }

    @Test
    public void filterSelection_verifyFilterIsApplied2() {
        catalog.steps()
                .applyFilter(Color.WHITE)
                .applyFilter(Size.XL);

        catalog.verify()
                .activeFilters("Color: White")
                .activeFilters("Size: XL");
    }

    @Test
    public void sortingSelection_verifySortingIsApplied() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_Z_TO_A);

        catalog.verify()
                .sortingOrderIs(SortBy.NAME_Z_TO_A);
    }

    @Test
    public void filterAndSortingSelection_verifyOptionsAreApplied() {
        catalog.steps()
                .applyFilter(Composition.MATT_PAPER)
                .applyFilter(Dimension._80X120CM)
                .changeSortingOrder(SortBy.NAME_A_TO_Z);

        catalog.verify()
                .activeFilters("Composition: Matt paper")
                .activeFilters("Dimension: 80x120cm")
                .sortingOrderIs(SortBy.NAME_A_TO_Z);
    }

    @Test
    public void defaultPage_verifyDefaultSorting() {
        catalog.verify()
                .sortingOrderIs(SortBy.RELEVANCE);
    }

    @Test
    public void defaultPage_verifyProductsPerPage() {
        catalog.verify()
                .productsPerPage(12);
    }

    @Test
    public void defaultPage_verifyContainsProduct() {
        catalog.verify()
                .pageContainsProduct("Mug The Best Is Yet To Come");
    }

    @Test
    public void defaultPage_verifyDoesNotContainProduct() {
        catalog.verify()
                .pageDoesNotContainProduct("Customizable Mug");
    }

    @Test
    public void ascSortedPage_verifyContainsProduct() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_A_TO_Z);

        catalog.verify()
                .pageContainsProduct("Brown Bear - Vector Graphics")
                .pageContainsProduct("Brown Bear Notebook")
                .pageContainsProduct("Customizable Mug")
                .pageContainsProduct("Hummingbird - Vector Graphics")
                .pageContainsProduct("Hummingbird Notebook");
    }

    @Test
    public void descSortedPage_verifyDoesNotContainProduct() {
        catalog.steps()
                .changeSortingOrder(SortBy.NAME_Z_TO_A);

        catalog.verify()
                .pageDoesNotContainProduct("Brown Bear - Vector Graphics")
                .pageDoesNotContainProduct("Brown Bear Notebook")
                .pageDoesNotContainProduct("Customizable Mug")
                .pageDoesNotContainProduct("Hummingbird - Vector Graphics")
                .pageDoesNotContainProduct("Hummingbird Notebook");
    }

    @Test
    public void descPriceSortedPage_verifyHighestProductPrice() {
        catalog.steps()
                .changeSortingOrder(SortBy.PRICE_HIGH_TO_LOW);

        catalog.verify()
                .firstPriceIs(35.00);
    }

    @Test
    public void ascPriceSortedPage_verifyLowestProductPrice() {
        catalog.steps()
                .changeSortingOrder(SortBy.PRICE_LOW_TO_HIGH);

        catalog.verify()
                .firstPriceIs(9.00);
    }

}
