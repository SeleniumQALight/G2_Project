package api;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;



public class ApiHelperPrivat {
    Logger logger = Logger.getLogger(getClass());

    public CurrencyDTO getCurrencyDTO (String currency) {
        CurrencyDTO aCurrencyDTO=null;
        CurrencyDTO[] currencyDTOS=new ApiHelper().getCurrencyExchangePrivat();

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", formatSymbols);
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        for (CurrencyDTO dto : currencyDTOS) {
            if (currency.equalsIgnoreCase(dto.getCcy())){
                aCurrencyDTO = dto;
                try{
                    aCurrencyDTO.setBuy(df.format(Double.parseDouble(dto.getBuy())));
                   aCurrencyDTO.setSale(df.format(Double.parseDouble(dto.getSale())));
                } catch (Exception e){
                    logger.error("Error of currency parsing");
                    Assert.fail("Error of currency parsing");
                }
                logger.info("Actual CurrencyDTO: " + aCurrencyDTO);
                break;
            }
        }
        if(aCurrencyDTO == null){
            logger.error("Error of getting CurrencyDTO with type of currency '" + currency + "'");
            Assert.fail("Error of getting CurrencyDTO with type of currency '" + currency + "'");
        }
        return aCurrencyDTO;
    }
}