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
	
	
}
