package seng201.team0;

import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class UpgradeManager {
    private List<Upgrade> upgradeList = new ArrayList<>();
    public UpgradeManager(){
        upgradeList.addAll(List.of(new Upgrade("Tower Level Boost!", 600.00),
                new Upgrade("Tower Resource Amount Boost!", 200.00),
                new Upgrade("Tower Reload Speed Boost!", 200.00)));
    }
    public List<Upgrade> getUpgradeList(){
        return upgradeList;
    }

}
