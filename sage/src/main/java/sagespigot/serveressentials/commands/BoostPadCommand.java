package sagespigot.serveressentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import sagespigot.serveressentials.ServerEssentials;

/**
 * Created on 13.03.2022, 17:25 in spigot.
 */
public class BoostPadCommand implements CommandExecutor {
    FileConfiguration configFile = ServerEssentials.getInstance().getConfigFile();

    String boostpadBlock = configFile.getString("boostpad-block");
    String boostpadPlate = configFile.getString("boostpad-plate");

    Double boostpadAddHeightMult = (Double) configFile.get("boostpad-add-height-multiplier");
    Double boostpadAddVelMult = (Double) configFile.get("boostpad-add-velocity-multiplier");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player ingameSender = (Player) sender;

            if (args.length == 1 || args.length == 2) {
                if (args[0].equalsIgnoreCase("setplate")) {
                    if (args.length == 2) {
                        String plate = args[1].toUpperCase();

                        try {
                            Material.valueOf(plate);
                        } catch (Exception exception) {
                            ingameSender.sendMessage(ChatColor.RED + "The given name has to be a valid block.");
                            return false;
                        }

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-plate", plate);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadPlate = configFile.getString("boostpad-plate");

                        ingameSender.sendMessage(ChatColor.GRAY + "The boostpad plate has been set to " + ChatColor.YELLOW + plate);

                        return false;
                    } else {
                        ingameSender.sendMessage(ChatColor.DARK_RED + "This command has to be executed using the following format: " + ChatColor.RED + "/" + label + " [setplate/setblock] <name> or /" + label + " getinfo");
                    }

                } else if (args[0].equalsIgnoreCase("setblock")) {
                    if (args.length == 2) {
                        String block = args[1].toUpperCase();

                        try {
                            Material.valueOf(block);
                        } catch (Exception exception) {
                            ingameSender.sendMessage(ChatColor.RED + "The given name has to be a valid block.");

                            return false;
                        }

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-block", block);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadBlock = configFile.getString("boostpad-block");

                        ingameSender.sendMessage(ChatColor.GRAY + "The boostpad block has been set to " + ChatColor.YELLOW + block);

                        return false;
                    } else {
                        ingameSender.sendMessage(ChatColor.DARK_RED + "This command has to be executed using the following format: " + ChatColor.RED + "/" + label + " [setplate/setblock/setvelocity/setheight] <name> or /" + label + " info");
                    }

                    return false;
                } else if (args[0].equalsIgnoreCase("setvelocity")) {
                    if (args.length == 2) {
                        try {
                            Double.valueOf(args[1]);
                        } catch (Exception exception) {
                            ingameSender.sendMessage(ChatColor.RED + "The given value has to be a double (i.e. 1.25).");

                            return false;
                        }

                        Double velocityAddMult = Double.valueOf(args[1]);

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-add-velocity-multiplier", velocityAddMult);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadAddVelMult = (Double) configFile.get("boostpad-add-velocity-multiplier");

                        ingameSender.sendMessage(ChatColor.GRAY + "The additional boostpad velocity multiplier has been set to " + ChatColor.YELLOW + velocityAddMult);

                        return false;
                    } else {
                        ingameSender.sendMessage(ChatColor.DARK_RED + "This command has to be executed using the following format: " + ChatColor.RED + "/" + label + " [setplate/setblock/setvelocity/setheight] <name> or /" + label + " info");
                    }

                    return false;
                } else if (args[0].equalsIgnoreCase("setheight")) {
                    if (args.length == 2) {
                        try {
                            Double.valueOf(args[1]);
                        } catch (Exception exception) {
                            ingameSender.sendMessage(ChatColor.RED + "The given value has to be a double (i.e. 1.25).");

                            return false;
                        }

                        Double heightAddMult = Double.valueOf(args[1]);

                        ServerEssentials.getInstance().getConfigFile().set("boostpad-add-height-multiplier", heightAddMult);
                        ServerEssentials.getInstance().saveConfig();
                        ServerEssentials.getInstance().reloadConfig();
                        boostpadAddHeightMult = (Double) configFile.get("boostpad-add-height-multiplier");

                        ingameSender.sendMessage(ChatColor.GRAY + "The additional boostpad height multiplier has been set to " + ChatColor.YELLOW + heightAddMult);

                        return false;
                    } else {
                        ingameSender.sendMessage(ChatColor.DARK_RED + "This command has to be executed using the following format: " + ChatColor.RED + "/" + label + " [setplate/setblock/setvelocity/setheight] <name> or /" + label + " info");
                    }

                    return false;
                } else if (args[0].equalsIgnoreCase("info")) {
                    ingameSender.sendMessage(ChatColor.GRAY + "BoostpadBlock: " + ChatColor.YELLOW + boostpadBlock);
                    ingameSender.sendMessage(ChatColor.GRAY + "BoostpadPlate: " + ChatColor.YELLOW + boostpadPlate);
                    ingameSender.sendMessage(ChatColor.GRAY + "BoostpadHeightAddMultiplier: " + ChatColor.YELLOW + boostpadAddHeightMult);
                    ingameSender.sendMessage(ChatColor.GRAY + "BoostpadVelocityAddMultiplier: " + ChatColor.YELLOW + boostpadAddVelMult);

                    return false;
                }
            } else {
                ingameSender.sendMessage(ChatColor.RED + "This command has to be executed using the following format: " + ChatColor.DARK_RED + "/" + label + " [setplate/setblock/setvelocity/setheight] <name> or /" + label + " info");
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Please execute this command via player instance.");
        }

        return false;
    }
}