package MainPackage.PlugHack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Jesus implements CommandExecutor {
    PlugHackMainC plugin;
    public Jesus(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getLabel().equalsIgnoreCase("jesus")) {
            if(commandSender instanceof Player) {
                if(commandSender.hasPermission("PlugHack.Jesus")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "jesus"), PersistentDataType.STRING, "false").equals("false")) {
                        commandSender.sendMessage(ChatColor.GREEN + "Enabled Jesus");
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "jesus"), PersistentDataType.STRING, "true");
                    }else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "jesus"), PersistentDataType.STRING, "false");
                        commandSender.sendMessage(ChatColor.RED + "Disabled Jesus");
                    }
                }else {
                    commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }
            }else {
                Bukkit.getLogger().info("Console cannot run this command");
            }
        }


        return true;
    }
}
