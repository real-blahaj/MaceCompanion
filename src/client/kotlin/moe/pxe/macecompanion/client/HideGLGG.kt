package moe.pxe.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import moe.pxe.macecompanion.client.config.Config
import moe.pxe.macecompanion.client.util.ContainsText
import moe.pxe.macecompanion.client.util.OnMaceRoulette
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents

object HideGLGG {
    var glTexts = listOf(
        "GOOD LUCK",
        "HAVE FUN",
        "GL",
        "HF",
    )
    var ggTexts = listOf(
        "GOOD GAME",
        "GG"
    )
    fun registerListener() {
        ClientReceiveMessageEvents.ALLOW_GAME.register { message, overlay ->
            if (!OnMaceRoulette.onMace) return@register true
            if (overlay) return@register true
            if (Config.hideGLMessages.value) glTexts.forEach { if (ContainsText.boldString(message, it)) return@register false }
            if (Config.hideGGMessages.value) ggTexts.forEach { if (ContainsText.boldString(message, it)) return@register false }
            return@register true
        }
    }
}