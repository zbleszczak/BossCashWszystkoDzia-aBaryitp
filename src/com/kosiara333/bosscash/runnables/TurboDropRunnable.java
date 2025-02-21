package com.kosiara333.bosscash.runnables;

import com.kosiara333.bosscash.bossbar.TurboDropBar;
import com.kosiara333.bosscash.others.DropPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TurboDropRunnable extends BukkitRunnable {
    private final Plugin plugin;
    private int remainingTime;
    private int duration;
    private boolean titleDisplayed;
    private DropPlugin dropPlugin;
    private static DropPlugin instance;
    private BukkitRunnable countdownTask;
    private static boolean turboDropEnabled;
    private TurboDropRunnable turboDropRunnable;
    private TurboDropBar turboDropBar;

    public TurboDropRunnable(Plugin plugin, TurboDropBar turboDropBar, int duration) {
        this.plugin = plugin;
        this.turboDropBar = turboDropBar;
        this.duration = duration;
        this.remainingTime = duration;
    }

    @Override
    public void run() {
        if (!titleDisplayed) {
            String title = ChatColor.RED + "Turbo Drop" + ChatColor.GOLD + " został włączony!";
            String subtitle = ChatColor.YELLOW + "Pozostały czas: " + remainingTime + "s";
            int fadeIn = 10;
            int stay = 40;
            int fadeOut = 10;

            sendTitleToAllPlayers(title, subtitle, fadeIn, stay, fadeOut);

            titleDisplayed = true;
        } else if (remainingTime > 0) {
            remainingTime--;
        } else {
            // Wyłącz turbo drop po zakończeniu czasu
            if (plugin != null) {
                // Tutaj możesz wywołać metodę z klasy głównej pluginu, która wyłączy Turbo Drop
                disableTurboDrop();
            }
            cancel();
        }
    }

    public void disableTurboDrop() {
        int turboDropDuration = 0;

        // Zatrzymanie odliczania czasu turbo dropu
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }
        if (turboDropBar != null) {
            turboDropBar.stopTimer();
        }
        turboDropEnabled = false;

        // Wyświetlenie wiadomości o wyłączeniu Turbo Drop
        if (turboDropRunnable != null) {
            String title = ChatColor.RED + "Turbo Drop został wyłączony.";
            String subtitle = "";
            int fadeIn = 10;
            int stay = 40;
            int fadeOut = 10;
            turboDropRunnable.sendTitleToAllPlayers(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public static boolean isTurboDropEnabled() {
        return turboDropEnabled;
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
