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
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDataBase = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable =
        mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2_chupryna'");
        logger.info(dataFromSeleniumTable);
        int numberOfRows = mysqlDataBase.changeTable("insert into seleniumTable values(666, 'G2_chupryna', 'pass')");
        logger.info("Num of inserted rows: " + numberOfRows);
        dataFromSeleniumTable =
                mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2_chupryna'");
        logger.info(dataFromSeleniumTable);
        numberOfRows = mysqlDataBase.changeTable("delete from seleniumTable where login = 'G2_chupryna'");
        logger.info("Num of deleted rows: " + numberOfRows);
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDataBase.quit();
    }


}
