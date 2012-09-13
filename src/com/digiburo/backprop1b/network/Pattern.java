package com.digiburo.backprop1b.network;

/**
 * Pattern container
 */
import java.io.Serializable;

/**
 * Pattern container
 * 
 * @author gsc
 */
public class Pattern implements Serializable {

    /**
     * @param input input pattern
     * @param output output pattern
     */
    public Pattern(double[] input, double[] output) {
    	_input = (double[]) input.clone();
    	_output = (double[]) output.clone();
    }
    
    /**
     * @return input pattern
     */
    public double[] getInput() {
    	return(_input);
    }
    
    /**
     * @return output pattern
     */
    public double[] getOutput() {
    	return(_output);
    }
    
    /**
     * @return true, this pattern passed training
     */
    public boolean isTrained() {
    	return(_trained);
    }
    
    /**
     * @param arg true, this pattern passed training
     */
    public void setTrained(boolean arg) {
    	_trained = arg;
    }

	/**
	 * Return object contents as a String
	 * 
	 * @return object contents as a String
	 */
	public String toString() {
		String result = "trained:" + _trained;

		result += " input ";
		if (_input == null) {
			result += " null ";
		} else {
			for (int ii = 0; ii < _input.length; ii++) {
				result += _input[ii] + " ";
			}
		}

		result += " output ";
		if (_output == null) {
			result += " null ";
		} else {
			for (int ii = 0; ii < _output.length; ii++) {
				result += _output[ii] + " ";
			}
		}

		return(result);
	}

	/**
	 * Write pattern to stdout
	 */
	public void dumpPattern() {
		System.out.println("output pattern");
		for (int ii = 0; ii < _output.length; ii++) {
			System.out.println(ii + " " + _output[ii]);
		}

		System.out.println("input pattern");
		for (int ii = 0; ii < _input.length; ii++) {
			System.out.println(ii + " " + _input[ii]);
		}
	}
    
    /**
     * Input pattern
     */
    private double[] _input;
    
    /**
     * Output pattern
     */
    private double[] _output;
    
    /**
     * true, this pattern has passed training
     */
    private boolean _trained = false;
    
    /**
     * eclipse generated
     */
    private static final long serialVersionUID = 9154436050951080903L;
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
