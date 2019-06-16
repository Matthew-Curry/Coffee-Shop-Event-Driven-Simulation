/**
 * A class that represents customers. It holds the arrival time of the customers to the coffee shop.
 *
 * @author Matthew Curry
 */
public class Customer
{
    //the arrival time
    int aTime;
    /**
     * Constructor for objects of class Customer
     */
    public Customer(int time)
    {
        //hold the time associated with the customer event 
        this.aTime = time;
    }
    
    /**
     * A method that returns a an int representing the time associated with the customer's arrival
     * 
     * @param void
     * @return atTime an integer associated with the Customer object's arrival time
     */
    public int arrivalTime(){
        //return the arrival time of the customer
        return aTime;
    }
}

