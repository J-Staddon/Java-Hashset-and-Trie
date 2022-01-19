package uk.ac.aber.cs21120.trie.tests;

import uk.ac.aber.cs21120.trie.solution.HashSetDictionary;
import uk.ac.aber.cs21120.trie.solution.Trie;

import java.util.ArrayList;

public class Performance {


    public static void main(String[] args){
        int wordLength = 4;
        for (int i = 0; i < 14; i++) { //for each word length
            long totalTime = 0;
            WordList wordList = new WordList();
            ArrayList<String> words = wordList.getWordsOfLength(wordLength, 1000);
            for (int a = 0; a < 400; a++) { //Loops 400 times
                Trie trie = new Trie();
                long start = System.nanoTime(); //Starts the timer
                for (String word : words) {
                    trie.add(word);
                }
                long elapsedTime = System.nanoTime() - start; //Calculates the actual time
                totalTime = totalTime + elapsedTime; //Adds all the times together
            }

            System.out.println("\nTrie");
            System.out.println("Length of words: " + wordLength);
            System.out.println("Total time: " + totalTime + "\nAverage time: " + totalTime / 400); //Works out the average time
            totalTime = 0;

            for (int a = 0; a < 400; a++) { //Does the same but for the Hash Set
                HashSetDictionary hashSetDictionary = new HashSetDictionary();
                long start = System.nanoTime();
                for (String word : words) {
                    hashSetDictionary.add(word);
                }
                long elapsedTime = System.nanoTime() - start;
                totalTime = totalTime + elapsedTime;
            }

            System.out.println("\nhashSetDictionary");
            System.out.println("Length of words: " + wordLength);
            System.out.println("Total time: " + totalTime + "\nAverage time: " + totalTime / 400);
            wordLength++; //Increments the word length
        }
    }
}

