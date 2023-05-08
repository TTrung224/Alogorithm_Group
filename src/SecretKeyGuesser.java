public class SecretKeyGuesser {
    public void start() {
        SecretKey key = new SecretKey();
        String guessingKey = "RRRRRRRRRRRRRRRR";

        int correctLetters = key.guess(guessingKey);
        if (correctLetters == -1){
            System.out.println("key length is not 16 or contains invalid characters");
            return;
        }

        // Check each letter in str
        for (int i = 0; i < guessingKey.length(); i++) {
            if(correctLetters == guessingKey.length()){
                break;
            }

            // Loop for each letter: R, M, I, T
            for(int j = 0 ; j < 3; j ++){
                String newStr = next(guessingKey, i);
                int newCorrectLetters = key.guess(newStr);

                if(newCorrectLetters < correctLetters){
                    break;
                }
                else if(newCorrectLetters > correctLetters){
                    guessingKey = newStr;
                    correctLetters = newCorrectLetters;
                    break;
                }else {
                    guessingKey = newStr;
                }
            }
        }
        System.out.println("I found the secret key. It is " + guessingKey);
    }
    static String next(String str, int index){
        char[] curr = str.toCharArray();
        curr[index] = charOf(order(curr[index]) + 1);

        return String.valueOf(curr);
    }
    static int order(char c) {
        if (c == 'R') {
            return 0;
        } else if (c == 'M') {
            return 1;
        } else if (c == 'I') {
            return 2;
        }
        return 3;
    }

    static char charOf(int order) {
        if (order == 0) {
            return 'R';
        } else if (order == 1) {
            return 'M';
        } else if (order == 2) {
            return 'I';
        }
        return 'T';
    }
}