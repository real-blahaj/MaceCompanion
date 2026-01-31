package moe.pxe.macecompanion.client.config.controllers

import net.minecraft.client.gui.screen.Screen

interface ConfigurableEnum {
    fun generateConfig(parent: Screen): Screen?
}