package pages;

import org.openqa.selenium.WebDriver;

public class ProfilePage extends ParentPage{
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }
}
