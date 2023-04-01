package teststore.pages.product.sorting;

public enum SortBy {
    RELEVANCE("Relevance"),
    NAME_A_TO_Z("Name, A to Z"),
    NAME_Z_TO_A("Name, Z to A"),
    PRICE_LOW_TO_HIGH("Price, low to high"),
    PRICE_HIGH_TO_LOW("Price, high to low");

    private final String value;

    SortBy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
