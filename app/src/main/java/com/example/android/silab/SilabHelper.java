package com.example.android.silab;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by adrian on 13/06/15.
 * Helper class for silab project, creates list of emoji and character hashtable.
 */
public class SilabHelper {

    // This is an array of byte arrays. Each byte array can transformed into an emoji
    // the (byte) cast is needed because byte is unsigned in java... ugly ugly ugly
    private final byte[][] byteArray = {
            // Emoticons (smilies)
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x81},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x82},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x83},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x84},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x85},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x86},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x89},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8A},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8B},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8C},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8D},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8F},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x92},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x93},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x94},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x98},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9A},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9C},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9D},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9E},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA0},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA1},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA2},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA3},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA4},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA5},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA8},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA9},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAA},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAB},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAD},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB0},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB1},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB2},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB3},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB5},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB7},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB8},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB9},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xBA},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xBB},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xBC},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xBE},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xBF},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x80},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x85},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x86},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x87},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x88},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x89},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8A},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8B},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8C},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8D},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8E},
            {(byte) 0xF0,(byte)0x9F,(byte)0x99,(byte)0x8F},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x80},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x87},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x88},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x8E},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x90},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x91},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x95},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x97},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x99},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9B},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0x9F},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA6},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xA7},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAC},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAE},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xAF},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB4},
            {(byte) 0xF0,(byte)0x9F,(byte)0x98,(byte)0xB6},

            // Dingbats
            {(byte)0xE2,(byte)0x9C,(byte)0x82},
            {(byte)0xE2,(byte)0x9C,(byte)0x85},
            {(byte)0xE2,(byte)0x9C,(byte)0x88},
            {(byte)0xE2,(byte)0x9C,(byte)0x89},
            {(byte)0xE2,(byte)0x9C,(byte)0x8A},
            {(byte)0xE2,(byte)0x9C,(byte)0x8B},
            {(byte)0xE2,(byte)0x9C,(byte)0x8C},
            {(byte)0xE2,(byte)0x9C,(byte)0x8F},
            {(byte)0xE2,(byte)0x9C,(byte)0x92},
            {(byte)0xE2,(byte)0x9C,(byte)0x94},
            {(byte)0xE2,(byte)0x9C,(byte)0x96},
            {(byte)0xE2,(byte)0x9C,(byte)0xA8},
            {(byte)0xE2,(byte)0x9C,(byte)0xB3},
            {(byte)0xE2,(byte)0x9C,(byte)0xB4},
            {(byte)0xE2,(byte)0x9D,(byte)0x84},
            {(byte)0xE2,(byte)0x9D,(byte)0x87},
            {(byte)0xE2,(byte)0x9D,(byte)0x8C},
            {(byte)0xE2,(byte)0x9D,(byte)0x8E},
            {(byte)0xE2,(byte)0x9D,(byte)0x93},
            {(byte)0xE2,(byte)0x9D,(byte)0x94},
            {(byte)0xE2,(byte)0x9D,(byte)0x95},
            {(byte)0xE2,(byte)0x9D,(byte)0x97},
            {(byte)0xE2,(byte)0x9D,(byte)0xA4},
            {(byte)0xE2,(byte)0x9E,(byte)0x95},
            {(byte)0xE2,(byte)0x9E,(byte)0x96},
            {(byte)0xE2,(byte)0x9E,(byte)0x97},
            {(byte)0xE2,(byte)0x9E,(byte)0xA1},
            {(byte)0xE2,(byte)0x9E,(byte)0xB0},

            //Transport
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x80},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x83},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x84},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x85},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x87},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x89},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x8C},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x8F},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x91},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x92},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x93},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x95},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x97},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x99},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0x9A},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA2},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA4},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA5},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA7},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA8},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xA9},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xAA},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xAB},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xAC},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xAD},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xB2},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xB6},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xB9},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xBA},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xBB},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xBC},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xBD},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9A,(byte)0xBE},
            {(byte) 0xF0,(byte)0x9F,(byte)0x9B,(byte)0x80},

            // Enclosed Characters
            {(byte)0xE2,(byte)0x93,(byte)0x82},
            {(byte)0xF0,(byte)0x9F,(byte)0x85,(byte)0xB0},
            {(byte)0xF0,(byte)0x9F,(byte)0x85,(byte)0xB1},
            {(byte)0xF0,(byte)0x9F,(byte)0x85,(byte)0xBE},
            {(byte)0xF0,(byte)0x9F,(byte)0x85,(byte)0xBF},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x8E},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x91},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x92},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x93},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x94},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x95},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x96},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x97},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x98},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x99},
            {(byte)0xF0,(byte)0x9F,(byte)0x86,(byte)0x9A},
            // Flags would go here, but Android flags are shit
            // going to ignore them until improved
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0x81},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0x82},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0x9A},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xAF},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB2},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB3},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB4},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB5},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB6},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB7},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB8},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xB9},
            {(byte)0xF0,(byte)0x9F,(byte)0x88,(byte)0xBA},
            {(byte)0xF0,(byte)0x9F,(byte)0x89,(byte)0x90},
            {(byte)0xF0,(byte)0x9F,(byte)0x89,(byte)0x91}
    };

    //This is a character array containing the useable characters
    //TODO add available characters here as they are finished
    private final char[] letters = {
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
            'Z'
    };

    //This is an ugly structure for containing character maps.
    //maps need to be in the order of the letters array above.
    private final int[][][] maps = {
            {
                    //!
                    {0,0,0,1,0,1,0,1},
                    {0,0,0,0,1,1,1},
                    {0,0,0,0,0,0,1,0,1},
                    {0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,1},
                    {},
                    {0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //Space
                    {},
                    {}
            },
            {
                    //A
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                    {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            },

            {
                    //B
                    {1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1}
            },

            {
                    //C
                    {0,0,0,0,1,1,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1},
                    {1},
                    {1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,1,1,1}
            },
            {
                    //H
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            },
            {
                    //P
                    {1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1},
                    {1},
                    {1},
                    {1}
            },
            {
                    //Y
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,1,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //I
                    {0,0,0,0,0,1,1,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,1,1,1}
            },
            {
                    //R
                    {1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1,1},
                    {1,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //T
                    {1,1,1,1,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //D
                    {1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,1},
                    {1,1,1}
            },
            {
                    //E
                    {1,1,1,1,1},
                    {1},
                    {1},
                    {1,1,1,1},
                    {1},
                    {1},
                    {1,1,1,1,1}
            },
            {
                    //F
                    {1,1,1,1,1},
                    {1},
                    {1},
                    {1,1,1,1},
                    {1},
                    {1},
                    {1}
            },
            {
                    //G
                    {0,0,0,0,1,1,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1},
                    {1,0,0,0,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,1,1,1}
            },
            {
                    //J
                    {0,0,0,1,1,1,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,1},
                    {0,0,0,0,0,1,1}
            },
            {
                    //K
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,1},
                    {1,1},
                    {1,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //L
                    {1},
                    {1},
                    {1},
                    {1},
                    {1},
                    {1},
                    {1,1,1,1,1}
            },
            {
                    //M
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,1,0,0,0,0,0,1,0,1},
                    {1,0,0,0,1,0,1,0,0,0,1},
                    {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //N
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            },
            {
                    //O
                    {0,0,0,0,0,0,0,0,1,1},
                    {0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,1,0,0,0,0,0,0,0,0,0,0,1}
                    {0,0,0,0,0,0,0,0,1,1}
            },
            {
                    //Q
                    {0,0,0,0,1,1,1},
                    {0,1,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,1,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,1,0,0,0,1},
                    {0,0,0,0,1,1,1,0,0,1,1}
            },
            {
                    //S
                    {0,0,0,0,0,1,1},
                    {0,0,1,0,0,0,0,0,0,0,1},
                    {0,1},
                    {0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,1,1}
            },
            {
                    //U
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,1,1,1}
            },
            {
                    //V
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,1,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,1,0,1},
                    {0,0,0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //W
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                    {0,0,0,1,0,0,0,0,1,1,0,0,0,0,1},
                    {0,0,0,0,0,1,1,0,0,0,0,1,1},
                    {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1}
            },
            {
                    //X
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,1,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,1,0,0,1},
                    {0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,1,0,0,1},
                    {0,0,0,1,0,0,0,0,0,0,0,0,1},
                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}
            },
            {
                    //Z
                    {1,1,1,1,1},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,1},
                    {0,0,0,0,0,0,1,},
                    {0,0,0,1,},
                    {1,1,1,1,1},
            }
    };

    //What will become the list of emoji
    private List<String> emojiList = new ArrayList<>();

    //What will become the structure linking characters to their maps
    private Hashtable<Character, int[][]> charMap = new Hashtable<>();

    //constructor for this helper class
    public SilabHelper(){
        //On construction the emoji list is populated from the byte array above,
        //the Character Map is constructed
        populateEmojiList();
        buildCharMap();
    }

    //function that creates emoji from byte arrays and adds them to list
    protected void populateEmojiList(){
        for(int m=0;m<byteArray.length;m++){
            try {
                emojiList.add(new String(byteArray[m], "UTF-8"));
            }catch(UnsupportedEncodingException e){
                e.printStackTrace(); //have to catch this error, don't actually know what it is
            }
        }
    }

    //function that builds the Hashtable linking characters to their maps
    protected void buildCharMap(){
        for(int m=0; m<letters.length; m++) {
            charMap.put(letters[m],maps[m]);
        }
    }

    //the function called by application to get the emoji list
    public List<String> getEmojiList() {
        return emojiList;
    }

    //the function called by application to get the character map
    public Hashtable<Character, int[][]> getCharMap(){
        return charMap;
    }

}
