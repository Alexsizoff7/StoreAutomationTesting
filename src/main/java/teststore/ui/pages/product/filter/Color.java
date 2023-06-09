package teststore.ui.pages.product.filter;

public enum Color implements FilterType {
    WHITE("White"),
    BLACK("Black");

    private final Filter filter = Filter.COLOR;

    private final String value;

    Color(String value) {
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
