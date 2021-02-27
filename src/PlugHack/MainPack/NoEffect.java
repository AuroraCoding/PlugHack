package PlugHack.MainPack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class NoEffect implements CommandExecutor {
    PlugHackMainC plugin;

    public NoEffect(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (command.getLabel().equalsIgnoreCase("noeffect")) {
            if (commandSender instanceof Player) {
                if (commandSender.hasPermission("PlugHack.NoEffect")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "noeffect"), PersistentDataType.STRING, "false").equals("false")) {
                        commandSender.sendMessage(ChatColor.GREEN + "Enabled NoEffect");
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "noeffect"), PersistentDataType.STRING, "true");
                    } else {
                        commandSender.sendMessage(ChatColor.RED + "Disabled NoEffect");
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "noeffect"), PersistentDataType.STRING, "false");
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
