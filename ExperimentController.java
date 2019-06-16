import java.util.Scanner;
import java.io.File;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.io.FileReader;
/**
 * The experiment controller of the simulation. Runs a simulation of the coffee shop for a number of cashiers passed in by the command line,
 * as well as the profit per customer, cost of a cashier, and time to serve read in from the input document. 
 *
 * @author Matthew Curry
 */
public class ExperimentController
{
    //parameters read in from the file
    float profit;
    float cost;
    float serveTime;
    //the number of cashiers passed from the command line
    Integer s = 0;
    //the number of customers that come to the shop
    int total;
    //the number of available cashiers
    int a;
    //the capacity of the queue, defined as 8 times number of cashiers
    int cap;
    //the number of customers served in a day
    int num;
    //the number of customers turned away
    int over;
    //the queue to store the customers
    Queue<Customer> q = new ArrayDeque<Customer>();
    //the priority queue that hold events
    PriorityQueue<Event> e = new PriorityQueue<Event>();
    //an arraylist for the wait times
    ArrayList<Integer> wait = new ArrayList<Integer>();
    // a variable to keep track of the time of the shop, measured in seconds
    int shopTime;
    Scanner reader = null;
    //a boolean related to whether the shop is open
    boolean open;
    public static void main(String[]args){
        //create an instance of the experiment controller class
        ExperimentController exp = new ExperimentController();
        exp.run(args);
    }

