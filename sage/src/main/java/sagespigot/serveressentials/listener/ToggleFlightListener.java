package sagespigot.serveressentials.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import sagespigot.serveressentials.ServerEssentials;

/**
 * Created on 13.03.2022, 03:51 in spigot.
 */
public class ToggleFlightListener implements Listener {
    FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();

    Double doubleJumpHeightMult = (Double) configFile.get("jumppad-height-multiplier");
    Double doubleJumpVelMult = (Double) configFile.get("jumppad-velocity-multiplier");

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent flightEvent) {
        Player player = flightEvent.getPlayer();
        Location playerLocation = player.getLocation();

        if (flightEvent.getPlayer().getGameMode() == GameMode.SURVIVAL &&
                playerLocation.subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
            flightEvent.setCancelled(true);

            Vector changePlayerMovementDir = playerLocation.getDirection().multiply(doubleJumpVelMult).setY(doubleJumpHeightMult);
            player.setVelocity(changePlayerMovementDir);

            player.playSound(playerLocation, Sound.ENTITY_BAT_TAKEOFF, 1, (float) 0.9);
        } else {
            flightEvent.setCancelled(true);
        }
    }
}
