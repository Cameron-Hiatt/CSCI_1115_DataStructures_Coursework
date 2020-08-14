/*
Author: Cameron Hiatt
Date: 8-18-2020

Description: This program uses comparator and comparable with quicksort to sort lists of comparable things.
*/
import java.util.Comparator;

public class Exercise23_03 {
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
                     new Circle(5), new Circle(6), new Circle(1), new Circle(2),
                     new Circle(3), new Circle(14), new Circle(12)};
    quickSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.println(list1[i] + " ");
    }
  }
  
  public static <E extends Comparable<E>> void quickSort(E[] list)
  {
	  quickSort(list, 0, list.length - 1);
  }
  
  public static <E extends Comparable<E>> void quickSort(E[] list, Integer left, Integer right)
  {
	  if (left < right)
	  {
		  Integer initialLeft = left;
		  Integer initialRight = right;
		  E pivot = list[(left + right) / 2];
	 
		  do
		  {
			  while (list[left].compareTo(pivot) < 0)
			  {
				  left++;
			  }
			  
			  while (list[right].compareTo(pivot) > 0)
			  {
				  right--;
			  }
			  
			  if (left <= right)
			  {
				  E temp = list[left];
				  list[left] = list[right];
				  list[right] = temp;
				  left++;
				  right--;
			  }
		  }while (left <= right);
		  
		  quickSort(list, initialLeft, right);
		  quickSort(list, left, initialRight);
	  }
  }

  public static <E> void quickSort(E[] list, Comparator<? super E> comparator)
  {
	  quickSort(list, 0, list.length - 1, comparator);
  }
  
  public static <E> void quickSort(E[] list, Integer left, Integer right, Comparator<? super E> comparator)
  {
	  if (left < right)
	  {
		  Integer initialLeft = left;
		  Integer initialRight = right;
		  E pivot = list[(left + right) / 2];
	 
		  do
		  {
			  while (comparator.compare(list[left], pivot) < 0)
			  {
				  left++;
			  }
			  
			  while (comparator.compare(list[right], pivot) > 0)
			  {
				  right--;
			  }
			  
			  if (left <= right)
			  {
				  E temp = list[left];
				  list[left] = list[right];
				  list[right] = temp;
				  left++;
				  right--;
			  }
		  }while (left <= right);
		 
			  quickSort(list, initialLeft, right, comparator);
			  quickSort(list, left, initialRight, comparator);
	  	}
  	}
}