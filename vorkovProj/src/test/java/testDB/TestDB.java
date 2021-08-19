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

    @After
    public void tearDown() throws SQLException {
        mysqlDataBase.quit();
    }

    @Test
    public void testDataFromDB() throws SQLException {
        List<Map<String,String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2-vorkoff33'");
        logger.info(dataFromSeleniumTable);

//        int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(2613, 'G2-vorkoff33', '1233213')");
//        logger.info(numberOfRows + " Rows added");

        dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2-vorkoff33'");
        logger.info(dataFromSeleniumTable);

        dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable");
        logger.info(dataFromSeleniumTable);
    }
}
