package dev.pixiboot.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import dev.pixiboot.macecompanion.client.config.Config
import dev.pixiboot.macecompanion.client.util.SendMessage

object AutoGL {
    fun sendGlMessage() {
        if (!Config.useAutoGL.value) return
        SendMessage.sendDelayedMessage(Config.autoGLStrings.value.random(), Config.glDelayTicks.value)
    }
}