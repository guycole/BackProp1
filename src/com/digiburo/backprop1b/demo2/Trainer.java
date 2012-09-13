package com.digiburo.backprop1b.demo2;

import java.io.File;
import java.io.IOException; 
import java.io.FileNotFoundException;

import com.digiburo.backprop1b.network.PatternList;

/**
 * Train for demo2, XOR pattern.
 *
 * Since there are only four patterns, I generate the training
 * patterns manually.
 *
 * @author G.S. Cole (gsc@digiburo.com)
 * @version $Id: Trainer.java,v 1.4 2002/02/03 20:31:41 gsc Exp $
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
 *   Revision 1.4  2002/02/03 20:31:41  gsc
 *   Format tweaks
 *
 *   Revision 1.3  2002/02/02 20:53:53  gsc
 *   More testing tweaks
 *
 *   Revision 1.2  2002/02/01 06:14:07  gsc
 *   Work In Progress
 *
 *   Revision 1.1  2002/02/01 02:48:56  gsc
 *   Initial Check In
 */

public class Trainer {
    public static final double ONE = 0.9999999999;
    public static final double ZERO = 0.0000000001;
    
    private static final String NETWORK_FILENAME = "demo2.serial";

    private BpDemo2 bp;
    private PatternList pl;

    /**
     * Create network
     */
    public Trainer() {
    	bp = new BpDemo2(2, 3, 1, 0.25, 0.5);
    }

	/**
	 * Create training datum
	 */
	public int loadTraining() throws IOException, FileNotFoundException, ClassNotFoundException {
		pl = new PatternList();

		double[] input = new double[2];
		double[] output = new double[1];

		input[0] = ZERO;
		input[1] = ZERO;
		output[0] = ZERO;
		pl.add(input, output);

		input = new double[2];
		output = new double[1];

		input[0] = ZERO;
		input[1] = ONE;
		output[0] = ONE;
		pl.add(input, output);

		input = new double[2];
		output = new double[1];

		input[0] = ONE;
		input[1] = ZERO;
		output[0] = ONE;
		pl.add(input, output);

		input = new double[2];
		output = new double[1];

		input[0] = ONE;
		input[1] = ONE;
		output[0] = ZERO;
		pl.add(input, output);

		return (pl.size());
	}

	/**
	 * Train the network on these patterns
	 */
	public void performTraining() {
		bp.trainNetwork(pl, pl.size(), -1, 0.05, true);
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
		int population = tr.loadTraining();
		System.out.println("PatternList loaded w/" + population + " patterns");
		tr.performTraining();
		tr.saveTraining(new File(NETWORK_FILENAME));

		System.out.println("end");
	}
}
