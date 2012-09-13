package com.digiburo.backprop1b.demo3;

import java.io.File;

import java.io.IOException; 
import java.io.FileNotFoundException;

import com.digiburo.backprop1b.network.PatternList;

/**
 * Train for demo3, digit recognition.
 *
 * @author G.S. Cole (gsc@digiburo.com)
 * @version $Id: Trainer.java,v 1.1 2002/02/03 04:24:05 gsc Exp $
 */

/*
 * Development Environment:
 *   Linux 2.2.14-5.0 (Red Hat 6.2)
 *   Java Developers Kit 1.3.1
 *
 * Legalise:  
 *   Copyright (C) 2002 Digital Burro, INC.
 *
 * Maintenance History:
 *   $Log: Trainer.java,v $
 *   Revision 1.1  2002/02/03 04:24:05  gsc
 *   Initial Check In
 */

public class Trainer {
    public static final double one = 0.9999999999;
    public static final double zero = 0.0000000001;

    private static final String TRAIN_FILENAME = "demo3.trn";    
    private static final String NETWORK_FILENAME = "demo3.serial";

    private BpDemo3 bp;
    private PatternList pl;

    /**
     * Create network
     */
    public Trainer() {
    	bp = new BpDemo3(25, 5, 10, 0.45, 0.9);
    }

    /**
     * Load training datum
     * @param datum training file
     */
    public int loadTraining(File datum) throws IOException, FileNotFoundException, ClassNotFoundException {
    	pl = new PatternList();
    	pl.reader(datum);
    	return(pl.size());
    }

    /**
     * Train the network on these patterns
     */
    public void performTraining() {
		bp.trainNetwork(pl, pl.size(), -1, 0.15, true);
    }

    /**
     * Save this network for later use.
     * @param datum file to save as
     */
    public void saveTraining(File datum) throws IOException, FileNotFoundException {
        bp.saveNetwork(datum);
    }

	/**
     *
     */
	public static void main(String args[]) throws Exception {
		System.out.println("begin");

		Trainer tr = new Trainer();
		int population = tr.loadTraining(new File(TRAIN_FILENAME));
		System.out.println("PatternList loaded w/" + population + " patterns");
		tr.performTraining();
		tr.saveTraining(new File(NETWORK_FILENAME));

		System.out.println("end");
	}
}
