package com.backtobedrock.LitePlaytimeRewards.guis;

import com.backtobedrock.LitePlaytimeRewards.configs.PlayerData;
import com.backtobedrock.LitePlaytimeRewards.enums.GUIType;
import com.backtobedrock.LitePlaytimeRewards.models.Reward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RewardsGUI extends PagedGUI {

    private final PlayerData data;
    private final TreeMap<String, Reward> rewards;

    public RewardsGUI(PlayerData data, TreeMap<String, Reward> rewards) {
        super(new CustomHolder(data.getRewards().size(), true, GUIType.REWARDS), data.getRewards().size());
        this.customHolder.setTitle(this.plugin.getMessages().getRewardsInventoryTitle());
        this.rewards = rewards;
        this.data = data;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.setData();
    }

    @Override
    public void setData() {
        super.setData();
        this.setRewards();
    }

    public void setRewards() {
        List<Reward> orderedRewards = this.rewards.values().stream().sorted(this.plugin.getLPRConfig().getRewardsOrder().getComparator()).collect(Collectors.toList());
        List<Icon> icons = new ArrayList<>();

        orderedRewards.subList((this.currentPage - 1) * 28, Math.min(this.currentPage * 28, this.rewards.size())).forEach(e -> {
            Icon icon = new Icon(this.createGUIItem(e.getcReward().getDisplayName(), e.getRewardsGUIDescription(this.data.getPlayer()), false, e.getcReward().getDisplayItem()), Collections.emptyList(), e);
            switch (this.plugin.getLPRConfig().getInventoryLayout()) {
                case FILL:
                    this.customHolder.addIcon(icon);
                    break;
                case CENTERED:
                    icons.add(icon);
                    if (icons.size() == 7) {
                        this.customHolder.addRow(icons);
                        icons.clear();
                    }
                    break;
            }
        });

        if (!icons.isEmpty()) {
            this.customHolder.addRow(icons);
        }
    }
}
