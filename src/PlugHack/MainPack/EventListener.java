package PlugHack.MainPack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public final class EventListener implements Listener {
    private static final String TRUE_STRING = Boolean.toString(true);
    private static final String FALSE_STRING = Boolean.toString(false);

    private final NamespacedKey scaffoldToggle;
    private final NamespacedKey noFall;
    private final NamespacedKey speed;
    private final NamespacedKey velocity;
    private final NamespacedKey noEffect;
    private final NamespacedKey god;
    private final PlugHackMainC plugin;

    public EventListener(PlugHackMainC plugin) {
        this.plugin = plugin;
        this.scaffoldToggle = new NamespacedKey(plugin, "scaffoldtoggle");
        this.noFall = new NamespacedKey(plugin, "nofall");
        this.speed = new NamespacedKey(plugin, "speed");
        this.velocity = new NamespacedKey(plugin, "velocity");
        this.noEffect = new NamespacedKey(plugin, "noeffect");
        this.god = new NamespacedKey(plugin,"god");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (event.getPlayer().hasPlayedBefore()) {
            return;
        }

        PersistentDataContainer container = player.getPersistentDataContainer();

        container.set(this.scaffoldToggle, PersistentDataType.STRING, FALSE_STRING);
        container.set(this.noFall, PersistentDataType.STRING, FALSE_STRING);
        container.set(this.speed, PersistentDataType.STRING, FALSE_STRING);
        container.set(this.velocity, PersistentDataType.STRING, FALSE_STRING);
        container.set(this.noEffect, PersistentDataType.STRING, FALSE_STRING);
        container.set(this.god, PersistentDataType.STRING, FALSE_STRING);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();
        boolean hasScaffold = Objects.equals(container.get(this.scaffoldToggle, PersistentDataType.STRING),TRUE_STRING);

        if (!hasScaffold) {
            return;
        }

        Block block = player.getLocation().subtract(0, 1, 0).getBlock();

        if (block.getType() != Material.AIR) {
            return;
        }

        MemorySection config = this.plugin.getConfig();
        String configBlockMaterial = config.getString("scaffold_block");

        if (configBlockMaterial == null) {
            this.plugin.getLogger().severe("Absent material value.");
            Bukkit.getPluginManager().disablePlugin(this.plugin);
            return;
        }

        Material configblock = Material.matchMaterial(configBlockMaterial);

        if (configblock == null) {
            this.plugin.getLogger().severe("Bad material value.");
            Bukkit.getPluginManager().disablePlugin(this.plugin);
            return;
        }

        block.setType(configblock);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        PersistentDataContainer container = entity.getPersistentDataContainer();

        boolean hasNoFall = Objects.equals(container.get(this.noFall,PersistentDataType.STRING),TRUE_STRING);
        boolean isPlayer = entity instanceof Player;
        boolean isFallDamageCause = event.getCause() == EntityDamageEvent.DamageCause.FALL;

        if (hasNoFall && isPlayer && isFallDamageCause) {
            event.setCancelled(true);
        }

        boolean hasGod = Objects.equals(container.get(this.god,PersistentDataType.STRING),TRUE_STRING);

        if (hasGod) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        for (LivingEntity entity : event.getAffectedEntities()) {
            PersistentDataContainer container = entity.getPersistentDataContainer();
            boolean hasNoEffect = Objects.equals(container.get(this.noEffect,PersistentDataType.STRING),TRUE_STRING);

            if (hasNoEffect) {
                entity.getActivePotionEffects().clear();
            }
        }
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent e) {
        Player player = e.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();
        boolean hasVelocity = Objects.equals(container.get(this.velocity, PersistentDataType.STRING),TRUE_STRING);

        if (hasVelocity) {
            e.setCancelled(true);
        }
    }
}