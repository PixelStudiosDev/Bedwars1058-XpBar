package me.leoo.bedwars.xpbar.configuration;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Path {

    public final String XP_BAR = "xp_bar";


    public final String LEVEL = XP_BAR + ".level";
    public final String LEVEL_EVENT = LEVEL + ".events.%type%";
    public final String LEVEL_WORLDS = LEVEL + ".worlds";
    public final String FIREWORKS_ON_LEVELUP = LEVEL + ".fireworks-on-levelup";


    public final String XP = XP_BAR + ".experience";
    public final String XP_EVENT = XP + ".events.%type%";
    public final String XP_WORLDS = XP + ".worlds";

}
