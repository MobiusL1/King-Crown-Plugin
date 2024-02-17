package com.mobiusl.unknownsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class givecrown implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] arg) {

        if (sender.isOp()){
            if (arg.length == 0){
                arg = new String[]{"its not me"};
            }else {



                String playerName = arg[0];

                Player player = Bukkit.getServer().getPlayerExact(playerName);


                if (player == null){
                    sender.sendMessage("not a valid player");
                }else {
                    ItemStack crown = new ItemStack(Material.NETHERITE_HELMET);
                    ItemMeta cmeta = crown.getItemMeta();

                    cmeta.setCustomModelData(1998);
                    cmeta.setUnbreakable(true);
                    cmeta.setDisplayName(ChatColor.YELLOW +"" + ChatColor.BOLD + "Crown");
                    cmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2,true);
                    cmeta.addEnchant(Enchantment.WATER_WORKER,1,true);
                    crown.setItemMeta(cmeta);

                    Inventory inv = player.getInventory();

                    inv.addItem(crown);
                }
            }

        }else {
            sender.sendMessage(ChatColor.RED + "No Permission");
        }





        return true;
    }
}
