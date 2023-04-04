package teststore.ui.pages.product.filter;

public enum Dimension implements FilterType {
    _40X60CM("40x60cm"),
    _60x90CM("60x90cm"),
    _80X120CM("80x120cm");

    private final Filter filter = Filter.DIMENSION;

    private final String value;

    Dimension(String value) {
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
