package com.backtobedrock.LitePlaytimeRewards.guis.clickActions;

import com.backtobedrock.LitePlaytimeRewards.guis.PagedGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class PreviousPageClickAction extends ClickAction {

    private final PagedGUI gui;

    public PreviousPageClickAction(PagedGUI gui) {
        this.gui = gui;
    }

    @Override
    public void execute(Player player, ClickType clickType) {
        this.gui.previousPage();
    }
}
