import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		
		//implementation of HashMap allows code to run in O(N) time
		HashMap<String, Integer> map = new HashMap<>();
		
		int[] ret = new int[codons.length];
		
		//for loop adds codons to map (HashMap)
		//search map for current key, 
		//and if the map does not have it - add it to the map
		for (String str : codons) {
			if (!map.containsKey(str)) {
				map.put(str, 0);
			}
		}
		
		//original code here can remain the same with the use of a HashMap
		Iterator<Character> iter = strand.iterator();
		while (iter.hasNext()) {
			char a = iter.next();
			char b = 'z';     // not part of any real codon
			char c = 'z';
			if (iter.hasNext()) {
				b = iter.next();
			}
			if (iter.hasNext()) {
				c = iter.next();
			}
			String cod = "" + a + b + c;
			
			if (map.containsKey(cod)) {
				map.put(cod, map.get(cod) + 1);
			}
		}
		
		//loop obtains codon integers and adds them to the array being returned
		for (int i = 0; i < codons.length; i++) {
			ret[i] = map.get(codons[i]);
		}
		return ret;
	}
	}

