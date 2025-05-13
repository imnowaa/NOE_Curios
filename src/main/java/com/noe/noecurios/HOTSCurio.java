package com.noe.noecurios;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraft.block.Blocks;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class HOTSCurio implements ICurio {

    private final ItemStack stack;

    public HOTSCurio(ItemStack stack) {
        this.stack = stack;
    }

    public boolean canRender(String identifier, int index, LivingEntity entity) {
        return true;
    }

    public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light,
                       LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks,
                       float ticks, float headYaw, float headPitch) {

        matrixStack.pushPose();

        // 🔧 Essayer de récupérer un modèle Biped
        if (Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity) instanceof net.minecraft.client.renderer.entity.LivingRenderer) {
            net.minecraft.client.renderer.entity.LivingRenderer renderer =
                    (net.minecraft.client.renderer.entity.LivingRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);

            if (renderer.getModel() instanceof BipedModel) {
                BipedModel model = (BipedModel) renderer.getModel();
                model.head.translateAndRotate(matrixStack);
            }
        }

        // Ajustement de position pour bien caler la citrouille
        matrixStack.translate(0.0D, -0.25D, 0.0D);
        matrixStack.scale(0.625F, -0.625F, -0.625F);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180));

        Minecraft.getInstance().getItemRenderer().renderStatic(
                stack,
                ItemCameraTransforms.TransformType.HEAD,
                light,
                OverlayTexture.NO_OVERLAY,
                matrixStack,
                buffer
        );


        matrixStack.popPose();
    }
}
