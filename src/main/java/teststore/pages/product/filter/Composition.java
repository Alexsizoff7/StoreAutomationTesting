package teststore.pages.product.filter;

public enum Composition implements FilterType {
    CERAMIC("Ceramic"),
    COTTON("Cotton"),
    MATT_PAPER("Matt paper"),
    POLYESTER("Polyester"),
    RECYCLED_CARDBOARD("Recycled cardboard");

    private final Filter filter = Filter.COMPOSITION;

    private final String value;

    Composition(String value) {
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
