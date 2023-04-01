package teststore.pages.product.filter;

public enum Brand implements FilterType {
    GRAPHIC_CORNER("Graphic Corner"),
    STUDIO_DESIGN("Studio Design");

    private final Filter filter = Filter.BRAND;

    private final String value;

    Brand(String value) {
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
