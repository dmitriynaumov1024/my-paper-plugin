package monolith.core.kit;

import java.sql.*;

import org.bukkit.entity.Player;

import monolith.dbcon.DbConnection;

public class PlayerKitRepo 
{
	private static Connection dbcon = getAndPrepareDb();
	
	private static Connection getAndPrepareDb() {
		try {
			Connection con = DbConnection.instance();
			con.prepareStatement(
				"create table if not exists player_kit (" +
				"player_id varchar(44)," +
				"kit_name varchar(44)," +
				"given_at bigint default 0," +
				"primary key(player_id, kit_name)" +
				");").execute();
			return con;
		} 
		catch (Exception ex) {
			System.out.println("Something went wrong when preparing database");
			return null;
		}
	}
	
	public static long getGivenAt (Player player, Kit kit) {
		try {
			PreparedStatement stmt = dbcon.prepareStatement("select given_at from player_kit where player_id=? and kit_name=?;");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, kit.getName().toLowerCase());
			ResultSet r = stmt.executeQuery();
			if (!r.next()) {
				insertGivenAt(player, kit, 0);
				return 0;
			}
			return r.getLong("given_at");
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when trying to get given at");
			return 0;
		}
	}
	
	public static void insertGivenAt (Player player, Kit kit, long givenAt) {
		try {
			PreparedStatement stmt = dbcon.prepareStatement("insert into player_kit (player_id, kit_name, given_at) values (?, ?, ?);");
			stmt.setString(1, player.getUniqueId().toString());
			stmt.setString(2, kit.getName().toLowerCase());
			stmt.setLong(3, givenAt);
			stmt.execute();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when trying to insert given at");
		}
	}
	
	public static void updateGivenAt (Player player, Kit kit, long givenAt) {
		try {
			PreparedStatement stmt = dbcon.prepareStatement("update player_kit set given_at=? where player_id=? and kit_name=?;");
			stmt.setString(2, player.getUniqueId().toString());
			stmt.setString(3, kit.getName().toLowerCase());
			stmt.setLong(1, givenAt);
			stmt.execute();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when trying to update given at");
		}
	}
}
