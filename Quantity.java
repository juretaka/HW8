/*
 * CSE 12 Homework 8
 * Joshua Chang and Kirk Srijongsirikul
 * A11666538 and A11687702
 * A00 (both partners)
 * 5-27-14
 */

import java.io.*;
import java.text.DecimalFormat;

public class Quantity {

  // stores the numerical value of the Quantity
  private double value;
  // stores the attached units
  private Map<String, Integer> unitMap;


  public Quantity()
  {
    value = 1.0;
    //
  }

  public Quantity( Quantity orig )
  {
  }

  public Quantity(double value, List<String> numUnits, List<String> denomUnits)
  {
    this.value = value;
  }

  // multiplies two Quantity's together
  public Quantity mul( Quantity multip ) throws IllegalArgumentException
  {
    if( multip == null )
    {
      throw new IllegalArgumentException();
    }
  }

  // this is the dividend
  public Quantity div( Quantity divis ) throws IllegalArgumentException
  {
    if( divis == null )
    {
      throw new IllegalArgumentException();
    }
  }

  // returns this to the power of the passed in exponenet
  public Quantity pow( int exp )
  {
  }

  // adds two Quantity's together
  public Quantity add( Quantity toAdd ) throws IllegalArgumentException
  {
    //should throw IllegalArgument if units are not the same
  }

  // subtracts the argument from this quantity ( neither should change )
  public Quantity sub( Quantity toSub ) throws IllegalArgumentException
  {
    // should also throw IllegaArgumentException if units are not the same
    if( toSub == null )
    {
      throw new IllegalArgumentException();
    }
  }

  // returns negation of this Quantity
  public Quantity negate( )
  {
  }

  // MUST UPDATE WITH OUR VARIABLE NAMES
  public String toString()
  {
    // XXX You will need to fix these lines to match your fields!
    double valueOfTheQuantity = this.NAME_OF_RELEVANT_FIELD;
    Map<String,Integer> mapOfTheQuantity = this.NAME_OF_RELEVANT_FIELD;

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

    DecimalFormat df = new DecimalFormat("0,0####");

    // Put it all together and return.
    return df.format(valueOfTheQuantity) + unitsString.toString();
  }

  // checks if an object is equalo to this Quantity
  public boolean equals( Object obj )
  {
  }

  // returns an integer, such that equal Quantities always return the same int
  public int hashCode()
  {
  }

  // returns a brand-new normalized Quantity equal to one of the given unit
  public static Quantity normalizedUnit(String unit, Map<String, Quantity> data)
  {
  }


  // returns a copy of this in normalized form
  public Quantity normalize( String unit, Map<String, Quantity> data )
  {
  }

}
