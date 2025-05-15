package game.forbiddenisland.model;

import org.junit.jupiter.api.Test;
import team.group14.forbiddenisland.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class WaterMeterTest {
    @Test
    void testInitializeLevel() {
        WaterMeter waterMeter = new WaterMeter(1);
        assertEquals(1, waterMeter.getLevel());
    }

    @Test
    void testIncreaseLevel() {
        WaterMeter waterMeter = new WaterMeter(1);
        waterMeter.increaseLevel();
        assertEquals(2, waterMeter.getLevel());
    }

    @Test
    void testGetFloodDrawCount() {
        WaterMeter waterMeter = new WaterMeter(0);
        assertEquals(2, waterMeter.getFloodDrawCount());
        waterMeter.increaseLevel();
        assertEquals(3, waterMeter.getFloodDrawCount());
        waterMeter.increaseLevel();
        assertEquals(4, waterMeter.getFloodDrawCount());
        waterMeter.increaseLevel();
        assertEquals(5, waterMeter.getFloodDrawCount());
        waterMeter = new WaterMeter(10);
        assertEquals(5, waterMeter.getFloodDrawCount());
    }
}
