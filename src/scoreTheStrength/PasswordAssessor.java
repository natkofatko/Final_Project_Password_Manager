package scoreTheStrength;

import java.util.regex.*;



/*

 * Method returns a score rating the strength of a password

 * With 0 indicating the weakest password and 7 the strongest possible

 *

 * The criteria include the following like 1) length above eight characters,

 * 2) use of non-repeating characters 3) use of non-repeating numbers

 * 4) use of both lower and uppercase

 * 5) and a mix of numbers, letters and special characters

 *

 * The method returns a score of 0 for unacceptable passwords like the following:

 * 1) password shorter than eight characters, 2) common passwords

 *

 * The list of common passwords comes from the following article:

 * http://fortune.com/2017/12/19/the-25-most-used-hackable-passwords-2017-star-wars-freedom/

 *

 * */



public class PasswordAssessor {

    private static String[] commonPasswords = {"123456", "Password", "12345678", "qwerty", "12345",

            "123456789", "letmein", "1234567", "football", "iloveyou",

            "admin", "welcome", "monkey", "login", "abc123", "starwars", "123123", "dragon",

            "passw0rd", "master", "hello", "freedom", "whatever", "qazwsx",

            "trustno1", "654321", "jordan23", "password1", "1234", "robert", "matthew",

            "jordan", "asshole", "daniel"};



    /**

     * Uses regex to search the password for numbers

     * @param password

     * @return boolean

     */

    private static boolean containsNumbers(String password) {

        Pattern numbers = Pattern.compile("\\d");

        Matcher matcher = numbers.matcher(password);

        if (matcher.find()) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Uses regex to search the password for special characters

     * @param password

     * @return boolean

     */

    private static boolean containsSpecialCharacters(String password) {

        Pattern specialCharacters = Pattern.compile("\\W");

        Matcher matcher = specialCharacters.matcher(password);

        if (matcher.find()) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Uses regex to search the password for letters, including uppercase and lowercase letters

     * @param password

     * @return boolean

     */

    private static boolean containsLetters(String password) {

        Pattern letters = Pattern.compile("(?i)[a-z]");

        Matcher matcher = letters.matcher(password);

        if (matcher.find()) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Uses regex to search password to see whether it contains both upper and lowercase letters

     * @param password

     * @return boolean

     */

    private static boolean containsUpperandLowerCase(String password) {

        Pattern upperCase = Pattern.compile("[A-Z]");

        Pattern lowerCase = Pattern.compile("[a-z]");

        Matcher matcher = upperCase.matcher(password);

        Matcher matcher2 = lowerCase.matcher(password);

        if (matcher.find() && matcher2.find()) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Uses regex to search the password for consecutive integers like "123" or "987"

     * @param password

     * @return boolean

     */

    private static boolean hasConsecutiveNumbers(String password) {
        Pattern numbersAscending = Pattern.compile("(098|987|876|765|654|543|432|321|210)");
        Pattern numbersDescending = Pattern.compile("(123|234|345|456|567|678|789|890)");
        Matcher matcher = numbersAscending.matcher(password);
        Matcher matcher2 = numbersDescending.matcher(password);
        if (matcher.find() || matcher2.find()) {

            return true;

        } else {

            return false;

        }

    }







    /**

     * Uses regex to search for passwords for letters that repeat three times or more (like "aaa", "999" or "@@@")

     * @param password

     * @return boolean

     */

    private static boolean hasRepeatingCharacters(String password) {

        Pattern letters = Pattern.compile("(.)\\1{2,}");

        Matcher matcher = letters.matcher(password);

        if (matcher.find()) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Uses regex to match the password with elements inside the commonPasswords array

     * @param password

     * @return boolean

     */

    private static boolean isCommonPassword(String password) {

        for (String commonPassword : commonPasswords) {

            if (password.equals(commonPassword)) {

                return true;

            }

        }

        return false;

    }



    /**

     * Checks to see whether the length of a password is eight or longer

     * @param password

     * @return

     */

    private static boolean eightOrLonger (String password) {

        if (password.length() >= 8) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Checks whether the length of a password is 12 or longer

     * @param password

     * @return boolean

     */

    private static boolean twelveOrLonger (String password) {

        if (password.length() >= 12) {

            return true;

        } else {

            return false;

        }

    }



    /**

     * Method adds one to variable passwordScore for meeting certain criteria

     * Assigns 0 for unacceptable passwords (shorter than eight characters, matches common passwords)

     * @param password

     * @return int

     */

    public static int assessPassword(String password) {

        int passwordScore = 0;



        if (containsLetters(password)

                && containsNumbers(password)

                && containsSpecialCharacters(password)) {

            passwordScore = 1;

        }



        if (containsUpperandLowerCase(password)) {

            passwordScore += 1;

        }



        if (hasRepeatingCharacters(password) == false) {

            passwordScore += 1;

        }



        if (hasConsecutiveNumbers(password) == false) {

            passwordScore += 1;

        }



        if (isCommonPassword(password)) {

            passwordScore = 0;

        } else {

            passwordScore += 1;

        }



        //length

        if (eightOrLonger(password) == true) {

            passwordScore += 1;

        } else {

            passwordScore = 0;

        }



        if (twelveOrLonger(password) == true) {

            passwordScore += 1;

        }




        return passwordScore;

    }


    public static String passwordStrength(String password) {

        PasswordAssessor pa= new PasswordAssessor();

        if (pa.assessPassword(password) < 3) {

            return "Weak password";

        } else if (pa.assessPassword(password) >= 3 && pa.assessPassword(password) < 5) {

            return "Medium password";

        } else {

            return "Strong password";

        }

    }

}