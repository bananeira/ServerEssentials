package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

import java.util.Objects;

public class SetLobbyWorldExecutor {
    public static boolean setLobbyWorldHere(Player executor) {
        FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();
        String curr = executor.getWorld().getName();

        if ( !executor.hasPermission((String) Objects.requireNonNull(configFile.get("se-admin")))
             && !executor.hasPermission((String) Objects.requireNonNull(configFile.get("se-change-lobby-world"))) ) {
            executor.sendMessage(String.format(
                    (String) Objects.requireNonNull(
                            ServerEssentials
                                    .getInstance()
                                    .getConfigFile()
                                    .get("no-permission")),
                    configFile.get("se-change-lobby-world") + "; " + configFile.get("se-admin")));
            return false;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if ( player == executor ) {
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

        return false;
    }
}
