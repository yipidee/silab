package com.example.android.silab;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

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

    ArrayAdapter<String> adapter;
    List<String> emojiPattern;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> emojiString = new SilabHelper().getEmojiList();
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                emojiString);
        emojiPattern = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the view for this layout
        View v = inflater.inflate(R.layout.emoji_input, container, false);

        GridView gv = (GridView)v.findViewById(R.id.emoji_keyboard);
        gv.setNumColumns(5);
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
}
