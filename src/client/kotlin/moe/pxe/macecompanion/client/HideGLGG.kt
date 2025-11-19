package moe.pxe.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import moe.pxe.macecompanion.client.config.Config
import moe.pxe.macecompanion.client.util.ContainsText
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents
import net.minecraft.text.Style
import net.minecraft.text.Text

object HideGLGG {
    var glTexts = listOf(
        Text.literal("GOOD LUCK").setStyle(Style.EMPTY.withColor(0xFFCE2D).withBold(true)),
        Text.literal("HAVE FUN").setStyle(Style.EMPTY.withColor(0xFFCE2D).withBold(true)),
        Text.literal("GL").setStyle(Style.EMPTY.withColor(0xFFCE2D).withBold(true)),
        Text.literal("HF").setStyle(Style.EMPTY.withColor(0xFFCE2D).withBold(true)),
    )
    var ggTexts = listOf(
        Text.literal("GOOD GAME").setStyle(Style.EMPTY.withColor(0xA0F9FF).withBold(true)),
        Text.literal("GG").setStyle(Style.EMPTY.withColor(0xA0F9FF).withBold(true))
    )
    fun registerListener() {
        ClientReceiveMessageEvents.ALLOW_GAME.register { message, overlay ->
            if (overlay) return@register true
            if (Config.hideGLMessages.value) glTexts.forEach { if (ContainsText.containsText(message, it)) return@register false }
            if (Config.hideGGMessages.value) ggTexts.forEach { if (ContainsText.containsText(message, it)) return@register false }
            return@register true
        }
    }
}