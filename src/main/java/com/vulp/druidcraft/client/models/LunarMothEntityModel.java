package com.vulp.druidcraft.client.models;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.vulp.druidcraft.entities.BeetleEntity;
import com.vulp.druidcraft.entities.LunarMothEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PhantomModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class LunarMothEntityModel<T extends LunarMothEntity> extends EntityModel<T> {
    private final RendererModel main;
    private final RendererModel body;
    private final RendererModel antennae;
    private final RendererModel wing1;
    private final RendererModel wing2;

    public LunarMothEntityModel() {
        textureWidth = 32;
        textureHeight = 32;

        main = new RendererModel(this);
        main.setRotationPoint(0.0F, 21.0F, 0.0F);

        body = new RendererModel(this, 0, 0);
        body.setRotationPoint(0.0F, -1.0F, 0.0F);
        main.addChild(body);
        body.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 6, 0.0F, false);

        antennae = new RendererModel(this, 4, 8);
        antennae.setRotationPoint(0.0F, -2.0F, -3.0F);
        main.addChild(antennae);
        antennae.addBox(-2.5F, 0.0F, -3.0F, 2, 0, 3, 0.0F, false);
        antennae.addBox(0.5F, 0.0F, -3.0F, 2, 0, 3, 0.0F, true);

        wing1 = new RendererModel(this, 0, 8);
        wing1.setRotationPoint(-1.0F, -2.0F, 1.5F);
        main.addChild(wing1);
        wing1.addBox(-6.0F, 0.0F, -5.5F, 6, 0, 14, 0.0F, false);

        wing2 = new RendererModel(this, 0, 8);
        wing2.setRotationPoint(1.0F, -2.0F, 1.5F);
        main.addChild(wing2);
        wing2.addBox(0.0F, 0.0F, -5.5F, 6, 0, 14, 0.0F, true);
    }

    @Override
    public void render(LunarMothEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0F, 240.0F);
        main.render(scale);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(LunarMothEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngle(main, -0.3491F, 0.0F, 0.0F);
        setRotationAngle(antennae, -0.6109F, 0.0F, 0.0F);
        setRotationAngle(wing1, 0.0F, 0.0F, MathHelper.cos(ageInTicks * 1.3F) * 3.1415927F * 0.25F);
        setRotationAngle(wing2, 0.0F, 0.0F, -(MathHelper.cos(ageInTicks * 1.3F) * 3.1415927F * 0.25F));
    }
}