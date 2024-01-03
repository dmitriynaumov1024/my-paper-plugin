package org.naumov.mypaperplugin.kit;

import org.bukkit.Material;
import org.naumov.mypaperplugin.gamble.Prize;

public class KitStart extends Kit
{
	static Prize[] items = new Prize[] {
		new Prize(Material.CHAINMAIL_BOOTS).amount(1),
		new Prize(Material.CHAINMAIL_LEGGINGS).amount(1),
		new Prize(Material.CHAINMAIL_CHESTPLATE).amount(1),
		new Prize(Material.CHAINMAIL_HELMET).amount(1),
		new Prize(Material.OAK_LOG).amount(32),
		new Prize(Material.COBBLESTONE).amount(32),
		new Prize(Material.IRON_INGOT).amount(64),
		new Prize(Material.COAL).amount(32),
		new Prize(Material.COOKED_BEEF).amount(32)
	};
	
	protected Prize[] getPrizes() {
		return items;
	}
	
	public long getCooldown() {
		return 7200; // 2 hours
	}
	
	public String getName() {
		return "Start";
	}
}
