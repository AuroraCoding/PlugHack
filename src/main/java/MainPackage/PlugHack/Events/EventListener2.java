package MainPackage.PlugHack.Events;

import MainPackage.PlugHack.PlugHackMainC;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EventListener2 implements Listener {
    PlugHackMainC plugin;
    public EventListener2(PlugHackMainC plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity DamagedPlayer = e.getDamager();
        Entity HurtEntity = e.getEntity();
        PersistentDataContainer PDCContainer = DamagedPlayer.getPersistentDataContainer();
        Boolean isSet = plugin.getConfig().isSet("superkb_amount");
        boolean hasPDC = PDCContainer.getOrDefault(new NamespacedKey(this.plugin, "superkb"), PersistentDataType.STRING, "false").equals("true");

        if (DamagedPlayer instanceof Player) {
                if (hasPDC) {
                    if (isSet) {
                    int KnockBackInt = plugin.getConfig().getInt("superkb_amount");
                    HurtEntity.setVelocity(e.getDamager().getLocation().getDirection().setY(0).normalize().multiply(KnockBackInt));
                }else {
                        Bukkit.getLogger().info("Please provide a amount in the config");
                    }
            }

        }
    }
}
