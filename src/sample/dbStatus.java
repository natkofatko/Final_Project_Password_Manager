package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.dbConnection;
import database.dbConnection;


public class dbStatus {



    Connection connection;

    public dbStatus() {
        try {


            this.connection = dbConnection.getCOnnection();

        }
        catch(SQLException e)

        {
            e.printStackTrace();
        }
        if(this.connection==null)
        {
            System.out.print("Problem with database connection");
            System.exit(0);
        }

    }

    public boolean isDatabaseConnected()
    {
        return this.connection!=null;
    }

    public boolean isUserLogIn(String user,String pass) throws Exception{
        PreparedStatement pr = null;
        ResultSet rs = null;
        //userpass1 is a name of the table username and password are the names of the columns
        String FirstQuery = "SELECT*FROM userpass1 WHERE username=? AND password = ? ";

        try {
            pr = this.connection.prepareStatement(FirstQuery);
            pr.setString(1, user);
            pr.setString(2, pass);
            rs = pr.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        finally {
            pr.close();
            rs.close();
    }

    }
}
