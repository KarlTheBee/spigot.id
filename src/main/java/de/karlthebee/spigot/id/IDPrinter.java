package de.karlthebee.spigot.id;

import java.util.EnumSet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IDPrinter {

	public static void printID(Player p, boolean detail) {
		printID(p, p, detail);
	}

	public static void printID(Player p, Player receiver, boolean detail) {
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		ItemStack offHand = p.getInventory().getItemInOffHand();

		Block blockLookingAt = p.getTargetBlock(EnumSet.of(Material.AIR, Material.WATER, Material.STATIONARY_WATER),
				10);

		if (mainHand != null && mainHand.getType() != Material.AIR)
			sendMessage(receiver, "Your main hand item is", itemToString(mainHand, detail));
		if (offHand != null && offHand.getType() != Material.AIR)
			sendMessage(receiver, "Your off hand item is", itemToString(offHand, detail));
		if (blockLookingAt != null && blockLookingAt.getType() != Material.AIR)
			sendMessage(receiver, "You're looking at", blockToString(blockLookingAt, detail));
	}

	private static void sendMessage(Player receiver, String prefix, String name) {
		receiver.sendMessage(ChatColor.GRAY + "[ID] " + ChatColor.WHITE + prefix + " " + name);
	}

	@SuppressWarnings("deprecation")
	public static String itemToString(ItemStack stack, boolean detail) {
		int amount = stack.getAmount();
		Material material = stack.getType();

		StringBuilder builder = new StringBuilder();
		builder.append(ChatColor.YELLOW);
		builder.append(material.name().toLowerCase());
		builder.append(ChatColor.WHITE);
		builder.append(" (");
		builder.append(stack.getTypeId() + ":" + stack.getDurability());
		builder.append(") ");

		if (detail) {
			builder.append("amount: ");
			builder.append(amount);
			builder.append("/");
			builder.append(stack.getMaxStackSize());
			builder.append(", ");

			if (stack.getEnchantments().size() != 0) {
				builder.append("enchantments: ");
				for (Enchantment e : stack.getEnchantments().keySet()) {
					builder.append(e.getName());
					builder.append(" : ");
					builder.append(stack.getEnchantments().get(e));
					builder.append("/");
					builder.append(e.getMaxLevel());
					builder.append(", ");
				}
			}
		}

		return builder.toString();
	}

	@SuppressWarnings("deprecation")
	public static String blockToString(Block block, boolean detail) {
		StringBuilder builder = new StringBuilder();

		builder.append(ChatColor.YELLOW);
		builder.append(block.getType().name().toLowerCase());
		builder.append(ChatColor.WHITE);
		builder.append(" ");

		builder.append("(");
		builder.append(block.getTypeId());
		builder.append(":");
		builder.append(block.getData());
		builder.append(") ");

		if (detail) {
			builder.append("redstone: ");
			builder.append(block.getBlockPower());

			builder.append(", humidity: ");
			builder.append(Math.round(block.getHumidity() * 100));
			builder.append("%");

			builder.append(", temperature: ");
			builder.append(Math.round(block.getTemperature() * 100));
			builder.append("%");

			builder.append(", light: ");
			builder.append(block.getLightLevel());
			builder.append("/15");
			builder.append(" (sky: ");
			builder.append(block.getLightFromSky());
			builder.append(", blocks: ");
			builder.append(block.getLightFromBlocks());
			builder.append("), ");

		}

		return builder.toString();
	}

}
