
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList; 
/**
 * The test class testExp.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class testExp
{
    /**
     * Default constructor for test class testExp
     */
    public testExp()
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
     * A method that tests if the results are correct for two cashiers. Tests all results other than the overflow rate.
     * @result will pass the test if the values match the results already determined to be correct 
     */
    @Test public void testResultArray(){
        //an instance of the controller
        ExperimentController exp = new ExperimentController();
        //set the number of cashiers at 2
        String[] cash = new String[1];
        cash[0]="2";
        //run the experiment
        exp.run(cash);
        //define the correct array
        int[] trueAns1 = new int[5];
        trueAns1[0] = 1674;
        trueAns1[1] = 600;
        trueAns1[2] = 1074;
        trueAns1[3] = 959;
        trueAns1[4] = 575;
        //call the result
        int[] theAns1 = exp.resultArray();
        assertArrayEquals(trueAns1, theAns1);
    }
    
    /**
     * A method that tests if the resulting overflow rate is correct for two cashiers.
     * @result will pass the test if the values match the results already determined to be correct 
     */
    @Test public void testOverFlow(){
    //an instance of the controller
        ExperimentController exp = new ExperimentController();
        //set the number of cashiers at 2
        String[] cash = new String[1];
        cash[0]="2";
        //run the experiment
        exp.run(cash);
        //the overflow given by the experiment
        double theAns = exp.overFlow();
        //the true ans
        double trueAns = .2059;
        assertEquals(theAns, trueAns, .001);
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
