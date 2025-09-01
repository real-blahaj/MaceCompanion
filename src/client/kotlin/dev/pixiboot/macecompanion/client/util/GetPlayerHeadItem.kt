package dev.pixiboot.macecompanion.client.util

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.client.MinecraftClient
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.ProfileComponent
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import java.util.UUID

object GetPlayerHeadItem {
    private val headItemCache = mutableMapOf<GameProfile, ItemStack>()

    fun getPlayerHeadItem(property: Property): ItemStack {
        val profile = GameProfile(UUID.randomUUID(), "")
        profile.properties.put("textures", property)
        return getPlayerHeadItem(profile)
    }

    fun getPlayerHeadItem(profile: GameProfile): ItemStack {
        headItemCache[profile]?.also { return it }
        val head = ItemStack(Items.PLAYER_HEAD)
        MinecraftClient.getInstance().skinProvider.fetchSkinTextures(profile)
        head.set(DataComponentTypes.PROFILE, ProfileComponent(profile))
        headItemCache[profile] = head
        return head
    }
}