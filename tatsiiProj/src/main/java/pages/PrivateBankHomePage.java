package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PrivateBankHomePage extends ParentPage{
    public String buyValue;
    public String saleValue;

    public PrivateBankHomePage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = "https://next.privat24.ua";
    }
    @Override
    String getRelativeUrl() {
        return "/exchange-rates";
    }

    public void openPage(){
        webDriver.get("https://next.privat24.ua/exchange-rates");
        webDriverWait15.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact-number")));
    }
    public boolean getExchangeCourse(String ccy){
        WebElement table = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]"));
        List<WebElement> rows = table.findElements(By.xpath("./div[contains(@class, 'root_')]"));
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            WebElement title = row.findElement(By.xpath("./div/div[2]/div"));
            String currencyTitle = title.getText();
            if(currencyTitle .equals(ccy)){
                WebElement buy = row.findElement(By.xpath("./div[2]"));
                buyValue = buy.getText();

                WebElement sale = row.findElement(By.xpath("./div[4]"));
                saleValue = sale.getText();
                return true;
            }
        }
        return false;
    }
}
