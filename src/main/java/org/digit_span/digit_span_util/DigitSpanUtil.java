package org.digit_span.digit_span_util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DigitSpanUtil {



    public static boolean checkForwardsSequence(ArrayList<Integer> userSequence, ArrayList<Integer> randomizedSequence) {
        return userSequence.equals(randomizedSequence);
    }

    public static boolean checkBackwardsSequence(ArrayList<Integer> userSequence, ArrayList<Integer> randomizedSequence) {
        boolean sequenceMatched = false;
        for (int i = 0; i < userSequence.size(); ++i) {
            for (int j = randomizedSequence.size() - 1; j >= 0; --j) {
                if (userSequence.get(i).equals(randomizedSequence.get(j))) {
                    sequenceMatched = true;
                } else {
                    sequenceMatched = false;
                }
            }
        }
        return sequenceMatched;
    }

    public static boolean checkSequencing(ArrayList<Integer> userSequence, ArrayList<Integer> randomizedSequence) {
        ArrayList<Integer> sortedSequence = new ArrayList<>(randomizedSequence);
        for (int i = 0; i < sortedSequence.size() - 1; ++i) {
            for (int j = i + 1; j < sortedSequence.size(); ++j) {
                if (sortedSequence.get(i) > sortedSequence.get(j)) {
                    int temp = sortedSequence.get(i);
                    sortedSequence.set(i, sortedSequence.get(j));
                    sortedSequence.set(j, temp);
                }
            }
        }
        return userSequence.equals(sortedSequence);
    }

    public static boolean checkLetterNumberSequencing
            (ArrayList<Object> userLetterNumberSequencing,
             ArrayList<Object> randomizedLetterNumberSequencing ) {

        ArrayList<Object> randomizedLNS = new ArrayList<>(randomizedLetterNumberSequencing);
        ArrayList<Character> characterArrayList = new ArrayList<>();
        ArrayList<Integer> integerArrayList = new ArrayList<>();

        for (int i = 0; i < randomizedLNS.size(); ++i){
            Object element = randomizedLNS.get(i);
            if (element instanceof Character) {
                characterArrayList.add((char) element);
            } else if (element instanceof Integer) {
                integerArrayList.add((int )element);
            }
        }

        Collections.sort(characterArrayList);
        Collections.sort(integerArrayList);

        ArrayList<Object> sortedRandomizedLetterNumberSequence = new ArrayList<>(integerArrayList);
        sortedRandomizedLetterNumberSequence.addAll(characterArrayList);

        return userLetterNumberSequencing.equals(sortedRandomizedLetterNumberSequence);
    }

    public String getNextIndicatorValue(int currentIndicatorValue) {
        return String.valueOf(currentIndicatorValue + 1);
    }

    public ArrayList<Integer> getNextRandomizedSequence(int currentIndicatorValue) {
        return sequenceRandomizer(currentIndicatorValue + 1);
    }

    public String getResetIndicatorValue() {
        return "1";
    }

    public ArrayList<Integer> getResetRandomizedSequence() {
        return null;
    }


    public ArrayList<Integer> sequenceRandomizer(int size) {
        ArrayList<Integer> sequence = new ArrayList<>(size);

        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            sequence.add(random.nextInt(9) + 1);
        }
        return sequence;
    }

    public ArrayList<Object> letterNumberSequenceRandomizer(int size) {
        ArrayList<Object> lns = new ArrayList<>(size) ;

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();

        for (int i = 0; i < size; ++i) {
            if (random.nextBoolean()) {
               char randomizedChar = chars.charAt(random.nextInt(chars.length()));
               lns.add(randomizedChar);
            } else {
               int randomizedNumber = random.nextInt(9) + 1;
               lns.add(randomizedNumber);
            }
        }
        return lns;
    }
}
