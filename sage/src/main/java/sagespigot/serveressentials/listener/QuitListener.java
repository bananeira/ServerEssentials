package sagespigot.serveressentials.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created on 11.03.2022, 21:37 in spigot.
 */
public class QuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent quitEvent) {
        String playerDisplayName = quitEvent.getPlayer().getDisplayName();

        quitEvent.setQuitMessage(String.format(ChatColor.RED + "%s" + ChatColor.GRAY + " has left the Server.", playerDisplayName));
    }
}