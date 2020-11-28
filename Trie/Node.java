package Trie;

import java.util.HashMap;

public class Node {
	public HashMap<Character, Node> childrens;
	public boolean endOfWord;
	
	public Node() {
		this.endOfWord = true;
		this.childrens = new HashMap<>();
	}
}
