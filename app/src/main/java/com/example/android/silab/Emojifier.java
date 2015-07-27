package com.example.android.silab;

import java.util.Hashtable;
import java.util.List;

/**
 * Helper class for emojification of strings.
 *
 * Rev.  Date        Author        Comment
 * 0     2015.7.23   A. Connolly   Initial implementation
 */

public class Emojifier {

    // load the maps into a Hashtable
    private static Hashtable<Character, int[][]> maps = CharMaps.getCharMap();

    public static String emojify(String userInput, List<String> selectedEmoji){
        // the unicode space used in this app
        final String space = "\u205F\u202F";
        int count = 0;  //counter to change emoji per character

        userInput = userInput.toUpperCase();
        char[] input = userInput.toCharArray();

        String emojifiedString = "";

        for (int i = 0; i < input.length; i++) {

            char mapToUse = input[i];  // Get each character in order

            if (maps.containsKey(mapToUse)) { //check if there is a map for this character

                int[][] map = maps.get(mapToUse);  //The map for the input character

                //This is what turns the map into the emoji based string.(as long as there's a map)
                for (int m = 0; m < map.length; m++) {
                    for (int n = 0; n < map[m].length; n++) {
                       switch (map[m][n]) {
                            case (0):
                                emojifiedString = emojifiedString + space;
                                break;
                            case (1):
                                emojifiedString = emojifiedString + selectedEmoji.get(count%selectedEmoji.size());
                                break;
                        }
                    }
                    emojifiedString = emojifiedString + "\n"; //'new line' character
                }
                // 'new line' for between characters, except for last character
                if (i != input.length - 1) emojifiedString = emojifiedString + "\n";
            } else {
                MainActivity.getDD().displayDialog(R.string.error_no_map);
            }
            count++;
        }
        // return emojified string
        return emojifiedString;
    }

    private static class CharMaps {

        //This is a character array containing the useable characters
        private static final char[] letters = {
                '!',
                ' ',
                'A',
                'B',
                'C',
                'H',
                'P',
                'Y',
                'I',
                'R',
                'T',
                'D',
                'E',
                'F',
                'G',
                'J',
                'K',
                'L',
                'M',
                'N',
                'O',
                'Q',
                'S',
                'U',
                'V',
                'W',
                'X',
                'Z'
        };

        //This is an ugly structure for containing character maps.
        //maps need to be in the order of the letters array above.
        private static final int[][][] maps = {
                {
                        //!
                        {0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //Space
                        {},
                        {}
                },
                {
                        //A
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                },

                {
                        //B
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1}
                },

                {
                        //C
                        {0, 0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1},
                        {1},
                        {1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 1, 1}
                },
                {
                        //H
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                },
                {
                        //P
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1},
                        {1},
                        {1}
                },
                {
                        //Y
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //I
                        {0, 0, 0, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 1, 1}
                },
                {
                        //R
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //T
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //D
                        {1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1}
                },
                {
                        //E
                        {1, 1, 1, 1, 1},
                        {1},
                        {1},
                        {1, 1, 1, 1},
                        {1},
                        {1},
                        {1, 1, 1, 1, 1}
                },
                {
                        //F
                        {1, 1, 1, 1, 1},
                        {1},
                        {1},
                        {1, 1, 1, 1},
                        {1},
                        {1},
                        {1}
                },
                {
                        //G
                        {0, 0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1},
                        {1, 0, 0, 0, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 1, 1}
                },
                {
                        //J
                        {0, 0, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 1, 1}
                },
                {
                        //K
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1},
                        {1, 0, 1},
                        {1, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //L
                        {1},
                        {1},
                        {1},
                        {1},
                        {1},
                        {1},
                        {1, 1, 1, 1, 1}
                },
                {
                        //M
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 0, 0, 0, 1, 1},
                        {1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //N
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                },
                {
                        //O
                        {0, 0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 1, 1},
                },
                {
                        //Q
                        {0, 0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 1, 1, 1, 1},
                },
                {
                        //S
                        {0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1},
                        {0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 1}
                },
                {
                        //U
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 1, 1}
                },
                {
                        //V
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //W
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //X
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                {
                        //Z
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1,},
                        {0, 0, 1,},
                        {1, 1, 1, 1, 1},
                }
        };


        //What will become the structure linking characters to their maps
        private static Hashtable<Character, int[][]> charMap = new Hashtable<>();

        //function that builds the Hashtable linking characters to their maps
        private static void buildCharMap() {
            for (int m = 0; m < letters.length; m++) {
                charMap.put(letters[m], maps[m]);
            }
        }

        //the function called by application to get the character map
        public static Hashtable<Character, int[][]> getCharMap(){
            buildCharMap();
            return charMap;
        }

    }
}
