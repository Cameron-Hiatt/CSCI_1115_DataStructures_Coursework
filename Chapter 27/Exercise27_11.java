//Author: Cameron Hiatt
//Date: 9-3-2020

//Changes made: I added lines 25-29 to complete the method

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Exercise27_11 {
  public static void main(String[] args) {
    Set<String> set = new HashSet<String>(); 
    set.add("Smith");
    set.add("Anderson");
    set.add("Lewis");
    set.add("Cook"); 
    set.add("Smith");
    
    ArrayList<String> list = setToList(set);
    System.out.println(list);
  }
  
  public static <E> ArrayList<E> setToList(Set<E> s) {
    
	ArrayList<E> a = new ArrayList<>();
   
    a.addAll(s);
   
    return a;
  }
}