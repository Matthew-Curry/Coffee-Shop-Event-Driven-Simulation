

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class testCus. Tests the customer class.
 *
 * @author  Matthew Curry
 * @version (a version number or a date)
 */
public class testCus
{
    /**
     * Default constructor for test class testCus
     */
    public testCus()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    /**
     * A method that tests the arrivalTime method.
     * @result will pass the test if the time sent to the constructor is the same time returned by the method. 
     */
    @Test public void testArrivalTime(){
    //test a method that should return the arrival time of a customer passed to the constructor
    //create a customer
    Customer cus = new Customer(5);
    //the method should return the same time passed to the constructor
    if(cus.arrivalTime()==5){
    System.out.println(true);    
    }
    else{
    System.out.println(false);    
    }
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
