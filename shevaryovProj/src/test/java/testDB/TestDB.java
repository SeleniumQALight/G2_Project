package testDB;

import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDB {
    private Database mysqlDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    //подключение к БД mySQL
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDataBase = MySQL_Database.getDataBase();
    }

    @After
    //разрыв соединения с БД
    public void tearDown() throws SQLException {
        mysqlDataBase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap(
                "SELECT * FROM seleniumTable WHERE login = 'G2_SHEVARYOV'"
        );
        logger.info(dataFromSeleniumTable);

//        int numberOfRows = mysqlDataBase.changeTable(
//                "INSERT INTO seleniumTable VALUES(789789, 'G2_SHEVARYOV', '123456qwerty')");
//        logger.info("Rows = " + numberOfRows);

        dataFromSeleniumTable = mysqlDataBase.selectTableAsMap(
                "SELECT * FROM seleniumTable WHERE login = 'G2_SHEVARYOV'"
        );
        logger.info(dataFromSeleniumTable);

    }
}
