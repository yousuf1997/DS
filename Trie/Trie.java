package Trie;

import java.util.Map;
/**
 * 
 * @author Mohamed Abdul
 *
 */
public class Trie {

	private Node root;
	
	public Trie() {
		 root = new Node();
		 root.endOfWord = true;
	}
	
	public void addWord(String word) {
		add(word.toUpperCase(), 0, root);
	}
	
	public void deleteWord(String word) {
		deleteWord(word.toUpperCase(), 0, root);
	}

	public boolean isWordPresent(String word) {
		return wordPresent(word.toUpperCase(), 0, root);
	}
	
	public void printTree() {
		print("", root);
	}
	
	public void printWordsBeginWith(String firstFewLetters) {
		System.out.println("Words Begin with " + firstFewLetters.toUpperCase() + " : ");
		printWordsBeginWith(firstFewLetters.toUpperCase(), "", 0, false, root);
	}
	//----------------------------------Helper Methods Section-----------------------------------
	private void add(String word, int index, Node parent) {
		if(index == word.length()) {
			parent.endOfWord = true;
			return;
		}
		parent.endOfWord = false;
		char currentCharacter = word.charAt(index);
		if(!parent.childrens.containsKey(currentCharacter)) 
			parent.childrens.put(currentCharacter, new Node());
		add(word, index + 1, parent.childrens.get(currentCharacter));
	}
	
	private void print(String currentString, Node parent) {
		if(parent.endOfWord) {
			System.out.println(currentString);
			return;
		}
		System.out.println(currentString);
		for(Map.Entry element: parent.childrens.entrySet()) 
			print(currentString + element.getKey().toString(), (Node) element.getValue());
	}
	
	private boolean wordPresent(String word, int index, Node parent) {
		if(index == word.length()) return parent.endOfWord;
		char currentCharacter = word.charAt(index);
		if(!parent.childrens.containsKey(currentCharacter)) 
			return false;
		return wordPresent(word, index + 1, parent.childrens.get(currentCharacter));
	}
	
	private boolean deleteWord(String word, int index, Node parent) {
		if(index == word.length()) return false;
	    char currentCharacter = word.charAt(index);
	    if(parent.childrens.containsKey(currentCharacter)) {
	    	//check if its the end of the word
	    	Node children = parent.childrens.get(currentCharacter);
	    	if(children.endOfWord) {
	    		//delete
	    		parent.childrens.remove(currentCharacter);
	    		return parent.childrens.size() == 0? true : false;
	    	}else {
	    		boolean result = deleteWord(word, index + 1, children);
	    		if(result) {
	    			//delete
	    			parent.childrens.remove(currentCharacter);
		    		return parent.childrens.size() == 0? true : false;
	    		}
	    	}
	    }
	    
		return false;
	}
	
	private void printWordsBeginWith(String words, String currentWord, int index, boolean sequenceCompleted, Node parent) {
		  if(parent.endOfWord) {
			  System.out.println(currentWord);
			  return;
		  }
		  if(index == words.length()) sequenceCompleted = true;
		  if(index < words.length()) {
			  char currentCharacter = words.charAt(index);
			  if(!parent.childrens.containsKey(currentCharacter) && !sequenceCompleted) 
				  return;
		      if(!sequenceCompleted && parent.childrens.containsKey(currentCharacter)) 
		    	  printWordsBeginWith(words, currentWord + currentCharacter, index + 1, 
		    			    sequenceCompleted, parent.childrens.get(currentCharacter));   
		  }else{
	    	  //traverse everything in the map  
	    	  HashMap<Character, Node> childrens = parent.childrens;
	    	  for(Map.Entry<Character, Node> entry : childrens.entrySet()) {
	    		   printWordsBeginWith(words, currentWord + entry.getKey().toString(), 
	    				   index, sequenceCompleted, entry.getValue());
	    	  }
	      }
	}
	
	
}
