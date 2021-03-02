package MainPackage.PlugHack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyHack implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            if (command.getLabel().equalsIgnoreCase("flyp")) {
                Player player = (Player) commandSender;
                if (commandSender instanceof Player) {
                    if (player.hasPermission("PlugHack.Fly")) {
                        if (player.getAllowFlight() == true) {
                            player.setAllowFlight(false);
                            player.sendMessage(ChatColor.RED + "Disabled flight");
                        } else {
                            player.setAllowFlight(true);
                            player.sendMessage(ChatColor.GREEN + "Enabled flight");
                        }

                    }
                    if (!player.hasPermission("PlugHack.fly")) {
                        player.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                    } else {
                        Bukkit.getLogger().info("Console cannot run this command");
                    }
                }
            }
        }
        return true;
    }
}

