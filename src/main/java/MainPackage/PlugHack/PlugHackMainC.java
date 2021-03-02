package MainPackage.PlugHack;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlugHackMainC extends JavaPlugin implements Listener {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        this.getCommand("flyp").setExecutor(new FlyHack());
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getCommand("scaffold").setExecutor(new ScaffoldCommand(this));
        this.getCommand("speed").setExecutor(new Speed(this));
        this.getCommand("nofall").setExecutor(new NoFall(this));

        this.getCommand("velocity").setExecutor(new Velocity(this));
        this.getCommand("godp").setExecutor(new God(this));
        this.getCommand("jesus").setExecutor(new Jesus(this));
        this.getCommand("plughackreload").setExecutor(new Reload(this));

        int pluginId = 10504;
        Metrics metrics = new Metrics(this, pluginId);
        this.saveDefaultConfig();
        Bukkit.getLogger().info("  _____   _                  _    _               _    ");
        Bukkit.getLogger().info(" |  __ \\ | |                | |  | |             | |   ");
        Bukkit.getLogger().info(" | |__) || | _   _   __ _   | |__| |  __ _   ___ | | __");
        Bukkit.getLogger().info(" |  ___/ | || | | | / _` |  |  __  | / _` | / __|| |/ /");
        Bukkit.getLogger().info(" | |     | || |_| || (_| |  | |  | || (_| || (__ |   < ");
        Bukkit.getLogger().info(" |_|     |_| \\__,_| \\__,  | |_|  ||\\__,_|\\___||_|\\_\\");
        Bukkit.getLogger().info("                     __/ |                             ");
        Bukkit.getLogger().info("                    |___/                              ");
        Bukkit.getLogger().info("A JuicySeals creation!");
        Bukkit.getLogger().info("");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("  _____   _                  _    _               _    ");
        Bukkit.getLogger().info(" |  __ \\| |                 | |  | |             | |   ");
        Bukkit.getLogger().info(" | |__) || | _   _   __ _   | |__| |  __ _   ___ | | __");
        Bukkit.getLogger().info(" |  ___/ | || | | | / _` |  |  __  | / _` | / __|| |/ /");
        Bukkit.getLogger().info(" | |     | || |_| || (_| |  | |  | || (_| || (__ |   < ");
        Bukkit.getLogger().info(" |_|     |_| \\__,_| \\__,  | |_|  ||\\__,_|\\___||_|\\_\\");
        Bukkit.getLogger().info("                     __/ |                             ");
        Bukkit.getLogger().info("                    |___/                              ");
        Bukkit.getLogger().info("A JuicySeals creation!");

    }


}

