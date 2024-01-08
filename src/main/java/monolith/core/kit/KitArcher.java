package monolith.core.kit;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import monolith.core.gamble.Prize;

public class KitArcher extends Kit
{
	static Prize[] items = new Prize[] {
			new Prize(Material.BOW)
				.enchant(Enchantment.ARROW_DAMAGE, 3),
			new Prize(Material.POTION)
				.name("Potion of Invisibility")
				.effect(PotionEffectType.INVISIBILITY, 1, 480)
				.amount(4),
			new Prize(Material.TIPPED_ARROW)
				.name("Poisonous tip")
				.effect(PotionEffectType.HARM, 4, 1)
				.amount(9),
			new Prize(Material.TIPPED_ARROW)
				.name("Poisonous tip for undead")
				.effect(PotionEffectType.HEAL, 5, 1)
				.amount(9)
		};
		
		protected Prize[] getPrizes() {
			return items;
		}
		
		public long getCooldown() {
			return 43200; // 12 hours
		}
		
		public String getName() {
			return "Archer";
		}
		
		@Override
		public boolean canGiveTo (Player player) {
			return super.canGiveTo(player) &&
				player.getStatistic(Statistic.PLAY_ONE_MINUTE) > 30 * 20; // at least 1 hour playtime
		}
}
