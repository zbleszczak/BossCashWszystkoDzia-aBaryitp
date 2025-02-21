package com.kosiara333.bosscash.commands;

import com.kosiara333.bosscash.others.DropPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DropCommand implements CommandExecutor {

    private DropPlugin dropPlugin;

    public DropCommand(DropPlugin dropPlugin) {
        this.dropPlugin = dropPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda może być wykonana tylko przez gracza!");
            return true;
        }

        Player player = (Player) sender;

        // Tworzenie inventory dla GUI
        Inventory dropGUI = Bukkit.createInventory(player, 36, "Drop z kamienia");

        // Dodawanie itemów do GUI
        dropGUI.setItem(10, createDropItem(Material.DIAMOND, 0.05, true, "Diament"));
        dropGUI.setItem(11, createDropItem(Material.EMERALD, 1.00, true, "Emerald"));
        dropGUI.setItem(12, createDropItem(Material.GOLD_INGOT, 0.50, true, "Złoto"));
        dropGUI.setItem(13, createDropItem(Material.IRON_INGOT, 1.00, true, "Żelazo"));
        dropGUI.setItem(14, createDropItem(Material.GUNPOWDER, 0.20, true, "Proch"));
        dropGUI.setItem(15, createDropItem(Material.BOOK, 1.00, true, "Książka"));
        dropGUI.setItem(16, createDropItem(Material.APPLE, 1.00, true, "Jabłko"));
        dropGUI.setItem(20, createDropItem(Material.REDSTONE, 1.00, true, "Redstone"));
        dropGUI.setItem(24, createDropItem(Material.COAL, 1.00, true, "Węgiel"));


        // Otwieranie GUI dla gracza
        player.openInventory(dropGUI);

        return true;
    }

    // Metoda pomocnicza do tworzenia ItemStack z informacjami o dropie
    private ItemStack createDropItem(Material material, double dropChance, boolean hasFortune, String displayName) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Ustawianie nazwy i lore dla ItemStacku
        itemMeta.setDisplayName(ChatColor.GREEN + displayName);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Szansa na drop: " + ChatColor.YELLOW + dropChance + "%");
        lore.add(ChatColor.GRAY + "Fortuna: " + (hasFortune ? ChatColor.GREEN + "Tak" : ChatColor.RED + "Nie"));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    // Metoda pomocnicza do tworzenia ItemStack z informacjami o turbodropie


}