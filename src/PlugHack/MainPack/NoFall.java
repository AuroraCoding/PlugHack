package PlugHack.MainPack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class NoFall implements CommandExecutor {
    PlugHackMainC plugin;

    public NoFall(PlugHackMainC plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (command.getLabel().equalsIgnoreCase("nofall")) {
            if (commandSender instanceof Player) {
                if (commandSender.hasPermission("PlugHack.NoFall")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "nofall"), PersistentDataType.STRING, "false").equals("true")) {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "nofall"), PersistentDataType.STRING, "false");
                        commandSender.sendMessage(ChatColor.RED + "Disabled NoFall");
                    } else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "nofall"), PersistentDataType.STRING, "true");
                        commandSender.sendMessage(ChatColor.GREEN + "Enabled NoFall");
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
