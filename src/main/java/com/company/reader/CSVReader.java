package com.company.reader;

import com.company.Record;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSVReader extends CalculateRecord {
    private final static String CSV_SPLITTER = ";";
    private final static String DISCIPLINE_WITH_TIME = "d1500m";
    private final static int COLUMN_AMOUNT = 11;
    private final List<CSVData> cSVData;

    public CSVReader(final List<CSVData> cSVData, final String csvFile) throws Exception {
        this.cSVData = cSVData;
        try (final Scanner scanner = new Scanner(new File(csvFile))) {
            int lineNumber = 0;
            while (scanner.hasNext()) {
                lineNumber++;
                final String[] recordStr = scanner.nextLine().split(CSV_SPLITTER);
                if (recordStr.length != COLUMN_AMOUNT) {
                    continue;
                }
                final Record record = new Record(recordStr[0]);
                final Map<String, Double> scores = record.getScores();
                for (int i = 1; i < recordStr.length - 1; i++) {
                    final String shortName = getScoreNameByIndex(i);
                    if (shortName != null) {
                        try {
                            scores.put(shortName, Double.parseDouble(recordStr[i]));
                        } catch (NumberFormatException e) {
                            throw new Exception("Error in file. Not valid format in line" + lineNumber + " and column " + (i + 1));
                        }
                    }
                }
                scores.put(DISCIPLINE_WITH_TIME, getSeconds(recordStr[recordStr.length - 1]));
                getRecords().add(record);
                record.setTotalScore(generateTotalScore(record.getScores()));
            }
        }
    }

    /**
     * Translates format date from m.s.ms to seconds.
     *
     * @param s Date in format m.s.ms to
     * @return seconds.
     */
    public Double getSeconds(final String s) {
        if (s == null) {
            System.out.println("Column with time is empty.");
            return null;
        }
        final String[] time = s.split("\\.");
        try {
            final long min = Long.parseLong(time[0].trim());
            final long sec = Long.parseLong(time[1].trim());
            final long milliseconds = Long.parseLong(time[2].trim());
            return Duration.ofMinutes(min).plusSeconds(sec).plusMillis(milliseconds).toMillis() / 1000.;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Time string '" + s + "' is incorrect");
            return null;
        }
    }

    /**
     * Returns data about event by short name.
     *
     * @param name Short name.
     * @return Data about events.
     */
    protected EventData getEventDataByScoreName(final String name) {
        return cSVData.stream().filter(csvData -> csvData.getShortName().equals(name)).findFirst().get();
    }

    /**
     * Return the short name of a discipline by the column index.
     *
     * @param i The column index in CSV file.
     * @return The short name of discipline.
     */
    protected String getScoreNameByIndex(int i) {
        return cSVData.stream().filter(c -> c.getIndex().equals(i)).findFirst().get().getShortName();
    }
}
