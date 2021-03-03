package MainPackage.PlugHack.Events;

import MainPackage.PlugHack.PlugHackMainC;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EventListener2 implements Listener {
    PlugHackMainC plugin;
    public EventListener2(PlugHackMainC plugin) {
        this.plugin = plugin;
    }


    public void onDamage(EntityDamageByEntityEvent e) {
        Entity DamagedPlayer = e.getDamager();
        Entity HurtEntity = e.getEntity();
        Entity targetPlayer = e.getDamager();
        PersistentDataContainer PDCContainer = targetPlayer.getPersistentDataContainer();
        boolean hasPDC = PDCContainer.getOrDefault(new NamespacedKey(this.plugin, "superkb"), PersistentDataType.STRING, "false").equals("true");


        if(DamagedPlayer instanceof Player) {
            if(hasPDC) {
                HurtEntity.setVelocity(targetPlayer.getLocation().getDirection().multiply(2));
            }
        }

    }

}
