//Author: Cameron Hiatt
//Date: 8-21-2020
//Creating a GenericQueue through inheritance

import java.util.*;

public class GenericQueue<E> extends LinkedList
{
	public void enqueue(E e)
	{
		addLast(e);
	}
	
	public E dequeue()
	{
		return (E) removeFirst();
	}
	
	public int getSize()
	{
		return size();
	}
	
	@Override
	public String toString()
	{
		return super.toString(); 
	}
	
}