package moe.pxe.macecompanion.client.util

import net.minecraft.text.Text

object ContainsText {
    fun containsText(text: Text, subtext: Text): Boolean {
        if (text.string.contains(subtext.string) && text.style.equals(subtext.style)) {
            return true
        }
        for (child in text.siblings) {
            if (containsText(child, subtext)) return true
        }
        return false
    }
}