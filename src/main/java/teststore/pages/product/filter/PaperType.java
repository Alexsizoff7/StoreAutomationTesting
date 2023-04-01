package teststore.pages.product.filter;

public enum PaperType implements FilterType {
    RULED("Ruled"),
    PLAIN("Plain"),
    SQUARRED("Squarred"),
    DOTED("Doted");

    private final Filter filter = Filter.PAPER_TYPE;

    private final String value;

    PaperType(String value) {
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
