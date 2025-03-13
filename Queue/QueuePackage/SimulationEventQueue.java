package QueuePackage; // for some reason this does not work?

import QueuePackage.SimulationEventQueueInterface;
import QueuePackage.EmptyQueueException;
import QueuePackage.PriorityQueueInterface;
import QueuePackage.QueueInterface;
import QueuePackage.SimulationEvent;
import QueuePackage.SimulationEventInterface;
import QueuePackage.VectorQueue;
import java.util.*;

public class SimulationEventQueue implements SimulationEventQueueInterface
{
    private Vector<SimulationEvent> queue;
    private double theCurrentTime;

    public SimulationEventQueue()
    {
        queue = new Vector<SimulationEvent>();
        theCurrentTime = 0.0;
    } 
    
    public void add(SimulationEvent newEvent)
    {
        if(newEvent.getTime() < theCurrentTime)
        {
            //System.out.println("Fails to add event... before the current time");
        }
        else if(queue.isEmpty())
        {
            queue.add(newEvent);
            //System.out.println("   added to empty queue");
        }
        else
        {
            System.out.println("Adding an event " + newEvent + " at time " + newEvent.getTime());
            int pos = -1;
            boolean foundLarger = false;
            Iterator<SimulationEvent> iter = queue.iterator();
            while(iter.hasNext() && !foundLarger)
            {
                pos++;
                SimulationEvent check = iter.next();
                //System.out.println("  checked against time " + check.getTime() );
                
                foundLarger = check.getTime() > newEvent.getTime();
            }
            
            System.out.println("   pos is " + (pos));
            System.out.println("   foundLarger is " + (foundLarger));
            
            if(!foundLarger)
            {
                queue.add(newEvent);        // add to end
                System.out.println("   added to end ");
            }
            else 
            {
                queue.add(pos,newEvent);        // legal to add
                System.out.println("   added to position " + (pos));
            }
         }       
    } // end add

    public SimulationEvent peek()
    {
        SimulationEvent front = null;
        if (isEmpty())
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        else
            front = queue.get(0);
        return front;
    } // end get
    

    public SimulationEvent remove() {
        SimulationEvent front = null;
        if (isEmpty()) {
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        } else {
            front = queue.get(0);
            queue.remove(0);
            theCurrentTime = front.getTime();
            //System.out.println("Removed the first event " + front + " time is now " + theCurrentTime);
        } // end if
        return front;
    } // end remove

    public boolean isEmpty()
    {
        return queue.isEmpty();
    } // end isEmpty
    
    public void clear()
    {
        queue.clear();
    } // end clear

	
	public int getSize()
	{
	   return queue.size();
	}
	
	public double getCurrentTime()
	{
	   return theCurrentTime;
	}

}
