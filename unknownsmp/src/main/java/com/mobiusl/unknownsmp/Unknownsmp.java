package com.mobiusl.unknownsmp;

import com.mobiusl.unknownsmp.commands.givecrown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public final class Unknownsmp extends JavaPlugin implements Listener {

    public static HashMap<String,String> dead = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        getCommand("givecrown").setExecutor(new givecrown());

        dead = new HashMap<>();



        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getInventory().getHelmet() != null){
                        if (player.getInventory().getHelmet().hasItemMeta()){
                            if (player.getInventory().getHelmet().getItemMeta().getCustomModelData() == 1998){
                                PotionEffect strenght = new PotionEffect(PotionEffectType.INCREASE_DAMAGE,70,1);
                                player.addPotionEffect(strenght);
                                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
                            }
                        }
                    }else{
                        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    }
                }
                }

        }, 20L, 20L);

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        ItemStack crown = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta cmeta = crown.getItemMeta();

        cmeta.setCustomModelData(1998);
        cmeta.setUnbreakable(true);
        cmeta.setDisplayName(ChatColor.YELLOW +"" + ChatColor.BOLD + "Crown");
        cmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2,true);
        cmeta.addEnchant(Enchantment.WATER_WORKER,1,true);
        crown.setItemMeta(cmeta);
        if (event.getDrops().contains(crown)){
            dead.put(event.getEntity().getName(),event.getEntity().getName());
        }

        event.getDrops().remove(crown);



    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        ItemStack crown = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta cmeta = crown.getItemMeta();

        cmeta.setCustomModelData(1998);
        cmeta.setUnbreakable(true);
        cmeta.setDisplayName(ChatColor.YELLOW +"" + ChatColor.BOLD + "Crown");
        cmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2,true);
        cmeta.addEnchant(Enchantment.WATER_WORKER,1,true);
        crown.setItemMeta(cmeta);

        Inventory inv = event.getPlayer().getInventory();

        if (!dead.isEmpty()){
            if (dead.containsKey(event.getPlayer().getName())){
                inv.addItem(crown);
                dead.remove(event.getPlayer().getName());
            }
        }
    }

}
