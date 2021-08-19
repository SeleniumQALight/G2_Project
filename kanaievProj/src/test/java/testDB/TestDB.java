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
    private Database mySqlDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDataBase = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mySqlDataBase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap(
                        "SELECT * FROM seleniumTable WHERE login = 'G2-Kanaiev'"
                );
        logger.info(dataFromSeleniumTable);
//        int numberOfRows = mySqlDataBase.changeTable(
//                "INSERT INTO seleniumTable VALUES(554433,'G2-Kanaiev','na_gorshke_sidit_korol`')"
//        );
//
//        logger.info("Rows = "  + numberOfRows);

        dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap(
                        "SELECT * FROM seleniumTable WHERE login = 'G2-Kanaiev'"
                );
        logger.info(dataFromSeleniumTable);
    }
}
