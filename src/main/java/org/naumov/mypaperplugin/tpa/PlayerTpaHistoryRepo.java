package org.naumov.mypaperplugin.tpa;

import java.sql.*;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.dbcon.DbConnection;

public class PlayerTpaHistoryRepo
{
	private static Connection dbcon = getAndPrepareDb();
	
	private static Connection getAndPrepareDb() {
		try {
			Connection con = DbConnection.instance();
			con.prepareStatement(
				"create table if not exists player_tpa_history (" +
				"player_id varchar(44)," +
				"world_name varchar(44)," +
				"x double," +
				"y double," +
				"z double," +
				"primary key(player_id)" +
				");").execute();
			return con;
		} 
		catch (Exception ex) {
			System.out.println("Something went wrong when preparing database");
			return null;
		}
	}
	
	public static Location getPrevLocation (Player player, Server server) {
		try {
			PreparedStatement stmt = dbcon.prepareStatement("select world_name, x, y, z from player_tpa_history where player_id=?;");
			stmt.setString(1, player.getUniqueId().toString());
			ResultSet r = stmt.executeQuery();
			if (r.next()) {
				return new Location(server.getWorld(r.getString("world_name")), r.getDouble("x"), r.getDouble("y"), r.getDouble("z"));
			}
			else {
				insertPrevLocation(player);
			}
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting previous location");
		}
		return player.getLocation();
	}
	
	public static void insertPrevLocation (Player player) {
		try {
			Location loc = player.getLocation();
			PreparedStatement stmt = dbcon.prepareStatement("insert into player_tpa_history (player_id, world_name, x, y, z) values (?, ?, ?, ?, ?);");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, loc.getWorld().getName());
			stmt.setDouble(3, loc.getX());
			stmt.setDouble(4, loc.getY());
			stmt.setDouble(5, loc.getZ());
			stmt.execute();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when inserting previous location");
		}
	}
	
	public static void updatePrevLocation (Player player) {
		try {
			Location loc = player.getLocation();
			PreparedStatement stmt = dbcon.prepareStatement("update player_tpa_history set world_name=?, x=?, y=?, z=? where player_id=?;");
			stmt.setString(5, player.getUniqueId().toString());
			stmt.setString(1, loc.getWorld().getName());
			stmt.setDouble(2, loc.getX());
			stmt.setDouble(3, loc.getY());
			stmt.setDouble(4, loc.getZ());
			stmt.execute();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when updating previous location");
		}
	}
}
