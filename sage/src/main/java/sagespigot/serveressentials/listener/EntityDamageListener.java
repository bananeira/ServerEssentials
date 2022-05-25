package sagespigot.serveressentials.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import sagespigot.serveressentials.ServerEssentials;

import java.util.Objects;

/**
 * Created on 13.03.2022, 03:42 in spigot.
 */
public class EntityDamageListener implements Listener {
    @EventHandler
    public void onDamageEvent(EntityDamageEvent damageEvent) {
        if ( damageEvent.getEntity() instanceof Player
             && ( (Player) damageEvent.getEntity() )
                     .getPlayer()
                     .getWorld()
                     .getName().equals(Objects.requireNonNull(ServerEssentials
                        .getInstance()
                        .getConfigFile()
                        .getString("lobby-world")) ) ) {
            damageEvent.setCancelled(true);
        }
    }
}
