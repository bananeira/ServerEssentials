package sagespigot.serveressentials.listener;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import sagespigot.serveressentials.ServerEssentials;

/**
 * Created on 13.03.2022, 03:51 in spigot.
 */
public class ToggleFlightListener implements Listener {
    FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();
    double delay = (double) configFile.get("djump-delay");

    Double doubleJumpAddHeightMult = (Double) configFile.get("djump-add-height-multiplier");
    Double doubleJumpAddVelMult = (Double) configFile.get("djump-add-velocity-multiplier");

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent flightEvent) {
        Player player = flightEvent.getPlayer();
        Location playerLocation = player.getLocation();

        if ( flightEvent.getPlayer().getGameMode() == GameMode.SURVIVAL
             && !ServerEssentials.getInstance().recentlyPerformedDoubleJump.contains(player)
             && player.hasPermission("se.doublejump") ) {
            ServerEssentials.getInstance().performingDoubleJump.add(player);
            ServerEssentials.getInstance().recentlyPerformedDoubleJump.add(player);

            flightEvent.setCancelled(true);

            ServerEssentials.getInstance().triggerDoubleJumpDelay(player, delay);

            Vector changePlayerMovementDir = playerLocation
                    .getDirection()
                    .normalize()
                    .setY(doubleJumpAddHeightMult)
                    .multiply(doubleJumpAddVelMult);
            player.setVelocity(changePlayerMovementDir);

            player.playSound(playerLocation, Sound.ENTITY_BAT_TAKEOFF, 1, (float) 0.9);
        }
    }
}
