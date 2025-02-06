package src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Password {
    /**
     * Hashes the provided password using the SHA-256 algorithm.
     * 
     * @param password the password to be hashed
     * @return a hexadecimal string representing the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Attempts a brute-force attack to find the 6-digit number
     * 
     * @param targetHash the target hash to match
     * @return the 6-digit number that matches, or null if no match is found
     */
    public static String bruteForce6Digit(String targetHash) {
        for (int i = 1; i <= 999999; i++) {

            String number = String.format("%06d", i);
            String hashedNumber = hashPassword(number);

            if (hashedNumber.equals(targetHash)) {
                return number;
            }
        }
        return null;
    }

    /**
     * Checks if the given password is strong according to the following criteria:
     * 
     * <ul>
     * <li>Minimum length of 12 characters</li>
     * <li>At least one uppercase letter</li>
     * <li>At least one lowercase letter</li>
     * <li>At least one digit</li>
     * <li>No whitespace characters</li>
     * </ul>
     * 
     * @param password the password to check
     * @return true if the password is strong, false otherwise
     */
    public static boolean isStrongPassword(String password) {
        if (password.length() < 12) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasWhitespace = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (Character.isWhitespace(ch)) {
                hasWhitespace = true;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit && !hasWhitespace;
    }

    /**
     * Checks the strength of multiple passwords and stores the results in a
     * HashMap.
     *
     * @param passwords An ArrayList of passwords to be checked.
     * @return A HashMap where each password is mapped to a Boolean value:
     *         true if the password is strong, false otherwise
     */
    public static HashMap<String, Boolean> checkPasswordsList(ArrayList<String> passwords) {
        HashMap<String, Boolean> result = new HashMap<>();

        for (String password : passwords) {
            result.put(password, isStrongPassword(password));
        }

        for (Map.Entry<String, Boolean> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        return result;
    }

    public static void main(String[] args) {

        // Question 1
        System.out.println("\nQ1\n" + "-".repeat(20));
        String HashedPwd = "a97755204f392b4d8787b38d898671839b4a770a864e52862055cdbdf5bc5bee";
        String bruteForcedPwd = bruteForce6Digit(HashedPwd);
        if (bruteForcedPwd != null)
            System.out.println(bruteForcedPwd);
        else
            System.out.println("no result found");

        // Question 2
        System.out.println("\nQ2\n" + "-".repeat(20));
        System.out.println("Abc5          -> " + isStrongPassword("1234"));
        System.out.println("abcdef123456  -> " + isStrongPassword("abcdef123456"));
        System.out.println("AbCdEf123456  -> " + isStrongPassword("AbCdEf123456"));
        System.out.println("AbCdEf 123456 -> " + isStrongPassword("AbCdEf 123456"));

        // Question 3
        System.out.println("\nQ3\n" + "-".repeat(20));
        ArrayList<String> passwords = new ArrayList<>(List.of("Abc5", "abcdef123456", "AbCdEf123456", "AbCdEf 123456"));
        checkPasswordsList(passwords);

    }

}
