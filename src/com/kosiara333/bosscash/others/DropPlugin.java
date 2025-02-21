package com.kosiara333.bosscash.others;

import com.kosiara333.bosscash.runnables.TurboDropRunnable;
import com.kosiara333.bosscash.runnables.TurboExpRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class DropPlugin implements Listener {
    private DropPlugin dropPlugin;
    private static DropPlugin instance;
    private boolean turboDropEnabled;
    private boolean turboExpEnabled;

    private BukkitRunnable countdownTask;

    private TurboDropRunnable turboDropRunnable;


    public static DropPlugin getInstance() {
        return instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack itemInHand = e.getPlayer().getInventory().getItemInMainHand();
        if (itemInHand.getType() == Material.DIAMOND_PICKAXE && itemInHand.getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
            int fortuneLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            if (e.getBlock().getType() == Material.STONE) {
                Random random = new Random();
                double dropChance = random.nextInt(100);
                if (TurboExpRunnable.isTurboExpEnabled()){
                    e.getPlayer().giveExp(5);
                }else{
                    e.getPlayer().giveExp(1);
                }

                    if(TurboDropRunnable.isTurboDropEnabled()){
                        double modifiedDropChance = dropChance * 1.15;

                        if (modifiedDropChance < 0.15) {

                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack diamond = new ItemStack(Material.DIAMOND, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), diamond);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.AQUA + "diament" + ChatColor.RESET
                                    + ChatColor.GREEN + ", jesteś farciarzem.");
                        } else if (modifiedDropChance < 0.6) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack gunpowder = new ItemStack(Material.GUNPOWDER, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), gunpowder);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.GUNPOWDER, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GRAY + "proch" + ChatColor.RESET
                                    + ChatColor.GREEN + ", przeciwna gildia już się boi.");
                        } else if (modifiedDropChance < 1.3) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                        if (isInventoryFull(e.getPlayer())) {
                                ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), goldIngot);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.YELLOW + "złoto" + ChatColor.RESET
                                    + ChatColor.GREEN + ", będzie na klepę.");
                        } else if (modifiedDropChance < 2.5) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack emerald = new ItemStack(Material.EMERALD, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), emerald);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.EMERALD, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GREEN + "emerald" + ChatColor.RESET
                                    + ChatColor.GREEN + ", wykorzystaj go mądrze.");
                        } else if (modifiedDropChance < 4.5) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack book = new ItemStack(Material.BOOK, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), book);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.BOOK, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GOLD + "książkę" + ChatColor.RESET
                                    + ChatColor.GREEN + ", przyda się na enchant.");
                        } else if (modifiedDropChance < 6.9) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack apple = new ItemStack(Material.APPLE, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), apple);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.APPLE, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.RED + "jabłko" + ChatColor.RESET
                                    + ChatColor.GREEN + ", bardzo użyteczne.");
                        } else if (modifiedDropChance < 18.3) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), ironIngot);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.WHITE + "żelazo" + ChatColor.RESET
                                    + ChatColor.GREEN + ", ciekawe na co to użyjesz.");
                        } else if (modifiedDropChance < 25) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack redstone = new ItemStack(Material.REDSTONE, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), redstone);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.REDSTONE, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.DARK_RED + "redstone" + ChatColor.RESET
                                    + ChatColor.GREEN + ", na pewno Ci się przyda.");
                        } else if (modifiedDropChance < 35) {
                            int minDropAmount = 1;
                            int maxDropAmount = fortuneLevel + 1;
                            int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                            if (isInventoryFull(e.getPlayer())) {
                                ItemStack coal = new ItemStack(Material.COAL, dropAmount);
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), coal);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, dropAmount));
                            }
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                    + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.DARK_GRAY + "węgiel" + ChatColor.RESET
                                    + ChatColor.GREEN +", pochodnie się przydadzą.");
                        }
                            }else {
                                if (dropChance < 0.05) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack diamond = new ItemStack(Material.DIAMOND, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), diamond);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.AQUA + "diament" + ChatColor.RESET
                                            + ChatColor.GREEN + ", jesteś farciarzem.");
                                } else if (dropChance < 0.25) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack gunpowder = new ItemStack(Material.GUNPOWDER, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), gunpowder);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.GUNPOWDER, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GRAY + "proch" + ChatColor.RESET
                                            + ChatColor.GREEN + ", przeciwna gildia już się boi.");
                                } else if (dropChance < 0.65) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), goldIngot);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_INGOT, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.YELLOW + "złoto" + ChatColor.RESET
                                            + ChatColor.GREEN + ", będzie na klepę.");
                                } else if (dropChance < 1.25) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack emerald = new ItemStack(Material.EMERALD, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), emerald);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.EMERALD, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GREEN + "emerald" + ChatColor.RESET
                                            + ChatColor.GREEN + ", wykorzystaj go mądrze.");
                                } else if (dropChance < 2.25) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack book = new ItemStack(Material.BOOK, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), book);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.BOOK, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.GOLD + "książkę" + ChatColor.RESET
                                            + ChatColor.GREEN + ", przyda się na enchant.");
                                } else if (dropChance < 3.45) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack apple = new ItemStack(Material.APPLE, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), apple);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.APPLE, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.RED + "jabłko" + ChatColor.RESET
                                            + ChatColor.GREEN + ", bardzo użyteczne.");
                                } else if (dropChance < 4.95) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), ironIngot);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.WHITE + "żelazo" + ChatColor.RESET
                                            + ChatColor.GREEN + ", ciekawe na co to użyjesz.");
                                } else if (dropChance < 6.65) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack redstone = new ItemStack(Material.REDSTONE, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), redstone);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.REDSTONE, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.DARK_RED + "redstone" + ChatColor.RESET
                                            + ChatColor.GREEN + ", na pewno Ci się przyda.");
                                } else if (dropChance < 9.15) {
                                    int minDropAmount = 1;
                                    int maxDropAmount = fortuneLevel + 1;
                                    int dropAmount = minDropAmount + random.nextInt(maxDropAmount - minDropAmount + 1);
                                    if (isInventoryFull(e.getPlayer())) {
                                        ItemStack coal = new ItemStack(Material.COAL, dropAmount);
                                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), coal);
                                    } else {
                                        e.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, dropAmount));
                                    }
                                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "Boss" + ChatColor.GOLD + "Cash " + ChatColor.RESET
                                            + ChatColor.GREEN + "-> " + "Brawo zdobyłeś " + ChatColor.DARK_GRAY + "węgiel" + ChatColor.RESET
                                            + ChatColor.GREEN +", pochodnie się przydadzą.");
                                }

                    }
            }
        }
    }


        public boolean isTurboExpEnabled(){
            return  turboExpEnabled;
        }



    private boolean isInventoryFull(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() == Material.AIR) {
                return false;
            }
        }
        return true;
    }


}