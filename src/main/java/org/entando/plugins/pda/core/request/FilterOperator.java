package org.entando.plugins.pda.core.request;

public enum FilterOperator {
    LIKE("like"),
    EQUAL("eq"),
    NOT_EQUAL("not"),
    GREATER("gt"),
    LOWER("lt");

    private final String value;

    FilterOperator(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static FilterOperator parse(String op) {
        final FilterOperator[] values = values();
        for (FilterOperator value : values) {
            if (value.getValue().equals(op)) {
                return value;
            }
        }
        throw new IllegalArgumentException(op + " is not a supported filter operator");
    }
}
