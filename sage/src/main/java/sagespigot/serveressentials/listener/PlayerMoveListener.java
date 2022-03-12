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

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent moveEvent) {
        Material boostpadBlock = Material.getMaterial(configFile.getString("boostpad-block"));
        Material boostpadPlate = Material.getMaterial(configFile.getString("boostpad-plate"));

        Player player = moveEvent.getPlayer();
        Location playerLocation = player.getLocation();

        if (playerLocation.getBlock().getType() == boostpadBlock
                && playerLocation.subtract(0, 1, 0).getBlock().getType() == boostpadPlate) {
            Vector changePlayerMovementDir = playerLocation.getDirection().multiply(2).setY(1);
            player.setVelocity(changePlayerMovementDir);

            player.playSound(playerLocation, Sound.ENTITY_CAT_HISS, 1, 1);
        }
    }
}