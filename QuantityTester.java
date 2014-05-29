/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */

import java.util.*;

public class QuantityTester extends junit.framework.TestCase {

  private Quantity defaultQuant; // stores default Quantity
  private Quantity dist; // stores distance Quantity
  private Quantity vel; // stores velocity Quantity
  private Quantity acc; // stores acceleration Quantity

  // stores empty List
  private List<String> emp = Collections.<String>emptyList();

  // stores database of units
  private Map<String, Quantity> quantityDB;

  // value for the Quantity's
  private static final double VALUE = 100.0;

  protected void setUp()
  {
    defaultQuant = new Quantity(); // creates default Quantity
    dist = new Quantity( VALUE, Arrays.asList("meter"), emp );
    vel = new Quantity( VALUE, Arrays.asList("meter"), Arrays.asList("s") );
    acc = new Quantity( VALUE, Arrays.asList("meter"), Arrays.asList("s", "s"));

    quantityDB = QuantityDB.getDB(); // stores database
  }

  /** Tests No-Argument Constructor */
  public void testNoArgConst()
  {
    assertEquals( "1.0" , defaultQuant.toString() );
  }

  /** Tests One-Argument Copy Constructor */
  public void testOneArgConst()
  {
    Quantity testQuant = new Quantity( defaultQuant );
    assertTrue( testQuant.equals( defaultQuant ) );
    assertEquals( "1.0", testQuant.toString() );
  }

  /** Tests Three Argument Constructor */
  public void testThreeArgConst()
  {
    Quantity testQuant = new Quantity( VALUE, Arrays.asList( "watt" ), emp );
    assertEquals( VALUE + " watt", testQuant.toString() );
    assertEquals( VALUE + " meter" + " s^-1", vel.toString() );
    assertEquals( VALUE + " meter" + " s^-2", acc.toString() );

    // testing if IllegalArgumentException is thrown correctly
    try
    {
      testQuant = new Quantity( VALUE, emp, null );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
    try
    {
      testQuant = new Quantity( VALUE, null, emp );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
    try
    {
      testQuant = new Quantity( VALUE, null, null );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }

  }

  /** Tests mul method */
  public void testMul()
  {
    Quantity toMult = new Quantity( 1.0, Arrays.asList( "s" ), emp );
    assertTrue( dist.toString().equals( vel.mul(toMult).toString() ) );
    assertTrue( vel.toString().equals( acc.mul(toMult).toString() ) );

    toMult = new Quantity( 10.0, emp, emp );
    assertEquals( (10*VALUE) + " meter", dist.mul(toMult).toString() );

    // testing that neither factor is changed
    assertEquals( VALUE + " meter", dist.toString() );
    assertEquals( "10.0", toMult.toString() );

    // testing if IllegalArgumentException is thrown correctly
    toMult = null;
    try
    {
      dist.mul( toMult );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
  }

  /** Tests div method */
  public void testDiv()
  {
    Quantity toDiv = new Quantity( 1.0, Arrays.asList("meter"), emp );
    assertEquals( String.valueOf(VALUE), dist.div( toDiv ).toString() );

    toDiv = new Quantity( 10.0, Arrays.asList("meter"), emp );
    assertEquals( (VALUE/10) + " s^-1", vel.div( toDiv ).toString() );
    assertEquals( (VALUE/10) + " s^-2", acc.div( toDiv ).toString() );

    // testing that neither divisor or dividend is changed
    assertEquals( "10.0 meter", toDiv.toString() );
    assertEquals( VALUE + " meter s^-1", vel.toString() );

    // testing if IllegalArgumentException is thrown correctly
    try
    {
      dist.div( null );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
    try
    {
      dist.div( new Quantity( 0, emp, emp ) );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
  }

  /** Tests add method */
  public void testAdd()
  {
    Quantity toAdd = new Quantity( 50.0, Arrays.asList("meter"), emp );
    assertEquals( String.valueOf(VALUE + 50.0) + " meter", 
                  dist.add( toAdd ).toString() );
    
    // test adding a negative number
    toAdd = new Quantity( -50.0, Arrays.asList("meter"), emp );
    assertEquals( String.valueOf(VALUE - 50.0) + " meter", 
                  dist.add( toAdd ).toString() );

    // testing that neither Quantity is changed
    assertEquals( "-50.0 meter", toAdd.toString() );
    assertEquals( VALUE + " meter", dist.toString() );
    
    // testing if IllegalArgumentException is thrown correctly
    try
    {
      dist.add( null );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
    try
    {
      vel.add( toAdd );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }

  }

  /** Tests sub method */
  public void testSub()
  {
    Quantity toSub = new Quantity( 0, Arrays.asList("meter"), emp );
    assertEquals( VALUE + " meter", dist.sub( toSub ).toString() );

    toSub = new Quantity( 50.0, Arrays.asList("meter"), emp );
    assertEquals( VALUE - 50.0 + " meter", dist.sub( toSub ).toString() );

    // testing that neither Quantity is changed
    assertEquals( 50.0 + " meter", toSub.toString() );
    assertEquals( VALUE + " meter", dist.toString() );

    // testing that IllegalArgumentException is thrown correctly
    try
    {
      dist.sub( null );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }
    try
    {
      vel.sub( toSub );
      System.err.println( "Failure: Should throw IllegalArgumentException" );
      fail();
    }
    catch( IllegalArgumentException e )
    {
    }

  }

  /** Tests Negate Method */
  public void testNegate()
  {
    assertEquals( "-" + VALUE + " meter", dist.negate().toString() );

    // test that orginal quantity is not changed
    assertEquals( VALUE + " meter", dist.toString() );
  }

  /** Tests normalizedUnit method */
  public void testNormalizedUnit()
  {
    assertEquals( "3600.0 second", 
                  Quantity.normalizedUnit("hour", quantityDB).toString() );
    assertEquals( "1000.0 meter", 
                  Quantity.normalizedUnit("km", quantityDB).toString() );
    assertEquals( "0.27778 meter second^-1",
                  Quantity.normalizedUnit("kph", quantityDB).toString() );
  }

  /** Tests normalize method */
  public void testNormalize()
  {
    Quantity testQuant = new Quantity( 100.0, Arrays.asList("yard"), emp );
    assertEquals( "91.44 meter",
                  testQuant.normalize( quantityDB ).toString() );

    testQuant = new Quantity( 1, Arrays.asList("km"), emp );
    assertEquals( "1000.0 meter",
                  testQuant.normalize( quantityDB ).toString() );
                   
  }

  /** Tests pow method */
  public void testPow()
  {
    int exp = 2;
    assertEquals( (VALUE*VALUE) + " meter^2", dist.pow( exp ).toString() );

    // testing that original Quantity is not changed
    assertEquals( VALUE + " meter", dist.toString() );
  }

  /** Tests equal method */
  public void testEquals()
  {
    Quantity distClone = new Quantity( VALUE, Arrays.asList("meter"), emp );
    assertTrue( dist.equals( distClone ) );

    Quantity accClone = new Quantity( acc );
    assertTrue( acc.equals( accClone ) );

    assertFalse( new Integer( 100 ).equals( dist ) );
  }

  /** Tests hashCode method */
  public void testHashCode()
  {
    Quantity velClone = new Quantity( vel );
    assertEquals( vel.toString().hashCode(), velClone.toString().hashCode() );
    assertFalse( acc.toString().hashCode() == vel.toString().hashCode() );
  }

}
