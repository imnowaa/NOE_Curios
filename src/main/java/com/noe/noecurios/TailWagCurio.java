package com.noe.noecurios;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class TailWagCurio implements ICurio {

    private final ItemStack stack;

    public TailWagCurio(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean canRender(String identifier, int index, LivingEntity entity) {
        return true;
    }

    @Override
    public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light,
                       LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks,
                       float ticks, float headYaw, float headPitch) {

        matrixStack.pushPose();

        // 🔄 Se baser sur le corps, pas la tête
        if (Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity) instanceof LivingRenderer) {
            LivingRenderer<?, ?> renderer = (LivingRenderer<?, ?>) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
            if (renderer.getModel() instanceof BipedModel) {
                BipedModel<?> model = (BipedModel<?>) renderer.getModel();
                model.body.translateAndRotate(matrixStack);
            }
        }

        // 🦎 Position approximative en bas du dos
        matrixStack.translate(0.0D, 0.15D, 0.65D); // X = centré, Y = vers le bas, Z = vers l’arrière

        matrixStack.translate(0.0D, 0.0D, -0.8D);
        float time = ticks + Minecraft.getInstance().getFrameTime();
        float sway = (float) Math.sin(time * 0.5F + entity.getId()) * 7F; // amplitude ±2.5°
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(sway));
        matrixStack.translate(0.0D, 0.0D, 0.8D);

        matrixStack.scale(1.05F, -1.05F, -1.05F); // plus petite qu’un bloc, et retournée
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));

        Minecraft.getInstance().getItemRenderer().renderStatic(
                stack,
                ItemCameraTransforms.TransformType.FIXED,
                light,
                OverlayTexture.NO_OVERLAY,
                matrixStack,
                buffer
        );

        matrixStack.popPose();
    }
}
