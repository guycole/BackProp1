package com.digiburo.backprop1b.network;

/**
 * Backpropagation Network Container.
 */
import java.io.Serializable;

/**
 * Backpropagation network container.
 * 
 * @author gsc
 */
public class NetWork implements Serializable {

    /**
     * Constructor for standard 3 layer backpropagation network. 
     * Create arcs and nodes and connect them to each other.
     * 
     * @param input_population input node count
     * @param middle_population middle node count
     * @param output_population output node count
     * @param learning_rate (suggest 25 to 50 percent)
     * @param momentum
     */
    public NetWork(int input_population, int middle_population, int output_population, double learning_rate, double momentum) {
    	_inputz = new InputNode[input_population];
    	for (int ii = 0; ii < _inputz.length; ii++) {
    		_inputz[ii] = new InputNode();
    	}

    	_middlez = new MiddleNode[middle_population];
    	for (int ii = 0; ii < _middlez.length; ii++) {
    		_middlez[ii] = new MiddleNode(learning_rate, momentum);
    	}
	
    	_outputz = new OutputNode[output_population];
    	for (int ii = 0; ii < _outputz.length; ii++) {
    		_outputz[ii] = new OutputNode(learning_rate, momentum);
    	}
	
    	_arcz = new Arc[(input_population * middle_population) + (middle_population * output_population)];
    	for (int ii = 0; ii < _arcz.length; ii++) {
    		_arcz[ii] = new Arc();
    	}
	
    	int ii = 0;
    	for (int jj = 0; jj < _inputz.length; jj++) {
    		for (int kk = 0; kk < _middlez.length; kk++) {
    			_inputz[jj].connect(_middlez[kk], _arcz[ii++]);
    		}
    	}
	
    	for (int jj = 0; jj < _middlez.length; jj++) {
    		for (int kk = 0; kk < _outputz.length; kk++) {
    			_middlez[jj].connect(_outputz[kk], _arcz[ii++]);
    		}
    	}
    }
    
    /**
     * Retrieve input nodes, only used by JUnit
     * @return input nodes
     */
    public InputNode[] getInputNodes() {
    	return(_inputz);
    }
    
    /**
     * Retrieve middle nodes, only used by JUnit
     * @return middle nodes
     */
    public MiddleNode[] getMiddleNodes() {
    	return(_middlez);
    }
    
    /**
     * Retrieve output nodes, only used by JUnit
     * @return output nodes
     */
    public OutputNode[] getOutputNodes() {
    	return(_outputz);
    }
    
    /**
     * Retrieve arcs, only used by JUnit
     * @return arcs
     */
    public Arc[] getArcs() {
    	return(_arcz);
    }
    
    /**
     * Run network to classify pattern.
     * 
     * @param input node values (pattern to classify)
     * @return output node values (classification answer)
     */
    public double[] runNetWork(double[] input) {
    	for (int ii = 0; ii < input.length; ii++) {
    		_inputz[ii].setValue(input[ii]);
    	}
	
    	for (int ii = 0; ii < _middlez.length; ii++) {
    		_middlez[ii].runNode();
    	}
	
    	for (int ii = 0; ii < _outputz.length; ii++) {
    		_outputz[ii].runNode();
    	}
	
    	double[] result = new double[_outputz.length];
    	for (int ii = 0; ii < _outputz.length; ii++) {
    		result[ii] = _outputz[ii].getValue();
    	}
	
    	return(result);
    }
    
    /**
     * Train by backpropagation (move backward through nodes and tweak weights w/error values).
     * 
     * @param truth pattern w/output (truth) for network to learn
     */
    public double[] trainNetWork(double[] truth) {
    	for (int ii = 0; ii < truth.length; ii++) {
    		_outputz[ii].setError(truth[ii]);
    	}
	
    	for (int ii = _outputz.length-1; ii >= 0; ii--) {
    		_outputz[ii].trainNode();
    	}
	
    	for (int ii = _middlez.length-1; ii >= 0; ii--) {
    		_middlez[ii].trainNode();
    	}
	
    	double[] result = new double[_outputz.length];
    	for (int ii = 0; ii < _outputz.length; ii++) {
    		result[ii] = _outputz[ii].getValue();
    	}
	
    	return(result);
    }
    
    /**
     * Arcs connect nodes
     */
    private final Arc[] _arcz;
    
    /**
     * Input nodes contain pattern to classify
     */
    private final InputNode[] _inputz;
    
    /**
     * Opaque middle node
     */
    private final MiddleNode[] _middlez;
    
    /**
     * Classifier result
     */
    private final OutputNode[] _outputz;
    
    /**
     * eclipse generated
     */
    private static final long serialVersionUID = 5668812853290831632L;
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
