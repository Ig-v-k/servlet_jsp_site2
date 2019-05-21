import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSourceFactory {

//    private static MysqlDataSource dataSource;
//    public static DataSourceFactory INSTANCE = new DataSourceFactory();
    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

    public DataSourceFactory() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        String rootpath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties").getPath());
//        InputStream inputStream = null;

        try(InputStream inputStream = new FileInputStream(rootpath)) {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(inputStream);
            mysqlDataSource.setDatabaseName(properties.getProperty("database"));
            mysqlDataSource.setServerName(properties.getProperty("serverName"));
            mysqlDataSource.setPort(Integer.parseInt(properties.getProperty("port")));
            mysqlDataSource.setUser(properties.getProperty("user"));
            mysqlDataSource.setPassword(properties.getProperty("password"));
        }
        catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File database.properties not found", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO ERROR", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Class.forName have been thought error", e);
        }

        this.dataSource = mysqlDataSource;
    }

    public static synchronized Connection getConn() throws SQLException {
/*        if (DataSourceFactory.SingletonHelper.getInstansevalue() == null) {
            SingletonHelper.INSTANCE = new DataSourceFactory();
            return SingletonHelper.INSTANCE.dataSource.getConnection();
        }*/
        return dataSource.getConnection();
    }

/*    private static class SingletonHelper {

        public static DataSourceFactory getInstansevalue() {
            return SingletonHelper.INSTANCE;
        }

        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }*/
}