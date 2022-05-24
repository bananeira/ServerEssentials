package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

import java.util.Objects;

public class SetLobbyWorldHere {
    public static void setLobbyWorldHere(Player executor) {
        String curr = executor.getWorld().getName();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player == executor) {
                executor.sendMessage(String.format(
                        (String) Objects.requireNonNull(ServerEssentials
                                .getInstance()
                                .getConfigFile()
                                .get("set-lobby-world")),
                        curr));
            } else {
                Bukkit.broadcastMessage(String.format(
                        (String) Objects.requireNonNull(ServerEssentials
                                .getInstance()
                                .getConfigFile()
                                .get("broadcast-set-lobby-world")),
                        executor.getDisplayName(),
                        curr));
            }
        }

        ServerEssentials.getInstance().getConfigFile().set("lobby-world", curr);
        ServerEssentials.getInstance().saveConfig();
        ServerEssentials.getInstance().reloadConfig();
    }
}
