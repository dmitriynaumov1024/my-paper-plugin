package monolith.subplugins;

import monolith.dbcon.DbConnection;
import monolith.plugin.SubPlugin;

public class DbConnectionPlugin extends SubPlugin 
{
	// sets up database connection that will be used by 
	// all other subplugins of this monolith plugin
	
	private Object connectionLock;
	
	public DbConnectionPlugin() {
		
	}
	
	public void onEnable() {
		this.connectionLock = DbConnection.createConnection();
	}
	
	public void onDisable() {
		DbConnection.disposeConnection(this.connectionLock);
		this.connectionLock = null;
	}
}
