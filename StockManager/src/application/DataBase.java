package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DataBase
{

	public boolean Insert(String qry,Object ob[])
	{
		
		Connection con;
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanager","root","");
	    	int i;
			PreparedStatement ps = con.prepareStatement(qry);
			for (i=1;i<=ob.length;i++)
			{
				if(ob[i-1] instanceof Integer )
					ps.setInt(i, (Integer)ob[i-1]);
				else if(ob[i-1] instanceof String )
					ps.setString(i, (String)ob[i-1]);
				else if(ob[i-1] instanceof Date)
					ps.setDate(i, (Date)ob[i-1]);
			}
	
			ps.execute();
			con.close();
			return true;
	    }
	   catch(Exception e){e.printStackTrace();}
	   return false;
	}
	
	public ResultSet getData(String qry){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanager","root","");
			PreparedStatement ps = con.prepareStatement(qry);
			ResultSet rs =  ps.executeQuery();
			return rs;
			}
		catch(Exception e){e.printStackTrace();}
		return null;
		}
	
	public ResultSet getData(String qry, Object ob[]){
		try{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanager","root","");
		int i;
		PreparedStatement ps = con.prepareStatement(qry);
		for (i=1;i<=ob.length;i++)
		{
			if(ob[i-1] instanceof Integer )
				ps.setInt(i, (Integer)ob[i-1]);
			else if(ob[i-1] instanceof String )
				ps.setString(i, (String)ob[i-1]);
			else if(ob[i-1] instanceof Date)
				ps.setDate(i, (Date)ob[i-1]);
			
		}
		
		ResultSet rs = ps.executeQuery();
		return rs;
		}
		catch(Exception e){e.printStackTrace(); return null;}
	}
	
	public boolean updateOrDeleteData(String qry){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanager","root","");
			PreparedStatement ps = con.prepareStatement(qry);
			ps.executeUpdate();
			return true;
			}
		catch(Exception e){e.printStackTrace();}
		return false;
	}
	
	public boolean updateOrDeleteData(String qry,Object ob[]){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanager","root","");
			int i;
			PreparedStatement ps = con.prepareStatement(qry);
			for (i=1;i<=ob.length;i++)
			{
				if(ob[i-1] instanceof Integer )
					ps.setInt(i, (Integer)ob[i-1]);
				else if(ob[i-1] instanceof String )
					ps.setString(i, (String)ob[i-1]);
				else if(ob[i-1] instanceof Date)
					ps.setDate(i, (Date)ob[i-1]);
			}
			ps.executeUpdate();
			return true;
			}
		catch(Exception e){e.printStackTrace();}
		return false;
	}
}