package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

import java.util.Objects;

public class AdminEssentialsCommand implements CommandExecutor {
    String pluginName = ServerEssentials.getInstance().getDescription().getName();
    String pluginVersion = ServerEssentials.getInstance().getDescription().getVersion();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if ( !( commandSender instanceof Player ) ) {
            Bukkit.getServer().getConsoleSender().sendMessage(String.format(
                    (String) Objects.requireNonNull(ServerEssentials
                            .getInstance()
                            .getConfigFile()
                            .get("sender-instance")),
                    label));
            return false;
        }

        Player inGameSender = (Player) commandSender;

        if ( !inGameSender.hasPermission("se.admin") ) {
            inGameSender.sendMessage(String.format(
                    (String) Objects.requireNonNull(ServerEssentials
                            .getInstance()
                            .getConfigFile()
                            .get("no-permission")),
                    "se.admin"));
            inGameSender.sendMessage(pluginName + pluginVersion);
            return false;
        }

        if ( args.length == 0 ) {
            inGameSender.sendMessage(String.format((String) Objects.requireNonNull(ServerEssentials
                            .getInstance()
                            .getConfigFile()
                            .get("plugin-info")),
                    pluginName, pluginVersion));
            return false;
        }

        if ( args.length == 1
             && !(
                args[0].equals("setLobbyWorldHere")
                || args[0].equals("boostpad")
                || args[0].equals("doublejump")
        )
        ) {
            inGameSender.sendMessage(String.format(
                    (String) Objects.requireNonNull(ServerEssentials
                            .getInstance()
                            .getConfigFile()
                            .get("command-format")),
                    "/" + label + " <setLobbyWorldHere | boostpad | doublejump>"));
            return false;
        } else {
            switch ( args[0] ) {
                case "setLobbyWorldHere":
                    SetLobbyWorldHere.setLobbyWorldHere(inGameSender);
                case "boostpad":
                case "doublejump":
            }
        }

        return false;
    }
}
