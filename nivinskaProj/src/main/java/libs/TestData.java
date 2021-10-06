package libs;

public class TestData {
    public final static String VALID_LOGIN = "nivinskao";
    public final static String VALID_PASSWORD = "caramacara12";

    protected static Double currency_buy_api;
    protected static Double currency_sell_api;

    protected static Double currency_buy_web;
    protected static Double currency_sell_web;

    public static String getValidLogin() {
        return VALID_LOGIN;
    }

    public static Double getCurrency_buy_api() {
        return currency_buy_api;
    }

    public static void setCurrency_buy_api(Double currency_buy_api) {
        TestData.currency_buy_api = currency_buy_api;
    }

    public static Double getCurrency_sell_api() {
        return currency_sell_api;
    }

    public static void setCurrency_sell_api(Double currency_sell_api) {
        TestData.currency_sell_api = currency_sell_api;
    }

    public static Double getCurrency_buy_web() {
        return currency_buy_web;
    }

    public static void setCurrency_buy_web(Double currency_buy_web) {
        TestData.currency_buy_web = currency_buy_web;
    }

    public static Double getCurrency_sell_web() {
        return currency_sell_web;
    }

    public static void setCurrency_sell_web(Double currency_sell_web) {
        TestData.currency_sell_web = currency_sell_web;
    }
}
