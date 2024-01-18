package me.leoo.bedwars.xpbar;

import com.andrei1058.bedwars.api.BedWars;
import lombok.Getter;
import me.leoo.bedwars.xpbar.configuration.MainConfig;
import me.leoo.bedwars.xpbar.listeners.*;
import me.leoo.bedwars.xpbar.utils.BedwarsMode;
import me.leoo.utils.bukkit.Utils;
import me.leoo.utils.bukkit.config.ConfigManager;
import me.leoo.utils.bukkit.events.Events;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

@Getter
public class XpBar extends JavaPlugin {

    @Getter
    private static XpBar plugin;

    private ConfigManager mainConfig;
    private BedWars bedWars;
    private BedwarsMode bedwarsMode;

    @Override
    public void onEnable() {
        plugin = this;

        Utils.initialize(this);

        for (BedwarsMode mode : BedwarsMode.values()) {
            if (getPluginManager().isPluginEnabled(mode.getName())) {
                bedwarsMode = mode;

                getLogger().info("Hooked into " + mode.getName());
            }
        }

        if (bedwarsMode == null) {
            getLogger().info("Bedwars1058/BedwarsProxy not found. Disabling...");

            getPluginManager().disablePlugin(this);

            return;
        }

        mainConfig = new MainConfig("config", "plugins/" + bedwarsMode.getName() + "/Addons/XpBar");

        registerEvents();

        getLogger().info(getDescription().getName() + " plugin by itz_leoo has been successfully enabled.");
    }

    @Override
    public void onDisable() {
        Utils.disable();

        getLogger().info(getDescription().getName() + " plugin by itz_leoo has been successfully disabled.");
    }

    private void registerEvents() {
        Events.register(new JoinEvent());

        if(bedwarsMode.equals(BedwarsMode.BEDWARS)) {
            Events.register(new XpBarEvent(), new LevelupXpgainEvent());
        }
    }

    public static XpBar get() {
        return plugin;
    }
}
