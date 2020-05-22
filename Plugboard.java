/** Class modelling an Enigma plugboard, in which 6 pairs of characters are plugged together.
 *
 * The plugboard forms one step of the encryption process of an Enigma machine, in which a letter is encrypted as
 *      its pair if it is plugged. This happens both before and after the rotor encryptions.
 */
public class Plugboard {

    /** Class modelling a pair of characters that have been 'plugged' together on an Enigma plugboard
     *
     * When two characters are 'plugged' together, they can be encrypted as each other with the plugboard.
     */
    public static class LetterPair {
        private char one;
        private char two;

        public LetterPair(){
            this.one = 'A';
            this.two = 'B';
        }

        public LetterPair(char one, char two) {
            this.one = one;
            this.two = two;
        }

        public char getOne(){
            return this.one;
        }

        public char getTwo(){
            return this.two;
        }

        public void setOne(char c){
            this.one = c;
        }

        public void setTwo(char c){
            this.two = c;
        }

        /** @return true if either this.one or this.two are equal to c
         *
         * @param c the character to be searched for
         */
        public boolean contains(char c) {
            return c == this.one || c == this.two;
        }

        /** Encrypts a character with its pair, assumes that contains() has already been called for the character
         *
         * @param c the character to encrypt
         * @return the encrypted character
         */
        public char switchLetter(char c) {
            if (c == this.one) {
                return this.two;
            } else {
                return this.one;
            }
        }
    }

    private LetterPair pair1;
    private LetterPair pair2;
    private LetterPair pair3;
    private LetterPair pair4;
    private LetterPair pair5;
    private LetterPair pair6;

    /** This constructor initialises an "empty" plugboard, where all the LetterPairs contain "self-plugged" letters.
     *  This means that no letters are swapped by the plugboard step of the encryption.
     */
    public Plugboard(){
        this.pair1 = new LetterPair('A', 'A');
        this.pair2 = new LetterPair('B', 'B');
        this.pair3 = new LetterPair('C', 'C');
        this.pair4 = new LetterPair('D', 'D');
        this.pair5 = new LetterPair('E', 'E');
        this.pair6 = new LetterPair('F', 'F');
    }

    /** This constructor enforces that each LetterPair param passed contains no repeated letters.
     *  i.e. if the user sets param 'pair1' to contain 'A', they must not set any other pair param to contain 'A'
     */
    public Plugboard(LetterPair pair1, LetterPair pair2, LetterPair pair3, LetterPair pair4, LetterPair pair5,
                     LetterPair pair6){
        this.pair1 = pair1;
        if (!pair1.contains(pair2.one) && !pair1.contains(pair2.two)){
            this.pair2 = pair2;
        }
        else {
            System.out.println("Please select unique plugboard connections with no overlapping letters");
            return;
        }
        if (!pair1.contains(pair3.one) && !pair1.contains(pair3.two) &&
                !pair2.contains(pair3.one) && !pair2.contains(pair3.two)){
            this.pair3 = pair3;
        }
        else {
            System.out.println("Please select unique plugboard connections with no overlapping letters");
            return;
        }
        if (!pair1.contains(pair4.one) && !pair1.contains(pair4.two) &&
                !pair2.contains(pair4.one) && !pair2.contains(pair4.two) &&
                !pair3.contains(pair4.one) && !pair3.contains(pair4.two)){
            this.pair4 = pair4;
        }
        else {
            System.out.println("Please select unique plugboard connections with no overlapping letters");
            return;
        }
        if (!pair1.contains(pair5.one) && !pair1.contains(pair5.two) &&
                !pair2.contains(pair5.one) && !pair2.contains(pair5.two) &&
                !pair3.contains(pair5.one) && !pair3.contains(pair5.two) &&
                !pair4.contains(pair5.one) && !pair4.contains(pair5.two)){
            this.pair5 = pair5;
        }
        else {
            System.out.println("Please select unique plugboard connections with no overlapping letters");
            return;
        }
        if (!pair1.contains(pair6.one) && !pair1.contains(pair6.two) &&
                !pair2.contains(pair6.one) && !pair2.contains(pair6.two) &&
                !pair3.contains(pair6.one) && !pair3.contains(pair6.two) &&
                !pair4.contains(pair6.one) && !pair4.contains(pair6.two) &&
                !pair5.contains(pair6.one) && !pair5.contains(pair6.two)){
            this.pair6 = pair6;
        }
        else {
            System.out.println("Please select unique plugboard connections with no overlapping letters");
            return;
        }
    }

    public LetterPair getPair1(){
        return this.pair1;
    }

    public LetterPair getPair2(){
        return this.pair2;
    }

    public LetterPair getPair3(){
        return this.pair3;
    }

    public LetterPair getPair4(){
        return this.pair4;
    }

    public LetterPair getPair5(){
        return this.pair5;
    }

    public LetterPair getPair6(){
        return this.pair6;
    }

    public void setPair1(LetterPair pair){
        this.pair1 = pair;
    }

    public void setPair2(LetterPair pair){
        this.pair2 = pair;
    }

    public void setPair3(LetterPair pair){
        this.pair3 = pair;
    }

    public void setPair4(LetterPair pair){
        this.pair4 = pair;
    }

    public void setPair5(LetterPair pair){
        this.pair5 = pair;
    }

    public void setPair6(LetterPair pair){
        this.pair6 = pair;
    }

    /** Checks if the character is a member of a LetterPair object in this Plugboard
     *  If so, @returns the encrypted character, otherwise @returns the original character
     *
     * @param c the character to be encrypted
     * @return the encrypted character
     */
    public char encrypt(char c) {
        if (pair1.contains(c)) {
            return pair1.switchLetter(c);
        } else if (pair2.contains(c)) {
            return pair2.switchLetter(c);
        } else if (pair3.contains(c)) {
            return pair3.switchLetter(c);
        } else if (pair4.contains(c)) {
            return pair4.switchLetter(c);
        } else if (pair5.contains(c)) {
            return pair5.switchLetter(c);
        } else if (pair6.contains(c)) {
            return pair6.switchLetter(c);
        } else {
            return c;
        }
    }
}


