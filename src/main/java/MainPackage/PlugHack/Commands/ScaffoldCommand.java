package MainPackage.PlugHack.Commands;

import MainPackage.PlugHack.PlugHackMainC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ScaffoldCommand implements CommandExecutor {
    PlugHackMainC plugin;

    public ScaffoldCommand(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("scaffold")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("PlugHack.Scaffold")) {
                    Player targetPlayer = ((Player) sender).getPlayer();
                    if (Objects.requireNonNull(targetPlayer).getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "scaffoldtoggle"), PersistentDataType.STRING, "false").equals("false")) {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "scaffoldtoggle"), PersistentDataType.STRING, "true");
                        targetPlayer.sendMessage(ChatColor.GREEN + "Scaffold enabled");
                    } else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "scaffoldtoggle"), PersistentDataType.STRING, "false");
                        targetPlayer.sendMessage(ChatColor.RED + "Disabled Scaffold");
                    }

                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }
            } else {
                Bukkit.getLogger().info("Console cannot run this command");
            }
        }

        return true;
    }
}
