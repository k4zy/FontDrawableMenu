package com.kazy.fontdrawable.menu;

import com.kazy.fontdrawable.FontDrawable;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

public abstract class MenuCreator {

    private static final int MENU_ICON_SIZE = 32;

    private static final int MENU_ICON_PADDING = 4;

    private Typeface typeface;

    private Context context;

    public MenuCreator(Typeface typeface, Context context) {
        this.typeface = typeface;
        this.context = context;
    }

    public void setupMenu(Menu menu) {
        menu.clear();
        @StringRes int[] visibleMenuTitles = visibleMenuTitles();
        char[] visibleMenuIcons = visibleMenuIcons();
        for (int i = 0; i < visibleMenuTitles.length; i++) {
            MenuItem item = menu.add(Menu.NONE, visibleMenuTitles[i], Menu.NONE, visibleMenuTitles[i]);
            item.setIcon(createMenuIcon(visibleMenuIcons[i]));
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        for (@StringRes int stringRes : invisibleMenuTitles()) {
            menu.add(Menu.NONE, stringRes, Menu.NONE, stringRes);
        }
    }

    private Drawable createMenuIcon(char fontCode) {
        return new FontDrawable.Builder(context, fontCode, typeface)
                .setSizeDp(MENU_ICON_SIZE)
                .setColor(ContextCompat.getColor(context, itemColor()))
                .setPaddingDp(MENU_ICON_PADDING).build();
    }

    @ColorRes
    public int itemColor() {
        return R.color.gray;
    }

    public abstract char[] visibleMenuIcons();

    @StringRes
    public abstract int[] visibleMenuTitles();

    @StringRes
    public abstract int[] invisibleMenuTitles();

}
