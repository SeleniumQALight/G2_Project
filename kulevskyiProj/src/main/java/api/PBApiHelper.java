package api;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class PBApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public CurrencyDTO getPBCurrencyDTOFromApiByCurrencyCode(String currencyCode) {
        CurrencyDTO actualCurrencyDTO = null;
        CurrencyDTO[] currencyDTOS = new ApiHelper().getAllCurrencyByPrivatBank();

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", formatSymbols);
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        for (CurrencyDTO dto : currencyDTOS) {
            if (currencyCode.equalsIgnoreCase(dto.getCcy())){
                actualCurrencyDTO = dto;
                try{
                    actualCurrencyDTO.setBuy(df.format(Double.parseDouble(dto.getBuy())));
                    actualCurrencyDTO.setSale(df.format(Double.parseDouble(dto.getSale())));
                } catch (Exception e){
                    logger.error("Cannot parse currency");
                    Assert.fail("Cannot parse currency");
                }
                logger.info("Actual CurrencyDTO: " + actualCurrencyDTO);
                break;
            }
        }
        if(actualCurrencyDTO == null){
            logger.error("Cannot get CurrencyDTO with currencyCode '" + currencyCode + "'");
            Assert.fail("Cannot get CurrencyDTO with currencyCode '" + currencyCode + "'");
        }
        return actualCurrencyDTO;
    }
}
