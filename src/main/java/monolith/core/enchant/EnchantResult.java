package monolith.core.enchant;

import java.util.*;

public class EnchantResult 
{	
	public boolean 
	ignore = false,
	info = false,
	cancelled = false,
	prepared = false,
	confirmed = false,
	tooExpensive = false,
	targetEmpty = false,
	targetAlreadyEnchanted = false,
	targetNotPrepared = false,
	targetNotSupported = false,
	targetTypeMismatch = false;
	
	public int price = 0;
	
	public List<Ench> enchantments = new ArrayList<>();
	
	public EnchantResult addEnchant(String id, int level) {
		this.enchantments.add(new Ench(id, level));
		return this;
	}
	
	public class Ench 
	{
		public String id;
		public int level;
	
		public Ench(String id, int level) {
			this.id = id;
			this.level = level;
		}
	}
}
