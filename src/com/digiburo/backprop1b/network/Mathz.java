package com.digiburo.backprop1b.network;

/**
 * Math support, all static methods
 */
import java.util.Random;

/**
 * Math support, all static methods
 * 
 * @author gsc
 */
public class Mathz {
    
    /**
     * Return a random number within the specified range
     * 
     * @param lower range
     * @param upper range
     * @return a random number within the specified range
     */
    public static synchronized double getBoundedRandom(double lower, double upper) {
    	double range = upper - lower;
    	double result = _random.nextDouble() * range + lower;
    	return(result);
    }
    
    /**
     * Return a random double
     * 
     * @return random double
     */
    public static synchronized double getRandomDouble() {
    	return(_random.nextDouble());
    }
    
    /**
     * Convert a double array to a thresholded array
     * 
     * @param threshold value
     * @param candidatez raw values to threshold
     * @return 0, 1 or -1 (if failed threshold)
     */
    public static synchronized int[] thresholdArray(double threshold, double[] candidatez) {
    	double upper_threshold = 1.0 - threshold;
    	double lower_threshold = threshold;
	
    	int[] result = new int[candidatez.length];
	
    	for (int ii = 0; ii < candidatez.length; ii++) {
    		if (candidatez[ii] > upper_threshold) {
    			result[ii] = 1;
    		} else if (candidatez[ii] < lower_threshold) {
    			result[ii] = 0;
    		} else {
    			result[ii] = -1;
    		}
    	}
	
    	return(result);
    }
    
    /**
     * Duh.
     */
    private final static Random _random = new Random();
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
