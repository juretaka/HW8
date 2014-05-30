/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Quantity {

  // stores the numerical value of the Quantity
  private double value;
  // stores the attached units
  private Map<String, Integer> unitMap;


  public Quantity()
  {
    unitMap = new HashMap<String, Integer>();
    value = 1.0;
  }

  public Quantity( Quantity orig )
  {
    if(orig == null)
      throw new IllegalArgumentException();
    value = orig.value;
    unitMap = orig.unitMap;
  }

  public Quantity(double value, List<String> numUnits, List<String> denomUnits)
    throws IllegalArgumentException
  {
    if(numUnits == null || denomUnits == null)
      throw new IllegalArgumentException();
    unitMap = new HashMap<String, Integer>();
    int i = 0;
    String tmpUnit;
    int tmpVal;
    while( i < numUnits.size() )
    {
      tmpUnit = numUnits.get(i);
      if( unitMap.containsKey( tmpUnit ) )
      {
        tmpVal = unitMap.remove( tmpUnit );
        unitMap.put( tmpUnit, tmpVal + 1 );
      }
      else
      {
        unitMap.put( tmpUnit, 1 );
      }
      i++;
    }
    i = 0;
    while( i < denomUnits.size() )
    {
      tmpUnit = denomUnits.get(i);
      if( unitMap.containsKey( tmpUnit) )
      {
        tmpVal = unitMap.remove( tmpUnit );
        unitMap.put( tmpUnit, tmpVal - 1 );
      }
      else
      { 
        unitMap.put( tmpUnit, -1 );
      }
      i++;
    }
    this.value = value;
  }

  // multiplies two Quantity's together
  public Quantity mul( Quantity multip ) throws IllegalArgumentException
  {
    if( multip == null )
    {
      throw new IllegalArgumentException();
    }
    Quantity toReturn = new Quantity();
    double newVal = this.value * multip.value;
    
    //Iterator<Map.Entry<String, Integer>> iter = unitMap.entrySet().iterator();
    //Iterator<Map.Entry<String, Integer>> iterOther = 
    //                                     multip.unitMap.entrySet().iterator();

    Iterator<String> iterKey1 = unitMap.keySet().iterator();
    Iterator<String> iterKey2 = multip.unitMap.keySet().iterator();

    // stores the units for the numerator
    List<String> numUnits = new ArrayList<String>();
    // stores the units for the denominator
    List<String> denomUnits = new ArrayList<String>();

    String key;
    Integer val;
    while( iterKey1.hasNext() )
    {
      key = iterKey1.next();
      val = unitMap.get(key);
      if( val < 0 )
      {
        denomUnits
      }
    }

    while(iterKey1.hasNext())
    {
      System.out.println("ITERKEY1: " + iterKey1.next());
      
    }


    //while(iter.hasNext())
    {
      //System.out.println("ITER: " + iter.next());
    }


    System.out.println("ENTRYSET: " + unitMap.entrySet());
    System.out.println("KEYSET: " + unitMap.keySet());
    System.out.println("VALUES: " + unitMap.values());

    return null;
  }

  // this is the dividend
  public Quantity div( Quantity divis ) throws IllegalArgumentException
  {
    if( divis == null )
    {
      throw new IllegalArgumentException();
    }
    return null;
  }

  // returns this to the power of the passed in exponenet
  public Quantity pow( int exp )
  {
    return null;
  }

  // adds two Quantity's together
  public Quantity add( Quantity toAdd ) throws IllegalArgumentException
  {
    //should throw IllegalArgument if units are not the same
    return null;
  }

  // subtracts the argument from this quantity ( neither should change )
  public Quantity sub( Quantity toSub ) throws IllegalArgumentException
  {
    // should also throw IllegaArgumentException if units are not the same
    if( toSub == null )
    {
      throw new IllegalArgumentException();
    }
    return null;
  }

  // returns negation of this Quantity
  public Quantity negate( )
  {
    return null;
  }

  // MUST UPDATE WITH OUR VARIABLE NAMES
  public String toString()
  {
    // XXX You will need to fix these lines to match your fields!
    double valueOfTheQuantity = this.value;
    Map<String,Integer> mapOfTheQuantity = this.unitMap;

    //Ensure we get the units in order
    TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());

    StringBuffer unitsString = new StringBuffer();

    for( String key : orderedUnits )
    {
      int expt = mapOfTheQuantity.get(key);
      unitsString.append( " " + key );
      if( expt != 1)
      {
        unitsString.append( "^" + expt);
      }
    }

    // Used to convert doubles to a string with a fixed maximum number of
    // decimal places.

    DecimalFormat df = new DecimalFormat("0.0####");

    // Put it all together and return.
    return df.format(valueOfTheQuantity) + unitsString.toString();
  }

  // checks if an object is equal to this Quantity
  public boolean equals( Object obj )
  {
    if( obj == null )
    {
      return false;
    }
    if( obj.getClass() != this.getClass() )
    {
      return false;
    }
    Quantity newObj = (Quantity)obj;
    if( newObj.toString().equals(this.toString()) )
    {
      return true;
    }
    return false;
  }

  // returns an integer, such that equal Quantities always return the same int
  public int hashCode()
  {
    return this.toString().hashCode();
  }

  // returns a brand-new normalized Quantity equal to one of the given unit
  public static Quantity normalizedUnit(String unit, Map<String, Quantity> data)
  {
    return null;
  }


  // returns a copy of this in normalized form
  public Quantity normalize( Map<String, Quantity> data )
  {
    return null;
  }

}
