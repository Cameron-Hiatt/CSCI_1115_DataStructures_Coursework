/* 
Author: Cameron Hiatt
Date:	9-2-2020

Given: "4.5"      Expected: 51451
Given: "Hello"    Expected: 69609650
*/
public class Exercise27_09 { 
  public static void main(String[] args) {
    String s = "4.5";
    System.out.println(hashCodeForString(s));
    
    s = "Hello";
    System.out.println(hashCodeForString(s));
  }
  
  public static int hashCodeForString(String s) {
    
	  int hashCode = 0;
	  
	  for(int i = 0; i < s.length(); i++)
    {
    	hashCode = 31 * hashCode + s.charAt(i);
    }
	  return hashCode;
  }
}