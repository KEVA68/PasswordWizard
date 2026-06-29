/***************
 * Author = Kartik Jain
 * Date = 12th June 2026
 * Version 4
 * Description = The below program asks the user various questions and generates a password using this information. I/O is through pop-up windows.
 **************/


// Allows program to access libraries to take input from user via keyboard and to generate random numbers.
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.util.Random;

class javaPasswordv4 {
    // main method declared, so that compiler knows where to go after entering the class.
    public static void main(String[] args){
        passwordGenerator();
        return;
    }

    //Generalised method prints a question to user and takes String input, which is returned to call site.
    public static String inputString (String question) {
        String inputValue = JOptionPane.showInputDialog(question);
        return inputValue;
    }

    /* Reverse the order of the words in a string and separate them with underscores.
    For loop iterates through each word in the string.
    When a new word is found, the loop arguments are reset and the remaining words are iterated through again.
    */
    public static String swapWordsInAString (String words)
    {
        String word;
        String newWords = "";
        int totalLengthOfRemainingWords = words.length();

        for (int i = 0; i < totalLengthOfRemainingWords ; i++)
        {
            if (words.charAt(i) == ' ')
            {
                word = words.substring(0, i);
                newWords = "_" + word + newWords;
                words = words.substring(i+1, words.length());
                i = 0;
                totalLengthOfRemainingWords = words.length();
            }
        }

        newWords = words + newWords;

        return newWords;
    }

    // Asks a question, takes string input, checks it is in the spChar array and returns to call-site.
    // If not in array, ask to re-enter until valid.
    public static String inputSpecialChar (String question)
    {
        final String[] spCharArray = {"!","@","$","£","&",".","?"};
        String spChar = inputString(question);
        boolean valid = false;

        while (!valid)
        {
            for (int i = 0; i < spCharArray.length; i++)
            {
                if (spCharArray[i].equals(spChar))
                {
                    valid = true;
                }
            }
            if (!valid)
            {
                spChar = inputString("<html><p>" + question + "</p><p color='red'>Invalid character. Try again: </p></html>");
            }
        }

        return spChar;
    }

    // Extends a name with special characters (acting as placeholders), if it is too short.
    // Where too short means 'less than 3 characters long'.
    public static String lengthFixer(String name) {
        while (name.length() < 3) {
            name = name + "$";
        }
        return name;
    }

    // Outer method. Inputs all the components of the password, creates the password and outputs it.
    public static void passwordGenerator ()
    {
        String password;
        String[] pwComponents = new String[5];

        pwComponents = inputComponents();
        password = createPassword(pwComponents);
        outputPassword(password);

        return;
    }

    // Gathers all the information from user to assist creating a strong password.
    // Stores all the information in an array, which it returns to call-site.
    public static String[] inputComponents() {
        String fname = inputString("Hey!\nWhat is your first name?");
        String lname = inputString("What is your last name?");
        String favNum = inputString("What is your favourite number?");
        String spChar = inputSpecialChar("Choose one special character from the following list: !, @, $, £, &, ., ?");
        String food  = inputString("What is your favourite food item?");

        String[] pwComponents = {fname, lname, favNum, spChar, food};

        return pwComponents;
    }

    // Takes the array of user info as a parameter and generates the passsword. Order of info components is randomised.
    public static String createPassword(String[] userInfoComponents)
    {
        String password = "";

        userInfoComponents[0] = lengthFixer(userInfoComponents[0]);
        userInfoComponents[0] = userInfoComponents[0].substring(0, 3);
        userInfoComponents[1] = lengthFixer(userInfoComponents[1]);
        userInfoComponents[1] = userInfoComponents[1].substring(0,3).toUpperCase();

        userInfoComponents[4] = swapWordsInAString(userInfoComponents[4]);

        userInfoComponents = randomiseArrayComponentPositions(userInfoComponents);

        for (int i = 0; i < userInfoComponents.length; i++)
        {
            password = password + userInfoComponents[i];
        }

        return password;
    }

    // Prints the generated password to a new window (JFrame).
    public static void outputPassword(String password)
    {
        JFrame outputFrame = new JFrame("New Password Generated");
        outputFrame.setLayout(new BorderLayout());
        JComponent content = (JComponent) outputFrame.getContentPane();
        content.setBorder(new EmptyBorder(10, 10, 10, 10)); // outer padding
        outputFrame.setLocationRelativeTo(null); // This line centers the JFrame on the screen

        JLabel msg = new JLabel("Your Password is: ");
        outputFrame.add(msg, BorderLayout.NORTH);

        JTextField newPW = new JTextField(password);
        newPW.setEditable(false);
        newPW.setHorizontalAlignment(JTextField.CENTER);
        outputFrame.add(newPW, BorderLayout.CENTER);

        outputFrame.setVisible(true);
        outputFrame.setSize(300, 100);
        outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return;
    }

    // Switch the order of the items in a String array randomly.
    public static String[] randomiseArrayComponentPositions(String[] array)
    {
        String[] newArray = new String[array.length];
        int randomPosition;
        int[] generatedPositions = new int[array.length];

        for (int i = 0; i < array.length; i++)
        {
            generatedPositions[i] = 100; // Initialises all the array compartments to an invalid number.
        }

        for (int i = 0; i < array.length; i++)
        {
            randomPosition = generateRandomNumber(array.length);
            while (linearSearch(randomPosition, generatedPositions) != -1)
            {
                randomPosition = generateRandomNumber(array.length);
            }
            generatedPositions[i] = randomPosition;
            newArray[i] = array[randomPosition];
        }

        return newArray;
    }

    // Roll a virtual dice to generate a random number
    public static int generateRandomNumber(int bound)
    {
        Random dice = new Random();
        int diceThrow = dice.nextInt(bound);
        return diceThrow;
    }

    // Searching an integer array from left to right for a key value.
    // Returns index of first instance of key or -1 if not found.
    public static int linearSearch(int key, int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == key)
            {
                return i;
            }
        }
        return -1;
    }

}
