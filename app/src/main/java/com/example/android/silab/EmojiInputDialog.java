package com.example.android.silab;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A. Connolly on 26.06.15
 *
 * Custom view for emoji input. Initiallybuilt into
 * Emojify app, but plan to allow use through intents.
 *
 * Rev.   Date      Author         Description
 *  0     26.06.15  A. Connolly    Initial concept
 *
 */

public class EmojiInputDialog extends DialogFragment {

    private ArrayAdapter<String> adapter;
    private List<String> emojiPattern;
    private TextView tv;

    float diaWidth;
    float diaHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> emojiString = Emoji.getEmojiList();
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                emojiString);
        emojiPattern = new ArrayList<String>();

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();

        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        diaWidth = outMetrics.widthPixels / density;
        diaWidth = (diaWidth -6)*density;
        diaHeight = outMetrics.heightPixels / density;
        diaHeight = (diaHeight /(float)2.2) * density;
    }

    // Over ride this method purely to remove title bar from dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // Removing the unused title bar space from the dialog window
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout((int) diaWidth,(int) diaHeight);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the view for this layout
        View v = inflater.inflate(R.layout.emoji_input, container, false);

        GridView gv = (GridView)v.findViewById(R.id.emoji_keyboard);
        //TODO: set columns based on screen width
        gv.setNumColumns(5);
        gv.setGravity(Gravity.CENTER);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String)parent.getItemAtPosition(position);
                emojiPattern.add(s);
                if(tv.getText().equals(getString(R.string.emoji_string_textview))) {
                    tv.setText("");
                }
                tv.setText(tv.getText()+s);
            }
        });

        Button delete = (Button)v.findViewById(R.id.emoji_string_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        Button enter = (Button) v.findViewById(R.id.emoji_string_enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter();
            }
        });

        tv = (TextView)v.findViewById(R.id.emoji_string_text);
        tv.setTypeface(MainActivity.getFont());

        return v;
    }

    public void delete(){
        if(!emojiPattern.isEmpty()){
            emojiPattern.remove(emojiPattern.size()-1);
            if(emojiPattern.isEmpty()){
                tv.setText(getString(R.string.emoji_string_textview));
            }else {
                tv.setText(tv.getText().subSequence(0, tv.getText().length() - 2));
            }
        }
    }

    public void enter(){
        TextView patternView = (TextView)getActivity().findViewById(R.id.pattern_display);
        patternView.setText(tv.getText());
        this.dismiss();
    }

    public List<String> getEmojiPattern(){
        return emojiPattern;
    }

    private static class Emoji{

        // This is an array of byte arrays. Each byte array can transformed into an emoji
        // the (byte) cast is needed because byte is unsigned in java... ugly ugly ugly
        private final static byte[][] byteArray = {
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
                {(byte)0xF0,(byte)0x9F,(byte)0x89,(byte)0x91},

                //Uncategorized
                {(byte)0xC2,(byte)0xA9},
                {(byte)0xC2,(byte)0xAE},
                {(byte)0xE2,(byte)0x80,(byte)0xBC},
                {(byte)0xE2,(byte)0x84,(byte)0xA2},
                {(byte)0xE2,(byte)0x84,(byte)0xB9},
                {(byte)0xE2,(byte)0x86,(byte)0x94},
                {(byte)0xE2,(byte)0x86,(byte)0x95},
                {(byte)0xE2,(byte)0x86,(byte)0x96},
                {(byte)0xE2,(byte)0x86,(byte)0x97},
                {(byte)0xE2,(byte)0x86,(byte)0x98},
                {(byte)0xE2,(byte)0x86,(byte)0x99},
                {(byte)0xE2,(byte)0x86,(byte)0xA9},
                {(byte)0xE2,(byte)0x86,(byte)0xAA},
                {(byte)0xE2,(byte)0x8C,(byte)0x9A},
                {(byte)0xE2,(byte)0x8C,(byte)0x9B},
                {(byte)0xE2,(byte)0x8F,(byte)0xA9},
                {(byte)0xE2,(byte)0x8F,(byte)0xAA},
                {(byte)0xE2,(byte)0x8F,(byte)0xA9},
                {(byte)0xE2,(byte)0x8F,(byte)0xAB},
                {(byte)0xE2,(byte)0x8F,(byte)0xAC},
                {(byte)0xE2,(byte)0x8F,(byte)0xB0},
                {(byte)0xE2,(byte)0x8F,(byte)0xB3},
                {(byte)0xE2,(byte)0x96,(byte)0xAA},
                {(byte)0xE2,(byte)0x96,(byte)0xAB},
                {(byte)0xE2,(byte)0x96,(byte)0xB6},
                {(byte)0xE2,(byte)0x97,(byte)0x80},
                {(byte)0xE2,(byte)0x97,(byte)0xBB},
                {(byte)0xE2,(byte)0x97,(byte)0xBC},
                {(byte)0xE2,(byte)0x97,(byte)0xBD},
                {(byte)0xE2,(byte)0x97,(byte)0xBE},
                {(byte)0xE2,(byte)0x98,(byte)0x80},
                {(byte)0xE2,(byte)0x98,(byte)0x81},
                {(byte)0xE2,(byte)0x98,(byte)0x8E},
                {(byte)0xE2,(byte)0x98,(byte)0x91},
                {(byte)0xE2,(byte)0x98,(byte)0x94},
                {(byte)0xE2,(byte)0x98,(byte)0x95},
                {(byte)0xE2,(byte)0x98,(byte)0x9D},
                {(byte)0xE2,(byte)0x98,(byte)0xBA},
                {(byte)0xE2,(byte)0x99,(byte)0x88},
                {(byte)0xE2,(byte)0x99,(byte)0x89},
                {(byte)0xE2,(byte)0x99,(byte)0x8A},
                {(byte)0xE2,(byte)0x99,(byte)0x8B},
                {(byte)0xE2,(byte)0x99,(byte)0x8C},
                {(byte)0xE2,(byte)0x99,(byte)0x8D},
                {(byte)0xE2,(byte)0x99,(byte)0x8E},
                {(byte)0xE2,(byte)0x99,(byte)0x8F},
                {(byte)0xE2,(byte)0x99,(byte)0x90},
                {(byte)0xE2,(byte)0x99,(byte)0x91},
                {(byte)0xE2,(byte)0x99,(byte)0x92},
                {(byte)0xE2,(byte)0x99,(byte)0x93},

                {(byte)0xF0,(byte)0x9F,(byte)0x92,(byte)0xA9}
        };

        //What will become the list of emoji
        private static List<String> emojiList = new ArrayList<>();

        //function that creates emoji from byte arrays and adds them to list
        private static void populateEmojiList(){
            for(int m=0;m<byteArray.length;m++){
                try {
                    emojiList.add(new String(byteArray[m], "UTF-8"));
                }catch(UnsupportedEncodingException e){
                    e.printStackTrace(); //have to catch this error, don't actually know what it is
                }
            }
        }

        //the function called by application to get the emoji list
        public static List<String> getEmojiList() {
            populateEmojiList();
            return emojiList;
        }
    }
}
