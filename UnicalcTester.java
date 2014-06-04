/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */

/**
 * Title: class UnicalcTester
 * Description: Tests the Unicalc class
 * @author Joshua Chang and Kirk Srijongsirikul
 * @version 1.0 27-May-2014
 */
public class UnicalcTester extends junit.framework.TestCase
{
  Unicalc unicalc;

  AST ast;
  AST ast2;

  /** Initializes the Unicalc */
  protected void setUp()
  {
    unicalc = new Unicalc();
  }
  
  /** Tests the S grammar rule */
  public void testS()
  {
    unicalc.tokenize("def smoot 67 in");
    ast = unicalc.S();

    assertEquals("Testing S", 
        "Define(smoot,Product(Value(67.0),Value(1.0 in)))", ast.toString());
  }

  /** Tests the S grammar rule */
  public void testS2()
  {
    unicalc.tokenize("def nmi 1852 m");
    ast = unicalc.S();
    unicalc.tokenize("def nmi 1852 m");
    ast2 = unicalc.parse();

    assertEquals("Testing S", ast2, ast);
  }

  /** Tests the L grammar rule */
  public void testL()
  {
    System.out.println("----------------------");
    unicalc.tokenize("# 60Hz * 30s");
    ast = unicalc.L();

    assertEquals("Testing L", 
      "Normalize(Product(Product(Value(60.0),Value(1.0 Hz)),Product(Value(30.0),Value(1.0 s))))", 
       ast.toString());
    System.out.println("----------------------");
  }

  /** Tests the L grammar rule */
  public void testL2()
  {
    unicalc.tokenize("def smoot 67 in");
    unicalc.parse();
    unicalc.tokenize("# 100 smoot");
    ast = unicalc.L();

    assertEquals("Testing L", 
        "Normalize(Product(Value(100.0),Value(1.0 smoot)))", ast.toString());
  }

  /** Tests the E grammar rule */
  public void testE()
  {
    unicalc.tokenize("3+14");
    ast = unicalc.E();

    assertEquals("Testing E", "Sum(Value(3.0),Value(14.0))", ast.toString());
  }

  /** Tests the E grammar rule */
  public void testE2()
  {
    unicalc.tokenize("3-14");
    ast = unicalc.E();

    assertEquals("Testing E", "Difference(Value(3.0),Value(14.0))",
                 ast.toString());
  }

  /** Tests the P grammar rule */
  public void testP()
  {
    unicalc.tokenize("2*8");
    ast = unicalc.P();

    assertEquals("Testing P", "Product(Value(2.0),Value(8.0))", ast.toString());
  }

  /** Tests the P grammar rule */
  public void testP2()
  {
    unicalc.tokenize("2/8");
    ast = unicalc.P();

    assertEquals("Testing P", "Quotient(Value(2.0),Value(8.0))", 
                 ast.toString());
  }

  /** Tests the K grammar rule */
  public void testK()
  {
    unicalc.tokenize("-6");
    ast = unicalc.K();

    assertEquals("Testing K", "Negation(Value(6.0))", ast.toString());
  }

  /** Tests the Q grammar rule */
  public void testQ()
  {
    unicalc.tokenize("3m");
    ast = unicalc.Q();

    assertEquals("Testing Q", "Product(Value(3.0),Value(1.0 m))", 
                 ast.toString());
  }

  /** Tests the Q grammar rule with parentheses */
  public void testQ2()
  {
    unicalc.tokenize("(3)(m)");
    ast = unicalc.Q();

    assertEquals("Testing Q", "Product(Value(3.0),Value(1.0 m))",
                 ast.toString());
  }

  /** Tests the R grammar rule */
  public void testR()
  {
    unicalc.tokenize("2^4");
    ast = unicalc.R();

    assertEquals("Testing R", "Power(Value(2.0),4)", ast.toString());
  }

  /** Tests the R grammar rule with parentheses */
  public void testR2()
  {
    unicalc.tokenize("(2)^4);
    ast = unicalc.R();

    assertEquals("Testing R", "Power(Value(2.0),4)", ast.toString());
  }
}


