package com.javasree.spring.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ImageTest 
{
	public static void main(String args[])
	{
		try(FileInputStream imageInputStream = 
				new FileInputStream("C:\\Users\\emvsraj\\workspace\\PersonalProjects\\spring_exampapers\\"
						+ "src\\main\\resources\\images\\black_forest.jpeg")){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sreepapers","sreepapers");
			PreparedStatement ps = con.prepareStatement("insert into image_Test(image_data,image_title) values(?,?)");
			ps.setString(2, "Testing Image");
			
			
			ps.setBinaryStream(1,imageInputStream , imageInputStream.available());
			int i = ps.executeUpdate();
			System.out.println(i + " records updated");
			ps.close();
			con.close();
			imageInputStream.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
