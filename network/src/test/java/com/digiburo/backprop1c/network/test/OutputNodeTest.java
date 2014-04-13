package com.digiburo.backprop1c.network.test;

import com.digiburo.backprop1c.network.OutputNode;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Exercise output nodes
 * @author gsc
 */
public class OutputNodeTest {

  @Test
  public void testValueInput() {
    double learningRate = _random.nextDouble();
    double momentum = _random.nextDouble();

    OutputNode outputNode = new OutputNode(learningRate, momentum);
    assertEquals(learningRate, outputNode.getLearningRate(), 0.0000001);
    assertEquals(momentum, outputNode.getMomentum(), 0.0000001);
  }

  //
  private Random _random = new Random();
}
/*
 * Copyright 2014 Digital Burro, INC
 * Created 4/12/14 by gsc
 */