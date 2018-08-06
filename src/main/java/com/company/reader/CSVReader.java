package com.company.reader;

import com.company.*;

import java.io.File;
import java.util.*;

public class CSVReader extends CalculateRecord {
    private final static String CSV_SPLITTER = ";";
    private final List<CSVData> cSVData;
    private final List<Record> records = new ArrayList<>();

    @Override
    public List<Record> getRecords() {
        return records;
    }

    public CSVReader(final List<CSVData> cSVData, final String csvFile) throws Exception {
        this.cSVData = cSVData;
        final Scanner scanner = new Scanner(new File(csvFile));
        int lineNumber = 0;
        while (scanner.hasNext()) {
            lineNumber++;
            final String line = scanner.nextLine();
            final String[] recordStr = line.split(CSV_SPLITTER);
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
            scores.put("d1500m", getSeconds(recordStr[recordStr.length - 1]));
            records.add(record);
            record.setTotalScore(generateTotalScore(record.getScores()));
        }
        scanner.close();
        sortByTotalScore(records);
    }

    /**
     * Translates format date from m.s.ms to seconds.
     *
     * @param s Date in format m.s.ms to
     * @return seconds.
     */
    private Double getSeconds(final String s) {
        final String[] time = s.split("\\.");
        final double min = Double.parseDouble(time[0].trim());
        final double sec = Double.parseDouble(time[1].trim());
        final double milliseconds = Double.parseDouble(time[2].trim());
        return min * 60 + sec + 1000. / milliseconds;
    }

    /**
     * Returns data about event by short name.
     * @param name Short name.
     * @return Data about events.
     */
    protected EventData getEventDataByScoreName(final String name) {
        for (final CSVData CSVData : cSVData) {
            if (CSVData.getShortName().equals(name)) return CSVData;
        }
        return null;
    }

    /**
     * Return the short name of a discipline by the column index.
     *
     * @param i The column index in CSV file.
     * @return The short name of discipline.
     */
    private String getScoreNameByIndex(int i) {
        for (final CSVData CSVData : this.cSVData) {
            if (CSVData.getIndex().equals(i)) return CSVData.getShortName();
        }
        return null;
    }
}
