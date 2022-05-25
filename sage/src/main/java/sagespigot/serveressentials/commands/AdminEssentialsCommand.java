package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.addAll;

public class AdminEssentialsCommand implements TabExecutor {
    String pluginName = ServerEssentials.getInstance().getDescription().getName();
    String pluginVersion = ServerEssentials.getInstance().getDescription().getVersion();

    boolean matchCase = false;

    FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if ( !( commandSender instanceof Player ) ) {
            Bukkit.getServer().getConsoleSender().sendMessage(String.format(
                    (String) Objects.requireNonNull(
                            ServerEssentials
                                    .getInstance()
                                    .getConfigFile()
                                    .get("sender-instance")),
                    label));
            return false;
        }

        Player inGameSender = (Player) commandSender;

        if ( !inGameSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-admin")))
             || !inGameSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-doublejump")))
             || !inGameSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-boostpad")))
             || !inGameSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-change-lobby-world")))
        ) {
            inGameSender.sendMessage(String.format(
                    (String) Objects.requireNonNull(
                            ServerEssentials
                                    .getInstance()
                                    .getConfigFile()
                                    .get("no-permission")),
                    configFile.get("se-admin")));
            return false;
        }

        if ( args.length == 0 ) {
            inGameSender.sendMessage(String.format(
                    (String) Objects.requireNonNull(
                            ServerEssentials
                                    .getInstance()
                                    .getConfigFile()
                                    .get("plugin-info")),
                    pluginName, pluginVersion));
            return false;
        }

        if ( args.length == 1 && !( args[0].equals("setLobbyWorldHere") || args[0].equals("boostpad") || args[0].equals("doublejump") ) ) {
            inGameSender.sendMessage(String.format(
                    (String) Objects.requireNonNull(
                            ServerEssentials
                                    .getInstance()
                                    .getConfigFile()
                                    .get("command-format")),
                    "/" + label + " <setLobbyWorldHere | boostpad | doublejump>"));
            return false;
        } else {
            switch ( args[0] ) {
                case "setLobbyWorldHere":
                    if ( commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-admin")))
                         || commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-change-lobby-world"))
                    ) )
                        SetLobbyWorldExecutor.setLobbyWorldHere(inGameSender);
                    else
                        commandSender.sendMessage(String.format(
                                (String) Objects.requireNonNull(
                                        ServerEssentials
                                                .getInstance()
                                                .getConfigFile()
                                                .get("no-permission")),
                                configFile.get("se-change-lobby-world") + "; " + configFile.get("se-admin")));
                    matchCase = true;
                    return false;

                case "boostpad":
                    if ( commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-admin")))
                         || commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-manage-boostpad"))
                    ) )
                        BoostPadExecutor.manageBoostPad(inGameSender, label, args);
                    else
                        commandSender.sendMessage(String.format(
                                (String) Objects.requireNonNull(
                                        ServerEssentials
                                                .getInstance()
                                                .getConfigFile()
                                                .get("no-permission")),
                                configFile.get("se-manage-boostpad") + "; " + configFile.get("se-admin")));
                    matchCase = true;
                    return false;

                case "doublejump":
                    if ( commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-admin")))
                         || commandSender.hasPermission(
                            (String) Objects.requireNonNull(configFile.get("se-manage-doublejump"))
                    ) ) {
                    } else
                        commandSender.sendMessage(
                                String.format(
                                        (String) Objects.requireNonNull(
                                                ServerEssentials
                                                        .getInstance()
                                                        .getConfigFile()
                                                        .get("no-permission")),
                                        configFile.get("se-manage-doublejump") + "; " + configFile.get("se-admin")));
                    matchCase = true;
                    return false;
            }

            if ( !matchCase ) {
                inGameSender.sendMessage(String.format(
                        (String) Objects.requireNonNull(
                                ServerEssentials
                                        .getInstance()
                                        .getConfigFile()
                                        .get("command-format")),
                        "/" + label + " <setLobbyWorldHere | boostpad | doublejump>"));
                return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();

        if ( args.length == 1
             && ( commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-admin")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-doublejump")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-boostpad")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-change-lobby-world"))
        ) ) ) {
            addAll(arguments, "setLobbyWorldHere", "boostpad", "doublejump");
            return arguments;
        }

        if ( args.length == 2
             && ( args[0].equals("boostpad")
                  || args[0].equals("doublejump") )
             && ( commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-admin")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-doublejump")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-boostpad")))
                  || commandSender.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-change-lobby-world"))
        ) ) ) {
            switch ( args[0] ) {
                case "boostpad":
                    addAll(arguments, "info", "setBlock", "setPlate", "setHeightMult", "setVelocityMult");
                    return arguments;
                case "doublejump":
                    addAll(arguments, "info", "setHeightMult", "setVelocityMult", "delay");
                    return arguments;
            }
        }
        return null;
    }
}
