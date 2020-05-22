/**
 * This program implements an Enigma machine that is capable of encrypting a message.
 *
 * @author Amanda Pryor
 * Contact: agy17huu@uea.ac.uk
 *
 * @since 01/10/2019
 *
 */
public class main {

    /** Returns the string in a formatted way to improve readability and conform with radio operations standards on
     * which Enigma messages were sent. Returns a string with a space between every 5 letters
     *
     * @param s the string to be formatted
     * @return the formatted string
     */
    public static String formatOutput(String s){
        StringBuilder sb = new StringBuilder(s.length() + s.length()/5);
        int counter = 1;

        for (char c : s.toCharArray()){
            if (counter < 5){
                sb.append(c);
            }
            else if (counter == 5){
                counter = 0;
                sb.append(c).append(" ");
            }
            counter++;
        }

        return sb.toString();
    }

    public static void main(String[] args){

        Plugboard plugboard = new Plugboard(new Plugboard.LetterPair('E', 'P'),
                new Plugboard.LetterPair('T', 'C'),
                new Plugboard.LetterPair('N', 'V'),
                new Plugboard.LetterPair('R', 'Q'),
                new Plugboard.LetterPair('Z', 'D'),
                new Plugboard.LetterPair('W', 'X'));

        Enigma enigma = new Enigma(Enigma.orderOne, plugboard);
        enigma.rotors.setScramblers(8, 6, 14);

        System.out.println(formatOutput(enigma.encrypt("Here is an example message. The program will normalise this message" +
                "by converting all letters to capitals, removing punctuation and whitespace, and removing numbers like 23049. " +
                "The system will output the encrypted message as a formatted string.")));
    }


}
