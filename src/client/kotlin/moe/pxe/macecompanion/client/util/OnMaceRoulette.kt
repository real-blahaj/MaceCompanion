package moe.pxe.macecompanion.client.util

import dev.dfonline.flint.Flint
import dev.dfonline.flint.FlintAPI
import dev.dfonline.flint.feature.trait.ModeSwitchListeningFeature
import dev.dfonline.flint.hypercube.Mode
import moe.pxe.macecompanion.client.StateManager

object OnMaceRoulette {
    var onMace = false
    fun registerFlintFeature() {
        FlintAPI.confirmLocationWithLocate()
        FlintAPI.registerFeature(
            ModeSwitchListeningFeature { oldMode, newMode ->
                StateManager.resetState()
                onMace = false
                if (newMode != Mode.PLAY) return@ModeSwitchListeningFeature
                onMace = setOf(14000002 /* Mace Roulette */, 14000004 /* MaceDev */, 14000007, 70525, 25000002).contains(Flint.getUser().plot?.id)
            }
        )
    }
}