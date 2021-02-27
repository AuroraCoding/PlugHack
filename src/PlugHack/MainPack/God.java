package PlugHack.MainPack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class God implements CommandExecutor {
    PlugHackMainC plugin;

    public God(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("godp")) {
            if (commandSender instanceof Player) {
                if (commandSender.hasPermission("PlugHack.God")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "god"), PersistentDataType.STRING, "false").equals("false")) {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "god"), PersistentDataType.STRING, "true");
                        commandSender.sendMessage(ChatColor.GREEN + "Enabled God");
                    } else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "god"), PersistentDataType.STRING, "false");
                        commandSender.sendMessage(ChatColor.GREEN + "Disabled God");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }
            } else {
                Bukkit.getLogger().info("Console cannot run this command");
            }
        }

        return true;
    }
}
