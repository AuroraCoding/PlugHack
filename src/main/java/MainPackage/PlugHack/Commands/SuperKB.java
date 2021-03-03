package MainPackage.PlugHack.Commands;

import MainPackage.PlugHack.PlugHackMainC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SuperKB implements CommandExecutor {

    PlugHackMainC plugin;
    public SuperKB(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player targetPlayer = ((Player) commandSender).getPlayer();
        boolean hasPerm = commandSender.hasPermission("PlugHack.SuperKB");
        boolean isPlayer = commandSender instanceof Player;
        boolean isCommand = command.getLabel().equalsIgnoreCase("superkb");
        boolean hasPDC = targetPlayer.getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "superkb"), PersistentDataType.STRING, "false").equals("true");
        PersistentDataContainer PDCContainer = targetPlayer.getPersistentDataContainer();

        if(isCommand) {
            if(isPlayer) {
                if(hasPerm) {
                    if(!hasPDC) {
                        PDCContainer.set(new NamespacedKey(this.plugin, "superkb"), PersistentDataType.STRING, "true");
                        targetPlayer.sendMessage(ChatColor.GREEN + "Enabled Super knockback");
                    }else {
                        PDCContainer.set(new NamespacedKey(this.plugin, "superkb"), PersistentDataType.STRING, "false");
                        targetPlayer.sendMessage(ChatColor.RED+ "Disabled Super knockback");
                    }
                }else {
                    commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }
            }else {
                Bukkit.getLogger().info("Console cant run this command");
            }
        }

        return true;
    }
}
