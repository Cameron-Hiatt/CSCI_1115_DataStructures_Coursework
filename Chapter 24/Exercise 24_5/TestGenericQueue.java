//Author: Cameron Hiatt
//Date: 8-21-2020
//Testing a GenericQueue made through inheritance


public class TestGenericQueue 
{
	public static void main(String[] args)
	{
		GenericQueue<String> queue = new GenericQueue<>();
		
		queue.enqueue("Cameron");
		queue.enqueue("Magnus");
		queue.enqueue("Parker");
		
		System.out.println("After adding 3 names through enqueue: " + queue.toString());
		
		queue.enqueue("Noah");
		
		System.out.println("Adding a 4th name through enqueue: " + queue.toString());
		
		queue.dequeue();
		
		System.out.println("Running dequeue once: " + queue.toString());
		
		queue.dequeue();
		queue.dequeue();
		
		System.out.println("Running dequeue twice more: " + queue.toString());
	}
}
