package uk.ac.aber.cs21120.trie.solution;

import uk.ac.aber.cs21120.trie.interfaces.IDictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HashSetDictionary implements IDictionary{

    private HashSet<String> hashSet;

    public HashSetDictionary() {
        hashSet = new HashSet<String>();
    }

    /**
     * Adds to the hash set
     * @param s string to add
     */
    @Override
    public void add(String s){
            hashSet.add(s);
    }

    /**
     * Looks for string in the hash set
     * @param s string to find
     * @return If the string is contained
     */
    @Override
    public boolean contains(String s){
        return hashSet.contains(s);
    }

    /**
     * Deletes a string from the hash set
     * @param s string to delete
     */
    @Override
    public void delete(String s){
            hashSet.remove(s);
    }

    /**
     * Looks at how big the hash set is
     * @return the size of the hash set
     */
    @Override
    public int count(){
        return hashSet.size();
    }

    /**
     * Gets all elements in the hash set
     * @return the strings
     */
    @Override
    public List<String> contents() {
        return new ArrayList<String>(hashSet);
    }
}
