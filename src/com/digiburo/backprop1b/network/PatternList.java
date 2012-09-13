package com.digiburo.backprop1b.network;

/**
 * Pattern List
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * Pattern List
 * 
 * @author gsc
 */
public class PatternList {
	
    /**
     * mandatory empty ctor
     */
    public PatternList() {
    	//empty
    }
    
    /**
     * Read the specified pattern file
     * 
     * @param file pattern file
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public PatternList(File file) throws IOException, FileNotFoundException, ClassNotFoundException {
    	reader(file);
    }
    
    /**
     * Add a new element to the list
     * 
     * @param pp pattern to add to list
     */
    public void add(Pattern pp) {
    	_list.add(pp);
    }
    
    /**
     * Add a new element to the list
     * 
     * @param input input pattern
     * @param output output pattern
     */
    public void add(double[] input, double[] output) {
    	_list.add(new Pattern(input, output));
    }
    
    /**
     * Return the specified Pattern
     * 
     * @param index into pattern list, zero is first
     * @return the specified Pattern
     */
    public Pattern get(int index) {
    	return(_list.get(index));
    }
    
    /**
     * Return the element population
     * 
     * @return the element population
     */
    public int size() {
    	return(_list.size());
    }

	/**
	 * Write patterns as a serialized object
	 * 
	 * @param file
	 *            to be written
	 */
	public void writer(File file) throws IOException, FileNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(_list);
		oos.close();
	}

	/**
	 * Read serialized pattern
	 * 
	 * @param file
	 *            to be read
	 */
	@SuppressWarnings("unchecked")
	public void reader(File file) throws IOException, FileNotFoundException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		_list = (ArrayList<Pattern>) ois.readObject();
		ois.close();
	}
    
    /**
     * Pattern List 
     */
    private ArrayList<Pattern> _list = new ArrayList<Pattern>();
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
