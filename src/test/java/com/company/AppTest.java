package com.company;

import static org.junit.Assert.assertTrue;

import com.company.reader.CSVData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static final String[] args = new String[]{"C:/results.csv","C:/Users/Игорь/IdeaProjects/csv/file.xml"};
    public static final List<CSVData> EVENT_DATA = new ArrayList<>();

    /**
     * Initializing data for calculation and naming of the decathlon.
     */
    static  {
        EVENT_DATA.add(new CSVData(1, "100 m", "d100m", 25.4347, 18., 1.81));
        EVENT_DATA.add(new CSVData(2, "Long jump", "longJump", 0.14354, 220., 1.4));
        EVENT_DATA.add(new CSVData(3, "Shot put", "shotPut", 51.39, 1.5, 1.05));
        EVENT_DATA.add(new CSVData(4, "High jump", "highJump", 0.8465, 75., 1.42));
        EVENT_DATA.add(new CSVData(5, "400 m", "d400m", 1.53775, 82., 1.81));
        EVENT_DATA.add(new CSVData(6, "110 m hurdles", "d110m", 5.74352, 28.5, 1.92));
        EVENT_DATA.add(new CSVData(7, "Discus throw", "discusThrow", 12.91, 4., 1.1));
        EVENT_DATA.add(new CSVData(8, "Pole vault", "poleVault", 0.2797, 100., 1.35));
        EVENT_DATA.add(new CSVData(9, "Javelin throw", "javelinThrow", 10.14, 7., 1.08));
        EVENT_DATA.add(new CSVData(10, "1500 m", "d1500m", 0.03768, 480., 1.85));
    }
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        App.main(args);
    }
}
