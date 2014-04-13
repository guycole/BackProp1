package com.digiburo.backprop1c.network.test;

import com.digiburo.backprop1c.network.MiddleNode;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Exercise middle nodes
 * @author gsc
 */
public class MiddleNodeTest {

  @Test
  public void testValueInput() {
    double learningRate = _random.nextDouble();
    double momentum = _random.nextDouble();

    MiddleNode middleNode = new MiddleNode(learningRate, momentum);
    assertEquals(learningRate, middleNode.getLearningRate(), 0.0000001);
    assertEquals(momentum, middleNode.getMomentum(), 0.0000001);
  }

  //
  private Random _random = new Random();
}
/*
 * Copyright 2014 Digital Burro, INC
 * Created 4/12/14 by gsc
 */