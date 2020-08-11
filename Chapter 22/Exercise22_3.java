//Author: Cameron Hiatt
//Date Created: August 10th, 2020

import java.util.*;

public class Exercise22_3 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		LinkedList<Character> largerStringAsList = new LinkedList<>();
		LinkedList<Character> smallerStringAsList = new LinkedList<>();
		String string1 = new String();
		String string2 = new String();
		String biggerString = new String();
		String smallerString = new String();
		
		System.out.print("Enter first string: ");
		string1 = input.nextLine();
		
		System.out.print("Enter second string: ");
		string2 = input.nextLine();
		
		//this if else statement determines the bigger of the two strings entered so it can check if the smaller is a substring of the larger and makes a linked list that 
		//holds the characters for each string entered. It also assigns them to another string variable to allow easy use of the contains() method.
		if (string1.length() > string2.length())
		{
			for (int j = 0; j < string1.length(); j++)
				largerStringAsList.add(string1.charAt(j));
			
			for (int i = 0; i < string2.length(); i++)
				smallerStringAsList.add(string2.charAt(i));
			
			biggerString = string1;
			smallerString = string2;
		}
		else
		{
			for (int j = 0; j < string2.length(); j++)
				largerStringAsList.add(string2.charAt(j));
			
			for (int i = 0; i < string1.length(); i++)
				smallerStringAsList.add(string1.charAt(i));
			
			biggerString = string2;
			smallerString = string1;
		}
		
		//this if statement checks if the smaller of the two strings is found inside the bigger of the two strings. If it is, it then goes to a for loop
		//that iterates through the bigger string. The if statement compares the character at index of i to the first character found in the smaller string, if that becomes equal,
		//it then compares the last character in the smaller string to the character at index i + the size of the smaller string to see if they are equal. 
		//if they are, then you have found at index i, that the smaller string is a substring of the larger. 
		if (biggerString.contains(smallerString))
		{
			for (int i = 0; i < largerStringAsList.size(); i++)
			{
				if (largerStringAsList.get(i) == smallerStringAsList.get(0) && largerStringAsList.get(i + (smallerStringAsList.size() - 1)) == smallerStringAsList.get(smallerStringAsList.size() - 1))
				{
					System.out.println("Index matched at " + i + " of string: " + biggerString);
				}
			}
		}
		
		input.close();
		
	}//end of main method
}//end of Exercise22_3 class

