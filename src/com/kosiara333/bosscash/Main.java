package com.kosiara333.bosscash;

import com.kosiara333.bosscash.bossbar.JoinServerBar;
import com.kosiara333.bosscash.bossbar.TurboDropBar;
import com.kosiara333.bosscash.bossbar.TurboExpBar;
import com.kosiara333.bosscash.commands.DropCommand;
import com.kosiara333.bosscash.commands.RepairCommand;
import com.kosiara333.bosscash.commands.TurboDropCommand;
import com.kosiara333.bosscash.commands.TurboExpCommand;
import com.kosiara333.bosscash.others.DropPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public DropPlugin getDropPlugin() {
        return dropPlugin;
    }
    private DropPlugin dropPlugin;
    private TurboDropCommand turboDropCommand;
    private RepairCommand repairCommand;

    private TurboExpCommand turboExpCommand;
    private JoinServerBar bar;
    private JoinServerBar joinServerBar;

    @Override
    public void onEnable() {
        dropPlugin = new DropPlugin();

        // Inicjalizacja instancji TurboDropCommand i przekazanie instancji DropPlugin
        turboDropCommand = new TurboDropCommand(this);
        turboExpCommand = new TurboExpCommand(this);

        joinServerBar = new JoinServerBar(this);
        getServer().getPluginManager().registerEvents(joinServerBar, this);
        bar = new JoinServerBar(this);
        this.getServer().getPluginManager().registerEvents(this, this);
        if(Bukkit.getOnlinePlayers().size() > 0){
            for(Player online: Bukkit.getOnlinePlayers()){
                bar.addPlayer(online);
            }
        }
        TurboExpBar turboExpBar = new TurboExpBar(this, 0);
        TurboDropBar turboDropBar = new TurboDropBar(this, 0);

        Bukkit.getPluginManager().registerEvents(turboDropBar, this);
        Bukkit.getPluginManager().registerEvents(turboExpBar, this);

        getCommand("turboexp").setExecutor(turboExpCommand);
        // Rejestracja komendy TurboDropCommand
        getCommand("turbodrop").setExecutor(turboDropCommand);
        repairCommand = new RepairCommand();
        getCommand("repair").setExecutor(repairCommand);

        // Rejestracja listenera DropPlugin
        Bukkit.getPluginManager().registerEvents(dropPlugin, this);


        // Rejestracja komendy DropCommand z przekazaniem instancji DropPlugin
        getCommand("drop").setExecutor(new DropCommand(dropPlugin));
    }

    @Override
    public void onDisable() {

        JoinServerBar.disableAllBars();
        TurboDropBar.disableAllBars();
        TurboExpBar.disableAllBars();

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!bar.getBar().getPlayers().contains(e.getPlayer())){
            bar.addPlayer(e.getPlayer());
        }
    }

}
