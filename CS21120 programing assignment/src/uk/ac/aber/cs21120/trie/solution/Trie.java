package uk.ac.aber.cs21120.trie.solution;

import uk.ac.aber.cs21120.trie.interfaces.IDictionary;

import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Trie implements IDictionary {

    Node rootNode = new Node(); //Makes the root node
    int countOfItems = 0; //Create a count of items

    /**
     * Adds a string to the Trie
     * @param s string to add
     */
    @Override
    public void add(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        Node currentNode = rootNode; //Pointer to current node
        for (int i = 0; i < bytes.length; i++){
            if(currentNode.children[bytes[i]] == null){
                Node child = new Node(); //Makes the new node to set the current node
                currentNode.children[bytes[i]] = child;
            }
            currentNode = currentNode.children[bytes[i]]; //Make the current node the one that was just added
        }
        if (!currentNode.isleaf){
            currentNode.isleaf = true;
            countOfItems++;
        }
    }

    /**
     * Looks for string in the Trie
     * @param s string to find
     * @return if last node in string is a leaf
     */
    @Override
    public boolean contains(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        Node currentNode = rootNode;
        for (int i = 0; i < bytes.length; i++) {
            if (currentNode.children[bytes[i]] == null) {
                return false;
            }
            currentNode = currentNode.children[bytes[i]];
        }
        return currentNode.isleaf;
    }

    /**
     * Deletes a string from the Trie
     * @param s string to delete
     */
    @Override
    public void delete(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        Node currentNode = rootNode;
        Boolean notFound = false; //Use to determine if the node
        for (int i = 0; i < bytes.length; i++) {
            if (currentNode.children[bytes[i]] == null) { //If there are no children nodes
                notFound = true;
                break;
            }
            currentNode = currentNode.children[bytes[i]];
        }
        if (!notFound && currentNode.isleaf) {
            currentNode.isleaf = false; //Removes word
            countOfItems--; //Makes the item count smaller

        }
    }

    /**
     * The number of words in the Trie
     * @return the number
     */
    @Override
    public int count() {
        return contents().size(); //Takes the size of the contents
    }

    /**
     * Displays the contents of the Trie
     * @return the list of words
     */
    @Override
    public List<String> contents() {
        Node currentNode = rootNode;
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<String> words = new ArrayList<String>();
        if (rootNode.isleaf){
            words.add("");
        }
        while(words.size() != countOfItems) { //Loops until all words are found
            String word = ""; //Sets current word to nothing
            addContents(words, currentNode, visited, word);
        }
        return words;
    }

    /**
     * Finds the contents of the Trie
     * @param words list of discovered words
     * @param currentNode the current letter being looked at
     * @param visited list of all visited nodes
     * @param word the current word being made
     */
    private void addContents(List<String> words, Node currentNode, List<Node> visited, String word){

        for (int i = 0; i < 256; i++) {

            if (currentNode.children[i] != null && !visited.contains(currentNode.children[i])){
                word = word + Character.toString((char)i); //Adds character to end of word string
                if(currentNode.children[i].isleaf){
                    if(!words.contains(word)) {
                        words.add(word); //Adds the word to the list if it is not already in there
                    }
                }
                currentNode = currentNode.children[i];
                i = 0; //Starts the loop again

            }
        }
        visited.add(currentNode); //Adds the current node to the visited list

    }
}
