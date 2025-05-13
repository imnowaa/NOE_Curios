package com.noe.noecurios;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NoeCurios.MODID);

    public static final RegistryObject<Item> TAIL = ITEMS.register("tail",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> TAIL_SWAY = ITEMS.register("tail_sway",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> TAIL_WAG = ITEMS.register("tail_wag",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
