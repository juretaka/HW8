/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */


public class UnicalcTester extends junit.framework.TestCase
{
  Unicalc unicalc;

  AST ast;
  AST ast2;

  protected void setUp()
  {
    unicalc = new Unicalc();
  }
  
  public void testS()
  {
    unicalc.tokenize("def smoot 67 in");
    ast = unicalc.S();

    assertEquals("Testing S", 
        "Define(smoot,Product(Value(67.0),Value(1.0 in)))", ast.toString());
  }

  public void testS2()
  {
    unicalc.tokenize("def nmi 1852 m");
    ast = unicalc.S();
    unicalc.tokenize("def nmi 1852 m");
    ast2 = unicalc.parse();

    assertEquals("Testing S", ast2, ast);
  }

  public void testL()
  {
    unicalc.tokenize("# 60Hz * 30s");
    ast = unicalc.L();

    assertEquals("Testing L", "Normalize(Product(Product(Value(60.0),Value(1.0 Hz)),Product(Value(30.0),Value(1.0 s))))", ast.toString());
  }

  public void testL2()
  {
    unicalc.tokenize("def smoot 67 in");
    unicalc.parse();
    unicalc.tokenize("# 100 smoot");
    ast = unicalc.L();

    assertEquals("Testing L", 
        "Normalize(Product(Value(100.0),Value(1.0 smoot)))", ast.toString());
  }

  public void testE()
  {
    unicalc.tokenize("3+14");
    ast = unicalc.E();

    assertEquals("Testing E", "Sum(Value(3.0),Value(14.0))", ast.toString());
  }

  public void testE2()
  {
    unicalc.tokenize("3-14");
    ast = unicalc.E();

    assertEquals("Testing E", "Difference(Value(3.0),Value(14.0))",
                 ast.toString());
  }

  public void testP()
  {
    unicalc.tokenize("2*8");
    ast = unicalc.P();

    assertEquals("Testing P", "Product(Value(2.0),Value(8.0))", ast.toString());
  }

  public void testP2()
  {
    unicalc.tokenize("2/8");
    ast = unicalc.P();

    assertEquals("Testing P", "Quotient(Value(2.0),Value(8.0))", 
                 ast.toString());
  }

  public void testK()
  {
    unicalc.tokenize("-6");
    ast = unicalc.K();

    assertEquals("Testing K", "Negation(Value(6.0))", ast.toString());
  }

  public void testQ()
  {
    unicalc.tokenize("3m");
    ast = unicalc.Q();

    assertEquals("Testing Q", "Product(Value(3.0),Value(1.0 m))", 
                 ast.toString());
  }

  public void testR()
  {
    unicalc.tokenize("2^4");
    ast = unicalc.R();

    assertEquals("Testing R", "Power(Value(2.0),4)", ast.toString());
  }
}

