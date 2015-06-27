package com.example.android.silab;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

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

    ArrayAdapter<String> adapter;
    String[] emojiPattern = {"XX"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> emojiString = new SilabHelper().getEmojiList();
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                emojiString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the view for this layout
        View v = inflater.inflate(R.layout.emoji_input, container, false);
        GridView gv = (GridView)v.findViewById(R.id.emoji_keyboard);
        gv.setNumColumns(5);
        gv.setAdapter(adapter);
        return v;
    }

    public String[] getEmojiPattern(){
        return emojiPattern;
        //return emojiPattern;
    }
}
