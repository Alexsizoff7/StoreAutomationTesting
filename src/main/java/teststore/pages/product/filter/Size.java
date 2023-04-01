package teststore.pages.product.filter;

public enum Size implements FilterType {
    S("S"),
    M("M"),
    L("L"),
    XL("XL");

    private final Filter filter = Filter.SIZE;

    private final String value;

    Size(String value) {
        this.value = value;
    }

    public Filter getFilterType() {
        return filter;
    }

    @Override
    public String toString() {
        return value;
    }
}
