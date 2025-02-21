package com.kosiara333.bosscash.commands;

import com.kosiara333.bosscash.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.kosiara333.bosscash.bossbar.TurboExpBar;
import com.kosiara333.bosscash.runnables.TurboExpRunnable;

public class TurboExpCommand implements CommandExecutor {

    private Plugin plugin;

    public TurboExpCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda może być wykonana tylko przez gracza!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Nie podano czasu trwania turbo expa.");
            return false;
        }

        Player player = (Player) sender;
        int duration;

        try {
            duration = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Podany czas trwania turbo expa jest nieprawidłowy.");
            return false;
        }

        TurboExpBar turboExpBar = new TurboExpBar((Main) plugin, duration);
        turboExpBar.addPlayer(player);
        TurboExpRunnable turboExpRunnable = new TurboExpRunnable(plugin, turboExpBar, duration);
        turboExpBar.startTimer();
        turboExpRunnable.runTaskTimer(plugin, 0L, 20L);

        sender.sendMessage(ChatColor.GREEN + "Ustawiono czas trwania turbo expa na " + duration + " sekundy.");
        return true;
    }
}
