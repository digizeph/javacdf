package com.mwzhang.java.cdf;

import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.javatuples.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Mingwei on 12/9/15.
 * <p>
 * CDF related static functionality
 * <p>
 * Generate CDF with an input of a list of Integers.
 */
public class Cdf {

    private Frequency freq;
    private DescriptiveStatistics stats;

    private TreeSet<Double> listDoubles;
    private TreeSet<Integer> listIntegers;

    private Cdf() {
        // do something
        this.freq = new Frequency();
        this.stats = new DescriptiveStatistics();
        this.listDoubles = new TreeSet<>();
        this.listIntegers = new TreeSet<>();
    }

    public static void main(String[] args) {

        // take CSV file and column id as input.
        if (args.length != 2) {
            System.err.println("USAGE: java -jar javacdf.jar FILENAME COLUMN");
            return;
        }
        String filename = args[0];
        int col = Integer.valueOf(args[1]);


        Cdf cdf = new Cdf();
        cdf.readCsv(filename, col);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output-cdf.gnuplot"));
            cdf.writeGnuplotTemplate(writer);
            cdf.writeCsvResults(writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done.");
    }

    private void readCsv(String filename, int col) {

        // READING
        System.out.println("Reading CSV file...");
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length < col) {
                    System.err.println("ERR: file does not have enough columns");
                    return;
                }
                Double value = Double.valueOf(columns[col - 1]);
                addFloat(value);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCsvResults(BufferedWriter writer) throws IOException {
        System.out.println("Outputting CSV file...");
        for (Pair<Double, Double> pair : getCdfFloat()) {
            writer.write(String.format("%f,%f\n", pair.getValue0(), pair.getValue1()));
        }
    }

    private void writeGnuplotTemplate(BufferedWriter writer) throws IOException {
        System.out.println("Outputting Gnuplot file...");
        writer.write(GnuplotUtil.gnuplotTemplate);
    }

    private void addFloat(double x) {
        freq.addValue(x);
        stats.addValue(x);
        listDoubles.add(x);
    }

    private List<Pair<Double, Double>> getCdfFloat() {

        List<Pair<Double, Double>> results = new ArrayList<>();

        for (Double n : listDoubles) {
            Double p = freq.getCumPct(n);
            results.add(new Pair<>(n, p));
        }
        return results;
    }

}
