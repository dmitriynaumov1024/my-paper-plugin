package org.naumov.mypaperplugin.kit;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.gamble.Prize;

public class KitLevel1 extends Kit
{
	static Prize[] items = new Prize[] {
		new Prize(Material.IRON_BOOTS).amount(1),
		new Prize(Material.IRON_LEGGINGS).amount(1),
		new Prize(Material.IRON_CHESTPLATE).amount(1),
		new Prize(Material.IRON_HELMET).amount(1),
		new Prize(Material.BOW).amount(1),
		new Prize(Material.ARROW).amount(32),
		new Prize(Material.COOKED_BEEF).amount(32),
		new Prize(Material.GOLDEN_APPLE).amount(4)
	};
	
	protected Prize[] getPrizes() {
		return items;
	}
	
	public long getCooldown() {
		return 86400; // 1 day
	}
	
	public String getName() {
		return "Level-1";
	}
	
	@Override
	public boolean canGiveTo (Player player) {
		return super.canGiveTo(player) &&
			player.getStatistic(Statistic.PLAY_ONE_MINUTE) > 3600 * 20; // at least 1 hour playtime
	}
}
