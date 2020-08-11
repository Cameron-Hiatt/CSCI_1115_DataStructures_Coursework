//Author: Cameron Hiatt
//Date Created: August 10th, 2020

import java.util.*;

public class Exercise22_1 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		LinkedList<Character> maxCharacterString = new LinkedList<>();
		LinkedList<Character> tempList = new LinkedList<>();
		String userInput = new String();
		
		System.out.print("Enter a string: ");
		userInput = input.next();
		
		for (int i = 0; i < userInput.length(); i++)
		{
			if (tempList.size() > 1 && userInput.charAt(i) <= tempList.getLast() && tempList.contains(userInput.charAt(i)))
			{
				tempList.clear();
			}
			
			tempList.add(userInput.charAt(i));
			
			if (tempList.size() > maxCharacterString.size())
			{
				maxCharacterString.clear();
				maxCharacterString.addAll(tempList);
			}
		}//end of userInput for loop
		
		
		for (Character j: maxCharacterString)
		{
			System.out.print(j);
		}//end of for-each loop 
		
		input.close();
		
	}//end of main method
}//end of Exercise22_1 class
