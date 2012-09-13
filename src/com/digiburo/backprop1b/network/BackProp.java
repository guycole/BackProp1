package com.digiburo.backprop1b.network;

/**
 * Facade for backpropagation neural network.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Facade for backpropagation neural network
 *
 * @author gsc
 */
public class BackProp {

    /**
     * Constructor for new backpropagation network.
     *
     * @param input_population input node count
     * @param middle_population middle node count
     * @param output_population output node count
     * @param learning_rate learning rate to use during error calculations (suggest 25 to 50 percent)
     * @param momentum used during weight calculations 
     */
    public BackProp(int input_population, int middle_population, int output_population, double learning_rate, double momentum) {
    	_net_work = new NetWork(input_population, middle_population, output_population, learning_rate, momentum);
    }
    
    /**
     * ctor for a persisted backpropagation network.
     * 
     * @param file persisted network
     * @throws IOException if problem
     * @throws FileNotFoundException if problem
     * @throws ClassNotFoundException if problem
     */
    public BackProp(File file) throws IOException, FileNotFoundException, ClassNotFoundException {
    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    	_net_work = (NetWork) ois.readObject();
    	ois.close();
    }
    
    /**
     * Run the network w/the specified pattern
     * 
     * @param arg input pattern
     * @return classification output
     */
    public double[] runNetwork(double[] arg) {
    	return(_net_work.runNetWork(arg));
    }
    
    /**
     * Train this network using the supplied pattern list.
     * 
     * @param patternz list of training patterns
     * @param max_match quantity of patterns to match for training success, -1 = all
     * @param max_cycles maximum training cycles, -1 = no limit
     * @param threshold limit near zero or one to count as zero or one
     * @param verbose write progress messages
     * @return quantity of trained patterns
     */
    public int trainNetwork(PatternList patternz, int max_match, int max_cycles, double threshold, boolean verbose) {
    	int limit = patternz.size();
    	
    	if (max_match < 0) {
    		max_match = limit;
    	}
    	
    	int counter = 0;
    	int success;
    	int max_success = 0;
    	
    	boolean while_flag;
	
    	do {
    		success = 0;
	    
    		for (int ii = 0; ii < limit; ii++) {
    			Pattern pattern = patternz.get(ii);
		
    			_net_work.runNetWork(pattern.getInput());
		
    			double[] raw_results = _net_work.trainNetWork(pattern.getOutput());
		
    			int[] truth = Mathz.thresholdArray(threshold, pattern.getOutput());
    			int[] results = Mathz.thresholdArray(threshold, raw_results);
		
    			pattern.setTrained(true);
    			for (int jj = 0; jj < results.length; jj++) {
    				if (results[jj] != truth[jj]) {
    					pattern.setTrained(false);
    				}
    			}
		
    			if (pattern.isTrained()) {
    				++success;
    			}
    		}
	    
    		if (max_success < success) {
    			max_success = success;
    		}
	    
    		if ((++counter % 10000) == 0) {		
    			if (verbose) {
    				System.out.println(counter + " success:" + success + " needed:" + patternz.size() + " best run:" + max_success);
    			}
    		}
	    
    		if (success < limit) {
    			while_flag = true;
    		} else {
    			while_flag = false;
    		}
	    
    		if (max_cycles > -1) {
    			if (counter >= max_cycles) {
    				while_flag = false;
    			}
    		}
	    
    		if (success >= max_match) {
    			while_flag = false;
    		}
    	} while(while_flag);
    	
    	if (verbose) {
    		System.out.println("Training complete in " + counter + " cycles");
    	}
    	
    	return(success);
    }
    
    /**
     * Persist this BP network to a file
     * 
     * @param file to save
     * @throws IOException if problem
     * @throws FileNotFoundException if problem
     */
    public void saveNetwork(File file) throws IOException, FileNotFoundException {
    	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    	oos.writeObject(_net_work);
    	oos.close();
    }
    
    /**
     * current BP network
     */
    private NetWork _net_work;
}

/*
 * Copyright 2009 Digital Burro, INC
 * Created on August 31, 2009 by gsc
 */