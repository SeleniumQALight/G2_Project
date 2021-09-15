package testDB;

import libs.DB_Util;
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
    private Database mysqlDatabase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDatabase  = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDatabase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String,String>> dataFromSeleniumTable =
                mysqlDatabase.selectTableAsMap(
                        "select * from seleniumTable where login = 'G2Kalinina'"
                );
        logger.info(dataFromSeleniumTable);
//        //changeTable method for update / delete / insert
//        int numberOfRow = mysqlDatabase.changeTable(
//                "INSERT INTO seleniumTable VALUES(777, 'G2Kalinina', 'pass')");
//                logger.info("Rows = " + numberOfRow);

        dataFromSeleniumTable =
                mysqlDatabase.selectTableAsMap(
                        "select * from seleniumtable where login = 'G2Kalinina'" );
        logger.info(dataFromSeleniumTable);


    }
}
