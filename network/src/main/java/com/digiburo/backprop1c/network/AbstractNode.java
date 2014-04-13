package com.digiburo.backprop1c.network;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract parent for nodes
 * 
 * @author gsc
 */
public abstract class AbstractNode extends AbstractArcNode {

  /**
   * Error for this node
   */
  double error;

  /**
   * Value for this node
   */
  double value;

  /**
   * input arcs
   */
  ArrayList<Arc> inputArcs = new ArrayList<Arc>();

  /**
   * output arcs
   */
  ArrayList<Arc> outputArcs = new ArrayList<Arc>();

  /**
   * Return node error term
   *
   * @return node error term
   */
  public double getError() {
    return (error);
  }

  /**
   * Define node error term
   *
   * @param arg new error term
   */
  public void setError(double arg) {
    error = arg;
  }

  /**
   * Return node value
   *
   * @return node value
   */
  public double getValue() {
    return (value);
  }

  /**
   * Connect to another node via an arc
   *
   * @param destination node
   * @param arc to connect with
   */
  public void connect(AbstractNode destination, Arc arc) {
    outputArcs.add(arc);

    destination.inputArcs.add(arc);

    arc.setInputNode(this);
    arc.setOutputNode(destination);
  }

  /**
   * What am I connected to?
   */
  public String dumpConnections() {
    String result = "id:" + id;

    result += ":input:";
    Iterator<Arc> ii = inputArcs.iterator();
    while (ii.hasNext()) {
      result += ii.next().toString() + ":";
    }

    result += "output:";
    ii = outputArcs.iterator();
    while (ii.hasNext()) {
      result += ii.next().toString() + ":";
    }

    return (result);
  }

  /**
   * Return object state as a string
   *
   * @return object state as a string
   */
  public String toString() {
    return (id + " error:" + error + " value:" + value + " input:" + inputArcs.size() + " output:" + outputArcs.size());
  }

  /**
   * eclipse generated
   */
  private static final long serialVersionUID = -8437051058370670157L;
}

/*
 * Copyright 2009 Digital Burro, INC 
 * Created on August 31, 2009 by gsc
 */
