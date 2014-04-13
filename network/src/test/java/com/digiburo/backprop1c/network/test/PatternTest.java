package com.digiburo.backprop1c.network.test;

import com.digiburo.backprop1c.network.Pattern;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Exercise arcs
 * @author gsc
 */
public class PatternTest {

  @Test
  public void testValueInput() {
    double[] input = new double[] {1.0, 2.0, 3.0, 4.0, 5.0};
    double[] output = new double[] {5.0, 4.0, 3.0, 2.0, 1.0};

    Pattern pattern = new Pattern(input, output);

    double[] inputTemp = pattern.getInput();
    for (int ii = 0; ii < input.length; ii++) {
      assertEquals(input[ii], inputTemp[ii], 0.0000001);
    }

    double[] outputTemp = pattern.getOutput();
    for (int ii = 0; ii < output.length; ii++) {
      assertEquals(output[ii], outputTemp[ii], 0.0000001);
    }

    assertFalse(pattern.isTrained());
    pattern.setTrained(true);
    assertTrue(pattern.isTrained());
  }
}
/*
 * Copyright 2014 Digital Burro, INC
 * Created 4/12/14 by gsc
 */