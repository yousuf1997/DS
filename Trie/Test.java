package Trie;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Trie trie = new Trie();
		trie.addWord("Cat");
		trie.addWord("Bat");
		trie.addWord("Ball");
		trie.addWord("Rat");
		trie.addWord("Cap");
		trie.addWord("Be");
		trie.printTree();
		trie.deleteWord("Ball");
		
		System.out.println("--");
		trie.printTree();
		if(trie.isWordPresent("Ball")) {
			System.out.println("Present");
		}else {
			System.out.println("Not Present");
		}

	}

}
