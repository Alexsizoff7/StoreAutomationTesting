package teststore.pages.product.filter;

public enum Categories implements FilterType {
    ACCESSORIES("Accessories"),
    ART("Art"),
    CLOTHES("Clothes");

    private final Filter filter = Filter.CATEGORIES;

    private final String value;

    Categories(String value) {
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
