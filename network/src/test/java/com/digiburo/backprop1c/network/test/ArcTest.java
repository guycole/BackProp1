package com.digiburo.backprop1c.network.test;

import com.digiburo.backprop1c.network.Arc;
import com.digiburo.backprop1c.network.InputNode;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Exercise arcs
 * @author gsc
 */
public class ArcTest {

  @Test
  public void testValueInput() {
    double arg1 = _random.nextDouble();
    InputNode inputNode1 = new InputNode();
    inputNode1.setValue(arg1);
    assertEquals(1, inputNode1.getId());

    double arg2 = _random.nextDouble();
    InputNode inputNode2 = new InputNode();
    inputNode2.setValue(arg1);
    assertEquals(2, inputNode2.getId());

    Arc arc = new Arc();
    assertEquals(3, arc.getId());

    arc.setInputNode(inputNode1);
    assertEquals(arg1, arc.getInputValue(), 0.0000001);

  }


  //
  private Random _random = new Random();
}
/*
 * Copyright 2014 Digital Burro, INC
 * Created 4/12/14 by gsc
 */