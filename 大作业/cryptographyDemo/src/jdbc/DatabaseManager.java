package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	public static Connection connection=null;
	public static Statement statement=null;
	
	public static void connect() {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            // 建立与数据库的Connection连接
	            // 这里需要提供：
	            // 数据库所处于的ip:127.0.0.1 (本机)
	            // 数据库的端口号： 3306 （mysql专用端口号）
	            // 数据库名称 how2java
	            // 编码方式 UTF-8
	            // 账号 root
	            // 密码 admin
	            connection= DriverManager
	                    .getConnection(
	                            "jdbc:mysql://127.0.0.1:3306/password_manage?characterEncoding=UTF-8",
	                            "root", "shen2017");
	            statement=connection.createStatement();	
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	public static void close() {
		if(statement!=null) {
			try {
				statement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static ResultSet query(String sql) {
		ResultSet rs=null;
		try { 
			rs= statement.executeQuery(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public static void execute(String sql) {
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args) {
		connect();
		String sqlString="insert into  hash_check(originalHash,encryptHash) "
				+ "values ('aaa',"
				+ " '4a7b544936c0c577fffc0bfb439be789223117f8619b43901b1579062a8b7ede')" 
				+ " on duplicate key update "
				+ " originalHash='aaa',encryptHash='bbbccc' ";
		
		execute(sqlString);
		/*
		ResultSet rSet=execute(sqlString);
		

		try {
			while(rSet.next()) {
				String originString=rSet.getString("originalHash");
				String encryptString=rSet.getString("encryptHash");
				System.out.println(originString+"      "+encryptString);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		*/
				
		close();
    }

}
