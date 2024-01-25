import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Node {
    public Node[] next;
    public boolean word;

    public Node() {
        next = new Node[27];
        word = false;
    }
}

class T9 {
    private Node root;

    public T9() {
        root = new Node();
    }

    private int charToCode(char c) {
        if (c < 'a' || (c > 'z' && c != 'ö')) {
            return -1; // Return -1 for invalid characters
        }
        return c - 'a';
    }

    private int keyToIndex(char key) {
        return key - '1';
    }

    private char codeToChar(int code) {
        if (code == 26) return 'ö';
        return (char) ('a' + code);
    }

    private char charToKey(char c) {
        String[] keyMappings = {"abc", "def", "ghi", "jkl", "mno", "qrs", "tuv", "xyz", "åäö"};
        for (int i = 0; i < keyMappings.length; i++) {
            if (keyMappings[i].indexOf(c) != -1) {
                return (char) ('1' + i);
            }
        }
        return '0'; // Invalid character
    }

    public String wordToKeySequence(String word) {
        StringBuilder sequence = new StringBuilder();
        for (char c : word.toCharArray()) {
            sequence.append(charToKey(c));
        }
        return sequence.toString();
    }

    public void add(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            if (c < 'a' || (c > 'z' && c != 'ö' && c != 'ä' && c != 'å')) {
                System.out.println("Invalid character in word: " + word);
                return; // Skip this word
            }
            int index = charToCode(c);
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            current = current.next[index];
        }
        current.word = true;
    }

    public List<String> search(String sequence) {
        List<String> results = new ArrayList<>();
        searchHelper(root, "", sequence, 0, results);
        return results;
    }

    private void searchHelper(Node node, String currentWord, String sequence, int index, List<String> results) {
        if (node == null) return;
    
        if (index == sequence.length()) {
            if (node.word) {
                results.add(currentWord);
            }
            return;
        }
    
        int keyIndex = keyToIndex(sequence.charAt(index));
        for (int i = 0; i < 3; i++) {
            char nextChar = (char) ('a' + keyIndex * 3 + i);
            int charCode = charToCode(nextChar);
            if (charCode == -1) continue; // Skip invalid characters
            searchHelper(node.next[charCode], currentWord + nextChar, sequence, index + 1, results);
        }
    }
    

    public static void populateTrieFromFile(T9 trie, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                trie.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T9 trie = new T9();

        populateTrieFromFile(trie, "kelly.txt");

        // Test cases
        String[] testWords = {"miljard", "Anna", "du", "hela", "femte", "om"};
        for (String word : testWords) {
            String encoded = trie.wordToKeySequence(word);
            List<String> decodedWords = trie.search(encoded);
            System.out.println(encoded + ": " + decodedWords);
        }
        String testing = "3241";
        List<String> words = trie.search(testing);
        System.out.println(testing+ ": "  + words);



    }
}

