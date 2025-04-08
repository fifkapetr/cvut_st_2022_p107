package train;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTests {

    private Player spiedPlayer;

    @BeforeEach
    public void init() {
        spiedPlayer = Mockito.spy(Player.class);
    }

    @Test
    public void addWoodTest() {
        spiedPlayer.addWood(1);
        Mockito.verify(spiedPlayer, Mockito.times(1)).addWood(1);
        int wood = spiedPlayer.getWoodCount();
        Assertions.assertEquals(1, spiedPlayer.getWoodCount());
    }
}

class Player {
    private int stoneCount;
    private int woodCount;

    public void addWood(int count) {
        woodCount += count;
    }

    public void addStone(int count) {
        stoneCount += count;
    }

    public Player() {
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public int getWoodCount() {
        return woodCount;
    }
}
