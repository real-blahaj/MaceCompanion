package dev.pixiboot.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import dev.pixiboot.macecompanion.client.config.Config
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier

object RoundInfoHud {
    fun registerListener() {
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.of("macecompanion","round_info")
        ) { context, tickCounter ->
            if (!Config.displayHud.value) return@attachElementBefore
            if (!StateManager.gameOngoing) return@attachElementBefore

            val window = MinecraftClient.getInstance().window

            context.matrices.pushMatrix()
            var xTranslate = Config.hudXMargin.value.toFloat()
            if (Config.hudLocation.value.rightAligned) xTranslate = window.scaledWidth.minus(xTranslate)
            var yTranslate = Config.hudYMargin.value.toFloat()
            if (Config.hudLocation.value.bottomAligned) yTranslate = window.scaledHeight.minus(yTranslate)
            context.matrices.translate(xTranslate, yTranslate)

            var yOffset = 0
            var elements = Config.hudElements.value
            if (Config.hudLocation.value.bottomAligned) elements = elements.reversed()
            elements.forEach {
                yOffset += it.render(context, yOffset, Config.hudLocation.value.rightAligned, Config.hudLocation.value.bottomAligned)
            }

            context.matrices.popMatrix()
        }
    }
}