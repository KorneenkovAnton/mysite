package pool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    public  static  ConnectionPool instance;
    private static ResourceBundle rb = ResourceBundle.getBundle("sqlinfo");

    private final String URL="jdbc:mysql://localhost:3306/site_of_games";
    private final String USER = "root";
    private final  String PASSWORD = "1234";
    private final int COUNT_OF_CONNECTIONS = 4;


    private ArrayBlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(COUNT_OF_CONNECTIONS);

    public static synchronized ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void createPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(rb.getKeys().hasMoreElements()){
            createPoolWithBundle();
        }else {
            createPoolWithConstants();
        }

    }

    private void createPoolWithConstants(){
        for(int i = 0; i<COUNT_OF_CONNECTIONS;i++) {
            try {
                pool.add(DriverManager.getConnection(URL, USER, PASSWORD));
            } catch (SQLException e) {
                System.out.println("Connection failed...");
                e.printStackTrace();
            }
        }
    }

    private void createPoolWithBundle(){
        for(int i = 0; i<Integer.parseInt(rb.getString("count_of_connections"));i++) {
            try {
                pool.add(DriverManager.getConnection(rb.getString("url"), rb.getString("user"),
                        rb.getString("password")));
            } catch (SQLException e) {
                System.out.println("Connection failed...");
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        Connection con = null;

        try {
            con = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return con;
    }



    public void closeConnection(Connection c) {
        if(c != null) {
            try {
                pool.put(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionPool() {
        this.createPool();
    }
}
