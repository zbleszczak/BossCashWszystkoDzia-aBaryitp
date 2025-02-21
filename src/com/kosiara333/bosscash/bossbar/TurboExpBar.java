package com.kosiara333.bosscash.bossbar;

import com.kosiara333.bosscash.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class TurboExpBar implements Listener {

    private final Main plugin;
    private static BossBar bar;
    private BukkitRunnable timerTask;
    private int duration;
    private int countdown;

    public TurboExpBar(Main plugin, int duration) {
        this.plugin = plugin;
        this.duration = duration;
        createBar();
    }

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public void removePlayer(Player player) {
        bar.removePlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

    public void startTimer() {
        countdown = duration;
        updateBarTitle();

        timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                countdown--;
                if (countdown <= 0) {
                    stopTimer();
                } else {
                    updateBarTitle();
                }
            }
        };
        timerTask.runTaskTimer(plugin, 20, 20); // Uruchom zadanie co sekundę (20 ticków)
    }

    private void updateBarTitle() {
        String turboExpDuration = formatTime(countdown);
        String title = ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.GREEN + "-> "
                + ChatColor.YELLOW + "TurboExp: " + turboExpDuration + ChatColor.RESET + ChatColor.GREEN + " <- "
                + ChatColor.GOLD + "Boss" + ChatColor.DARK_RED + "Cash ";
        bar.setTitle(title);
    }

    public void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        for (Player player : bar.getPlayers()) {
            bar.removePlayer(player);
        }
        bar.setVisible(false);
    }

    private void createBar() {
        String turboExpDuration = formatTime(duration);
        String title = ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.GREEN + "-> "
                + ChatColor.YELLOW + "TurboExp: " + ChatColor.RESET + ChatColor.GREEN + " <- "
                + ChatColor.GOLD + "Boss" + ChatColor.DARK_RED + "Cash ";
        bar = Bukkit.createBossBar(title, BarColor.YELLOW, BarStyle.SOLID);
        bar.setVisible(true);
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void disableAllBars() {
        for (Player player : bar.getPlayers()) {
            bar.removePlayer(player);
        }
        bar.setVisible(false);
    }
}
