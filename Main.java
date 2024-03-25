/*
 * Class and Project: Operating Systems, COP 4610, Banker's algorithm
 * Author: Karim Hussien
 * Due Date: April 10, 2023
 * Description: Create a version of the Banker's algorithm and show it successfully running for one instance of the program, and another instance where it denies the request.
 * Output whether or not a safe state process was found or if it leaves the system in an unsafe state, along with the need matrix of the processes, and the available array at the beginning and end
 */

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main 
	{

		public static void main(String[] args) 
			{
				
				System.out.println("Please enter a digit for the Banker Algorithm size you would like to solve for: ");
				
				Scanner input = new Scanner(System.in);
				int maxSize = input.nextInt();
				//this variable is used to handle of the size of the allocation, max, need and available variables
				
				int processCounter = 0;
				//used to see if while loop is running too many iterations
				//if so, it means we have no safe state process
			
				Process p1 = new Process();
				Process p2 = new Process();
				Process p3 = new Process();
				Process p4 = new Process();
				Process p5 = new Process();
				//creation of the processes
				
				LinkedList<Integer> available = new LinkedList<Integer>();
				LinkedList<Integer> work = new LinkedList<Integer>();
				LinkedList<String> queue = new LinkedList<String>();
				//creation of unique LinkedLists that help emulate the banker's algorithm
				//these are intrinsic to the system's memory and not the individual processes
				//this is why they are declared outside of the process class
				
				setAvailable(available, maxSize);
				//sets the value for the available variable
				
				work = available;
				//work should have the same values as available at the beginning
				
				System.out.print("This is the available array at the beginning: ");
				
				for(int i = 0; i < maxSize; i++)
					{
						System.out.print(available.get(i));
						System.out.print(" ");
					}
				
				System.out.println();
				//informs the user of the available array
				
				setMax(p1, maxSize);
				setMax(p2, maxSize);
				setMax(p3, maxSize);
				setMax(p4, maxSize);
				setMax(p5, maxSize);
				//sets the value for the max attributes in the process variables
				
				setAllocation(p1, maxSize);
				setAllocation(p2, maxSize);
				setAllocation(p3, maxSize);
				setAllocation(p4, maxSize);
				setAllocation(p5, maxSize);
				//sets the value for the allocation attributes in the process variables
				
				setNeed(p1, maxSize);
				setNeed(p2, maxSize);
				setNeed(p3, maxSize);
				setNeed(p4, maxSize);
				setNeed(p5, maxSize);
				//sets the value for the need attributes in the process variables
				
				
				p1.setFinish(false);
				p2.setFinish(false);
				p3.setFinish(false);
				p4.setFinish(false);
				p5.setFinish(false);
				//sets the finish sign of all processes to false, since at this point none of them are done
				
				p1.setIsValid(false);
				p2.setIsValid(false);
				p3.setIsValid(false);
				p4.setIsValid(false);
				p5.setIsValid(false);
				//sets the valid sign of all processes to false, since at this point we do not know if any of them are valid
				
				checkValid(p1, work, maxSize);
				checkValid(p2, work, maxSize);
				checkValid(p3, work, maxSize);
				checkValid(p4, work, maxSize);
				checkValid(p5, work, maxSize);
				//checks to see if any process can run immediately once we enter the loop
				
				while(processCounter < 6)
					{
						
						if(p1.getFinish() == false)
							{
								if(p1.getIsValid() == true)
									{
										p1.setFinish(true);
										queue.add("p1");
										workUpdate(work, p1, maxSize);
										checkValid(p2, work, maxSize);
										checkValid(p3, work, maxSize);
										checkValid(p4, work, maxSize);
										checkValid(p5, work, maxSize);
									}
							}//checking over p1, if p1 can run, we need to update the work and check if any other process can now run
						
						if(p2.getFinish() == false)
							{
								if(p2.getIsValid() == true)
									{
										p2.setFinish(true);
										queue.add("p2");
										workUpdate(work, p2, maxSize);
										checkValid(p1, work, maxSize);
										checkValid(p3, work, maxSize);
										checkValid(p4, work, maxSize);
										checkValid(p5, work, maxSize);
									}
							}//checking over p2, if p2 can run, we need to update the work and check if any other process can now run
						
						if(p3.getFinish() == false)
							{
								if(p3.getIsValid() == true)
									{
										p3.setFinish(true);
										queue.add("p3");
										workUpdate(work, p3, maxSize);
										checkValid(p1, work, maxSize);
										checkValid(p2, work, maxSize);
										checkValid(p4, work, maxSize);
										checkValid(p5, work, maxSize);
									}
							}//checking over p3, if p3 can run, we need to update the work and check if any other process can now run
						
						if(p4.getFinish() == false)
							{
								if(p4.getIsValid() == true)
									{
										p4.setFinish(true);
										queue.add("p4");
										workUpdate(work, p4, maxSize);
										checkValid(p1, work, maxSize);
										checkValid(p2, work, maxSize);
										checkValid(p3, work, maxSize);
										checkValid(p5, work, maxSize);
									}
							}//checking over p4, if p4 can run, we need to update the work and check if any other process can now run
						
						if(p5.getFinish() == false)
							{
								if(p5.getIsValid() == true)
									{
										p5.setFinish(true);
										queue.add("p5");
										workUpdate(work, p5, maxSize);
										checkValid(p1, work, maxSize);
										checkValid(p2, work, maxSize);
										checkValid(p3, work, maxSize);
										checkValid(p4, work, maxSize);
									}
							}//checking over p5, if p5 can run, we need to update the work and check if any other process can now run
						
						processCounter++;
						//used to check if we have run too many times, the while loop should only run at most 5 times, if more, we should leave
						
					}//end of while loop
				
				if(p1.getFinish() == true && p2.getFinish() == true && p3.getFinish() == true && p4.getFinish() == true && p5.getFinish() == true)
					{
						System.out.print("A safe state process was found: ");
						
						for(int i = 0; i < 5; i ++)
							{
								System.out.print(queue.poll());
								System.out.print(" ");
							}
						
					}//end of if
				//checks to see if every process has its finish sign set to true
				//if so, we print out the safe state process
				
				else
					{
						System.out.println("Error, no process leaves us in a safe state, this combination leaves us deadlocked");
					}//end of else
				//if any of the processes still had a false finish sign after the loop, this means there was no safe state combination
				
				
				System.out.println();
				
				System.out.print("This is the available array at the end: ");
				
				for(int j = 0; j < maxSize; j++)
					{
						System.out.print(available.get(j));
						System.out.print(" ");
					}
				//prints out the available array after the code is done
				
				System.out.println();
				
				System.out.println("This is the need values for p1, p2, p3, p4, p5: ");
				
				printNeed(p1, maxSize);
				printNeed(p2, maxSize);
				printNeed(p3, maxSize);
				printNeed(p4, maxSize);
				printNeed(p5, maxSize);
				//prints out the need values for every process

			}//end main method
		
		//used to randomly fill the Available variable with values
		public static void setAvailable(LinkedList<Integer> available, int size)
			{
				Random rand = new Random();
			
				for(int i = 0; i < size; i++)
					{
						available.add(rand.nextInt(9) + 1);
					}
				
			}//end of setAvailable method
		
		//this is used to randomly set the max 
		public static void setMax (Process p, int size)
			{
				Random rand = new Random();
				
				for(int i = 0; i < size; i++)
					{
						p.max.add(rand.nextInt(9) + 1);
					}
				
			}//end of setMax method
		
		//used to set the values for the allocation variable in a process
		//the numbers randomly decided are always meant to be less than or equal to the max or that same process
		public static void setAllocation (Process p, int size)
			{
				Random rand = new Random();
			
				for(int i = 0; i < size; i++)
					{
						p.allocation.add(rand.nextInt(p.max.get(i)));
					}
			
			}//end of setAllocation method
		
		//used to set the values for the need variable in a process
		//Need is the max of a process - the allocation of a process
		public static void setNeed (Process p, int size)
			{
				for(int i = 0; i < size; i++)
					{
						p.need.add(p.max.get(i) - p.allocation.get(i));
					}
				
			}//end of setNeed method
		
		//used to see if a process can be allocated resources and leave the process in a safe states
		//looks at a processes' need values and sees if they are less than or equal to the available values
		//if they are, we say the process is valid, but if even one value is not less than or equal to, the process is not valid
		public static void checkValid (Process p, LinkedList<Integer> work, int size)
			{
				for(int i = 0; i < size; i++)
					{
						if(p.need.get(i) <= work.get(i))
							{
								p.setIsValid(true);
							}
						
						else
							{
								p.setIsValid(false);
								break;
							}
					}
				
			}//end of checkValid method
		
		//used to update the work variable when a process is completed
		//if a process is completed, work = work + allocation, so we grab that processes' allocation values and add them to work
		public static void workUpdate(LinkedList<Integer> work, Process p, int size)
			{
				for(int i = 0; i < size; i++)
					{
						work.set(i, work.get(i) + p.allocation.get(i));
					}
			}//end of workUpdate method
		
		//used to print the values of need
		public static void printNeed(Process p, int size)
			{
				for(int i = 0; i < size; i++)
					{
						System.out.print(p.need.get(i));
						System.out.print(" ");
					}
				
				System.out.println();
				
			}//end of printNeed method

	}//end of main class