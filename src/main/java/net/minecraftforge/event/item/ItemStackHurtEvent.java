package net.minecraftforge.event.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

/**
 * Fires when calculating damage reduction to ItemStack
 */
public class ItemStackHurtEvent extends PlayerItemStackEvent {

	private final int originalDamage;
	private final Random random;

	private double modifiedDamage;
	private boolean preventBreak = false;

	public ItemStackHurtEvent(Player user, ItemStack stack, int damage, int modifiedDamage, Random random) {
		super(user, stack);
		this.random = random;
		originalDamage = damage;
		this.modifiedDamage = modifiedDamage;
	}

	public int getOriginalDamage() {
		return originalDamage;
	}

	public double getModifiedDamage() {
		return modifiedDamage;
	}

	public boolean isPreventBreak() {
		return preventBreak;
	}

	public int getAndSetRandomizedDamage() {
		int base = (int) Math.floor(modifiedDamage);
		double remainder = modifiedDamage - base;
		int result = base + random.nextFloat() < remainder ? 1 : 0;
		modifiedDamage = result;
		return result;
	}

	public void scaleDamage(double factor) {
		modifiedDamage *= Math.max(0, factor);
	}

	public void setDamage(double damage) {
		modifiedDamage = Math.max(0, damage);
	}

	public void setPreventBreak(boolean preventBreak){
		this.preventBreak = preventBreak;
	}
}
