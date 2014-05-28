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

  /** Initializes the Map database */
  protected void setUp()
  {
    env = QuantityDB.getDB();
  }

  /** Tests Product */
  public void testProduct()
  {
    ast = new Product(new Value(new Quantity(3)), new Value(new Quantity(5)));

    assertEquals("Testing Product", new Quantity(15), ast.eval(env));
  }

  /** Tests Quotient */
  public void testQuotient()
  {
    ast = new Quotient(new Value(new Quantity(6)), new Value(new Quantity(2)));

    assertEquals("Testing Quotient", new Quantity(3), ast.eval(env));
  }

  /** Tests Sum */
  public void testSum()
  {
    ast = new Sum(new Value(new Quantity(5)), new Value(new Quantity(3)));

    assertEquals("Testing Sum", new Quantity(8), ast.eval(env));
  }

  /** Tests Difference */
  public void testDifference()
  {
    ast = new Difference(new Value(new Quantity(8)), 
        new Value(new Quantity(4)));

    assertEquals("Testing Difference", new Quantity(4), ast.eval(env));
  }

  /** Tests Power */
  public void testPower()
  {
    ast = new Power(new Value(new Quantity(2)), 4);

    assertEquals("Testing Power", new Quantity(16), ast.eval(env));
  }

  /** Tests Negation */
  public void testNegation()
  {
    ast = new Negation(new Value(new Quantity(-9)));

    assertEquals("Testing Negation", new Quantity(9), ast.eval(env));
  }

  /** Tests Normalize */
  public void testNormalize()
  {
    Normalize km = new Normalize(new Value(new Quantity(0.62137, 
            Arrays.asList("mi"), Collections.<String>emptyList())));

    assertEquals("Testing Normalize", "0.62137 mi", km.eval(env).toString());
  }

  /** Tests Define */
  public void testDefine()
  {
    Define smoot = new Define("smoot", new Value(new Quantity(67, 
            Arrays.asList("in"), Collections.<String>emptyList())));

    assertEquals("Testing Define", "67.0 in", smoot.eval(env).toString());
  }

  /** Tests Value */
  public void testValue()
  {
    Value val = new Value(new Quantity(5));

    assertEquals("Testing Value", "5.0", val.eval(env).toString());
  }
}

