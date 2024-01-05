package org.naumov.mypaperplugin.player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.BanEntry;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent.Reason;
import org.bukkit.event.player.PlayerKickEvent.Cause;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.block.TargetBlockInfo.FluidMode;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import com.destroystokyo.paper.profile.PlayerProfile;

import io.papermc.paper.entity.LookAnchor;
import io.papermc.paper.entity.TeleportFlag;
import io.papermc.paper.math.Position;
import io.papermc.paper.threadedregions.scheduler.EntityScheduler;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.chat.BaseComponent;

public class PlayerWithUID implements Player 
{
	private String name;
	private UUID id;
	
	public PlayerWithUID (UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public PlayerWithUID (String id, String name) {
		this.id = UUID.fromString(id);
		this.name = name;
	}
	
	@Override
	public @NotNull UUID getUniqueId() {
		return this.id;
	}
	
	@Override
	public @NotNull String getName() {
		return this.name;
	}
	
	// unused auto-generated stubs
	//
	@Override
	public @NotNull EntityEquipment getEquipment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull PlayerInventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Inventory getEnderChest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull MainHand getMainHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setWindowProperty(@NotNull Property prop, int value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEnchantmentSeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEnchantmentSeed(int seed) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull InventoryView getOpenInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openInventory(@NotNull Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openWorkbench(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openEnchanting(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(@NotNull InventoryView inventory) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable InventoryView openMerchant(@NotNull Villager trader, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openMerchant(@NotNull Merchant merchant, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openAnvil(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openCartographyTable(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openGrindstone(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openLoom(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openSmithingTable(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InventoryView openStonecutter(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(@NotNull Reason reason) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull ItemStack getItemInHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemInHand(@Nullable ItemStack item) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull ItemStack getItemOnCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItemOnCursor(@Nullable ItemStack item) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasCooldown(@NotNull Material material) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCooldown(@NotNull Material material) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCooldown(@NotNull Material material, int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHurtDirection(float hurtDirection) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDeeplySleeping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSleepTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @Nullable Location getPotentialBedLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable FishHook getFishHook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sleep(@NotNull Location location, boolean force) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void wakeup(boolean setSpawnLocation) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Location getBedLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull GameMode getGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGameMode(@NotNull GameMode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandRaised() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @Nullable ItemStack getItemInUse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExpToLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @Nullable Entity releaseLeftShoulderEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Entity releaseRightShoulderEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAttackCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean discoverRecipe(@NotNull NamespacedKey recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int discoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean undiscoverRecipe(@NotNull NamespacedKey recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int undiscoverRecipes(@NotNull Collection<NamespacedKey> recipes) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasDiscoveredRecipe(@NotNull NamespacedKey recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull Set<NamespacedKey> getDiscoveredRecipes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Entity getShoulderEntityLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShoulderEntityLeft(@Nullable Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Entity getShoulderEntityRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShoulderEntityRight(@Nullable Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dropItem(boolean dropAll) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getExhaustion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExhaustion(float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getSaturation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSaturation(float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFoodLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFoodLevel(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSaturatedRegenRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSaturatedRegenRate(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getUnsaturatedRegenRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUnsaturatedRegenRate(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStarvationRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStarvationRate(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Location getLastDeathLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastDeathLocation(@Nullable Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Firework fireworkBoost(@NotNull ItemStack fireworkItemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEyeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getEyeHeight(boolean ignorePose) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @NotNull Location getEyeLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Block getTargetBlock(int maxDistance, @NotNull FluidMode fluidMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidMode fluidMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidCollisionMode fluidMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable TargetBlockInfo getTargetBlockInfo(int maxDistance, @NotNull FluidMode fluidMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable RayTraceResult rayTraceEntities(int maxDistance, boolean ignoreBlocks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Block getTargetBlockExact(int maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable RayTraceResult rayTraceBlocks(double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemainingAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRemainingAir(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaximumAir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumAir(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getArrowCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setArrowCooldown(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getArrowsInBody() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setArrowsInBody(int count, boolean fireEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBeeStingerCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBeeStingerCooldown(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBeeStingersInBody() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBeeStingersInBody(int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaximumNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLastDamage(double damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNoDamageTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNoDamageTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNoActionTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNoActionTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Player getKiller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setKiller(@Nullable Player killer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addPotionEffect(@NotNull PotionEffect effect) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPotionEffect(@NotNull PotionEffectType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @Nullable PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePotionEffect(@NotNull PotionEffectType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Collection<PotionEffect> getActivePotionEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean clearActivePotionEffects() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasLineOfSight(@NotNull Entity other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasLineOfSight(@NotNull Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRemoveWhenFarAway(boolean remove) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCanPickupItems(boolean pickup) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getCanPickupItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeashed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull Entity getLeashHolder() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setLeashHolder(@Nullable Entity holder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGliding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGliding(boolean gliding) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSwimming() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSwimming(boolean swimming) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRiptiding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSleeping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClimbing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAI(boolean ai) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void attack(@NotNull Entity target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swingMainHand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swingOffHand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playHurtAnimation(float yaw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCollidable(boolean collidable) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull Set<UUID> getCollidableExemptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Sound getHurtSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Sound getDeathSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getFallDamageSound(int fallHeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getFallDamageSoundSmall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getFallDamageSoundBig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getDrinkingSound(@NotNull ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getEatingSound(@NotNull ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canBreatheUnderwater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull EntityCategory getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInvisible(boolean invisible) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInvisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getArrowsStuck() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setArrowsStuck(int arrows) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getShieldBlockingDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setShieldBlockingDelay(int delay) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull ItemStack getActiveItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearActiveItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getItemUseRemainingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHandRaisedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @NotNull EquipmentSlot getHandRaised() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isJumping() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJumping(boolean jumping) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playPickupItemAnimation(@NotNull Item item, int quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getHurtDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void knockback(double strength, double directionX, double directionZ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcastSlotBreak(@NotNull EquipmentSlot slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcastSlotBreak(@NotNull EquipmentSlot slot, @NotNull Collection<Player> players) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull ItemStack damageItemStack(@NotNull ItemStack stack, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void damageItemStack(@NotNull EquipmentSlot slot, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getBodyYaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBodyYaw(float bodyYaw) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable AttributeInstance getAttribute(@NotNull Attribute attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerAttribute(@NotNull Attribute attribute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void damage(double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void damage(double amount, @Nullable Entity source) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getAbsorptionAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAbsorptionAmount(double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHealth(double health) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetMaxHealth() {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Location getLocation(@Nullable Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVelocity(@NotNull Vector velocity) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @NotNull BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInWater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean teleport(@NotNull Location location, @NotNull TeleportCause cause,
			@NotNull TeleportFlag @NotNull... teleportFlags) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(@NotNull Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(@NotNull Location location, @NotNull TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(@NotNull Entity destination) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean teleport(@NotNull Entity destination, @NotNull TeleportCause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull List<Entity> getNearbyEntities(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEntityId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFireTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVisualFire(boolean fire) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisualFire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFreezeTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxFreezeTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFreezeTicks(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFrozen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFreezeTickingLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void lockFreezeTicks(boolean locked) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPersistent(boolean persistent) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setPassenger(@NotNull Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull List<Entity> getPassengers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPassenger(@NotNull Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePassenger(@NotNull Entity passenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getFallDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFallDistance(float distance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLastDamageCause(@Nullable EntityDamageEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable EntityDamageEvent getLastDamageCause() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTicksLived() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTicksLived(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playEffect(@NotNull EntityEffect type) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull EntityType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getSwimSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getSwimSplashSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Sound getSwimHighSpeedSplashSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInsideVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean leaveVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @Nullable Entity getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomNameVisible(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCustomNameVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisibleByDefault(boolean visible) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isVisibleByDefault() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGlowing(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isGlowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInvulnerable(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInvulnerable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSilent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSilent(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasGravity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setGravity(boolean gravity) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPortalCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPortalCooldown(int cooldown) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Set<String> getScoreboardTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addScoreboardTag(@NotNull String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeScoreboardTag(@NotNull String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull PistonMoveReaction getPistonMoveReaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull BlockFace getFacing() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Pose getPose() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPose(@NotNull Pose pose, boolean fixed) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasFixedPose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull SpawnCategory getSpawnCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Component teamDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Location getOrigin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fromMobSpawner() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull SpawnReason getEntitySpawnReason() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUnderWater() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInRain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInBubbleColumn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInWaterOrRain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInWaterOrBubbleColumn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInWaterOrRainOrBubbleColumn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInLava() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTicking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull Set<Player> getTrackedPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean spawnAt(@NotNull Location location, @NotNull SpawnReason reason) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInPowderedSnow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean collidesAt(@NotNull Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wouldCollideUsing(@NotNull BoundingBox boundingBox) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull EntityScheduler getScheduler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull String getScoreboardEntryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull List<MetadataValue> getMetadata(@NotNull String metadataKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMetadata(@NotNull String metadataKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(@NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(@NotNull String... messages) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(@Nullable UUID sender, @NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(@Nullable UUID sender, @NotNull String... messages) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Component name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermissionSet(@NotNull String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermissionSet(@NotNull Permission perm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(@NotNull String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(@NotNull Permission perm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value,
			int ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAttachment(@NotNull PermissionAttachment attachment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recalculatePermissions() {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOp(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Component customName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customName(@Nullable Component customName) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable String getCustomName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomName(@Nullable String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull PersistentDataContainer getPersistentDataContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile,
			@Nullable Vector velocity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile,
			@Nullable Vector velocity, @Nullable Consumer<T> function) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull TriState getFrictionState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFrictionState(@NotNull TriState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConversing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptConversationInput(@NotNull String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean beginConversation(@NotNull Conversation conversation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void abandonConversation(@NotNull Conversation conversation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendRawMessage(@Nullable UUID sender, @NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOnline() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBanned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Date expires,
			@Nullable String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason,
			@Nullable Instant expires, @Nullable String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason,
			@Nullable Duration duration, @Nullable String source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWhitelisted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWhitelisted(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getFirstPlayed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLastPlayed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasPlayedBefore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getLastLogin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLastSeen() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, int newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Set<String> getListeningPluginChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProtocolVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @Nullable InetSocketAddress getVirtualHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @UnmodifiableView @NotNull Iterable<? extends BossBar> activeBossBars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Component displayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayName(@Nullable Component displayName) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDisplayName(@Nullable String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerListName(@Nullable Component name) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Component playerListName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Component playerListHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Component playerListFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull String getPlayerListName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerListName(@Nullable String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable String getPlayerListHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable String getPlayerListFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerListHeader(@Nullable String header) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerListFooter(@Nullable String footer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCompassTarget(@NotNull Location loc) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Location getCompassTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable InetSocketAddress getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendRawMessage(@NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kickPlayer(@Nullable String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kick(@Nullable Component message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void kick(@Nullable Component message, @NotNull Cause cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason, @Nullable Date expires,
			@Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason,
			@Nullable Instant expires, @Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends BanEntry<? super PlayerProfile>> @Nullable E ban(@Nullable String reason,
			@Nullable Duration duration, @Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Date expires,
			@Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Instant expires,
			@Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Duration duration,
			@Nullable String source, boolean kickPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chat(@NotNull String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performCommand(@NotNull String command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnGround() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSneaking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSneaking(boolean sneak) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSprinting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSprinting(boolean sprinting) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSleepingIgnored(boolean isSleeping) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSleepingIgnored() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @Nullable Location getBedSpawnLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBedSpawnLocation(@Nullable Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBedSpawnLocation(@Nullable Location location, boolean force) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playNote(@NotNull Location loc, byte instrument, byte note) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category,
			float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category,
			float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull Sound sound, float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull String sound, float volume, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume,
			float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull String sound, @NotNull SoundCategory category, float volume,
			float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSound(@NotNull Sound sound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSound(@NotNull String sound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSound(@NotNull Sound sound, @Nullable SoundCategory category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSound(@NotNull String sound, @Nullable SoundCategory category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSound(@NotNull SoundCategory category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopAllSounds() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playEffect(@NotNull Location loc, @NotNull Effect effect, int data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void playEffect(@NotNull Location loc, @NotNull Effect effect, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean breakBlock(@NotNull Block block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendBlockChange(@NotNull Location loc, @NotNull Material material, byte data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockChange(@NotNull Location loc, @NotNull BlockData block) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockChanges(@NotNull Collection<BlockState> blocks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockChanges(@NotNull Collection<BlockState> blocks, boolean suppressLightUpdates) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockDamage(@NotNull Location loc, float progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMultiBlockChange(@NotNull Map<? extends Position, BlockData> blockChanges) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockDamage(@NotNull Location loc, float progress, @NotNull Entity source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockDamage(@NotNull Location loc, float progress, int sourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEquipmentChange(@NotNull LivingEntity entity, @NotNull EquipmentSlot slot,
			@Nullable ItemStack item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendEquipmentChange(@NotNull LivingEntity entity, @NotNull Map<EquipmentSlot, ItemStack> items) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable List<? extends Component> lines,
			@NotNull DyeColor dyeColor, boolean hasGlowingText) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable String[] lines) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable String[] lines, @NotNull DyeColor dyeColor,
			boolean hasGlowingText) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBlockUpdate(@NotNull Location loc, @NotNull TileState tileState) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMap(@NotNull MapView map) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showWinScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasSeenWinScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHasSeenWinScreen(boolean hasSeenWinScreen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendActionBar(@NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendActionBar(char alternateChar, @NotNull String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendActionBar(@NotNull BaseComponent... message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerListHeaderFooter(@Nullable BaseComponent[] header, @Nullable BaseComponent[] footer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerListHeaderFooter(@Nullable BaseComponent header, @Nullable BaseComponent footer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSubtitle(BaseComponent[] subtitle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSubtitle(BaseComponent subtitle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTitle(@Nullable BaseComponent[] title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTitle(@Nullable BaseComponent title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTitle(@Nullable BaseComponent[] title, @Nullable BaseComponent[] subtitle, int fadeInTicks,
			int stayTicks, int fadeOutTicks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTitle(@Nullable BaseComponent title, @Nullable BaseComponent subtitle, int fadeInTicks,
			int stayTicks, int fadeOutTicks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendTitle(@NotNull Title title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTitle(@NotNull Title title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendHurtAnimation(float yaw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCustomChatCompletions(@NotNull Collection<String> completions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCustomChatCompletions(@NotNull Collection<String> completions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCustomChatCompletions(@NotNull Collection<String> completions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable GameMode getPreviousGameMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerTime(long time, boolean relative) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getPlayerTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getPlayerTimeOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPlayerTimeRelative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPlayerTime() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayerWeather(@NotNull WeatherType type) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable WeatherType getPlayerWeather() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPlayerWeather() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getExpCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExpCooldown(int ticks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void giveExp(int amount, boolean applyMending) {
		// TODO Auto-generated method stub

	}

	@Override
	public int applyMending(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void giveExpLevels(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getExp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExp(float exp) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTotalExperience() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTotalExperience(int exp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendExperienceChange(float progress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendExperienceChange(float progress, int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getAllowFlight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAllowFlight(boolean flight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFlyingFallDamage(@NotNull TriState flyingFallDamage) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull TriState hasFlyingFallDamage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hidePlayer(@NotNull Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showPlayer(@NotNull Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showPlayer(@NotNull Plugin plugin, @NotNull Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canSee(@NotNull Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hideEntity(@NotNull Plugin plugin, @NotNull Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showEntity(@NotNull Plugin plugin, @NotNull Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canSee(@NotNull Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isListed(@NotNull Player other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlistPlayer(@NotNull Player other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean listPlayer(@NotNull Player other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFlying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlying(boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFlySpeed(float value) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWalkSpeed(float value) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public float getFlySpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWalkSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTexturePack(@NotNull String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @Nullable byte[] hash) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @Nullable byte[] hash, @Nullable String prompt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @Nullable byte[] hash, boolean force) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @Nullable byte[] hash, @Nullable String prompt, boolean force) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, byte @Nullable [] hash, @Nullable Component prompt,
			boolean force) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Scoreboard getScoreboard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScoreboard(@NotNull Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable WorldBorder getWorldBorder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWorldBorder(@Nullable WorldBorder border) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendHealthUpdate(double health, int foodLevel, float saturation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendHealthUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHealthScaled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHealthScaled(boolean scale) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHealthScale(double scale) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHealthScale() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @Nullable Entity getSpectatorTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpectatorTarget(@Nullable Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendTitle(@Nullable String title, @Nullable String subtitle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetTitle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count,
			@Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, double extra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, double extra, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, @Nullable T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull AdvancementProgress getAdvancementProgress(@NotNull Advancement advancement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClientViewDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @NotNull Locale locale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @NotNull String getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAffectsSpawning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAffectsSpawning(boolean affects) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getViewDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setViewDistance(int viewDistance) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSimulationDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSimulationDistance(int simulationDistance) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNoTickViewDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNoTickViewDistance(int viewDistance) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSendViewDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSendViewDistance(int viewDistance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCommands() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openBook(@NotNull ItemStack book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openSign(@NotNull Sign sign) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openSign(@NotNull Sign sign, @NotNull Side side) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showDemoScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAllowingServerListings() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setResourcePack(@NotNull String url, @NotNull String hash) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @NotNull String hash, boolean required) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResourcePack(@NotNull String url, @NotNull String hash, boolean required,
			@Nullable Component resourcePackPrompt) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable Status getResourcePackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable String getResourcePackHash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasResourcePack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public @NotNull PlayerProfile getPlayerProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerProfile(@NotNull PlayerProfile profile) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getCooldownPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCooledAttackStrength(float adjustTicks) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetCooldown() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> @NotNull T getClientOption(@NotNull ClientOption<T> option) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Firework boostElytra(@NotNull ItemStack firework) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendOpLevel(byte level) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAdditionalChatCompletions(@NotNull Collection<String> completions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAdditionalChatCompletions(@NotNull Collection<String> completions) {
		// TODO Auto-generated method stub

	}

	@Override
	public @Nullable String getClientBrandName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRotation(float yaw, float pitch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lookAt(double x, double y, double z, @NotNull LookAnchor playerAnchor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lookAt(@NotNull Entity entity, @NotNull LookAnchor playerAnchor, @NotNull LookAnchor entityAnchor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showElderGuardian(boolean silent) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWardenWarningCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWardenWarningCooldown(int cooldown) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWardenTimeSinceLastWarning() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWardenTimeSinceLastWarning(int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWardenWarningLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWardenWarningLevel(int warningLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseWardenWarningLevel() {
		// TODO Auto-generated method stub

	}

	@Override
	public @NotNull Spigot spigot() {
		// TODO Auto-generated method stub
		return null;
	}

}
