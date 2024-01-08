package monolith.core.gamble;
//
//import org.bukkit.Material;
//import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Casino 
{
	public static int minLevel = 15;
	
	public static String[] tierNames = new String[] {
		"Common",
		"Rare",
		"Epic",
		"Legendary"
	};
	
	public static Prize[][] tierPrizes = new Prize[][] {
		// common tier
		Tier0.prizes,
		// rare tier
		Tier1.prizes,
		// epic tier
		Tier2.prizes,
		// lego tier
		Tier3.prizes
	};
	
	public static int getTier (Player p) {
		if (p.getLevel() < minLevel) return -1;
		int result = (int)Math.pow((p.getLevel() - minLevel) / minLevel + Math.random() * 1.6, 0.75);
		return Math.max(0, Math.min(tierNames.length - 1, result));
	}
	
	public static Prize getPrize (int tier) {
		Prize[] prizes = tierPrizes[tier];
		int i = (int)Math.floor(Math.random() * prizes.length);
		if (i == prizes.length) i -= 1;
		return prizes[i];
	}
}
