package com.digiburo.backprop1c.network.test;

import com.digiburo.backprop1c.network.InputNode;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Exercise input nodes
 * @author gsc
 */
public class InputNodeTest {

  @Test
  public void testValueInput() {
    double arg = _random.nextDouble();

    InputNode inputNode = new InputNode();
    inputNode.setValue(arg);
    assertEquals(arg, inputNode.getValue(), 0.0000001);
  }

  @Test
  public void testErrorInput() {
    double arg = _random.nextDouble();

    InputNode inputNode = new InputNode();
    inputNode.setError(arg);
    assertEquals(arg, inputNode.getError(), 0.0000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeArgument() {
    InputNode inputNode = new InputNode();
    inputNode.setValue(-0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void positiveArgument() {
    InputNode inputNode = new InputNode();
    inputNode.setValue(1.01);
  }

  //
  private Random _random = new Random();
}
/*
 * Copyright 2014 Digital Burro, INC
 * Created 4/12/14 by gsc
 */