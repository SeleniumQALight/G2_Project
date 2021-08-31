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
    public void testDataFromDataBase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable");
        logger.info(dataFromSeleniumTable);

        //Insert to Table
        //List<Map<String, String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable where login='G2ODubishchev'");
        //logger.info(dataFromSeleniumTable);
        //int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(123,'G2ODubishchev','123456qwerty')");
        //logger.info("Rows" + numberOfRows);
        //logger.info(dataFromSeleniumTable);




    }


}
