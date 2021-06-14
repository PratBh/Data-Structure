import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Problems {
	//reverse words in a sentense. eg: 
	//ip:I am a girl  op:girl a am i
	
	String reverseSentence(String str) {
		String[] words = str.split("\\s+");
		StringBuffer sb=new StringBuffer();
		for(int i=words.length-1;i>=0;i--) {
			sb.append(words[i]);
			if(i!=0)
			sb.append(" ");
		}
		return sb.toString();
	}
	
	
	
	
	//Find permutations of a given string
	//https://www.youtube.com/watch?v=GuTPwotSdYw
	void findPermutations(String str) {
		int n=str.length();
		permuteUtil(str, 0, n-1);
	}
	
	void permuteUtil(String str,int start,int end) {
		if(start==end)
			System.out.println(str);
		else
			for(int i=start;i<=end;i++) {
				String curr=swapChar(str, start, i);
				permuteUtil(curr, start+1, end);
				str=swapChar(str, start, i);
			}
	}
	
	String swapChar(String str,int i,int j) {
		char[] ch=str.toCharArray();
		char c=ch[i];
		ch[i]=ch[j];
		ch[j]=c;
		return String.valueOf(ch);
	}
	
	
	
	//Print combinations of r elements in an array of size n
	//https://www.youtube.com/watch?v=7IQHYbmuoVU
	void printNumCombo(int []arr,int r) {
		int n=arr.length;
		int[]temp=new int[r];
		printNumComboUtil(arr, temp, 0, n-1, 0, r);
	}
	
	void printNumComboUtil(int []arr,int[]data,int start,int end,int index,int r) {
		if(index==r)
			System.out.println(data);
		else {
			for(int i=start;i<=end && end-i+1>=r-index;i++) {
				data[index]=arr[i];
				printNumComboUtil(arr, data, i+1, end, index+1, r);
			}
		}
	}
	
	//print all combinations or all sub sets of a given string
	void printStringCombo(String str) {
		String op="";
		printStringComboUtil(str, op);//if we want unique subsets then use a set and add all op to it
	}
	
	void printStringComboUtil(String ip,String op) {
		if(ip.length()==0) {
			System.out.println(op);
			return;
		}
		
		String op1=op;
		String op2=op+ip.charAt(0);
		String ip1=ip.substring(1);
		printStringComboUtil(ip1, op1);
		printStringComboUtil(ip1, op2);
	}
	
	
	//remove duplicate characters recursively
	//https://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
	 String removeDuplicates(String str)  
		{
			char last_removed = '\0';
			return removeUtil(str, last_removed);       
		}
	 
	 String removeUtil(String str, 
			char last_removed)
	{

		// If length of string is 1 or 0 
		if (str.length() == 0 || str.length() == 1)
			return str;

		// Remove leftmost same characters 
		// and recur for remaining  
		// string 
		if (str.charAt(0) == str.charAt(1))
		{
			last_removed = str.charAt(0);
			while (str.length() > 1 && str.charAt(0) == 
					str.charAt(1))
				str = str.substring(1, str.length());
			str = str.substring(1, str.length());
			return removeUtil(str, last_removed); 
		}

		// At this point, the first 
		// character is definiotely different  
		// from its adjacent. Ignore first 
		// character and recursively  
		// remove characters from remaining string 
		String rem_str = removeUtil(str.substring(
				1,str.length()), last_removed);

		// Check if the first character of 
		// the rem_string matches with  
		// the first character of the original string
		if (rem_str.length() != 0 && 
				rem_str.charAt(0) == str.charAt(0))
		{
			last_removed = str.charAt(0);

			// Remove first character
			return rem_str.substring(1,rem_str.length()); 
		} 

		// If remaining string becomes 
		// empty and last removed character 
		// is same as first character of 
		// original string. This is needed 
		// for a string like "acbbcddc" 
		if (rem_str.length() == 0 && last_removed == 
				str.charAt(0))
			return rem_str;

		// If the two first characters 
		// of str and rem_str don't match,  
		// append first character of str 
		// before the first character of 
		// rem_str
		return (str.charAt(0) + rem_str);
	}
	 
//	 A valid encoding of an array of words is any reference string s and array of indices indices such that:
//
//		 words.length == indices.length
//		 The reference string s ends with the '#' character.
//		 For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character
//		 is equal to words[i].
//		 Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
//
//		  
//
//		 Example 1:
//
//		 Input: words = ["time", "me", "bell"]
//		 Output: 10
//		 Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
//		 words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
//		 words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
//		 words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
//		 Example 2:
//
//		 Input: words = ["t"]
//		 Output: 2
//		 Explanation: A valid encoding would be s = "t#" and indices = [0].
//
//		  
//
//		 Constraints:
//
//		 1 <= words.length <= 2000
//		 1 <= words[i].length <= 7
//		 words[i] consists of only lowercase letters.
	 //https://leetcode.com/problems/short-encoding-of-words/solution/
	 
	 public int minimumLengthEncoding(String[] words) {
	      Set<String> good=new HashSet(Arrays.asList(words));
	      for(String word:words) {
	    	  for(int k=1;k<word.length();k++)
	    		  good.remove(word.substring(k));
	      }
	      
	      int len=0;
	      for(String word:good) {
	    	  len+=word.length()+1;
	      }
	      
	      return len;
	  }
	 
//	 We have a string S of lowercase letters, and an integer array shifts.
//
//	 Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a'). 
//
//	 For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
//
//	 Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
//
//	 Return the final string after all such shifts to S are applied.
//
//	 Example 1:
//
//	 Input: S = "abc", shifts = [3,5,9]
//	 Output: "rpl"
//	 Explanation: 
//	 We start with "abc".
//	 After shifting the first 1 letters of S by 3, we have "dbc".
//	 After shifting the first 2 letters of S by 5, we have "igc".
//	 After shifting the first 3 letters of S by 9, we have "rpl", the answer.
//	 Note:
//
//	 1 <= S.length = shifts.length <= 20000
//	 0 <= shifts[i] <= 10 ^ 9
	 //https://leetcode.com/problems/shifting-letters/solution/
	 
	 public String shiftingLetters(String S, int[] shifts) {
	        StringBuilder ans = new StringBuilder();
	        int X = 0;
	        for (int shift: shifts)
	            X = (X + shift) % 26;

	        for (int i = 0; i < S.length(); ++i) {
	            int index = S.charAt(i) - 'a';
	            ans.append((char) ((index + X) % 26 + 97));
	            X = Math.floorMod(X - shifts[i], 26);
	        }

	        return ans.toString();
	    }
	 
//	 Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names 
//	 from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. 
//	 If there are more than three products with a common prefix return the three lexicographically minimums products.
//
//			 Return list of lists of the suggested products after each character of searchWord is typed. 
//
//			  
//
//			 Example 1:
//
//			 Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
//			 Output: [
//			 ["mobile","moneypot","monitor"],
//			 ["mobile","moneypot","monitor"],
//			 ["mouse","mousepad"],
//			 ["mouse","mousepad"],
//			 ["mouse","mousepad"]
//			 ]
//			 Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
//			 After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
//			 After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
//			 Example 2:
//
//			 Input: products = ["havana"], searchWord = "havana"
//			 Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
//			 Example 3:
//
//			 Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
//			 Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
//			 Example 4:
//
//			 Input: products = ["havana"], searchWord = "tatiana"
//			 Output: [[],[],[],[],[],[],[]]
//			  
//
//			 Constraints:
//
//			 1 <= products.length <= 1000
//			 There are no repeated elements in products.
//			 1 <= Σ products[i].length <= 2 * 10^4
//			 All characters of products[i] are lower-case English letters.
//			 1 <= searchWord.length <= 1000
//			 All characters of searchWord are lower-case English letters.
	 
	 		class Trie{
	 			class Node{
	 				boolean isWord=false;
	 				List<Node>children=Arrays.asList(new Node[26]);
	 				char Data;
	 			};
	 			
	 			Node root,curr;
	 			
	 			List<String> resultBuffer;
	 			
	 			Trie() {
	 		        root = new Node();
	 		    }
	 			
	 			void insert(String word) {
	 				curr=root;
	 				for(char c:word.toCharArray()) {
	 					if(curr.children.get(c-'a')==null) {
	 						curr.children.set(c-'a',new Node());
	 						curr.Data=c;
	 					}
	 					curr=curr.children.get(c-'a');
	 				}
	 				curr.isWord=true;
	 			}
	 			
	 			List<String> getWordsStartingWith(String key){
	 				curr=root;
	 				resultBuffer=new ArrayList<String>();
	 				for(char c:key.toCharArray()) {
	 					if(curr.children.get(c-'a')==null)
	 						return resultBuffer;
	 					curr=curr.children.get(c-'a');
	 				}
	 				dfsWIthPrefix(curr, key);
	 				return resultBuffer;
	 			}
	 			
	 			void dfsWIthPrefix(Node curr, String word) {
	 				if(resultBuffer.size()==3)
	 					return;
	 				if(curr.isWord)
	 					resultBuffer.add(word);
	 				for(char c='a';c<='z';c++) {
	 					if(curr.children.get(c-'a')!=null)
	 						dfsWIthPrefix(curr.children.get(c-'a'), word+c);
	 				}
	 			}
	 		}
	 		
	 		public List<List<String>> suggestedProducts(String[] products, String searchWord) {
	 	        Trie trie=new Trie();
	 	       List<List<String>> result=new ArrayList<List<String>>();
	 	       for(String s:products)
	 	    	   trie.insert(s);
	 	       String prefix=new String();
	 	       for(char c:searchWord.toCharArray()) {
	 	    	   prefix +=c;
	 	    	   result.add(trie.getWordsStartingWith(prefix));
	 	       }
	 	       return result;
	 	    }
	 		
//	 		Design a data structure that supports adding new words and finding if a string matches any previously added string.
//
//	 		Implement the WordDictionary class:
//
//	 		WordDictionary() Initializes the object.
//	 		void addWord(word) Adds word to the data structure, it can be matched later.
//	 		bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.'
//	 		where dots can be matched with any letter.
//	 		 
//
//	 		Example:
//
//	 		Input
//	 		["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//	 		[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//	 		Output
//	 		[null,null,null,null,false,true,true,true]
//
//	 		Explanation
//	 		WordDictionary wordDictionary = new WordDictionary();
//	 		wordDictionary.addWord("bad");
//	 		wordDictionary.addWord("dad");
//	 		wordDictionary.addWord("mad");
//	 		wordDictionary.search("pad"); // return False
//	 		wordDictionary.search("bad"); // return True
//	 		wordDictionary.search(".ad"); // return True
//	 		wordDictionary.search("b.."); // return True
//	 		 
//
//	 		Constraints:
//
//	 		1 <= word.length <= 500
//	 		word in addWord consists lower-case English letters.
//	 		word in search consist of  '.' or lower-case English letters.
//	 		At most 50000 calls will be made to addWord and search.
	 		
	 		class WordDictionary {
	 		     class TrieNode {
	 			boolean endOfWord;
	 		    Map<Character, TrieNode> children;
	 		        public TrieNode() {
	 		            children = new HashMap<>();
	 		            endOfWord = false;
	 		        }
	 		    }

	 		    /** Initialize your data structure here. */
	 		    TrieNode root;
	 		    public WordDictionary() {
	 		        root = new TrieNode();
	 		    }
	 		    
	 		    public void addWord(String word) {
	 		        TrieNode curr = root;
	 		        
	 		        for(char ch: word.toCharArray()) {
	 		            TrieNode node = curr.children.get(ch);
	 		            if(node == null) {
	 		                node = new TrieNode();
	 		                curr.children.put(ch, node);
	 		            }
	 		            curr = node;
	 		        }
	 		        
	 		        curr.endOfWord = true;
	 		    }
	 		    
	 		    public boolean search(String word) {
	 		        return findMatch(root, word, 0);
	 		    }
	 		    
	 		    //Search recursively
	 		    private boolean findMatch(TrieNode node, String word, int index) {
	 		        if(index == word.length()) {
	 		            return node.endOfWord;
	 		        }
	 		        
	 		        if(word.charAt(index) == '.') {
	 		            for(char ch = 'a'; ch <= 'z'; ch++) {
	 		                if(node.children.get(ch) != null && findMatch(node.children.get(ch), word, index+1)) {
	 		                    return true;
	 		                }
	 		            }
	 		        } else {
	 		            char ch = word.charAt(index);
	 		            if(node.children.get(ch) != null && findMatch(node.children.get(ch), word, index+1)) {
	 		                return true;
	 		            }
	 		        }
	 		        
	 		        return false;
	 		    }
	 		}
	 		
//	 		You are given a string s that consists of lower case English letters and brackets. 
//
//	 		Reverse the strings in each pair of matching parentheses, starting from the innermost one.
//
//	 		Your result should not contain any brackets.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: s = "(abcd)"
//	 		Output: "dcba"
//	 		Example 2:
//
//	 		Input: s = "(u(love)i)"
//	 		Output: "iloveu"
//	 		Explanation: The substring "love" is reversed first, then the whole string is reversed.
//	 		Example 3:
//
//	 		Input: s = "(ed(et(oc))el)"
//	 		Output: "leetcode"
//	 		Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
//	 		Example 4:
//
//	 		Input: s = "a(bcdefghijkl(mno)p)q"
//	 		Output: "apmnolkjihgfedcbq"
//	 		 
//
//	 		Constraints:
//
//	 		0 <= s.length <= 2000
//	 		s only contains lower case English characters and parentheses.
//	 		It's guaranteed that all parentheses are balanced.
	 		
	 		public String reverseParentheses(String s) {
	 	       Stack<String> st=new Stack<String>();
	 	       String cur="";
	 	       for(int i=0;i<s.length();i++) {
	 	    	   if(s.charAt(i)=='(') {
	 	    		   st.push(cur);
	 	    		   cur="";
	 	    	   }else if(s.charAt(i)==')') {
	 	    		   cur=new StringBuffer(cur).reverse().toString();
	 	    		   cur=st.pop()+cur;
	 	    	   }else
	 	    		   cur+=s.charAt(i);
	 	       }
	 	       return cur;
	 	    }
	 		
//	 		Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
//
//	 				Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.
//
//	 				Clarification:
//
//	 				Confused why the returned value is an integer, but your answer is an array?
//
//	 				Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller.
//
//	 				Internally you can think of this:
//
//	 				// nums is passed in by reference. (i.e., without making a copy)
//	 				int len = removeDuplicates(nums);
//
//	 				// any modification to nums in your function would be known by the caller.
//	 				// using the length returned by your function, it prints the first len elements.
//	 				for (int i = 0; i < len; i++) {
//	 				    print(nums[i]);
//	 				}
//	 				 
//
//	 				Example 1:
//
//	 				Input: nums = [1,1,1,2,2,3]
//	 				Output: 5, nums = [1,1,2,2,3]
//	 				Explanation: Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
//	 				It doesn't matter what you leave beyond the returned length.
//	 				Example 2:
//
//	 				Input: nums = [0,0,1,1,1,1,2,3,3]
//	 				Output: 7, nums = [0,0,1,1,2,3,3]
//	 				Explanation: Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 
//	 				and 3 respectively. It doesn't matter what values are set beyond the returned length.
//	 				 
//
//	 				Constraints:
//
//	 				1 <= nums.length <= 3 * 104
//	 				-104 <= nums[i] <= 104
//	 				nums is sorted in ascending order.
	 		public int removeDuplicates(int[] nums) {
	 	       int prev=nums[0],count=1,len=nums.length;
	 	       for(int i=1;i<len;i++) {
	 	    	   if(prev==nums[i]) {
	 	    		   count++;
	 	    		   if(count>2) {
	 	    			   int j;
	 	    			  for(j=i+1;j<nums.length;j++) {
	 	    				  nums[j-1]=nums[j];
	 	    			  }
	 	    			  i--;
	 	    			  len--;
	 	    		   }
	 	    	   }else {
	 	    		   prev=nums[i];
	 	    		   count=1;
	 	    	   }
	 	       }
	 	       return len;
	 	    }
//	 		Given a sentence text (A sentence is a string of space-separated words) in the following format:
//
//	 			First letter is in upper case.
//	 			Each word in text are separated by a single space.
//	 			Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
//	 			If two words have the same length, arrange them in their original order.
//
//	 			Return the new text following the format shown above.
//
//	 			 
//
//	 			Example 1:
//
//	 			Input: text = "Leetcode is cool"
//	 			Output: "Is cool leetcode"
//	 			Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
//	 			Output is ordered by length and the new first word starts with capital letter.
//	 			Example 2:
//
//	 			Input: text = "Keep calm and code on"
//	 			Output: "On and keep calm code"
//	 			Explanation: Output is ordered as follows:
//	 			"On" 2 letters.
//	 			"and" 3 letters.
//	 			"keep" 4 letters in case of tie order by position in original text.
//	 			"calm" 4 letters.
//	 			"code" 4 letters.
//	 			Example 3:
//
//	 			Input: text = "To be or not to be"
//	 			Output: "To be or to be not"
//	 			 
//
//	 			Constraints:
//
//	 			text begins with a capital letter and then contains lowercase letters and single space between words.
//	 			1 <= text.length <= 10^5
	 		
	 		public String arrangeWords(String text) {
	 	        String[] textArray = text.toLowerCase().split(" "); 
	 	        
	 	        PriorityQueue<Word> queue = new PriorityQueue<>(
	 	            textArray.length,  // queue size wouldn't exceed the textArray's length
	 	            (a, b) -> a.val.length() == b.val.length()? a.index - b.index : a.val.length() - b.val.length()
	 	            // if the two words are of the same length, sort them by their indices
	 	        ); 
	 	        
	 	        // populate the queue
	 	        for (int i = 0; i < textArray.length; i++) { 
	 	            queue.add(new Word(textArray[i], i)); 
	 	        }
	 	        
	 	        StringBuilder sb = new StringBuilder(); 
	 	        
	 	        while (!queue.isEmpty()) {
	 	            String curr = queue.poll().val; 
	 	            sb.append(curr).append(" "); 
	 	        }
	 	        
	 	        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0))); 
	 	        
	 	        return sb.toString().trim();  // trim off the empty space at the very end
	 	    }
	 	    
	 	    class Word {
	 	        String val; 
	 	        int index; 
	 	        
	 	        Word(String val, int index) {
	 	            this.val = val; 
	 	            this.index = index;  // Adding an index to maintain the output order for words with same lengths
	 	        }
	 	    }
	 	    
