package moe.pxe.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import moe.pxe.macecompanion.client.config.Config
import moe.pxe.macecompanion.client.util.SendMessage

object AutoGG {
    fun sendGGMessage() {
        if (!Config.useAutoGG.value) return
        SendMessage.sendDelayedMessage(Config.autoGGStrings.value.random(), Config.ggDelayTicks.value)
    }
}