package com.digiburo.backprop1b.network;

/**
 * Provide unique ID for arcs/nodes
 */

/**
 * Provide unique ID for arcs/nodes
 * 
 * @author gsc
 */
public class SequenceGenerator {

    /**
     * Each arc/node has a unique identifer. 
     * This was to help w/development, but it doesn't hurt to retain.
     * 
     * @return unique identifier
     */
    public static synchronized int getId() {
    	return(_id++);
    }
    
    /**
     * Contains the next Arc/Node identifier.
     */
    private static int _id = 1;
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
