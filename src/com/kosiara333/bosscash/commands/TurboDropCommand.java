package com.kosiara333.bosscash.commands;

import com.kosiara333.bosscash.Main;
import com.kosiara333.bosscash.bossbar.TurboDropBar;
import com.kosiara333.bosscash.others.DropPlugin;
import com.kosiara333.bosscash.runnables.TurboDropRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TurboDropCommand implements CommandExecutor {

    private DropPlugin dropPlugin;
    private Plugin plugin;

    public TurboDropCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda może być wykonana tylko przez gracza!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Nie podano czasu trwania turbo dropu.");
            return false;
        }

        Player player = (Player) sender;
        int duration;

        try {
            duration = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Podany czas trwania turbo dropu jest nieprawidłowy.");
            return false;
        }
        TurboDropBar turboDropBar = new TurboDropBar((Main) plugin, duration);
        turboDropBar.addPlayer(player);
        TurboDropRunnable turboDropRunnable = new TurboDropRunnable(plugin, turboDropBar, duration);
        turboDropBar.startTimer();
        turboDropRunnable.runTaskTimerAsynchronously(plugin, 0L, 20L);

        sender.sendMessage(ChatColor.GREEN + "Ustawiono czas trwania turbo dropu na " + duration + " sekundy.");
        return true;
    }
}
