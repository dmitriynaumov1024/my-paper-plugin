package org.naumov.mypaperplugin.dbcon;

import java.sql.*;

import org.naumov.mypaperplugin.MyPaperPlugin;

public class DbConnection 
{
	private static Connection instance;
	
	public static Connection instance() {
		return instance;
	}
	
	private static Connection create() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:mypaperplugin.db");
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	
	static {
		instance = create();
		MyPaperPlugin.instance().doOnDisable(()-> {
			try {
				instance.close();
			}
			catch (Exception ex) {
				System.out.println(ex.toString());
			}
		});
	}
}
