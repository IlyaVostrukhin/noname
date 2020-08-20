package dev.noname;

public final class Constants {

    public static final String ACCOUNT_ACTIONS_HISTORY = "ACCOUNT_ACTIONS_HISTORY";

    public static final String CURRENT_SHOPPING_CART = "CURRENT_SHOPPING_CART";

    public static final String CURRENT_ACCOUNT = "CURRENT_ACCOUNT";

    public static final String SUCCESS_REDIRECT_URL_AFTER_SIGNIN = "SUCCESS_REDIRECT_URL_AFTER_SIGNIN";

    public static final String CURRENT_REQUEST_URL = "CURRENT_REQUEST_URL";

    // Макс количество продуктов одного наименования
    public static final int MAX_PRODUCT_COUNT_PER_SHOPPING_CART = 10;

    // Макс количество продуктов (позиций) в корзине
    public static final int MAX_PRODUCTS_PER_SHOPPING_CART = 20;

    //Макс количество продуктов на странице
    public static final int MAX_PRODUCTS_PER_HTML_PAGE = 12;

    public enum Cookie {
        SHOPPING_CART("iSCC", 60 * 60 * 24 * 365);

        private final String name;
        private final int ttl;

        Cookie(String name, int ttl) {
            this.name = name;
            this.ttl = ttl;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
