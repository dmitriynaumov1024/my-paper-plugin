package org.naumov.mypaperplugin.kit;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.naumov.mypaperplugin.gamble.Prize;

public class KitLevel2 extends Kit
{
	static Prize[] items = new Prize[] {
		new Prize(Material.DIAMOND_BOOTS).amount(1),
		new Prize(Material.DIAMOND_LEGGINGS).amount(1),
		new Prize(Material.DIAMOND_CHESTPLATE).amount(1),
		new Prize(Material.DIAMOND_HELMET).amount(1),
		new Prize(Material.BOW).amount(1),
		new Prize(Material.ARROW).amount(32),
		new Prize(Material.COOKED_BEEF).amount(32),
		new Prize(Material.ENCHANTED_GOLDEN_APPLE).amount(4),
		new Prize(Material.SADDLE).amount(1)
	};
	
	protected Prize[] getPrizes() {
		return items;
	}
	
	public long getCooldown() {
		return 86400; // 1 day
	}
	
	public String getName() {
		return "Level-2";
	}
	
	@Override
	public boolean canGiveTo (Player player) {
		return super.canGiveTo(player) &&
			player.getStatistic(Statistic.PLAY_ONE_MINUTE) > 43200 * 20; // at least 12 hours playtime
	}
}
