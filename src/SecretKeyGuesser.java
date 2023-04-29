public class SecretKeyGuesser {
    public void start() {
        SecretKey key = new SecretKey();
        String str = "RRRRRRRRRRRRRRRR";

        int correctLetters = key.guess(str);
        if (correctLetters == -1){
            System.out.println("secret key's length and guess key's length is not equal");
            return;
        }

        // Check each letter in str
        for (int i = 0; i < str.length(); i++) {
            if(correctLetters == str.length()){
                break;
            }

            // Loop for each letter: R, M, I, T
            for(int j = 0 ; j < 3; j ++){

                String newStr = next(str, i);
                int newCorrectLetters = key.guess(newStr);

                if(newCorrectLetters < correctLetters){
                    break;
                }
                else if(newCorrectLetters > correctLetters){
                    str = newStr;
                    correctLetters = newCorrectLetters;
                    break;
                }else {
                    str = newStr;
                }
            }
        }
        System.out.println("I found the secret key. It is " + str);
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

    // return the next value in 'RMIT' order, that is
    // R < M < I < T
    // public String next(String current) {
    //     char[] curr = current.toCharArray();
    //     for (int i = curr.length - 1; i >=0; i--) {
    //         if (order(curr[i]) < 3) {
    //             // increase this one and stop
    //             curr[i] = charOf(order(curr[i]) + 1);
    //             break;
    //         }
    //         curr[i] = 'R';
    //     }
    //     return String.valueOf(curr);
    // }
}