//	 	   Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
//
//	 	  Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. 
//	 	  You must return the indices in increasing order.
//
//	 	   
//
//	 	  Example 1:
//
//	 	  Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
//	 	  Output: [0,1,4] 
//	 	  Explanation: 
//	 	  Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] 
//	 			  corresponding to the person with index 0. 
//	 	  Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and
//	 	  favoriteCompanies[1]=["google","microsoft"]. 
//	 	  Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
//	 	  Example 2:
//
//	 	  Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
//	 	  Output: [0,1] 
//	 	  Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"],
//	 	  therefore, the answer is [0,1].
//	 	  Example 3:
//
//	 	  Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
//	 	  Output: [0,1,2,3]
//	 	   
//
//	 	  Constraints:
//
//	 	  1 <= favoriteCompanies.length <= 100
//	 	  1 <= favoriteCompanies[i].length <= 500
//	 	  1 <= favoriteCompanies[i][j].length <= 20
//	 	  All strings in favoriteCompanies[i] are distinct.
//	 	  All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
//	 	  All strings consist of lowercase English letters only.
	 	    //https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/discuss/1019209/java

	 	   public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
	 	        List<Integer> result = new ArrayList();
	 	        List<Set<String>> sets = new ArrayList();
	 	        for(List<String> fav: favoriteCompanies) 
	 	            sets.add(new HashSet(fav));
	 	        Collections.sort(sets, (a,b)->(b.size()-a.size()));
	 	        for(int i=0;i<favoriteCompanies.size();i++) {
	 	            int valid =1;
	 	            for(int j=0;j<favoriteCompanies.size() && sets.get(j).size() > favoriteCompanies.get(i).size();j++) {
	 	                if(sets.get(j).containsAll(favoriteCompanies.get(i))){
	 	                    valid=0;
	 	                    break;
	 	                }
	 	            }
	 	            if(valid==1) result.add(i);
	 	        }
	 	        return result;
	 	    }
	 	   
