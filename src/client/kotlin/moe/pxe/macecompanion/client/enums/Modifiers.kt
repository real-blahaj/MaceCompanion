package moe.pxe.macecompanion.client.enums

import com.mojang.authlib.properties.Property
import moe.pxe.macecompanion.client.util.GetPlayerHeadItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.text.MutableText
import net.minecraft.text.Text

enum class Modifiers {
    DOUBLE {
        override val matchName = "Double Mace"
        override val translatable = Text.translatable("mrc.modifier.double_mace")
        private fun icon(): ItemStack {
            val item = ItemStack(Items.MACE)
            item.count = 2
            return item
        }
        override val icon = icon()

    },

    TRIPLE {
        override val matchName = "Triple Mace"
        override val translatable = Text.translatable("mrc.modifier.triple_mace")
        private fun icon(): ItemStack {
            val item = ItemStack(Items.MACE)
            item.count = 3
            return item
        }
        override val icon = icon()
    },

    TINY {
        override val matchName = "Tiny Mace"
        override val translatable = Text.translatable("mrc.modifier.tiny_mace")
        override val icon = ItemStack(Items.STONE_BUTTON)
    },

    BIG {
        override val matchName = "Big Mace"
        override val translatable = Text.translatable("mrc.modifier.big_mace")
        override val icon = ItemStack(Items.STONE)
    },

    SLOW_TIME {
        override val matchName = "Slow Time"
        override val translatable = Text.translatable("mrc.modifier.slow_time")
        override val icon = ItemStack(Items.CLOCK)
    },

