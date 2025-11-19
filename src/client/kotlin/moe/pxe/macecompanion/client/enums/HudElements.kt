package moe.pxe.macecompanion.client.enums

import dev.isxander.yacl3.api.NameableEnum
import moe.pxe.macecompanion.client.StateManager
import moe.pxe.macecompanion.client.util.GetPlayerHeadItem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Colors
import net.minecraft.util.StringIdentifiable
import kotlin.time.DurationUnit
import kotlin.time.toDuration

enum class HudElements : NameableEnum, StringIdentifiable {
    ROUND_NUMBER {
        override fun render(
            context: DrawContext,
            yOffset: Int,
            rightAligned: Boolean,
            bottomAligned: Boolean
        ): Int {
            if (StateManager.round == -1) return 0

            val textRenderer = MinecraftClient.getInstance().textRenderer
            val text = Text.translatable("mrc.roundhud.round", "${StateManager.round}").setStyle(StateManager.roundColor.withBold(true))
            val width = textRenderer.getWidth(text)
            var xPos = 0
            if (rightAligned) xPos = -width
            var yPos = yOffset/2f
            if (bottomAligned) yPos = (-yOffset/2f) - 12
            context.matrices.pushMatrix()
            context.matrices.scale(2f)
            context.matrices.translate(xPos.toFloat(), yPos)
            context.drawTextWithShadow(textRenderer, text, 0, 0, -1)
            context.matrices.popMatrix()
            return 24
        }
    },

    PLAYERS_ALIVE {
        override fun render(
            context: DrawContext,
            yOffset: Int,
            rightAligned: Boolean,
            bottomAligned: Boolean
        ): Int {
            if (StateManager.playersAlive == -1) return 0

            val textRenderer = MinecraftClient.getInstance().textRenderer
            val countText = Text.literal("${StateManager.playersAlive}").withColor(0xd5fcf5)
            if (StateManager.playersTotal >= 0) countText.append(Text.literal("/${StateManager.playersTotal}").withColor(0xd0d0d0))
            val text = Text.translatable("mrc.roundhud.alive", countText).setStyle(Style.EMPTY.withColor(Colors.WHITE))
            val width = textRenderer.getWidth(text)
            var xPos = 0
            if (rightAligned) xPos = -width
            var yPos = yOffset
            if (bottomAligned) yPos = -yOffset - 12
            context.drawTextWithShadow(textRenderer, text, xPos, yPos, -1)
            return 12
        }
    },

    ELIMINATIONS {
        override fun render(
            context: DrawContext,
            yOffset: Int,
            rightAligned: Boolean,
            bottomAligned: Boolean
        ): Int {
            if (StateManager.eliminations == -1) return 0

            val textRenderer = MinecraftClient.getInstance().textRenderer
            val text = Text.translatable("mrc.roundhud.eliminations", "${StateManager.eliminations}").withColor(0xa63efc)
            val width = textRenderer.getWidth(text)
            var xPos = 0
            if (rightAligned) xPos = -width
            var yPos = yOffset
            if (bottomAligned) yPos = -yOffset - 12
            context.drawTextWithShadow(textRenderer, text, xPos, yPos, -1)
            return 12
        }
    },

    PLAYTIME {
        override fun render(
            context: DrawContext,
            yOffset: Int,
            rightAligned: Boolean,
            bottomAligned: Boolean
        ): Int {
            StateManager.playtime?.let {
                val textRenderer = MinecraftClient.getInstance().textRenderer
                val text = Text.translatable("mrc.roundhud.playtime", it.elapsedNow().toLong(DurationUnit.SECONDS).toDuration(DurationUnit.SECONDS).toString()).withColor(0x3efca1)
                val width = textRenderer.getWidth(text)
                var xPos = 0
                if (rightAligned) xPos = -width
                var yPos = yOffset
                if (bottomAligned) yPos = -yOffset - 12
                context.drawTextWithShadow(textRenderer, text, xPos, yPos, -1)
                return 12
            }
            return 0
        }
    },

    MODIFIERS {
        override fun render(
            context: DrawContext,
            yOffset: Int,
            rightAligned: Boolean,
            bottomAligned: Boolean
        ): Int {
            if (StateManager.modifiers.isEmpty()) return 0
            val textRenderer = MinecraftClient.getInstance().textRenderer
            var yPos = yOffset
            if (bottomAligned) yPos = -yOffset - 12 - (StateManager.modifiers.size*20)

            val headerText = Text.translatable("mrc.roundhud.modifiers").withColor(0xa63efc)
            val headerWidth = textRenderer.getWidth(headerText)
            var xPos = 0
            if (rightAligned) xPos = -headerWidth
            context.drawTextWithShadow(textRenderer, headerText, xPos, yPos, -1)
            yPos += 12

            StateManager.modifiers.forEach {
                var xPos = 0
                if (rightAligned) xPos = -16
                context.drawItem(it.icon, xPos, yPos)
                context.drawStackOverlay(textRenderer, it.icon, xPos, yPos)

                val modifierText = it.translatable.withColor(0xfcfc54)
                val modifierWidth = textRenderer.getWidth(modifierText)
                xPos = 22
                if (rightAligned) xPos = -22 - modifierWidth
                context.drawTextWithShadow(textRenderer, modifierText, xPos, yPos+4, -1)

                StateManager.modifierBoosters[it]?.let { playerList ->
                    playerList.indices.forEach {
                        xPos = 28 + modifierWidth + (it*20)
                        if (rightAligned) xPos = -44 - modifierWidth - (it*20)
                        context.drawItem(GetPlayerHeadItem.getPlayerHeadItem(playerList[it]), xPos, yPos)
                    }
                }

                yPos += 20
            }

            return 12+(StateManager.modifiers.size*20)
        }
    };

    abstract fun render(context: DrawContext, yOffset: Int, rightAligned: Boolean, bottomAligned: Boolean): Int
    override fun asString(): String = name
    override fun getDisplayName(): Text = Text.translatable("mrc.hudelement.${name.lowercase()}")

    companion object {
        val CODEC = StringIdentifiable.createCodec(::values)
    }
}