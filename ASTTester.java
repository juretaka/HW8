/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */

import java.util.Map;
import java.util.*;

/**
 * Title: class ASTTester
 * Description: Tests the AST class
 * @author Joshua Chang and Kirk Srijongsirikul
 * @version 1.0 27-May-2014
 */
public class ASTTester extends junit.framework.TestCase
{
  AST ast;
  Map<String, Quantity> env;
  Quantity threeMeters;
  Quantity nineMeters;
  private List<String> empty;

  /** Initializes the map database and two quantities */
  protected void setUp()
  {
    empty = Collections.<String>emptyList();
    env = QuantityDB.getDB();
    threeMeters = new Quantity(3, Arrays.asList("meter"), empty);
    nineMeters = new Quantity(9, Arrays.asList("meter"), empty);
  }

  /** Tests Product */
  public void testProduct()
  {
    ast = new Product(new Value(threeMeters), new Value(nineMeters));

    assertEquals("Testing Product", "27.0 meter^2", ast.eval(env).toString());
  }

  /** Tests Quotient */
  public void testQuotient()
  {
    ast = new Quotient(new Value(nineMeters), new Value(threeMeters));

    assertEquals("Testing Quotient", "3.0", ast.eval(env).toString());
  }

  /** Tests Sum */
  public void testSum()
  {
    ast = new Sum(new Value(threeMeters), new Value(nineMeters));

    assertEquals("Testing Sum", "12.0 meter", ast.eval(env).toString());
  }

  /** Tests Difference */
  public void testDifference()
  {
    ast = new Difference(new Value(nineMeters), new Value(threeMeters));

    assertEquals("Testing Difference", "6.0 meter", ast.eval(env).toString());
  }

  /** Tests Power */
  public void testPower()
  {
    ast = new Power(new Value(threeMeters), 3);

    assertEquals("Testing Power", "27.0 meter^3", ast.eval(env).toString());
  }

  /** Tests Negation */
  public void testNegation()
  {
    ast = new Negation(new Value(threeMeters));

    assertEquals("Testing Negation", "-3.0 meter", ast.eval(env).toString());
  }

  /** Tests Normalize */
  public void testNormalize()
  {
    Normalize km = new Normalize(new Value(new Quantity(0.62137, 
            Arrays.asList("mi"), empty)));

    assertEquals("Testing Normalize", "0.62137 mi", km.eval(env).toString());
  }

  /** Tests Define */ 
  public void testDefine()
  {
    Define smoot = new Define("smoot", new Value(new Quantity(67, 
            Arrays.asList("in"), empty)));

    assertEquals("Testing Define", "67.0 in", smoot.eval(env).toString());
  }

  /** Tests Value */  
  public void testValue()
  {
    Value val = new Value(nineMeters);

    assertEquals("Testing Value", "9.0 meter", val.eval(env).toString());
  }
}

