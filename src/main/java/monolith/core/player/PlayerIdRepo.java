package monolith.core.player;

import java.sql.*;

import org.bukkit.entity.Player;

import monolith.dbcon.DbConnection;

public class PlayerIdRepo 
{
	private static Connection dbcon = getAndPrepareDb();
	
	private static Connection getAndPrepareDb() {
		try {
			Connection con = DbConnection.instance();
			con.prepareStatement(
				"create table if not exists player_uid (" +
				"player_id varchar(44)," +
				"player_name varchar(128)," +
				"primary key(player_id, player_name)" +
				");").execute();
			return con;
		} 
		catch (Exception ex) {
			System.out.println("Something went wrong when preparing database");
			return null;
		}
	}
	
	public static void putPlayer (Player player) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("select 1 from player_uid where player_name=?");
			stmt.setString(1, player.getName());
			ResultSet r = stmt.executeQuery();
			if (r.next()) {
				stmt.close();
				stmt = dbcon.prepareStatement("update player_uid set player_id=? where player_name=?;");
				stmt.setString(1, player.getUniqueId().toString());
				stmt.setString(2, player.getName());
			}
			else {
				stmt.close();
				stmt = dbcon.prepareStatement("insert into player_uid (player_id, player_name) values (?, ?);");
				stmt.setString(1, player.getUniqueId().toString());
				stmt.setString(2, player.getName());
			}
			stmt.execute();
			stmt.close();
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when putting player");
		}
	}
	
	public static Player getByName (String name) {
		try {
			PreparedStatement stmt;
			stmt = dbcon.prepareStatement("select player_id, player_name from player_uid where player_name=?;");
			stmt.setString(1, name);
			ResultSet r = stmt.executeQuery();
			if (r.next()) {
				return new PlayerWithUID(r.getString("player_id"), r.getString("player_name"));
			}
			return null;
		}
		catch (Exception ex) {
			System.out.println("Something went wrong when getting player by name");
			return null;
		}
	}
}
