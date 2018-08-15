package com.company.reader;

import com.company.AppTest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculateRecordTest {
    private CalculateRecord calculateRecord;

    @Before
    public void setUp() throws Exception {
        calculateRecord = new CSVReader(AppTest.EVENT_DATA, AppTest.args[0]);
    }

    @Test
    public void sortByTotalScore() {
        calculateRecord.sortByTotalScore();
        assertEquals("Jane Doe", calculateRecord.getRecords().get(0).getName());
    }

    @Test
    public void getEventDataByScoreName() {
        final EventData eventData = calculateRecord.getEventDataByScoreName("d100m");
        assertEquals("100 m", eventData.getName());
        assertEquals("d100m", eventData.getShortName());
        assertEquals(25.4347, eventData.getA(), 0.1);
        assertEquals(18., eventData.getB(), 0.1);
        assertEquals(1.81, eventData.getC(), 0.1);

        try {
            final EventData eventData2 = calculateRecord.getEventDataByScoreName("d100m2");
            fail();
        } catch (NoSuchElementException e) {

        }
    }

    @Test
    public void generateTotalScore() {
        final Map<String, Double> scores = new HashMap<>();
        scores.put("d100m", 12.61);
        scores.put("longJump", 5.0);
        scores.put("shotPut", 9.22);
        scores.put("highJump", 1.50);
        scores.put("d400m", 60.39);
        scores.put("d110m", 16.43);
        scores.put("discusThrow", 21.60);
        scores.put("poleVault", 2.60);
        scores.put("javelinThrow", 35.81);
        scores.put("d1500m", 325.72);
        assertEquals(3946.1587,calculateRecord.generateTotalScore(scores),0.0001);

        try {
            final Map<String, Double> scores0 = new HashMap<>();
            scores0.put("d100m", 0.);
            scores0.put("longJump", 0.);
            scores0.put("shotPut", 0.);
            scores0.put("highJump", 0.);
            scores0.put("d400m", 0.);
            scores0.put("d110m", 0.);
            scores0.put("discusThrow", 0.);
            scores0.put("poleVault", 0.);
            scores0.put("javelinThrow", 0.);
            scores0.put("d1500m", 0.);
            calculateRecord.generateTotalScore(scores0);
            fail();
        } catch (RuntimeException ignored) {

        }

        try {
            final Map<String, Double> scoresMinus1 = new HashMap<>();
            scoresMinus1.put("d100m", -1.);
            scoresMinus1.put("longJump", -1.);
            scoresMinus1.put("shotPut", -1.);
            scoresMinus1.put("highJump", -1.);
            scoresMinus1.put("d400m", -1.);
            scoresMinus1.put("d110m", -1.);
            scoresMinus1.put("discusThrow", -1.);
            scoresMinus1.put("poleVault", -1.);
            scoresMinus1.put("javelinThrow", -1.);
            scoresMinus1.put("d1500m", -1.);
            calculateRecord.generateTotalScore(scoresMinus1);
            fail();
        } catch (RuntimeException ignored) {
        }
    }

    @Test
    public void calculateScore() {
        assertEquals(536.5402,calculateRecord.calculateScore("d100m",12.61),0.001);
        try {
            calculateRecord.calculateScore("d100m", -1.);
            fail();
        } catch(RuntimeException ignored){}
    }
}