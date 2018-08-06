package com.company.reader;

public class EventData {
    /**
     * Full name of discipline.
     */
    private String name;
    /**
     * Short name of the discipline.
     */
    private String shortName;
    /**
     * Coefficient a;
     */
    private Double a;
    /**
     * Coefficient b;
     */
    private Double b;
    /**
     * Coefficient c;
     */
    private Double c;
    /**
     * Creates EventData object with initialized parameters.
     *
     * @param shortName Short name of the discipline.
     * @param a         Coefficient a;
     * @param b         Coefficient b;
     * @param c         Coefficient c;
     */
    public EventData(final String name, final String shortName, final Double a, final Double b, final Double c) {
        this.name = name;
        this.shortName = shortName;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }
}
