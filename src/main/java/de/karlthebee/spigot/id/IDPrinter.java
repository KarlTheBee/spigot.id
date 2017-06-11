package de.karlthebee.spigot.id;

import java.util.EnumSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IDPrinter {

	public static void printID(Player p, Set<DetailType> types, Inputs input) {
		IDPrinter printer = new IDPrinter(p, types);
		printer.send(input);
	}

	private Player p;
	private Set<DetailType> types;

	public IDPrinter(Player p, Set<DetailType> types) {
		super();
		this.p = p;
		this.types = types;
	}

	public void send(Inputs input) {
		if (input == null) {
			sendMainHand();
			sendOffHand();
			sendBlock();
			return;
		}

		switch (input) {
		case BLOCK:
			sendBlock();
			break;
		case MAIN_HAND:
			sendMainHand();
			break;
		case OFF_HAND:
			sendOffHand();
			break;
		}

	}

	private void sendMainHand() {
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		if (mainHand != null && mainHand.getType() != Material.AIR)
			sendMessage("Your main hand item is", itemToString(mainHand));
	}

	private void sendOffHand() {
		ItemStack offHand = p.getInventory().getItemInOffHand();
		if (offHand != null && offHand.getType() != Material.AIR)
			sendMessage("Your off hand item is", itemToString(offHand));
	}

	private void sendBlock() {
		Block blockLookingAt = p.getTargetBlock(EnumSet.of(Material.AIR, Material.WATER, Material.STATIONARY_WATER),
				100);
		if (blockLookingAt != null && blockLookingAt.getType() != Material.AIR)
			sendMessage("You're looking at", blockToString(blockLookingAt));
	}

	private void sendMessage(String prefix, String name) {
		p.sendMessage(ChatColor.GRAY + "[ID] " + ChatColor.WHITE + prefix + " " + name);
	}

	@SuppressWarnings("deprecation")
	public String itemToString(ItemStack stack) {
		Material material = stack.getType();

		EntryParent parent = new EntryParent();
		// material name
		parent.append(new Entry("name", formatEnum(material)));
		// oldschool material id's
		parent.append(new EntryParent(new Entry(stack.getTypeId(), stack.getDurability())));
		// amount
		parent.append(new Entry("amount", stack.getAmount() + "/" + stack.getMaxStackSize()));

		// enchantments
		if (types.contains(DetailType.ENCHANTEMENT)) {
			EntryParent enchParent = new EntryParent("enchantements");
			boolean found = false;
			for (Enchantment e : stack.getEnchantments().keySet()) {
				enchParent.append(new Entry(formatEnum(e.getName()), stack.getEnchantments().get(e) + "/" + e.getMaxLevel()));
				found = true;
			}
			if (found)
				parent.append(enchParent);
		}

		return parent.toString();
	}

	@SuppressWarnings("deprecation")
	public String blockToString(Block block) {

		EntryParent parent = new EntryParent();

		parent.append(new Entry("name", formatEnum(block.getType())));
		parent.append(new EntryParent(new Entry(block.getTypeId(), block.getData())));

		if (types.contains(DetailType.BIOME)) {
			EntryParent parentBiome = new EntryParent("biome");

			parentBiome.append("type", formatEnum(block.getBiome()));
			parentBiome.append("humidity", Math.round(block.getHumidity() * 100) + "%");
			parentBiome.append("temperature", Math.round(block.getTemperature() * 100) + "%");
			parent.append(parentBiome);
		}

		if (types.contains(DetailType.REDSTONE)) {
			EntryParent parentRedstone = new EntryParent("redstone");

			parentRedstone.append("redstone power", block.getBlockPower());
			parentRedstone.append("piston move reaction", formatEnum(block.getPistonMoveReaction()));
			parent.append(parentRedstone);
		}

		if (types.contains(DetailType.LOCATON)) {
			EntryParent parentLocation = new EntryParent("location");

			Location l = block.getLocation();

			parentLocation.append("world", l.getWorld().getName());
			parentLocation.append("location (x,y,z)", l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ());
			parentLocation.append("chunk (x,z)", l.getChunk().getX() + "," + l.getChunk().getZ());
			parent.append(parentLocation);
		}

		return parent.toString();

	}

	private static String formatEnum(Object e) {
		return e.toString().toLowerCase().replaceAll("_", " ");
	}

}
