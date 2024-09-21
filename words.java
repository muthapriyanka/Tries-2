//time complexity: O(n * k + n^l * k)
//space complexity: O(n * k + n^l)
import java.util.ArrayList;
import java.util.List;

public class words {

    // Define a TrieNode class
    class TrieNode {
        TrieNode[] children;
        List<String> startWith;

        public TrieNode() {
            children = new TrieNode[26];  // For 26 lowercase letters
            startWith = new ArrayList<>();
        }
    }

    // Build a Trie from the input words
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr.startWith.add(word);
                curr = curr.children[c - 'a'];
            }
        }
        return root;
    }

    // Find all words that start with the given prefix
    private List<String> allStartWith(TrieNode root, String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startWith;
    }

    // Global variable to store the result
    List<List<String>> result;

    // Method to find all word squares
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        List<String> li = new ArrayList<>();  // Temporary list for the current word square
        for (String word : words) {
            // Action: add the word to the current list
            li.add(word);
            // Recurse: backtrack to build word squares
            backtrack(root, li, word.length());
            // Backtrack: remove the last word to explore other options
            li.remove(li.size() - 1);
        }

        return result;
    }

    // Backtracking function to explore all possible word squares
    private void backtrack(TrieNode root, List<String> li, int l) {
        // Base case: if we have constructed a valid word square
        if (li.size() == l) {
            result.add(new ArrayList<>(li));
            return;
        }

        // Get the prefix for the next word in the word square
        int i = li.size();
        StringBuilder prefix = new StringBuilder();
        for (String liWord : li) {
            prefix.append(liWord.charAt(i));
        }

        // Find all words that start with the prefix
        List<String> allStartWith = allStartWith(root, prefix.toString());

        // Backtrack: try each word that starts with the prefix
        for (String word : allStartWith) {
            // Action: add the word to the current list
            li.add(word);
            // Recurse: try to complete the word square
            backtrack(root, li, l);
            // Backtrack: remove the word to explore other options
            li.remove(li.size() - 1);
        }
    }

    // Main method for testing the wordSquares function
    public static void main(String[] args) {
        // Test case
        String[] words = {"area", "lead", "wall", "lady", "ball"};

        // Creating an instance of the words class
        words solution = new words();

        // Calling the wordSquares method
        List<List<String>> result = solution.wordSquares(words);

        // Printing the result (list of word squares)
        for (List<String> square : result) {
            for (String word : square) {
                System.out.println(word);
            }
            System.out.println();  // Newline between word squares
        }
    }
}
