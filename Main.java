/*
Stephen Young

Graphs — what are they good for? Poetry!
In this lab, we will explore a common Natural Language Processing technique known as N-gram.
This is a program that completes the poetry with bridge words.

Bridge words from corpus.txt will be applied to input.txt if
fitting words exist
 */
package assignment4;
import java.io.File;
import java.io.IOException;


public class Main {
    /**
     * Example program using GraphPoet.
     */


        /**
         * Generate example poetry.
         *
         * @param args unused
         * @throws IOException if a poet corpus file cannot be found or read
         */
        public static void main(String[] args) throws IOException {
            final GraphPoet nimoy = new GraphPoet(new File("corpus.txt"));
            System.out.println(nimoy.poem(new File("input.txt")));
        }
}
