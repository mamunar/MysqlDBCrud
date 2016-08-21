package com.mydb.test;
import java.util.ArrayList;

public class Main 
{

	public static void main(String[] args) throws Exception 
	{
		boolean st = new UserDao().deleteUser(3);
		ArrayList<User> alluser = new UserDao().getAllUsers();
		
		for (User user : alluser) 
		{
			System.out.println("Id: "+ user.getId());
			System.out.println("Name: "+ user.getName());
			System.out.println("Password: "+user.getPassword());
		}
		
		
	}

}
