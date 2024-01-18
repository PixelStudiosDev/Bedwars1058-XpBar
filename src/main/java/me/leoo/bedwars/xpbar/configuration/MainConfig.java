package me.leoo.bedwars.xpbar.configuration;

import me.leoo.bedwars.xpbar.XpBar;
import me.leoo.bedwars.xpbar.utils.EventType;
import me.leoo.utils.bukkit.config.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

public class MainConfig extends ConfigManager {

    public MainConfig(String name, String directory) {
        super(name, directory);

        YamlConfiguration yml = getYml();

        //header
        PluginDescriptionFile description = XpBar.get().getDescription();

        yml.options().header(description.getName() + " v" + description.getVersion() + " made by " + description.getAuthors() + ".\n" +
                "Dependencies: " + description.getDepend() + ".\n" +
                "SoftDependencies: " + description.getSoftDepend() + ".\n" +
                "Join my discord for support or suggestions: https://discord.gg/dtwanz4GQg\n");

        //remove old config
        if (yml.get("xp_bar.level.enabled") != null) {
            yml.set("xp_bar", null);
        }

        //worlds
        yml.addDefault(Path.LEVEL_WORLDS, List.of("all"));
        yml.addDefault(Path.FIREWORKS_ON_LEVELUP, true);

        yml.addDefault(Path.XP_WORLDS, List.of("all"));

        //events
        for (EventType value : EventType.values()) {
            yml.addDefault(value.getLevelPath(), value != EventType.MOVE);
            yml.addDefault(value.getExperiencePath(), value != EventType.MOVE);
        }

        yml.options().copyDefaults(true);
        save();
    }
}
