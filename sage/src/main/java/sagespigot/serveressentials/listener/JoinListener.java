package sagespigot.serveressentials.listener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created on 11.03.2022, 21:27 in spigot.
 */
public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent joinEvent) {
        String playerDisplayName = joinEvent.getPlayer().getDisplayName();
        Player player = joinEvent.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            player.setAllowFlight(true);
        }

        joinEvent.setJoinMessage(String.format(ChatColor.GREEN + "%s" + ChatColor.GRAY + " has joined the server.", playerDisplayName));
    }
}
