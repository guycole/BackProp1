package com.digiburo.backprop1b.network;

/**
 * Concrete output node
 */
import java.util.Iterator;

/**
 * Concrete output node
 * 
 * @author gsc
 */
public class OutputNode extends AbstractNode {

	/**
	 * ctor
	 * 
	 * @param learning_rate
	 * @param momentum
	 */
	public OutputNode(double learning_rate, double momentum) {
		this.learning_rate = learning_rate;
		this.momentum = momentum;
	}
    
    /**
     * Return learning rate
     * 
     * @return learning rate
     */
    public double getLearningRate() {
    	return(learning_rate);
    }
    
    /**
     * Return momentum term
     * 
     * @return momentum term
     */
    public double getMomentum() {
    	return(momentum);
    }

	/**
	 * Update node value by summing weighted inputs
	 */
	public void runNode() {
		double total = 0.0;

		Iterator<Arc> ii = input_arcs.iterator();
		while (ii.hasNext()) {
			Arc arc = ii.next();
			total += arc.getWeightedInputValue();
		}

		value = sigmoidTransfer(total);
	}

	/**
	 * Update input weights based on error (delta rule)
	 */
	public void trainNode() {
		error = computeError();

		Iterator<Arc> ii = input_arcs.iterator();
		while (ii.hasNext()) {
			Arc arc = ii.next();
			double delta = learning_rate * error * arc.getInputValue();
			arc.updateWeight(delta);
		}
	}
    
    /**
     * Return sigmoid transfer value, result 0.0 < value < 1.0
     * 
     * @return sigmoid transfer value, result 0.0 < value < 1.0
     */
    private double sigmoidTransfer(double value) {
    	return(1.0 / (1.0 + Math.exp(-value)));
    }
    
    /**
     * Compute output node error using the derivative of the sigmoid transfer function.
     * 
     * @return output node error
     */
    private double computeError() {
    	return(value * (1.0 - value) * (error - value));
    }
    
    /**
     * Return description of object
     * 
     * @return description of object
     */
    public String toString() {
    	return(toString("OutputNode:"));
    }
    
    /**
     * Return description of object
     * 
     * @return description of object
     */
    public String toString(String prefix) {
    	String result = prefix + super.toString() + " learning rate:" + learning_rate + " momentum:" + momentum;
	
    	return (result);
    }
    
    /**
     * learning rate is used to help compute error term.
     */
    double learning_rate;
    
    /**
     * momentum term is used to compute weight in Arc
     */
    double momentum;
    
    /**
     * eclipse generated
     */
    private static final long serialVersionUID = -8313299157918441811L;
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
