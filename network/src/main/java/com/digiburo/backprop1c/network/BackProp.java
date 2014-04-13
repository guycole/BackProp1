package com.digiburo.backprop1c.network;

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
   * current BP network
   */
  private NetWork _netWork;

  /**
   * Constructor for new backpropagation network.
   *
   * @param inputPopulation input node count
   * @param middlePopulation middle node count
   * @param outputPopulation output node count
   * @param learning_rate learning rate to use during error calculations (suggest 25 to 50 percent)
   * @param momentum used during weight calculations
   */
  public BackProp(int inputPopulation, int middlePopulation, int outputPopulation, double learningRate, double momentum) {
    _netWork = new NetWork(inputPopulation, middlePopulation, outputPopulation, learningRate, momentum);
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
    _netWork = (NetWork) ois.readObject();
    ois.close();
  }
    
  /**
   * Run the network w/the specified pattern
   *
   * @param arg input pattern
   * @return classification output
   */
  public double[] runNetwork(double[] arg) {
    	return(_netWork.runNetWork(arg));
    }
    
  /**
   * Train this network using the supplied pattern list.
   *
   * @param patternz list of training patterns
   * @param maxMatch quantity of patterns to match for training success, -1 = all
   * @param maxCycles maximum training cycles, -1 = no limit
   * @param threshold limit near zero or one to count as zero or one
   * @param verbose write progress messages
   * @return quantity of trained patterns
   */
  public int trainNetwork(PatternList patternz, int maxMatch, int maxCycles, double threshold, boolean verbose) {
    int limit = patternz.size();
    	
    if (maxMatch < 0) {
      maxMatch = limit;
    }
    	
    int counter = 0;
    int success;
    int maxSuccess = 0;
    	
    boolean whileFlag;
	
    do {
      success = 0;
	    
    	for (int ii = 0; ii < limit; ii++) {
    	  Pattern pattern = patternz.get(ii);
		
    		_netWork.runNetWork(pattern.getInput());
		
    		double[] rawResults = _netWork.trainNetWork(pattern.getOutput());
		
    		int[] truth = Mathz.thresholdArray(threshold, pattern.getOutput());
    		int[] results = Mathz.thresholdArray(threshold, rawResults);
		
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
	    
    	if (maxSuccess < success) {
    		maxSuccess = success;
    	}
	    
    	if ((++counter % 10000) == 0) {
    		if (verbose) {
    			System.out.println(counter + " success:" + success + " needed:" + patternz.size() + " best run:" + maxSuccess);
    		}
    	}
	    
    	if (success < limit) {
    		whileFlag = true;
    	} else {
    		whileFlag = false;
    	}
	    
    	if (maxCycles > -1) {
    		if (counter >= maxCycles) {
    			whileFlag = false;
    		}
    	}
	    
    	if (success >= maxMatch) {
    		whileFlag = false;
    	}
    } while(whileFlag);
    	
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
    oos.writeObject(_netWork);
    oos.close();
  }
}

/*
 * Copyright 2009 Digital Burro, INC
 * Created on August 31, 2009 by gsc
 */