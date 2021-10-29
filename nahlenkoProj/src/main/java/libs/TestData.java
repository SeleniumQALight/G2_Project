package libs;

import api.CurrencyDTO;

public class TestData {
    public final static String VALID_LOGIN = "viktoriiap";
    public final static String VALID_PASSWORD = "Qwerty7777777";
    public static CurrencyDTO currencyDTOFromAPI;
    public static CurrencyDTO currencyDTOFromSite;
    private static String exchangeSellSite;
    private static String exchangeBuySite;

    public static String getExchangeSellSite() {
        return exchangeSellSite;
    }

    public static void setExchangeSellSite(String exchangeSellSite) {
        TestData.exchangeSellSite = exchangeSellSite;
    }

    public static String getExchangeBuySite() {
        return exchangeBuySite;
    }

    public static void setExchangeBuySite(String exchangeBuySite) {
        TestData.exchangeBuySite = exchangeBuySite;
    }
}
