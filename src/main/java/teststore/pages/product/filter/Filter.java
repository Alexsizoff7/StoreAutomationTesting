package teststore.pages.product.filter;

public enum Filter {
    CATEGORIES("Categories"),
    SIZE("Size"),
    COLOR("Color"),
    COMPOSITION("Composition"),
    PROPERTY("Property"),
    BRAND("Brand"),
    DIMENSION("Dimension"),
    PAPER_TYPE("Paper Type");

    private final String value;

    Filter(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
