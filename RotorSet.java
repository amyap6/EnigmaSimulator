/** Class representing a Rotor Set in an Enigma machine
 *
 *  The Rotor Set contains 3 Scrambler slots, and a Reflector.
 *  The rotor set can encrypt a character.
 */
public class RotorSet {

    /** Class modelling an Enigma Scrambling wheel.
     *
     * The Scrambler's wirings are represented by a substitution cipher, and the rotation by the offset.
     * The Scrambler can encrypt a character in two directions.
     */
    public static class Scrambler {

        private String substitutionCipher;
        private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private int offset = 0;
        public String name;
        private int ringSetting = 0;

        /** Default constructor initialises a Scrambler object with the identity cipher as its substitution cipher
         */
        public Scrambler(){
            this.substitutionCipher = alphabet;
        }

        public Scrambler(String cipher, String name){
            this.substitutionCipher = cipher;
            this.name = name;
        }

        public int getOffset(){
            return this.offset;
        }

        public int getRingSetting(){
            return this.ringSetting;
        }

        public void setOffset(int offset){
            if (offset < 0 || offset > 25){
                throw new IndexOutOfBoundsException("Integer out of range. Enter a value between 0 and 25");
            }
            else {
                this.offset = offset;
            }
        }

        public void setRingSetting(int ringSetting){
            if (ringSetting < 0 || ringSetting > 25){
                throw new IndexOutOfBoundsException("Integer out of range. Enter a value between 0 and 25");
            }
            else {
                this.ringSetting = ringSetting;
            }
        }

        /** When rotate is called on a Scrambler, its offset increases by 1.
         * When the increased offset is greater than 25, it is set back to 0.
         */
        void rotate(){
            if (this.offset < 25) {
                this.offset++;
            }
            else {
                this.offset = this.offset - 25;
            }
        }

        /** This encrypts the character by finding its index in the alphabet, finding which character is at this index
         *      in the Scrambler's substitution cipher, and returning this character.
         *
         * @param c the character to be encrypted
         * @return the encrypted character
         */
        char encryptLeft(char c){
            char newc;
            int index = ((int)c) - 65;
            if (index + this.offset < 26) {
                newc = this.substitutionCipher.charAt(index + this.offset);
            }
            else {
                newc = this.substitutionCipher.charAt((index + this.offset) - 26);
            }

            return newc;
        }

        /** This encrypts the character by finding its index in the Scrambler's substitution cipher, finding which character
         *      is at this index in the alphabet, and returning this character.
         *
         * @param c the character to be encrypted
         * @return the encrypted character
         */
        char encryptRight(char c){
            int index = this.substitutionCipher.indexOf(c);

            if (index - this.offset < 0){
                index = index - this.offset + 26;
            }
            else {
                index = index - this.offset;
            }

            return alphabet.charAt(index);
        }
    }

    /** Class modelling an Enigma reflector wheel
     *
     * The reflector contains physical wirings represented as a substitution cipher, and can encrypt a character.
     */

    public static class Reflector {

        private String substitutionCipher;

        Reflector(String cipher){
            this.substitutionCipher = cipher;
        }

        /** Encrypts a character by finding the index of the character in the alphabet, and returning the character at
         *      this index in the substitution cipher.
         *
         * @param c character to be encrypted
         * @return the encrypted character
         */
        char encrypt(char c){
            char newc;
            int index = ((int)c) - 65;
            newc = this.substitutionCipher.charAt(index);
            return newc;
        }

    }

    private Scrambler left;
    private Scrambler middle;
    private Scrambler right;

    public RotorSet(){
        this.left = new Scrambler();
        this.middle = new Scrambler();
        this.right = new Scrambler();
    }

    /** This constructor assumes that the user does not try to place the same scrambler in two different slots
     * i.e if param 'left' is Scrambler II, 'middle' or 'right' should not be II.
     */
    public RotorSet(Scrambler left, Scrambler middle, Scrambler right){
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public Scrambler getLeft(){
        return this.left;
    }

    public Scrambler getMiddle(){
        return this.middle;
    }

    public Scrambler getRight(){
        return this.right;
    }

    /** Sets the offsets of each Scrambler
     *
     * @param offset1 the desired offset for the Scrambler in the left-most position
     * @param offset2 the desired offset for the Scrambler in the middle position
     * @param offset3 the desired offset for the Scrambler in the right-most position
     */
    public void setScramblers(int offset1, int offset2, int offset3){
        this.left.setOffset(offset1);
        this.middle.setOffset(offset2);
        this.right.setOffset(offset3);
    }

    /** Rotates the RotorSet unit.
     *
     *  The right-most scrambler rotates after every inputted character.
     *  The middle scrambler rotates when the right-most ring-setting is reached (every 26 letters)
     *  The left scrambler rotates when the middle ring-setting is reached (every 626 letters)
     */
    public void rotate(){
        boolean middleRotated = false;
        this.right.rotate();
        if (this.right.getOffset() == this.right.getRingSetting()){
            this.middle.rotate();
            middleRotated = true;
        }
        if (this.middle.getOffset() == this.middle.getRingSetting() && middleRotated){
            this.left.rotate();
        }
    }

}