//	 	  Given an array of words, print all anagrams together. For example, if the given array is
//	 	  {“cat”, “dog”, “tac”, “god”, “act”}, then output may be “cat tac act dog god”.
	 	   
	 	   public List<List<String>> findAnagram(String[]words){
	 		   HashMap<HashMap<Character, Integer>,List<String>> map=new HashMap<>();
	 		   for(String word:words) {
	 			   HashMap<Character,Integer> tmp=new HashMap<Character, Integer>();
	 			   char[]chars=word.toCharArray();
	 			   for(char c:chars)
	 				   tmp.put(c, tmp.getOrDefault(c, 0)+1);
	 			   if(map.containsKey(tmp))
	 				   map.get(tmp).add(word);
	 			   else {
	 				   List<String> tmpList=new ArrayList<String>();
	 				   tmpList.add(word);
	 				   map.put(tmp, tmpList);
	 			   }
	 		   }
	 		  List<List<String>> result=new ArrayList<>();
			   for(HashMap<Character,Integer> hm:map.keySet())
				   result.add(map.get(hm));
			   return result;
	 	   }
	 	   
//	 	  Given two integers N and K, the task is to find lexicographically Kth string of length N. If the number of possible strings of length N is
//	 	  less than K, print -1.
//
//	 	 Examples:
//
//	 	 Input: N = 3, K = 10
//	 	 Output: “aaj”
//	 	 Explanation: The 10th string in the lexicographical order starting from “aaa” is “aaj”.
//
//	 	 Input: N = 2, K = 1000
//	 	 Output: -1
//	 	 Explanation: A total of 26*26 = 676 strings of length 2 are possible. So the output will be -1.
	 	   
	 	  public String find_kth_String_of_n(int n, int k) {
	 		  if(k!=0)
	 			  k=k-1;
	 		  int[]d=new int[n];
	 		  for(int i=n-1;i>=0;i--) {
	 			  d[i]=(k)%26;
	 			  k=(k)/26;
	 		  }
	 		  if(k>0)
	 			  return "";
	 		  String s="";
	 		  for(int i=0;i<n;i++) {
	 			  s+=(char)(d[i]+'a');
	 		  }
	 		  return s;
	 		  
	 	  }
	 	  
