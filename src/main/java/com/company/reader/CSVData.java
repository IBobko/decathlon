package com.company.reader;

public class CSVData extends EventData {
    /**
     * Index of column in CSV file.
     */
    private Integer index;

    /** This class is a logical row of database with data about some of discipline of decathlon.
     *
     * @param index Index in CVS file.
     * @param name Full name of discipline,.
     * @param shortName Short name of discipline.
     * @param a Coefficient a;
     * @param b Coefficient b;
     * @param c Coefficient c;
     */
    public CSVData(final Integer index, final String name, final String shortName, final Double a, final Double b, final Double c) {
        super(name,shortName,a,b,c);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
