

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestEvent. Tests the event class.
 *
 * @author Matthew Curry
 */
public class TestEvent
{
    /**
     * Default constructor for test class TestEvent
     */
    public TestEvent()
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
     * A method that tests the isArrival method 
     * @result will pass the test if an arrival event returns true for isAr() and a departure event returns false from the same method 
     */
    @Test public void testIsAr(){
    //test a method that sould return whether an event is an arrival event
    //create an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    //the method should return true by default
    if(e.isArr()==true){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    //if the event is changed to departure, should return false
    e.change();
    if(e.isArr()==true){
    System.out.println(false);    
    }
    else{
    System.out.println(true);     
    }
    }
    
    /**
     * A method that tests the isDep method 
     * @result will pass the test if an arrival event returns false for isDep() and a departure event returns true from the same method 
     */
    @Test public void testIsDep(){
    //test a method that sould return whether an event is an arrival event
    //create an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    //the method should return true if made default
    e.change();
    if(e.isDep()==true){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    //if the event is changed to arrival, should return false
    e.change();
    if(e.isDep()==true){
    System.out.println(false);    
    }
    else{
    System.out.println(true);     
    }
    }
    
    @Test public void testGetCust(){
    //test an event that should return the customer passed to an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    Customer got =  e.getCust();
    //the returned customer should be the same as passed to the event constructor
    if(got==cus){
    System.out.println(true);     
    }else{
    System.out.println(false);     
    }
    }
    
    /**
     * A method that tests the returnTime method 
     * @result will pass the test if the method returns the same time used to construct the event and after the time has been changed.
     */
    @Test public void testReturnTime(){
    //test a method to return the time of the event
    //If not changed, should equal the time of the customer
    //create an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    if(e.returnTime()==5){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    //if the event time is changed, return time should reflect the change
    //change the time
    e.setTime(10);
    if(e.returnTime()==10){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    }
    
    /**
     * A method that tests the setTime method 
     * @result the test passes if the time the event is set to is the same time returned by the returnTime method 
     */
    @Test public void testSetTime(){
    //test a method that should set the time of an event's occurance
    //create an event
    //create an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    //set the time
    e.setTime(10);
    //check if the set time is the event's time
    if(e.returnTime()==10){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    }
    
    /**
     * A method that tests the change method 
     * @result will pass the test after the arrival event is changed to a departure, isDep method returns true 
     */
    @Test public void testChange(){
    //test a method that should change the type of event from arrival to departure
    //create an event
    Customer cus = new Customer(5);
    Event e = new Event(cus);
    //change the event
    e.change();
    //the event should be a departure
    if(e.isDep()==true){
    System.out.println(true);    
    }
    else{
    System.out.println(false);     
    }
    }
    
    /**
     * A method that tests the compareTo method 
     * @result will pass the test if the method returns 1 if the events are of the same type and the method correctly returns 1, and in the case when the events are of different types, returns one to signify that the arrival event is placed first 
     */
    @Test public void testCompare(){
    //test the compareTo method for events
    //case where both events are arrivals
    //first arrival event
    Customer cus1 = new Customer(5);
    Event e1 = new Event(cus1);
    //second arrival event
    Customer cus2 = new Customer(4);
    Event e2 = new Event(cus2);
    if(e1.compareTo(e2)==1){
    System.out.println(true);
    }else{
    System.out.println(false);    
    }
    //test the case where the events are of the opposite type
    //change event to departure
    e1.change();
    if(e1.compareTo(e2)==1){
    System.out.println(true);
    }else{
    System.out.println(false);    
    }
    //test case of 2 departure events
    //change both events to departures
    e2.change();
    //the events should compare the same way
    if(e1.compareTo(e2)==1){
    System.out.println(true);
    }else{
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
