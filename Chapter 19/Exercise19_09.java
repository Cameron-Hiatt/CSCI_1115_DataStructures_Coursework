/*
Author: Cameron Hiatt
Date: 7-20-2020

Description: Added a method that will sort an array list from lowest value to highest.
*/
import java.util.ArrayList;

public class Exercise19_09 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(4);
    list.add(42);
    list.add(5);
    Exercise19_09.<Integer>sort(list);
    
    System.out.print(list);
  }
  
  public static <E extends Comparable<E>> void sort(ArrayList<E> list) 
  {
	  //A temporary ArrayList to hold the sorted number before being used to copy the sorted list to the original array
	  ArrayList<E> listTemp = new ArrayList<E>();
	  E currentMin = list.get(0);
	  int size = list.size();
	  int counter = 0;
	  
	  //A counter controlled loop to allow non-nested for loops. With the need to remove items from the array list, 
	  //I wanted to eliminate having a nested for loop change the size of the array list and affect the outer for loop
	  //parameters. This way it allows me to find the minimum with one loop, then iterate through and remove that minimum item
	  //from the list without affecting an outer loop that wouldn't have been finished before the inner loop ran again.
	  while(counter != size)
	  {
		  //this must be placed here before the last item in the list is removed, otherwise and ArrayIndexOutOfBounds error occurs
		  currentMin = list.get(0);
		  
		  for (int i = 0; i < list.size(); i++)
		  {
			  if (currentMin.compareTo(list.get(i)) > 0)
				  currentMin = list.get(i);
		  }
		  
		  for (int j = 0; j < list.size(); j++)
		  {
			  if (currentMin.compareTo(list.get(j)) == 0)
				  list.remove(j);
		  }
		  
		  listTemp.add(currentMin);
		  
		  counter++;
	  }
	  
	  //copying the sorted version of the loop to the original loop.
	  for (int i = 0; i < listTemp.size(); i++)
		  list.add(listTemp.get(i));
	  
  }
}
