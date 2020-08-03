//Cameron Hiatt
//7-31-2020


public class WordOccurence implements Comparable<WordOccurence>
{
	String word;
	Integer count;
	
	public WordOccurence(String word, Integer count)
	{
		this.word = word;
		this.count = count;
	}
	
	@Override
	public int compareTo(WordOccurence o) {
		
		return o.count.compareTo(count);
	}
	
	@Override /** Override the toString method in the */
	public String toString() {
		return word + "_" + count;
	}

}
