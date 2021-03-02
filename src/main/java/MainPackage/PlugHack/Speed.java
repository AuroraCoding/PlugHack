package MainPackage.PlugHack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class Speed implements CommandExecutor {
    PlugHackMainC plugin;

    public Speed(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("speed")) {
            if (commandSender instanceof Player) {
                if (commandSender.hasPermission("PlugHack.Speed")) {
                    Player targetPlayer = ((Player) commandSender).getPlayer();
                    if (Objects.requireNonNull(targetPlayer).getPersistentDataContainer().getOrDefault(new NamespacedKey(this.plugin, "speed"), PersistentDataType.STRING, "false").equals("false")) {
                        ((Player) commandSender).setWalkSpeed(1);
                        commandSender.sendMessage(ChatColor.GREEN + "Set your speed to " + "10");
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "speed"), PersistentDataType.STRING, "true");

                    } else {
                        targetPlayer.getPersistentDataContainer().set(new NamespacedKey(this.plugin, "speed"), PersistentDataType.STRING, "false");
                        targetPlayer.sendMessage(ChatColor.RED + "Disabled speed");
                        targetPlayer.setWalkSpeed(0.2f);
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                }


            } else {
                Bukkit.getLogger().info("Console cannot run this command");
            }
            return true;
        }
        return true;
    }
}
