package MainPackage.PlugHack;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    PlugHackMainC plugin;

    public Reload(PlugHackMainC plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getLabel().equalsIgnoreCase("plughackreload")) {
            if (commandSender.hasPermission("PlugHack.Reload")) {
                commandSender.sendMessage(ChatColor.GREEN + "Reloading...");
                this.plugin.reloadConfig();
                commandSender.sendMessage(ChatColor.GREEN + "Reloaded config!");
            }
        }

        return true;
    }
}
