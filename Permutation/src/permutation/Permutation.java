package permutation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
public class Permutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
        String word;
        Set<Character> distinctLetters = new HashSet<>();
        int n, r;
        BigInteger answer;
        do {
            System.out.print("Enter a word without repeatable letter : ");
            word = scanner.nextLine();
            distinctLetters.clear();
            for (int i = 0; i < word.length(); i++) {
                if (!distinctLetters.add(word.charAt(i))) {
                    System.out.println("The word should not have repeatable letter. Please enter a new word.");
                    break;
                }
            }
        } while (distinctLetters.size() != word.length());
        
        System.out.println("Distinct letters in the word: " + distinctLetters);
        System.out.print("Enter the number of letters to use (r): ");
        r = Integer.parseInt(scanner.nextLine());
        n = distinctLetters.size();
        answer = calculateNP(n, r);
        System.out.println("Number of distinct words (using " + r + " out of " + n + " letters) is: " + answer);
        
        System.out.print("Do you want to see the list of distinct words? (YES/NO): ");
String userInput = scanner.nextLine();
if (userInput.equalsIgnoreCase("YES")) {
printWords(distinctLetters, r);
}
}
private static void printWords(Set<Character> distinctLetters, int r) {
    List<Character> distinctLettersList = new ArrayList<>(distinctLetters);
    permute(distinctLettersList, new ArrayList<>(), r);
}

    private static int count = 0; // Declare the counter variable
    public static void permute(List<Character> distinctLettersList, List<Character> prefix, int r) {
        if (prefix.size() == r) {
            count++; // Increment the counter before printing the word
            System.out.println(count + ". " + prefix);
        }
        for (int i = 0; i < distinctLettersList.size(); i++) {
            List<Character> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(distinctLettersList.get(i));
            List<Character> remainingLetters = new ArrayList<>(distinctLettersList);
            remainingLetters.remove(i);
            permute(remainingLetters, newPrefix, r);
        }
    }


private static BigInteger calculateNP(int n, int r) {
    BigInteger nFactorial = factorial(n);
    BigInteger nMinusrFactorial = factorial(n - r);
    return nFactorial.divide(nMinusrFactorial);
}

private static BigInteger factorial(int num) {
    BigInteger factorial = BigInteger.valueOf(1);
    for (int i = 1; i <= num; i++) {
        factorial = factorial.multiply(BigInteger.valueOf(i));
    }
    return factorial;
}
}
