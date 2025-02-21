package com.kosiara333.bosscash.bossbar;

import com.kosiara333.bosscash.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import static java.lang.String.format;

public class JoinServerBar implements Listener {

    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin().equals(plugin)) {
            disableAllBars();
        }
    }

    private int taskID = -1;
    private final Main plugin;
    private static BossBar bar;

    public JoinServerBar(Main plugin) {
        this.plugin = plugin;
        createBar();
    }

    public void addPlayer(Player player){
        bar.addPlayer(player);
    }

    public BossBar getBar(){
        return bar;
    }

    public void createBar() {
        bar = Bukkit.createBossBar(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.GREEN + "-> "
                + "Witaj na serwerze!" + ChatColor.GREEN + " <- " + ChatColor.GOLD + "Boss" + ChatColor.DARK_RED + "Cash ", BarColor.PINK, BarStyle.SOLID);
        bar.setVisible(true);
    }

    public static void disableAllBars() {
        for (Player player : bar.getPlayers()) {
            bar.removePlayer(player);
        }
        bar.setVisible(false);
    }


}








