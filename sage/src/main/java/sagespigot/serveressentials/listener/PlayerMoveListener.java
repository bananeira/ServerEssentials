package sagespigot.serveressentials.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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

    Material boostpadBlock = Material.valueOf(configFile.getString("boostpad-block"));
    Material boostpadPlate = Material.valueOf(configFile.getString("boostpad-plate"));

    Double boostpadHeightMult = (Double) configFile.get("boostpad-height-multiplier");
    Double boostpadVelMult = (Double) configFile.get("boostpad-velocity-multiplier");

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Location playerLocation = player.getLocation();

        while (playerLocation.getBlock().getType() == boostpadPlate &&
                playerLocation.subtract(0, 1, 0).getBlock().getType() == boostpadBlock) {

            Vector changePlayerMovementDir = playerLocation.getDirection().multiply(boostpadVelMult).setY(boostpadHeightMult);
            player.setVelocity(changePlayerMovementDir);

            player.playSound(playerLocation, Sound.ENTITY_CAT_HISS, 1, (float) 0.9);
        }
    }
}