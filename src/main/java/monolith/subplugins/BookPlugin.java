package monolith.subplugins;

import org.bukkit.command.CommandExecutor;

import monolith.command.book.BookCommandHandler;
import monolith.plugin.SubPlugin;

public class BookPlugin extends SubPlugin 
{
	private CommandExecutor bookHandler;
	
	public BookPlugin() {
		
	}
	
	public void onEnable() {
		this.bookHandler = new BookCommandHandler(this.getServer());
		this.addCommandHandler("book", this.bookHandler);
	}
}
