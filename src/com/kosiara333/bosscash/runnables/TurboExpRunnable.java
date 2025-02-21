package com.kosiara333.bosscash.runnables;

import com.kosiara333.bosscash.bossbar.TurboExpBar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TurboExpRunnable extends BukkitRunnable {
    private final Plugin plugin;
    private int remainingTime;
    private int duration;
    private boolean titleDisplayed;
    private BukkitRunnable countdownTask;
    private static boolean turboExpEnabled;
    private TurboExpRunnable turboExpRunnable;

    private TurboExpBar turboExpBar;

    public TurboExpRunnable(Plugin plugin, TurboExpBar turboExpBar, int duration) {
        this.plugin = plugin;
        this.turboExpBar = turboExpBar;
        this.duration = duration;
        this.remainingTime = duration;
    }

    @Override
    public void run() {
        if (!titleDisplayed) {
            String title = ChatColor.GREEN + "Turbo Exp" + ChatColor.GOLD + " został włączony!";
            String subtitle = ChatColor.YELLOW + "Pozostały czas: " + remainingTime + "s";
            int fadeIn = 10;
            int stay = 40;
            int fadeOut = 10;

            sendTitleToAllPlayers(title, subtitle, fadeIn, stay, fadeOut);

            titleDisplayed = true;
        } else if (remainingTime > 0) {
            remainingTime--;
        } else {
            disableTurboExp();
            cancel();
        }
    }

    public void disableTurboExp() {
        int turboExpDuration = 0;

        // Zatrzymanie odliczania czasu turbo expa
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }

        turboExpEnabled = false;
        String title = ChatColor.RED + "Turbo Exp został wyłączony.";
        String subtitle = "";
        int fadeIn = 10;
        int stay = 40;
        int fadeOut = 10;
        sendTitleToAllPlayers(title, subtitle, fadeIn, stay, fadeOut);
    }

    public static boolean isTurboExpEnabled() {
        return turboExpEnabled;
    }


    public void sendTitleToAllPlayers(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public void sendSubtitleToAllPlayers(String subtitle, int fadeIn, int stay, int fadeOut) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendTitle("", subtitle, fadeIn, stay, fadeOut);
        }
    }
}
