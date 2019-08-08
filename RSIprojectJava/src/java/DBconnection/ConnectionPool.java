package DBconnection;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @authors Tyler Jurczak
 */

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    /**
     * creates connection pool object and checks dataSource in META-INF/context.xml
     */
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/RSIUPLOAD");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }

    /**
     * gets a connection pool instance, allowing ping to database
     * @return 
     */
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * gets a connection pool, allowing ping to database
     * @return 
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * closes connection and returns it to pool
     * @param c connection that was used prior
     */
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}