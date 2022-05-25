package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

import java.util.Objects;

public class BoostPadExecutor {
    public static FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();

    public static String boostpadPlate = configFile.getString("boostpad-plate");
    public static String boostpadBlock = configFile.getString("boostpad-block");
    public static Double boostpadAddVelMult = (Double) configFile.get("boostpad-add-velocity-multiplier");
    public static Double boostpadAddHeightMult = (Double) configFile.get("boostpad-add-height-multiplier");

    public static boolean manageBoostPad(Player executor, String label, String[] args) {
        if ( executor.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-admin")))
             || executor.hasPermission(
                (String) Objects.requireNonNull(configFile.get("se-manage-boostpad")))
                && ( args.length == 2 || args.length == 3 ) ) {
            switch ( args.length ) {
                case 2:
                    if ( args[1].equalsIgnoreCase("info") ) {
                        executor.sendMessage(ChatColor.GRAY + "BoostpadBlock: " + ChatColor.YELLOW + boostpadBlock);
                        executor.sendMessage(ChatColor.GRAY + "BoostpadPlate: " + ChatColor.YELLOW + boostpadPlate);
                        executor.sendMessage(ChatColor.GRAY + "BoostpadHeightAddMultiplier: " + ChatColor.YELLOW + boostpadAddHeightMult);
                        executor.sendMessage(ChatColor.GRAY + "BoostpadVelocityAddMultiplier: " + ChatColor.YELLOW + boostpadAddVelMult);

                        return false;
                    }

                    executor.sendMessage(
                            String.format(
                                    (String) Objects.requireNonNull(
                                            ServerEssentials
                                                    .getInstance()
                                                    .getConfigFile()
                                                    .get("command-format")),
                                    "/" + label + " boostpad <info | setplate | setblock | setheight | setvelocity> <value (if not info)>"));
                    return false;

                case 3:
                    if ( args[1].equalsIgnoreCase("setplate") ) {
                        String plate = args[2].toUpperCase();

                        try {
                            Material.valueOf(plate);
                        } catch (Exception exception) {
                            executor.sendMessage(ChatColor.RED + "The given argument has to be a valid block.");
                            return false;
                        }

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-plate", plate);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadPlate = configFile.getString("boostpad-plate");

                        executor.sendMessage(
                                String.format(
                                        (String) Objects.requireNonNull(
                                                ServerEssentials
                                                        .getInstance()
                                                        .getConfigFile()
                                                        .get("set-boostpad-plate")),
                                        plate));

                        return false;
                    }

                    if ( args[1].equalsIgnoreCase("setblock") ) {
                        String block = args[2].toUpperCase();

                        try {
                            Material.valueOf(block);
                        } catch (Exception exception) {
                            executor.sendMessage(ChatColor.RED + "The given argument has to be a valid block.");

                            return false;
                        }

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-block", block);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadBlock = configFile.getString("boostpad-block");

                        executor.sendMessage(
                                String.format(
                                        (String) Objects.requireNonNull(
                                                ServerEssentials
                                                        .getInstance()
                                                        .getConfigFile()
                                                        .get("set-boostpad-block")),
                                        block));

                        return false;
                    }

                    if ( args[1].equalsIgnoreCase("setvelocitymult") ) {
                        try {
                            Double.valueOf(args[2]);
                        } catch (Exception exception) {
                            executor.sendMessage(ChatColor.RED + "The given argument has to be a number.");

                            return false;
                        }

                        Double velocityAddMult = Double.valueOf(args[2]);

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-add-velocity-multiplier", velocityAddMult);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadAddVelMult = (Double) configFile.get("boostpad-add-velocity-multiplier");

                        executor.sendMessage(
                                String.format(
                                        (String) Objects.requireNonNull(
                                                ServerEssentials
                                                        .getInstance()
                                                        .getConfigFile()
                                                        .get("set-boostpad-velocity")),
                                        velocityAddMult));

                        return false;
                    }

                    if ( args[1].equalsIgnoreCase("setheightmult") ) {
                        try {
                            Double.valueOf(args[2]);
                        } catch (Exception exception) {
                            executor.sendMessage(ChatColor.RED + "The given argument has to be a number.");

                            return false;
                        }

                        Double heightAddMult = Double.valueOf(args[2]);

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-add-height-multiplier", heightAddMult);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadAddHeightMult = (Double) configFile.get("boostpad-add-height-multiplier");

                        executor.sendMessage(
                                String.format(
                                        (String) Objects.requireNonNull(
                                                ServerEssentials
                                                        .getInstance()
                                                        .getConfigFile()
                                                        .get("set-boostpad-height")),
                                        heightAddMult));

                        return false;
                    }

                    executor.sendMessage(
                            String.format(
                                    (String) Objects.requireNonNull(
                                            ServerEssentials
                                                    .getInstance()
                                                    .getConfigFile()
                                                    .get("command-format")),
                                    "/" + label + " boostpad <info | setplate | setblock | setheight | setvelocity> <value (if not info)>"));
                    return false;
            }
        }
        return false;
    }
}
