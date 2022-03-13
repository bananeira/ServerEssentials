package sagespigot.serveressentials.listener;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import sagespigot.serveressentials.ServerEssentials;

/**
 * Created on 11.03.2022, 21:38 in spigot.
 */
public class PlayerMoveListener implements Listener {
    FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Location playerLocation = player.getLocation();

        Material boostpadBlock = Material.valueOf(configFile.getString("boostpad-block"));
        Material boostpadPlate = Material.valueOf(configFile.getString("boostpad-plate"));

        if (playerLocation.getBlock().getType() == boostpadPlate &&
                playerLocation.subtract(0, 1, 0).getBlock().getType() == boostpadBlock) {
            Double boostpadAddHeightMult = (Double) configFile.get("boostpad-add-height-multiplier");
            Double boostpadAddVelMult = (Double) configFile.get("boostpad-add-velocity-multiplier");

            Vector changePlayerMovementDir = playerLocation.getDirection().multiply(boostpadAddVelMult).setY(boostpadAddHeightMult);
            player.setVelocity(changePlayerMovementDir);

            player.playSound(playerLocation, Sound.ENTITY_CAT_HISS, 1, (float) 0.9);
        }

        if (player.getGameMode() == GameMode.SURVIVAL &&
                player.isFlying() == false &&
                playerLocation.subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
            player.setAllowFlight(true);
        } else if (player.getGameMode() == GameMode.SURVIVAL &&
                player.isFlying()) {
            player.setAllowFlight(false);
        }
    }
}