package teststore.pages.product.filter;

public enum Property implements FilterType {
    _120_PAGES("120 pages"),
    LONG_SLEEVES("Long sleeves"),
    REMOVABLE_COVER("Removable cover"),
    SHORT_SLEEVES("Short sleeves");

    private final Filter filter = Filter.PROPERTY;

    private final String value;

    Property(String value) {
        this.value = value;
    }

    @Override
    public Filter getFilterType() {
        return filter;
    }

    @Override
    public String toString() {
        return value;
    }
}
