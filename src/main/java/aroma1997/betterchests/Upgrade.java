/**
 * The code of BetterChests and all related materials like textures is copyrighted material.
 * It may only be redistributed or used for Commercial purposes with the permission of Aroma1997.
 * 
 * All Rights reserved (c) by Aroma1997
 * 
 * See https://github.com/Aroma1997/BetterChests/blob/master/LICENSE.md for more information.
 */

package aroma1997.betterchests;

import aroma1997.betterchests.upgrades.Animal;
import aroma1997.betterchests.upgrades.BasicUpgrade;
import aroma1997.betterchests.upgrades.CobbleGen;
import aroma1997.betterchests.upgrades.Collector;
import aroma1997.betterchests.upgrades.Feeding;
import aroma1997.betterchests.upgrades.Furnace;
import aroma1997.betterchests.upgrades.Harvesting;
import aroma1997.betterchests.upgrades.Null;
import aroma1997.betterchests.upgrades.Planting;
import aroma1997.betterchests.upgrades.PlayerFeeding;
import aroma1997.betterchests.upgrades.Rain;
import aroma1997.betterchests.upgrades.Resupply;
import aroma1997.betterchests.upgrades.Ticking;
import aroma1997.core.util.AromaRegistry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public enum Upgrade {
	SLOT(null, 7, true, true, Null.class),
	COBBLEGEN(null, 1, true, true, CobbleGen.class),
	REDSTONE(null, 1, true, false, Null.class),
	LIGHT(null, 1, true, false, Null.class),
	BASIC(null, 0, false, false, Null.class),
	COMPARATOR(null, 1, true, false, Null.class),
	VOID(null, 1, true, true, Null.class),
	UNBREAKABLE(null, 1, true, false, Null.class),
	PLAYER(UNBREAKABLE, 1, true, false, Null.class),
	RAIN(null, 1, true, false, Rain.class),
	ENERGY(null, 1, true, true, Null.class),
	FURNACE(ENERGY, 1, true, true, Furnace.class),
	COLLECTOR(ENERGY, 8, true, true, Collector.class),
	TICKING(ENERGY, 1, true, false, Ticking.class),
	FEEDING(null, 1, true, false, Feeding.class),
	PLAYERFOOD(null, 1, false, true, PlayerFeeding.class),
	RESUPPLY(null, 1, false, true, Resupply.class),
	PLANTING(ENERGY, 5, true, false, Planting.class),
	HARVESTING(ENERGY, 5, true, false, Harvesting.class),
	ANIMAL(ENERGY, 1, true, false, Animal.class);
	
	private final Upgrade requirement;
	
	private final int max;
	
	private final boolean canChest;
	
	private final boolean canBag;
	
	private BasicUpgrade upgrade;
	
	private Upgrade(Upgrade requirement, int max, boolean canChest, boolean canBag,
	        Class<? extends BasicUpgrade> claSS) {
		this.requirement = requirement;
		this.max = max;
		this.canChest = canChest;
		this.canBag = canBag;
		
		try {
			upgrade = claSS.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return StatCollector.translateToLocal("item.betterchests:upgrade." + toString() + ".name");
	}
	
	public String getTexture() {
		return Reference.MOD_ID + ":" + toString().toLowerCase();
	}
	
	public Upgrade getRequirement() {
		return requirement;
	}
	
	public static void generateRecipes() {
		Item item = BetterChests.upgrade;
		ItemStack itemUpgrade = new ItemStack(item, 1, Upgrade.BASIC.ordinal());
		// BASIC
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 8, Upgrade.BASIC.ordinal()),
		        false, "WIW", "ISI", "WIW", 'W', "plankWood", 'I', new ItemStack(Blocks.iron_bars),
		        'S', "stickWood");
		// SLOT
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.SLOT.ordinal()),
		        false, " W ", "WUW", " W ", 'W', "plankWood", 'U', itemUpgrade);
		// REDSTONE
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.REDSTONE.ordinal()), false, "RRR", "RUR", "RRR",
		        'R', new ItemStack(Items.redstone), 'U', itemUpgrade);
		// LIGHT
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.LIGHT.ordinal()),
		        false, " G ", "GUG", " G ", 'G', new ItemStack(Items.glowstone_dust), 'U',
		        itemUpgrade);
		// COMPARATOR
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.COMPARATOR.ordinal()), false, " Q ", "RUR", " Q ",
		        'Q', new ItemStack(Items.quartz), 'R', new ItemStack(Items.redstone), 'U',
		        itemUpgrade);
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.COMPARATOR.ordinal()), false, " R ", "QUQ", " R ",
		        'Q', new ItemStack(Items.quartz), 'R', new ItemStack(Items.redstone), 'U',
		        itemUpgrade);
		// PLAYER
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.PLAYER.ordinal()),
		        false, "OQO", "QUQ", "OQO", 'Q', new ItemStack(Items.quartz), 'O', new ItemStack(
		                Blocks.obsidian), 'U', itemUpgrade);
		// COBBLEGEN
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.COBBLEGEN.ordinal()), false, "CCC", "BUB", "CCC",
		        'C', "cobblestone", 'U', itemUpgrade, 'B', new ItemStack(Items.bucket));
		// VOID
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.VOID.ordinal()),
		        false, " E ", "RUR", " E ", 'E', new ItemStack(Items.ender_pearl), 'R',
		        new ItemStack(Items.redstone), 'U', itemUpgrade);
		// UNBREAKABLE
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.UNBREAKABLE.ordinal()), false, "OOO", "OUO", "OOO",
		        'O', new ItemStack(Blocks.obsidian), 'U', itemUpgrade);
		// RAIN
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.RAIN.ordinal()),
		        false, " B ", "BUB", " B ", 'B', new ItemStack(Items.bucket), 'U', itemUpgrade);
		// ENERGY
		AromaRegistry
		        .registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.ENERGY.ordinal()),
		                false, "BRB", "CUC", "BRB", 'B', new ItemStack(Blocks.coal_block), 'R',
		                new ItemStack(Items.redstone), 'C', new ItemStack(Items.repeater), 'U',
		                itemUpgrade);
		AromaRegistry
		        .registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.ENERGY.ordinal()),
		                false, "BCB", "RUR", "BCB", 'B', new ItemStack(Blocks.coal_block), 'R',
		                new ItemStack(Items.redstone), 'C', new ItemStack(Items.repeater), 'U',
		                itemUpgrade);
		// FURNACE
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, Upgrade.FURNACE.ordinal()),
		        false, "RFR", "FUF", "RFR", 'R', new ItemStack(Items.redstone), 'F', new ItemStack(
		                Blocks.furnace), 'U', itemUpgrade);
		// COLLECTOR
		AromaRegistry.registerShapedAromicRecipe(
		        new ItemStack(item, 1, Upgrade.COLLECTOR.ordinal()), false, " H ", "HUH", "RER",
		        'H', new ItemStack(Blocks.hopper), 'R', new ItemStack(Items.redstone), 'E',
		        new ItemStack(Items.ender_pearl), 'U', itemUpgrade);
		// TICKING
		// AromaRegistry.registerShapedAromicRecipe(
		// new ItemStack(item, 1, Upgrade.TICKING.ordinal()), false,
		// "QCQ", "RUR", "QCQ", 'Q', new ItemStack(Items.quartz), 'C', new
		// ItemStack(
		// Items.clock), 'R', new ItemStack(Items.comparator), 'U',
		// itemUpgrade);
		// FEEDING
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, FEEDING.ordinal()), false,
		        " W ", "WUW", " W ", 'W', new ItemStack(Items.wheat), 'U', itemUpgrade);
		// PLAYERFEEDING
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, PLAYERFOOD.ordinal()),
		        false, "FFF", "FUF", "FFF", 'F', ItemFood.class, 'U', itemUpgrade);
		// RESUPPLY
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, RESUPPLY.ordinal()), false,
		        "CUC", " H ", 'C', new ItemStack(Blocks.chest), 'H', new ItemStack(Blocks.hopper),
		        'U', itemUpgrade);
		// PLANTING
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, PLANTING.ordinal()), false,
		        "SHS", "HUH", "SHS", 'S', IPlantable.class, 'H', new ItemStack(Items.iron_hoe),
		        'U', itemUpgrade);
		// HARVESTING
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, HARVESTING.ordinal()),
		        false, "WWW", "WUW", "WWW", 'W', new ItemStack(Items.wheat), 'U', itemUpgrade);
		// ANIMAL
		AromaRegistry.registerShapedAromicRecipe(new ItemStack(item, 1, ANIMAL.ordinal()), false, " S ", "BUB", " S ",
				'S', new ItemStack(Items.shears), 'B', new ItemStack(Items.bucket), 'U', itemUpgrade);
		
		AromaRegistry.registerShapelessAromicRecipe(BASIC.getItem(), true, new ItemStack(item, 1,
		        OreDictionary.WILDCARD_VALUE));
	}
	
	public int getMaxAmount() {
		return max;
	}
	
	public boolean canChestTakeUpgrade() {
		return canChest;
	}
	
	public boolean canBagTakeUpgrade() {
		return canBag;
	}
	
	public static void addBagBookDescription(ArrayList<String> list) {
		for (Upgrade upgrade : Upgrade.values()) {
			list.add("book.betterchests:upgrade." + upgrade);
		}
	}
	
	public ItemStack getItem() {
		return new ItemStack(BetterChests.upgrade, 1, ordinal());
	}
	
	public BasicUpgrade getUpgrade() {
		return upgrade;
	}
}
