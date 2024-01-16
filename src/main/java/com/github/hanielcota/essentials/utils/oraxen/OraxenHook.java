package com.github.hanielcota.essentials.utils.oraxen;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.font.FontManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OraxenHook {
    private static FontManager fontManager;

    public static String getGlyphChar(String tagName) {
        return getFontManager().getGlyphFromName(tagName).getCharacter();
    }

    private static FontManager getFontManager() {
        if (fontManager == null) {
            fontManager = OraxenPlugin.get().getFontManager();
        }
        return fontManager;
    }
}
