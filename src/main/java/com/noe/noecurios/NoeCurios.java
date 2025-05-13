package com.noe.noecurios;

import net.minecraftforge.fml.common.Mod;

@Mod("noecurios")
public class NoeCurios {
    public static final String MODID = "noecurios";

    public NoeCurios() {
        System.out.println("Mod NoeCurios chargé uwu");
        ModItems.register();
    }
}