    MISS_EQUALS_DIE {
        override val matchName = "Miss = Die"
        override val translatable = Text.translatable("mrc.modifier.miss_equals_die")
        override val icon = GetPlayerHeadItem.getPlayerHeadItem(Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4NTJiYTE1ODRkYTllNTcxNDg1OTk5NTQ1MWU0Yjk0NzQ4YzRkZDYzYWU0NTQzYzE1ZjlmOGFlYzY1YzgifX19"))
    },

    SHOCKWAVE {
        override val matchName = "Shockwave Mace"
        override val translatable = Text.translatable("mrc.modifier.shockwave_mace")
        override val icon = ItemStack(Items.HEART_OF_THE_SEA)
    },

    ELYTRA {
        override val matchName = "Elytra Launch"
        override val translatable = Text.translatable("mrc.modifier.elytra_launch")
        override val icon = ItemStack(Items.ELYTRA)
    },

    WIND_CHARGE {
        override val matchName = "Wind Charge Storm"
        override val translatable = Text.translatable("mrc.modifier.wind_charge_storm")
        override val icon = ItemStack(Items.WIND_CHARGE)
    },

    COBWEBS {
        override val matchName = "Player Cobwebs"
        override val translatable = Text.translatable("mrc.modifier.player_cobwebs")
        override val icon = ItemStack(Items.COBWEB)
    },

    BLOCKS {
        override val matchName = "Player Blocks"
        override val translatable = Text.translatable("mrc.modifier.player_blocks")
        override val icon = ItemStack(Items.CHERRY_PLANKS)
    },

    CAGED {
        override val matchName = "Caged!"
        override val translatable = Text.translatable("mrc.modifier.caged")
        override val icon = ItemStack(Items.IRON_BARS)
    },

    PILLARS {
        override val matchName = "Pillars"
        override val translatable = Text.translatable("mrc.modifier.pillars")
        override val icon = ItemStack(Items.POLISHED_BASALT)
    },

    SWEEPER {
        override val matchName = "Sweeper"
        override val translatable = Text.translatable("mrc.modifier.sweeper")
        override val icon = ItemStack(Items.COMPASS)
    },

    DROP {
        override val matchName = "Mace Drop"
        override val translatable = Text.translatable("mrc.modifier.mace_drop")
        override val icon = ItemStack(Items.BARREL)
    },

    HOLES {
        override val matchName = "Holes"
        override val translatable = Text.translatable("mrc.modifier.holes")
        override val icon = ItemStack(Items.FROGSPAWN)
    },

    SHRINKING {
        override val matchName = "Shrinking Map"
        override val translatable = Text.translatable("mrc.modifier.shrinking_map")
        override val icon = ItemStack(Items.DISC_FRAGMENT_5)
    },

    DONUT {
        override val matchName = "Donut Map"
        override val translatable = Text.translatable("mrc.modifier.donut_map")
        override val icon = ItemStack(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE)
    },

    BOUNCY {
        override val matchName = "Bouncy Floor"
        override val translatable = Text.translatable("mrc.modifier.bouncy_floor")
        override val icon = ItemStack(Items.SLIME_BLOCK)
    },

    ICE {
        override val matchName = "Ice Floor"
        override val translatable = Text.translatable("mrc.modifier.ice_floor")
        override val icon = ItemStack(Items.ICE)
    },

    FRAGILE {
        override val matchName = "Fragile Floor"
        override val translatable = Text.translatable("mrc.modifier.fragile_floor")
        override val icon = ItemStack(Items.GLASS)
    },

    HIDDEN {
        override val matchName = "Hidden Floor"
        override val translatable = Text.translatable("mrc.modifier.hidden_floor")
        override val icon = ItemStack(Items.BARRIER)
    },

    PICKAXE {
        override val matchName = "Pickaxe"
        override val translatable = Text.translatable("mrc.modifier.pickaxe")
        override val icon = ItemStack(Items.NETHERITE_PICKAXE)
    },

    WIND_BURST {
        override val matchName = "Wind Burst Mace"
        override val translatable = Text.translatable("mrc.modifier.wind_burst_mace")
        override val icon = ItemStack(Items.ENCHANTED_BOOK)
    },

    VICTIM {
        override val matchName = "Victim Mace"
        override val translatable = Text.translatable("mrc.modifier.victim_mace")
        override val icon = ItemStack(Items.SPECTRAL_ARROW)
    },

    CEILING {
        override val matchName = "Ceiling"
        override val translatable = Text.translatable("mrc.modifier.ceiling")
        override val icon = ItemStack(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR)
    },

    RANDOM_SIZE {
        override val matchName = "Random Size"
        override val translatable = Text.translatable("mrc.modifier.random_size")
        override val icon = GetPlayerHeadItem.getPlayerHeadItem(Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZkNGEwMWRiNjEyNjYwMWRlZDE0MDZjZjYyMzhjZTJiNzAyNGVhY2U1ZWE2MDRmYmMyMDhhMmFmMjljOTdhZCJ9fX0="))
    },

    GLOWING {
        override val matchName = "Glow in the Dark"
        override val translatable = Text.translatable("mrc.modifier.glow_in_the_dark")
        override val icon = ItemStack(Items.GLOW_INK_SAC)
    },

    MOVE_EQUALS_GROW {
        override val matchName = "Move = Grow"
        override val translatable = Text.translatable("mrc.modifier.move_equals_grow")
        override val icon = ItemStack(Items.CHAINMAIL_BOOTS)
    },

    PINATA {
        override val matchName = "Zombie Piñata"
        override val translatable = Text.translatable("mrc.modifier.zombie_pinata")
        override val icon = ItemStack(Items.ZOMBIE_HORSE_SPAWN_EGG)
    },

    SOUL_FLOOR {
        override val matchName = "Soul Floor"
        override val translatable = Text.translatable("mrc.modifier.soul_floor")
        override val icon = ItemStack(Items.SOUL_SAND)
    },

    PUMPKIN {
        override val matchName = "Pumpkin Curse"
        override val translatable = Text.translatable("mrc.modifier.pumpkin_curse")
        override val icon = ItemStack(Items.PUMPKIN)
    },

    MIRAGE {
        override val matchName = "Mirage"
        override val translatable = Text.translatable("mrc.modifier.mirage")
        override val icon = ItemStack(Items.BREEZE_SPAWN_EGG)
    },

    ALL {
        override val matchName = "All Mace"
        override val translatable = Text.translatable("mrc.modifier.all_mace")
        private fun icon(): ItemStack {
            val item = ItemStack(Items.MACE)
            item.count = 99
            return item
        }
        override val icon = icon()
    },

    PHARAOHS_CURSE {
        override val matchName = "Pharaoh's Curse"
        override val translatable = Text.translatable("mrc.modifier.pharaohs_curse")
        override val icon = ItemStack(Items.SUSPICIOUS_SAND)
    },

    STICKY {
        override val matchName = "Sticky Floor"
        override val translatable = Text.translatable("mrc.modifier.sticky_floor")
        override val icon = ItemStack(Items.HONEY_BLOCK)
    },

    UNKNOWN {
        override val matchName = "???"
        override val translatable = Text.translatable("mrc.modifier.unknown")
        override val icon = ItemStack(Items.AIR)
    };


    abstract val matchName: String
    abstract val translatable: MutableText
    abstract val icon: ItemStack
}