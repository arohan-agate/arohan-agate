// Get user input
// Check if the entered word is equal to the reverse of the word
// If the user enters the word "stop", we stop checking for palindromes.
// We can use a while loop so that the user is asked to enter words, until the word "stop" is entered
// When we are done checking if the entered word is a palindrome, we want to return something to the user


import java.util.Scanner; // we need to import Scanner because we will be getting user input
public class Palindrome // we create a class named Palindrome
{
    public static void main( String[] args ) // the main method header always needs to have this synatx, one thing that needs to be memorized
    {
        Scanner input = new Scanner(System.in); // creates a new2 Scanner instance, this means that we can now scan for user input
        System.out.print( "Enter a word: "); // Prints out "enter a word:", this sets us up to call for user input in the next line.
        String word = input.nextLine().toLowerCase(); // the String variable "word" holds what ever the user inputted, as a string.
        // that string is converted to lowercase. it is important that we convert the string to lowercase to ensure that capitalization does not mess up our palindrome checker.
        while (!word.equals( "stop" )) // we are declaring this while loop that will continuosuly ask the user for words, until they enter the word "stop", this is one of our requirements
        {                               // NOW ITS TIME TO GO CREATE OUR PALINDROME CHECKER METHOD
            isPalindrome( word ); // now we can call the method we just wrote in the main method, so we just write the name of the method and pass in the String var "word" as a parameter
                                    // from here, it will print out whether or not our word is a palindrome when we run it.
            System.out.print( "Enter a word: "); // now that we have checked if our word is a palindrome, we can prompt the user for another word
            word = input.nextLine().toLowerCase(); // and we will turn that word into all lowercase, and we repeat this whole process until the word entered is "stop"
        }
    }

    public static void isPalindrome( String word ) // before we even write any of the actual algorithm, we can write the method header. We can declare the method as void since we can
                                                    // print out the result instead of returning it. We are going to pass in the word inputted by the user, so we have one string parameter
    {   // now onto the actual algorithm. like we brainstormed earlier, we can simply test if a word is a palindrome by checking to see if the word is the same as its reverse
        String reverse = ""; // so we can declare an empty string and name it reverse
        for(int i = word.length(); i > 0; i--) // and now to actually find what the reverse of the string is, we can start off by declaring a for loop, that loops through from the end of
                                                // the word to the first letter of the word. this is why we are going to start our index at word.length and then end the loop when the index
                                                // has reached to be the at the first letter. then, we decrement the value of i by 1, meaning that we will move down each letter of the word,
                                                // by 1 letter at a time.
        {
            reverse += word.substring(i-1, i); // now we add the letter at the index of i-1 to the reverse. we add the letter at the index of i-1 intead of i to avoid an index out of bounds
                                                // error. now, each letter will be added to the string reverse, in a reversed order. once we are done looping through each letter, the String
                                                // reverse is equal to the reverse of the string we passed into the method as a parameter

        }
        if( word.equals(reverse) ) // now we will check if the original word is equal to the reversed word. we do this using an if statement. it is also important that we use .equals
        {
            System.out.println("Your word is a palindrome" ); // if the reversed word is equal to the original word, we print out ...
        }
        else // if the reversed word is not equal to the original word
        {
            System.out.println("Your word is not a palindrome" ); //its not a palindrome so we print ....
        }
    }
}









