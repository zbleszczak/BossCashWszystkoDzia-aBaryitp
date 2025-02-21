package com.kosiara333.bosscash.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RepairCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String args, String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (itemInHand.getType().isItem()) {
                itemInHand.setDurability((short) 0);
                player.updateInventory();

                sender.sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                        + ChatColor.GREEN + "-> " + "Przedmiot został naprawiony do maksymalnej trwałości.");
            } else {
                sender.sendMessage("To nie jest przedmiot.");
            }
        } else {
            sender.sendMessage("To musi wykonać gracz.");
        }

        return true;
    }
}