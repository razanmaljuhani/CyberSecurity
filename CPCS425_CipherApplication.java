//CPCS 425 - Homework1 : Cipher Application
//Razan Muhammed Aljuhani - GAR 
package razan_cpcs425_ass1;

import java.util.*;

public class Razan_CPCS425_ASS1 {

//************************************************** Main *************************************************
//*********************************************************************************************************    
    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------------");
        System.out.println("------------ CPCS 425 Homework 1: Cipher Application ------------");
        System.out.println("------------- Razan Mohammed Aljuhani GAR -------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");

        Scanner scanner = new Scanner(System.in); //to read input from the user.
        System.out.println(">>> Enter a Sample of input (Type \".\" to stop): ");

        String userInput = ""; //the input fron user.
        //loop: For allow user to enter multiple lines 
        while (scanner.hasNext()) {
            userInput = userInput + scanner.nextLine();
            if (userInput.contains(".")) {
                break;//if enter (.) then stop, and take the user input
            }//end if
        }//end loop
        userInput = userInput.replace(".", ""); //1. Remove dot from the line.
        userInput = userInput.trim(); //2. Remove any leading, trailing whitespace from the line.

        if (userInput.length() >= 4) {
            String cipherText = Encryption(userInput); //call of Encryption method.
            System.out.println("");
            System.out.println("-------------------------Encryption------------------------------");
            System.out.println("Encryption Input Line: " + userInput);
            System.out.println("Encryption Output Line: " + cipherText);

            String OriginalText = Decryption(cipherText); //call of Decryption method.
            System.out.println("");
            System.out.println("-------------------------Decryption------------------------------");
            System.out.println("Decryption Input Line: " + cipherText);
            System.out.println("Decryption Output Line: " + OriginalText);
        } else {
            System.out.println("The input length is less than 4 character");
        }

    }//End main.

//******************************************* Encryption method *******************************************
//*********************************************************************************************************
    public static String Encryption(String userInput) {

        String ciphered = ""; //for the result of cipher text after applay each steps
        int lengthOfUserInput = userInput.length(); //length of user input

        //2. convert all letters in the user input to uppercase.
        userInput = userInput.toUpperCase();

        //3. Character substitutions:
        for (int i = 0; i < userInput.length(); i++) {
            userInput = userInput.replace("A", "@").replace("E", "=").replace("I", "!").replace("J", "?").replace("O", "*").replace("P", "#").
                    replace("R", "&").replace("S", "$").replace("T", "+").replace("V", "^").replace("X", "%").replace(" ", "_");
        }

        int fristPart; //frist part of string.
        if (lengthOfUserInput % 2 == 0) {
            fristPart = (lengthOfUserInput / 2); //when the length of input = even number.
        } else {
            fristPart = (lengthOfUserInput / 2) + 1; //when the length of input = odd number.
        }
        //------------------------------------------------------------------------------------------------------------------------------------
        //4. Move the first half of the string to be the last half.
        String section1 = userInput.substring(0, fristPart);
        String section2 = userInput.substring(fristPart, lengthOfUserInput);
        String arrange1 = section2 + section1;
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 4 Output line: " + arrange1);

        //------------------------------------------------------------------------------------------------------------------------------------
        //5. Swap the first 2 characters of the line with the last two characters.
        String fristTwoCharacters = arrange1.substring(0, 2);
        String middleCharachters = arrange1.substring(2, lengthOfUserInput - 2);
        String lastTwoCharachters = arrange1.substring(lengthOfUserInput - 2, lengthOfUserInput);
        String arrange2 = lastTwoCharachters + middleCharachters + fristTwoCharacters;
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 5 output line: " + arrange2);

        //------------------------------------------------------------------------------------------------------------------------------------
        //6. Swap the two characters immediately to the left of the middle of the string with the two characters that immediately follow them.
        String fristRest = arrange2.substring(0, fristPart - 2);
        String fristTowMiddle = arrange2.substring(fristPart - 2, fristPart);
        String lastTwoMiddle = arrange2.substring(fristPart, fristPart + 2);
        String lastRest = arrange2.substring(fristPart + 2, lengthOfUserInput);
        ciphered = fristRest + lastTwoMiddle + fristTowMiddle + lastRest;
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 6 output line: " + ciphered);

        return ciphered;
    }//End of encryption method.

//******************************************** Decryption method ******************************************
//*********************************************************************************************************
    public static String Decryption(String userInput) {

        String plainText = ""; //for the result of plain text after applay each steps
        int lengthOfUserInput = userInput.length(); //length of user input

        int fristPart; //frist part of string.
        if (lengthOfUserInput % 2 == 0) {
            fristPart = (lengthOfUserInput / 2); //when the length of input = even number.
        } else {
            fristPart = (lengthOfUserInput / 2) + 1; //when the length of input = odd number.
        }

        //------------------------------------------------------------------------------------------------------------------------------------
        //2. Swap the two characters immediately to the left of the middle of the string with the two characters that immediately follow them.
        String fristRest = userInput.substring(0, fristPart - 2);
        String fristTowMiddle = userInput.substring(fristPart - 2, fristPart);
        String lastTwoMiddle = userInput.substring(fristPart, fristPart + 2);
        String lastRest = userInput.substring(fristPart + 2, lengthOfUserInput);
        String arrange1 = fristRest + lastTwoMiddle + fristTowMiddle + lastRest;
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 2 output line: " + arrange1);

        //------------------------------------------------------------------------------------------------------------------------------------
        //3. Swap the first 2 characters of the line with the last two characters.
        String fristTwoCharacters = arrange1.substring(0, 2);
        String middleCharachters = arrange1.substring(2, lengthOfUserInput - 2);
        String lastTwoCharachters = arrange1.substring(lengthOfUserInput - 2, lengthOfUserInput);
        String arrange2 = lastTwoCharachters + middleCharachters + fristTwoCharacters;
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 3 output line: " + arrange2);

        //------------------------------------------------------------------------------------------------------------------------------------
        //4. Move the first half of the string to be the last half.
        String section1 = arrange2.substring(0, fristPart);
        String section2 = arrange2.substring(fristPart, lengthOfUserInput);
        plainText = section2 + section1;
        //this condition when the length equal odd number .
        if (lengthOfUserInput % 2 == 1) {
            section1 = arrange2.substring(0, fristPart - 1);
            section2 = arrange2.substring(fristPart - 1, lengthOfUserInput);
            plainText = section2 + section1;
        }
        //System.out.println("-------------------------------------------------------");
        //System.out.println("Step 4 Output line: " + plainText);

        //------------------------------------------------------------------------------------------------------------------------------------
        //5. Character substitutions:
        for (int i = 0; i < plainText.length(); i++) {
            plainText = plainText.replace("@", "A").replace("=", "E").replace("!", "I").replace("?", "J").replace("*", "O").replace("#", "P").
                    replace("&", "R").replace("$", "S").replace("+", "T").replace("^", "V").replace("%", "X").replace("_", " ");
        }
        //System.out.println("-------------------------------------------------");
        //System.out.println("Step 5 output line : " + plainText);

        //------------------------------------------------------------------------------------------------------------------------------------
        //2. convert all letters in the plain text to lowercase.
        plainText = plainText.toLowerCase();
        //System.out.println("Step 6 Output line: " + plainText);
        return plainText;
    } //End of decryption method.

}//End of class.
