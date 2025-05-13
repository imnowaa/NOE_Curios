package com.noe.noecurios;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import javax.annotation.Nullable;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

@Mod.EventBusSubscriber(modid = NoeCurios.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CurioCapabilityAttacher {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() == Items.CARVED_PUMPKIN) {
            event.addCapability(new ResourceLocation(NoeCurios.MODID, "curio"), new ICapabilityProvider() {
                final LazyOptional<ICurio> optional = LazyOptional.of(() -> new CarvedPumpkinCurio(event.getObject()));

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                    return cap == CuriosCapability.ITEM ? optional.cast() : LazyOptional.empty();
                }
            });

        }
        if (event.getObject().getItem() == Items.HEART_OF_THE_SEA) {
            event.addCapability(new ResourceLocation(NoeCurios.MODID, "curio"), new ICapabilityProvider() {
                final LazyOptional<ICurio> optional = LazyOptional.of(() -> new HOTSCurio(event.getObject()));

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                    return cap == CuriosCapability.ITEM ? optional.cast() : LazyOptional.empty();
                }
            });

        }
        if (event.getObject().getItem() == ModItems.TAIL_SWAY.get()) {
            event.addCapability(new ResourceLocation(NoeCurios.MODID, "curio"), new ICapabilityProvider() {
                final LazyOptional<ICurio> optional = LazyOptional.of(() -> new TailSwayCurio(event.getObject()));

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                    return cap == CuriosCapability.ITEM ? optional.cast() : LazyOptional.empty();
                }
            });
        }

        if (event.getObject().getItem() == ModItems.TAIL.get()) {
            event.addCapability(new ResourceLocation(NoeCurios.MODID, "curio"), new ICapabilityProvider() {
                final LazyOptional<ICurio> optional = LazyOptional.of(() -> new TailCurio(event.getObject()));

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                    return cap == CuriosCapability.ITEM ? optional.cast() : LazyOptional.empty();
                }
            });
        }

        if (event.getObject().getItem() == ModItems.TAIL_WAG.get()) {
            event.addCapability(new ResourceLocation(NoeCurios.MODID, "curio"), new ICapabilityProvider() {
                final LazyOptional<ICurio> optional = LazyOptional.of(() -> new TailWagCurio(event.getObject()));

                @Override
                public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
                    return cap == CuriosCapability.ITEM ? optional.cast() : LazyOptional.empty();
                }
            });
        }
    }
}
