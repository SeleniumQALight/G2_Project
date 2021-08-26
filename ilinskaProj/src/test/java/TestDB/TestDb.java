package TestDB;

import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDb {
    private Database mysqlDateBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        mysqlDateBase = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDateBase.quit();
    }

    @Test
    public void testDataFromDatabase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable =
                mysqlDateBase.selectTableAsMap(
                        "select * from seleniumTable where login='G2Ilinska'"

                );
//        logger.info(dataFromSeleniumTable);
//   int numberOfRow= mysqlDateBase.changeTable(
//           "INSERT INTO seleniumTable VALUES(777,'G2Ilinska','123')"
//   );
        dataFromSeleniumTable =
                mysqlDateBase.selectTableAsMap(
                        "select * from seleniumTable where login='G2Ilinska'"

                );
        logger.info(dataFromSeleniumTable);
    }
}