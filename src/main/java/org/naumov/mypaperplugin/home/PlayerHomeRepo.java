package org.naumov.mypaperplugin.home;

import java.sql.*;
import java.util.*;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.dbcon.DbConnection;

public class PlayerHomeRepo
{
	private static Connection dbcon = getAndPrepareDb();
	
	private static Connection getAndPrepareDb() {
		try {
			Connection con = DbConnection.instance();
			String createTablePlayerHome = 
				"create table if not exists player_home (" +
				"player_id varchar(44)," +
				"home_name varchar(128)," +
				"world_name varchar(44)," +
				"x double," +
				"y double," +
				"z double," +
				"primary key(player_id, home_name)" +
				");";
			String createTablePlayerHomeShare = 
				"create table if not exists player_home_share (" +
				"owner_id varchar(44)," +
				"home_name varchar(128)," + 
				"shared_with varchar(44)," +
				"primary key(owner_id, home_name, shared_with)" +
				");";
			con.prepareStatement(createTablePlayerHome).execute();
			con.prepareStatement(createTablePlayerHomeShare).execute();
			return con;
		} 
		catch (Exception ex) {
			System.out.println("Something went wrong when preparing database");
			return null;
		}
	}
	
	public static Location getHome (Player player, String homeName, Server server) {
		try {
			PreparedStatement stmt = dbcon.prepareStatement("select world_name, x, y, z from player_home where player_id=? and home_name=?;");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, homeName);
			ResultSet r = stmt.executeQuery();
			if (r.next()) {
				return new Location(server.getWorld(r.getString("world_name")), r.getDouble("x"),  r.getDouble("y"), r.getDouble("z"));
			}
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting home location");
		}
		return null;
	}
	
	public static List<String> getHomeNames (Player player, Server server) {
		List<String> result = new ArrayList<>();
		try {
			PreparedStatement stmt = dbcon.prepareStatement("select home_name from player_home where player_id=?;");
			stmt.setString(1, player.getUniqueId().toString());
			ResultSet r = stmt.executeQuery();
			while (r.next()) {
				result.add(r.getString("home_name"));
			}
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting home location");
		}
		return result;
	}
	
	public static boolean insertHome (Player player, String homeName, Server server) {
		try {
			Location loc = player.getLocation();
			PreparedStatement stmt = dbcon.prepareStatement("insert into player_home (player_id, home_name, world_name, x, y, z) values (?, ?, ?, ?, ?, ?);");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.setString(3, loc.getWorld().getName());
			stmt.setDouble(4, loc.getX());
			stmt.setDouble(5, loc.getY());
			stmt.setDouble(6, loc.getZ());
			stmt.execute();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when inserting home location");
			return false;
		}
	}
	
	public static boolean updateHome (Player player, String homeName, Server server) {
		try {
			Location loc = player.getLocation();
			PreparedStatement stmt = dbcon.prepareStatement("update player_home set world_name=?, x=?, y=?, z=? where player_id=? and home_name=?;");
			stmt.setString(5, player.getUniqueId().toString());
			stmt.setString(6, homeName);
			stmt.setString(1, loc.getWorld().getName());
			stmt.setDouble(2, loc.getX());
			stmt.setDouble(3, loc.getY());
			stmt.setDouble(4, loc.getZ());
			stmt.execute();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when updating home location");
			return false;
		}
	}
	
	public static boolean deleteHome (Player player, String homeName, Server server) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("delete from player_home where player_id=? and home_name=?;");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.execute();
			stmt = dbcon.prepareStatement("delete from player_home_share where owner_id=? and home_name=?;");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.execute();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when deleting home location");
			return false;
		}
	}
	
	public static boolean getHomeShare (Player owner, Player peer, String homeName) {
		if (owner.getUniqueId().equals(peer.getUniqueId())) {
			return true;
		}
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("select 1 from player_home_share where owner_id=? and home_name=? and (shared_with=? or shared_with=?);");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.setString(3, peer.getUniqueId().toString());
			stmt.setString(4, "*");
			ResultSet r = stmt.executeQuery();
			return r.next();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting home share");
			return false;
		}
	}
	
	public static boolean getHomeShareStrict (Player owner, Player peer, String homeName) {
		if (owner.getUniqueId().equals(peer.getUniqueId())) {
			return true;
		}
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("select 1 from player_home_share where owner_id=? and home_name=? and shared_with=?;");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.setString(3, peer.getUniqueId().toString());
			ResultSet r = stmt.executeQuery();
			return r.next();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting home share");
			return false;
		}
	}
	
	public static boolean getHomeShareAll (Player owner, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("select 1 from player_home_share where owner_id=? and home_name=? and shared_with=?;");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.setString(3, "*");
			ResultSet r = stmt.executeQuery();
			return r.next();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting home share");
			return false;
		}
	}
	
	public static boolean insertHomeShare (Player owner, Player peer, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("insert into player_home_share (owner_id, shared_with, home_name) values (?, ?, ?);");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, peer.getUniqueId().toString());
			stmt.setString(3, homeName);
			stmt.execute();
			stmt.close();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when inserting home share");
			return false;
		}
	}
	
	public static boolean insertHomeShareAll (Player owner, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("insert into player_home_share (owner_id, shared_with, home_name) values (?, ?, ?);");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, "*");
			stmt.setString(3, homeName);
			stmt.execute();
			stmt.close();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when inserting home share");
			return false;
		}
	}
	
	public static boolean deleteHomeShare (Player owner, Player peer, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("delete from player_home_share where owner_id=? and shared_with=? and home_name=?;");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, peer.getUniqueId().toString());
			stmt.setString(3, homeName);
			stmt.execute();
			stmt.close();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when deleting home share");
			return false;
		}
	}
	
	public static boolean deleteHomeShareAll (Player owner, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("delete from player_home_share where owner_id=? and shared_with=? and home_name=?;");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, "*");
			stmt.setString(3, homeName);
			stmt.execute();
			stmt.close();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when deleting home share");
			return false;
		}
	}
	
	public static boolean deleteHomeShareAtAll (Player owner, String homeName) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("delete from player_home_share where owner_id=? and home_name=?;");
			stmt.setString(1, owner.getUniqueId().toString());
			stmt.setString(2, homeName);
			stmt.execute();
			stmt.close();
			return true;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when deleting home share");
			return false;
		}
	}
}
