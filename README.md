This program models a German Army and Airforce Enigma machine. This work was done as part of my third year dissertation in my Computer Science undergraduate degree.

Dependencies to be able to use this program:
1. Make sure you have a Java compiler on your machine. 
	This can be downloaded at https://www.oracle.com/technetwork/java/javase/tech/index-jsp-139208.html
2. Add the compiler's path to the beginning of your PATH environment variable.
	The compiler's path should look something like this: C:\Program Files\Java\jdk1.6.0_27\bin;

Follow these steps to encrypt your own message using custom Enigma settings:
1. Open main.java in your favourite text editor
2. Configure the plugboard swaps by changing the LetterPair characters on lines 38 to 43.
	A letter may not be a member of more than one LetterPair. 
3. Configure the order of the Scramblers by changing 'orderOne' on line 45 to an order between One and Six.
	Rotor orders are specified on lines 13 to 18 in Enigma.java
4. Configure the rotation/offset of the Scramblers by changing the numbers on line 46. 
	These numbers correspond to indices in the alphabet, i.e 0 = A, 25 = Z.
5. Replace the text between the double quotes on lines 48 to 50 with the message you wish to encrypt.
6. Open a command line window and navigate to the Enigma\src folder
7. Type "javac main.java" and hit Enter
8. Type "java main" and hit Enter. Your encrypted message should be printed to the screen.