    /**
     * The first method called. Reads in customer arrival data and creates arrival events to store in the priority queue and assigns command line arguments to variables before calling methods to advance the program.
     * 
     * @param args String[] that is passed from the command line, where the first entry is taken as the number of cashiers, s
     * @throws exception try statement that will throw excpetions related to the reading in of the data file
     */
    public void run(String[] c){
        //the number of cashiers passed in
        s = Integer.parseInt(c[0]);
        //the cap
        cap = 8*s;
        //read in the file, set the parameters and array holding the customers in waiting
        try{
            //the file
            reader = new Scanner(new FileReader("input.txt"));
            //Read the lines of the file and place in the right spot
            profit = Float.parseFloat(reader.nextLine());
            //reader.nextLine();
            cost = Float.parseFloat(reader.nextLine());
            //reader.nextLine();
            serveTime = Float.parseFloat(reader.nextLine());
            //add all the arrival events
            while(reader.hasNextLine()){
                //take the next line and convert to an integer
                String entry = reader.nextLine();
                String[] parts = entry.split(":");
                int hour = Integer.parseInt(parts[0])*3600;
                int min = Integer.parseInt(parts[1])*60;
                String temp = parts[2];
                String temp2 = temp.substring(0,2);
                int sec  = Integer.parseInt(temp2);
                //add the pieces together to get complete time measured in seconds
                int time = hour + min +sec;
                //create a new customer and arrival event. Add arrivals to event queue
                Customer cust = new Customer(time);
                Event arr = new Event(cust);
                e.add(arr);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //open shop and run simulation
        openShop();
    }

    /**
     * A method that returns true if there is a cashier available to attend to customers
     * 
     * @param void
     * @return cashAv() boolean
     */
    public boolean cashAv(){
        if(a>0){
            return true;    
        }
        else{
            return false;    
        }
    }

    /**
     * A method that runs the simulation of the coffee shop. After initially the proper variables, in a while loop it calls method to handle the flow of customers and events while the shop is open.
     * 
     * @param void
     * @return void
     */
    public void openShop(){
        //set the open time
        shopTime = 26000;
        //the shop is open
        open = true;
        //all cashiers are available to start
        a = s.intValue();
        //so far, no customers have been served
        num = 0;
        while(!e.isEmpty()&&open){
            //run the simulation
            runSim();
            //check if the shop is still open
            checkOpen();
        }
        //if there are still customers in the queue after the shop is closed, they should be served
        while(!q.isEmpty()){
            //process the customers
            checkLine();
            //call run sim to deal with the departure events
            runSim();
        }
        //calculate relevant statistics
        shopResults();
    }

    /**
     * A method that checks the priority queue to see if there are customer arrival events that should be processed and processes the events.
     * 
     * @param void
     * @return void
     */
    public void runSim(){
        //first you must check customer queue
        checkLine();
        //examine the current event
        Event current = e.poll();
        if(current.isDep()){//if the top event is a departure, then a cashier is available
            a++;
            //a customer has been served
            num++;
            //update shopTime
            shopTime = current.returnTime();
        }else{
            if(cashAv()){
                //if you serve a customer, you lose a cashier
                a--;    
                //the shopTime is updated
                shopTime = current.returnTime();
                //add the time to serve to the event time, which is the current shop time
                current.setTime(shopTime + (int)serveTime);
                //convert the event from an arrival to a departure
                current.change();
                //add event back as departure
                e.add(current);
            }
            else{
                //No cashier is available, so the customer should be added to the queue if there is space
                if(q.size()<cap){
                    Customer cust = current.getCust();
                    q.add(cust);
                }else{
                    //the customer is turned away
                    over++;
                }
            }
        }
    }

    /**
     * A method removes the top customer in the priority queue if the queue is not empty and a cashier is available before creating an processing a corosponding departure event.
     * 
     * @param void
     * @return void
     */
    public void checkLine(){
        //check if the queue has customers, process top as a departure and add to event queue
        if(!q.isEmpty()){
            if(cashAv()){
                //convert the customer to an event
                Customer cust = q.poll();
                Event current = new Event(cust);
                //calculate customer wait time and store
                int waitTime = shopTime - current.returnTime();
                wait.add(waitTime);
                //convert event to departure, change time and put back in priority queue
                current.setTime(shopTime + (int)serveTime);
                current.change();
                e.add(current);
                //if you serve a customer, the number of available cashiers decreases
                a--;
            }
        }
    }

    /**
     * A method that checks if the shop is still open. It updates the parameter open to false if the closing time has arrived. 
     * 
     * @param void
     */
    public void checkOpen(){
        if(shopTime>=79200){
            open = false;    
            //the true closing time is 79200
        }
    }

    /**
     * A method that prints the results of the simulation.
     * 
     * @param void
     * @return void
     */
    public void shopResults(){
        //calculate the relevant statistics
        //the profit
        int totalProfit = (int)profit*num;
        System.out.println("Profit:"+totalProfit);   
        //the cost
        int totalCost = s*(int)cost;
        System.out.println("Cost:"+totalCost);
        //net profit
        int netProfit;
        netProfit = totalProfit - totalCost;
        System.out.println("Net Profit:"+netProfit);
        //OverFlow rate
        double totalCus = (double)num +(double)over;
        double overRate = over/totalCus;
        System.out.println("OverFlow Rate:"+overRate);
        //max wait time
        System.out.println("The max wait:"+maxWait());
        //average wait time
        System.out.println("The average wait:"+avWait());
    }

    /**
     * A method that returns the maximum wait time for the customers.
     * 
     * @param void
     * @return int max the maximum wait time for the customers.
     */
    public int maxWait(){
        //the max wait time
        Integer max = 0;    
        for(int i = 0;i<wait.size();i++){
            if(max.compareTo(wait.get(i))<0){
                max = wait.get(i);    
            }
        }
        return max;
    }

    /**
     * A method that returns the average wait time for the customers.
     * 
     * @param void
     * @return int av the average wait time for the customers.
     */
    public int avWait(){
        //the sum of the wait times
        int sum =0;
        for(int i = 0;i<wait.size();i++){
            sum = sum+wait.get(i);
        }
        int av = sum/num;
        return av;
    }

    /**
     * A method that returns experiment results in the form of an integer arraylist for the sake of unit testing. The array includes
     * all int results.
     * 
     * @param void 
     * @return ArrayList<Integer> result the arrayList that holds the resulting array. 
     */
    public int[] resultArray(){
        //the result array
        int[] result = new int[5]; 
        //the profit
        int totalProfit = (int)profit*num;  
        result[0] = totalProfit;
        //the total cost
        int totalCost = s*(int)cost;
        result[1] = totalCost;
        //net profit
        int netProfit;
        netProfit = totalProfit - totalCost;
        result[2] = netProfit;
        //the max wait
        maxWait();
        result[3] = maxWait();
        //the average wait
        avWait();
        result[4] = avWait();
        return result;
    }
    
    /**
     * A method that returns the overflow rate. It is distinguished from the ArrayList because it is a double rather than integer value. 
     * all int results.
     * 
     * @param void 
     * @return double overFlow() the overflow rate calculated by the program
     */
    public double overFlow(){
        //OverFlow rate
        double totalCus = (double)num +(double)over;
        double overRate = over/totalCus;   
        return overRate;
    }
}