//	 	 A happy string is a string that:
//
//	 		consists only of letters of the set ['a', 'b', 'c'].
//	 		s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
//	 		For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
//
//	 		Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
//
//	 		Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
//
//	 		 
//
//	 		Example 1:
//
//	 		Input: n = 1, k = 3
//	 		Output: "c"
//	 		Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
//	 		Example 2:
//
//	 		Input: n = 1, k = 4
//	 		Output: ""
//	 		Explanation: There are only 3 happy strings of length 1.
//	 		Example 3:
//
//	 		Input: n = 3, k = 9
//	 		Output: "cab"
//	 		Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba",
//	 		                                                              "cbc"]. You will find the 9th string = "cab"
//	 		Example 4:
//
//	 		Input: n = 2, k = 7
//	 		Output: ""
//	 		Example 5:
//
//	 		Input: n = 10, k = 100
//	 		Output: "abacbabacb"
	 	  
	 	 public String getHappyString(int n, int k) {
	         char[]chars= {'a','b','c'};
	         List<String> l1=new ArrayList<String>();
	         generateLexi(n, chars, l1, "", 0,true);
	         if(l1.size()-1<k-1)
	        	 return "";
	         return l1.get(k-1);
	     }
	 	 
	 	 void generateLexi(int n,char[]chars,List<String> l1,String op,int prev,boolean start) {
	 		 if(n==0) {
	 			 l1.add(op);
	 			 return;
	 		 }
	 		 for(int i=0;i<3;i++) {
	 			 if(start) {
	 				 generateLexi(n-1, chars, l1, op+chars[i], i,false);
	 			 }
	 			 else if(i!=prev) {
	 				 generateLexi(n-1, chars, l1, op+chars[i], i,false);
	 			 }
	 		 }
	 	 }
	 	 
	 	 //find kth lexicographically ordered string for a given string.
