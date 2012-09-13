package com.digiburo.backprop1b.network;

/**
 * Concrete input node
 */

/**
 * Concrete input node
 *
 * @author gsc
 */
public class InputNode extends AbstractNode {

	/**
	 * Define node value
	 * 
	 * @param arg
	 *            new node value
	 * @throws IllegalArgumentException
	 *             if arg < 0.0 or > 1.0
	 */
	public void setValue(double arg) {
		if ((arg < 0.0) || (arg > 1.0)) {
			throw new IllegalArgumentException("bad input value");
		}

		value = arg;
	}

	/**
	 * Return object state as a string
	 * 
	 * @return object state as a string
	 */
	public String toString() {
		return ("InputNode:" + super.toString());
	}
    
    /**
     * eclipse generated 
     */
    private static final long serialVersionUID = -7434630887769481379L;
}

/*
 * Copyright 2009 Digital Burro, INC
 * Created on August 31, 2009 by gsc
 */
