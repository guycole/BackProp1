package com.digiburo.backprop1c.network;

import java.util.Iterator;

/**
 * Concrete middle node
 *
 * @author gsc
 */
public class MiddleNode extends OutputNode {

  /**
   * ctor
   *
   * @param learning_rate
   * @param momentum
   */
  public MiddleNode(double learningRate, double momentum) {
    super(learningRate, momentum);
  }

  /**
   * Compute node error
   *
   * @return node error
   */
  private double computeError() {
    double total = 0.0;

    Iterator<Arc> ii = outputArcs.iterator();
    while (ii.hasNext()) {
      Arc arc = ii.next();
      total += arc.getWeightedOutputError();
    }

    double result = value * (1.0 - value) * total;

    return (result);
  }

  /**
   * Update input weights based on error (delta rule)
   */
  public void trainNode() {
    error = computeError();

    Iterator<Arc> ii = inputArcs.iterator();
    while (ii.hasNext()) {
      Arc arc = ii.next();
      double delta = learningRate * error * arc.getInputValue();
      arc.updateWeight(delta);
    }
  }

  /**
   * Return description of object
   *
   * @return description of object
   */
  public String toString() {
    return (toString("MiddleNode:"));
  }

  /**
   * eclipse generated
   */
  private static final long serialVersionUID = -1713752591254222978L;
}

/*
 * Copyright 2009 Digital Burro, INC
 * Created on August 31, 2009 by gsc
 */
