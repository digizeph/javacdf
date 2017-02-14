package com.mwzhang.java.cdf;

/**
 * GnuPlot utility functions
 *
 * Created by mingwei on 11/6/16.
 */
class GnuplotUtil {

    static String gnuplotTemplate =
                    "reset\n" +
                    "set term postscript eps color enhanced \"Helvetica\" 25\n" +
                    "#set ylabel \"CDF\"\n" +
                    "set key right bottom\n" +
                    "set xlabel \"X-label\"\n" +
                    "set yrange [-0.05:1.05]\n" +
                    "set datafile separator ','\n" +
                    "set output \"output-cdf.eps\"\n" +
                            "plot \"-\" using 1:2 title \"CDF\" with lines lw 5 lt 1;\n"
            ;

}