//	 	 e.g.---input ="abc" k=5
//	 			 output="abh"
	 	 
	 	 public String findKthLexical(int k,String str) {
	 		 int n=str.length();
	 		 int d[]=new int[n];
	 		 for(int i=n-1;i>=0;i--) {
	 			 d[i]=k%26;
	 			 k=k/26;
	 		 }
	 		 
	 		 if(k>0)
	 			 return "";
	 		 StringBuilder sb=new StringBuilder();
	 		 for(int i=0;i<n;i++) {
	 			 char c=str.toCharArray()[i];
	 			 int index=(d[i]+c-'a')%26;
	 			 sb.append((char)(index+'a'));
	 			// sb.append(((c+d[i])%26)'a');
	 		 }
	 		 return sb.toString();
	 	 }
	 	 
//	 	Given a string str and an integer K, the task is to reduce the string by applying the following operation any number of times until it is no
//	 	longer possible:
//
//	 		Choose a group of K consecutive identical characters and remove them from the string.
//
//	 		Finally, print the reduced string.
//
//	 		Examples:  
//
//	 		Input: K = 2, str = “geeksforgeeks” 
//	 		Output: gksforgks 
//	 		Explanation: After removal of both occurrences of the substring “ee”, the string reduces to “gksforgks”.
	 	 
	 	String reduced_String(int k, String s) {
	 		Stack<Map<Character,Integer>> st=new Stack<Map<Character,Integer>>();
	 		for(int i=0;i<s.length();i++) {
	 			if(st.size()==0) {
	 				Map<Character,Integer> mp=new HashMap<Character, Integer>();
	 				mp.put(s.charAt(i),1);
	 				st.push(mp);
	 			}else {
	 				if(st.peek().containsKey(s.charAt(i))) {
	 					Map<Character,Integer> mp=st.pop();
	 					mp.put(s.charAt(i), mp.get(s.charAt(i))+1);
	 					if(mp.get(s.charAt(i))==k)
	 						continue;
	 					else
	 						st.push(mp);
	 				}
	 				else {
	 					Map<Character,Integer> mp=new HashMap<Character, Integer>();
		 				mp.put(s.charAt(i),1);
		 				st.push(mp);
	 				}
	 			}
	 		}
	 		StringBuilder ans=new StringBuilder();
	 		while(!st.isEmpty()) {
	 			char c=' ';
	 			int count=0;
	 			for(Entry<Character, Integer>ent:st.peek().entrySet()) {
	 				c=ent.getKey();
	 				count=ent.getValue();
	 			}
	 			while(count-->0)
	 				ans.append(c);
	 			st.pop();
	 		}
	 		return ans.reverse().toString();
	 	}
	 	
