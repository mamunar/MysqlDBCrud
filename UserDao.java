package com.mydb.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mydb.test.ConnectionFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDao 
{
    public User getUser(int id)
    {
        Connection connection = (Connection) ConnectionFactory.getConnection();

        try {

            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_pref.user_info WHERE id=" + id);
            
            if(rs.next())
            {
                User user = new User();

                user.setId( rs.getInt("id") );
                user.setName( rs.getString("name") );
                user.setPassword( rs.getString("pass"));

                return user;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    return null;
    }
    public ArrayList<User> getAllUsers()
    {
    	ArrayList<User> allUser = new ArrayList<User>();
    	Connection connection = (Connection) ConnectionFactory.getConnection();

        try {

            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_pref.user_info");
            
            
            while(rs.next())
            {
                User user = new User();

                user.setId( rs.getInt("id") );
                user.setName( rs.getString("name") );
                user.setPassword( rs.getString("pass"));

                allUser.add(user);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    	return allUser;
    }
    public boolean insertUser(User user) 
    {

        Connection connection = (Connection) ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO user_pref.user_info VALUES (NULL, ?, ?)");

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());

            int status = ps.executeUpdate();

          if(status == 1) {

            return true;

          }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;

    }
    public boolean updateUser(User user) 
    {

        Connection connection = (Connection) ConnectionFactory.getConnection();

        try {

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE user_pref.user_info SET name=?, pass=? WHERE id=?");

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());

            int i = ps.executeUpdate();

          if(i == 1) {

        return true;

          }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;

    }
    
   public boolean deleteUser(int id)
   {
	   Connection connection = (Connection) ConnectionFactory.getConnection();

       try {

           PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM user_pref.user_info WHERE id=?");
           ps.setInt(1, id);
           int status = ps.executeUpdate();

         if(status == 1) {

       return true;

         }

       } catch (SQLException ex) {

           ex.printStackTrace();

       }

       return false;
   }
   
}
