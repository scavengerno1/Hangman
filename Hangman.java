import java.util.*;

class Hangman{
    public static void main(String[] args) {
      String[] words = {"profession", "dopamine", "java", "yellow" ,
      "follow", "pizza", "huge", "presidential", "expert", "oxymoron", "language", "unity", "probably", "define", "zoom", "calcium", "botox", "eggs", "hurricane", "humanity", "prison", "chinese", "superman"};
      // Pick random index of words array
      int randomWord = (int) (Math.random() * words.length);

      // Create an array to store already entered letters
      char[] letters = new char[words[randomWord].length()];
      int triesCount = 0;
      boolean guess = false;
      Scanner sc = new Scanner(System.in);
      System.out.print("How many tries do you need? ");
      int n = sc.nextInt();
      int tries = 0;

      do {
        if(triesCount == n){
          print("You could not guess the word. Try Again!"
          + " \nThe word was \"" + words[randomWord] + "\"");
          System.exit(0);
        }
        switch (letters(words[randomWord], letters)) {
          case 0:
            triesCount++;
            tries = triesCount;
            break;
          case 1:
            //triesCount++;
            break;
          case 2:
            break;
          case 3:
            guess = true;
          break;
        }
        print("\n" + triesCount + " of " + n + " tries used.");
      } while (!guess);
      print("_____________________________________________________");
      print("\nThe word was " + words[randomWord] + " and you guessed it!");
      print("*****************************************************");

      sc.close();
    }

    /* Hint user to enter a guess letter,
    returns 0 if letter entered is not in the word (counts as try),
    returns 1 if letter were entered 1st time (counts as try),
    returns 2 if already guessed letter was REentered,
    returns 3 if all letters were guessed */
    public static int letters(String word, char[] letters) {
      System.out.print("Enter a letter ");
      if (! printWord(word, letters))
        return 3;
        System.out.print(" :: ");
        Scanner scan = new Scanner(System.in);
        int emptyPosition = empty(letters);
        char input = scan.nextLine().charAt(0);
        if (inEnteredLetters(input, letters)) {
        System.out.println("\n" + input + " is already in the word");
        return 2;
      } else if (word.contains(String.valueOf(input))) {
          letters[emptyPosition] = input;
          return 1;
      } else {
          print("\n" + input + " is not in the word");
          return 0;
      }
    }

    // Print aray of underscore to hide the letters of the word
    public static boolean printWord(String word, char[] letters) {
    // Iterate through all letters in word
      boolean underScore = false;
      for (int i = 0; i < word.length(); i++) {
        char letter = word.charAt(i);
        // This checks if letter is being re-entered
        if (inEnteredLetters(letter, letters))
          System.out.print(letter); // If yes - print it
        else {
          System.out.print('_' + " ");
          underScore = true;
        }
      }
      return underScore;
    }

    // This boolean is to test if the word contains the letter entered
    public static boolean inEnteredLetters(char letter, char[] letters) {
      return new String(letters).contains(String.valueOf(letter));
    }

    // This method helps find the first empty position in array to be filled
    // Had to google this one
    public static int empty(char[] letters) {
      int i = 0;
      while (letters[i] != '\u0000') i++;
        return i;
    }

    public static void print(Object o){
      System.out.println(o);
    }
}
