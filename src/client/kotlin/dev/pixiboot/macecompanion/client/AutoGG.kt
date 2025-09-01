package dev.pixiboot.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import dev.pixiboot.macecompanion.client.config.Config
import dev.pixiboot.macecompanion.client.util.SendMessage

object AutoGG {
    fun sendGGMessage() {
        if (!Config.useAutoGG.value) return
        SendMessage.sendDelayedMessage(Config.autoGGStrings.value.random(), Config.ggDelayTicks.value)
    }
}