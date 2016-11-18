package com.mwzhang.java.cdf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GnuPlot utility functions
 *
 * Created by mingwei on 11/6/16.
 */
public class GnuplotUtil {

    static public String gnuplotTemplate =
                    "reset\n" +
                    "set term postscript eps color enhanced \"Helvetica\" 25\n" +
                    "#set ylabel \"CDF\"\n" +
                    "set key right bottom\n" +
                    "set xlabel \"X-label\"\n" +
                    "set yrange [-0.05:1.05]\n" +
                    "set datafile separator ','\n" +
                    "set output \"output-cdf.eps\"\n" +
                    "plot \"output-cdf.csv\" using 1:2 title \"CDF\" with lines lw 5 lt 1;\n"
            ;

}
