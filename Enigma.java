/** Class modelling an Enigma machine
 *
 * Each Enigma contains a RotorSet consisting of 3 Scramblers (both defined in Cyclometer.h), a Reflector,
 *      and a Plugboard.
 * The Enigma encrypts a string using the current state of its rotors and plugboard as the key.
 */
public class Enigma {

    public static RotorSet.Scrambler I = new RotorSet.Scrambler("KPTYUELOCVGRFQDANJMBSWHZXI", "I");
    public static RotorSet.Scrambler II = new RotorSet.Scrambler("UPHZLWEQMTDJXCAKSOIGVBYFNR", "II");
    public static RotorSet.Scrambler III = new RotorSet.Scrambler("QUDLYRFEKONVZAXWHMGPJBSICT", "III");

    public static RotorSet orderOne = new RotorSet(I, II, III);
    public static RotorSet orderTwo = new RotorSet(I, III, II);
    public static RotorSet orderThree = new RotorSet(II, I, III);
    public static RotorSet orderFour = new RotorSet(II, III, I);
    public static RotorSet orderFive = new RotorSet(III, I, II);
    public static RotorSet orderSix = new RotorSet(III, II, I);

    RotorSet rotors;
    private RotorSet.Reflector reflector = new RotorSet.Reflector("GEKPBTAUMOCNILJDXZYFHWVQSR");
    private Plugboard plugboard;

    public Enigma(RotorSet r, Plugboard p){
       this.rotors = r;
       this.plugboard = p;
    }

    void changeRotorOrder(RotorSet r){
        this.rotors = r;
    }

    /** Encrypts a character with the plugboard, 3 Scramblers in the "inward" direction and the Reflector
     *
     * @param c the character to be encrypted
     * @return the encrypted character after the Reflector.
     */
    public char encryptLeft(char c){
        char newc = plugboard.encrypt(c);
        newc = rotors.getRight().encryptLeft(newc);
        newc = rotors.getMiddle().encryptLeft(newc);
        newc = rotors.getLeft().encryptLeft(newc);
        newc = reflector.encrypt(newc);
        return newc;
    }

    /** Encrypts a character with 3 Scramblers in the "outward" direction and the plugboard.
     *
     * @param c the character to be encrypted
     * @return the encrypted character after the Scrambler in thr right-most slot.
     */
    public char encryptRight(char c){
        char newc = rotors.getLeft().encryptRight(c);
        newc = rotors.getMiddle().encryptRight(newc);
        newc = rotors.getRight().encryptRight(newc);
        newc = plugboard.encrypt(newc);
        return newc;
    }

    /** Fully encrypts the string by calling encryptLeft() and encryptRight() on each character
     *
     * This method also converts the message into a string of the form "THISISANEXAMPLE"
     * Where every letter is capital, and there are no non-letter characters.
     *
     * @param message the string to be encrypted
     * @return the encrypted string after the complete encryption process
     */
    public String encrypt(String message){
        String text = message.toUpperCase().
                replaceAll( "\\s", "" ).replaceAll("[^a-zA-Z\\s]", "" ).
                replaceAll("[0-9]","");

        StringBuilder sb = new StringBuilder(text.length());

        for (char c : text.toCharArray()){
            this.rotors.rotate();
            char newc = encryptRight(encryptLeft(c));
            sb.append(newc);
        }
        return sb.toString();
    }

}
