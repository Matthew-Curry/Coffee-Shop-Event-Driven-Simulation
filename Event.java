
/**
 * A class that represents the different event types for the simulation. The constructor takes a customer and can be an arrival or 
 * departure event.
 *
 * @author Matthew Curry
 */
public class Event implements Comparable<Event>
{
    //the time event happens
    private int time;
    //boolean for if arrival event
    boolean isArrival;
    //boolean for is departure
    boolean isDeparture;
    //the customer that the event relates to 
    Customer cust;
    /**
     * Constructor for objects of class Event
     */
    public Event(Customer cust)
    {
        //the customer
        this.cust = cust;
        //initial event is an arrival
        isArrival = true;
        isDeparture = false;
        //the initial time is the customer arrival time
        time = cust.arrivalTime();
    }

    /**
     * A method that returns a boolean represnting whether the event is an arrival event. 
     * 
     * @param void
     * @return isArrival a boolean representing whether the event is an arrival event.
     */
    public boolean isArr(){
        return isArrival;    
    }
    
    /**
     * A method that returns the customer held by the event object  
     * 
     * @param void
     * @return cust a customer object held by the event object 
     */
    public Customer getCust(){
        return cust;    
    }

    /**
     * A method that returns a boolean represnting whether the event is an departure event. 
     * 
     * @param void
     * @return isDep a boolean representing whether the event is an departure event.
     */
    public boolean isDep(){
        return isDeparture;    
    }

    /**
     * A method that returns a an int representing the time associated with the event's occurance. 
     * 
     * @param void
     * @return time an int representing the time the event occurs at. 
     */
    public int returnTime(){
        return time;    
    }

    /**
     * A method that sets the time of the event. 
     * 
     * @param void
     * @return void 
     */
    public void setTime(int time){
        this.time = time;    
    }

    /**
     * A method that changes the event type from an arrival to a departure. 
     * 
     * @param void
     * @return void 
     */
    public void change(){
        isArrival = false;
        isDeparture = true;
    }

    /**
     * A method that returns an int corosponding to the appropriate order events should be ordered by. 
     * 
     * @param void
     * @return Comparison returns int 1, -1, or 0 corosponding to the appropriate order the events should be compared by 
     */
    public int compareTo(Event e){
        if(this.time>e.returnTime()){
            return 1;    
        }else if(this.time<e.returnTime()){
            return -1;
        }
        else{
            return 0; 
        }
    }
}
