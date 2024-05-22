package seng201.team0.unittests.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.UpgradeManager;
import seng201.team0.models.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpgradesTest {
    private UpgradeManager upgradeManager;
    private List<Upgrade> testUpgradesList = new ArrayList<>();

    @BeforeEach
    /*
    instantiates 2 new upgrades
     */
    public void init (){
        upgradeManager = new UpgradeManager();
        Upgrade upgrade1 = new Upgrade("TestUpgrade1", 0);
        Upgrade upgrade2 = new Upgrade("TestUpgrade2", 0);
        Upgrade upgrade3 = new Upgrade("TestUpgrade3", 0);
        testUpgradesList.addAll(List.of(upgrade1,upgrade2,upgrade3));
    }
    @Test
    /* tests getUpgradeList method */
    public void getUpgradeListTest(){
        assertEquals(testUpgradesList.size(), upgradeManager.getUpgradeList().size());
    }
}