//	 	Given a string, find the first non-repeating character in it. For example, if the input string is “GeeksforGeeks”, 
//	 	then the output should be ‘f’ and if the input string is “GeeksQuiz”, then the output should be ‘G’. 

	 	public Character firstNonRepeating(String str) {
	 		int []count=new int[256];
	 		Arrays.fill(count, -1);
	 		for(int i=0;i<str.length();i++) {
	 			if(count[str.charAt(i)]==-1)
	 				count[str.charAt(i)]=i;
	 			else
	 				count[str.charAt(i)]=-2;
	 		}
	 		
	 		int res=Integer.MAX_VALUE;
	 		for(int i=0;i<256;i++) {
	 			if(count[i]>=0) {
	 				res=Math.min(res, count[i]);
	 			}
	 		}
	 		return str.charAt(res);
	 	}
	 	
//	 	Given a fraction, find a recurring sequence of digits if it exists, otherwise, print “No recurring sequence”.
//
//	 	Examples:
//
//	 	Input  : Numerator = 8, Denominator = 3
//	 	Output : Recurring sequence is 6 
//	 	Explanation : 8/3 = 2.66666666.......  
//
//	 	Input : Numerator = 50, Denominator = 22
//	 	Output : Recurring sequence is 27
//	 	Explanation : 50/22 = 2.272727272..... 
//
//	 	Input : Numerator = 11, Denominator = 2
//	 	Output : No recurring sequence
//	 	Explanation : 11/2 = 5.5
	 	
	 	String fractionToDecimal(int numr, int denr) {
	 		String res="";
	 		int rem=numr%denr;
	 		Map<Integer,Integer>mp=new HashMap<Integer, Integer>();
	 		while((rem!=0)&&(!mp.containsKey(rem))) {
	 			mp.put(rem,res.length());
	 			rem=rem*10;
	 			int res_part=rem/denr;
	 			res+=String.valueOf(res_part);
	 			rem=rem%denr;
	 		}
	 		if(rem==0)
	 			return "";
	 		else if(mp.containsKey(rem)) {
				return res.substring(mp.get(rem));
			}
	 		return "";
	 	}
}

