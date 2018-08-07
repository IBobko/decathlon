package com.company;

import com.company.reader.CSVData;
import com.company.reader.CSVReader;
import com.company.reader.RecordReader;
import com.company.writer.WriteXMLFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for calculation sportsmen's scores.
 */
public class App {
    private static final List<CSVData> EVENT_DATA = new ArrayList<>();

    /**
     * Initializing database for calculation and naming of the decathlon.
     */
    static {
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

    public static void main(String[] args) throws Exception {
        if (args.length >= 2) {
            if (!new File(args[0]).exists()) {
                System.out.println("File '" + args[0] + "' isn't exists. The end.");
                System.exit(0);
            }
            final RecordReader recordReader = new CSVReader(EVENT_DATA, args[0]);
            recordReader.sortByTotalScore();
            final List<Record> records = recordReader.getRecords();
            final WriteXMLFile writeXMLFile = new WriteXMLFile(args[1]);
            writeXMLFile.write(records);
            System.out.println("The end.");
        } else {
            System.out.println("Not enough parameters.");
        }
    }
}
