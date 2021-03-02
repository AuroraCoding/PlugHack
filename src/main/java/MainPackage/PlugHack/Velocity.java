package MainPackage.PlugHack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;


public class Velocity implements CommandExecutor {
    PlugHackMainC plugin;

    public Velocity(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("velocity")) {
            if (commandSender instanceof Player) {
                if (commandSender.hasPermission("PlugHack.Velocity")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "velocity"), PersistentDataType.STRING, "false").equals("false")) {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "velocity"), PersistentDataType.STRING, "true");
                        commandSender.sendMessage(ChatColor.GREEN + "Enabled velocity");

                    } else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "velocity"), PersistentDataType.STRING, "false");
                        commandSender.sendMessage(ChatColor.RED + "Disabled velocity");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }
            } else {
                Bukkit.getLogger().info("Console cant run this command");
            }
        }

        return true;
    }
}
