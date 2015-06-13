package com.example.android.silab;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.List;


public class MainActivity extends Activity {

    Hashtable<Character, int[][]> charMap;      //global variable for character maps
    Spinner spinner;                            //global variable for emoji dropdown

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SilabHelper sh = new SilabHelper();         //helper for list and map creation

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get emoji list from helper
        List<String> emojiList = sh.getEmojiList();

        //Create string array adapter for dropdown list (emoji)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, // current context
                                                                android.R.layout.simple_spinner_dropdown_item, //default android dropdown
                                                                emojiList //list of emoji created from Strings
                                                                );
        // make the list plain dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //get a handle on the emoji selection dropdown
        spinner = (Spinner)findViewById(R.id.emoji_selector);
        //populate drop down with above adapter
        spinner.setAdapter(adapter);

        // character map hashtable from helper
        charMap = sh.getCharMap();

    }

    // this is automatically created, did touch this function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // this is automatically created, did touch this function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Wrote this function. It's doing the heavy lifting
    public void render(View v){
        /* This is where the magic happens. Takes user string and selected emoji
         * and renders the character using the emoji.
         */

        String space = "\u3000"; // a double width space, literally same as using 2 standard space. kind of pointless...
        String selectedEmoji = (String)spinner.getSelectedItem(); // store selected emoji

        String emojisedString = ""; //what will become the final product
        TextView renderView = (TextView)findViewById(R.id.render_textview); //handle to the render view
        EditText userInput = (EditText)findViewById(R.id.user_text); //handle to the user input

        char mapToUse = userInput.getText().charAt(0); //only reading first char at minute TODO incomplete
        if((int)mapToUse>90) mapToUse=(char)((int)mapToUse-32); //ugly hack to capitalise characters TODO make this nicer

            if(charMap.containsKey(mapToUse)) { //check if there is a map for this character

                int[][] map = charMap.get(mapToUse);  //The map for the input character

                //This is what turns the map into the emoji based string.(as long as there's a map)
                for (int m = 0; m < map.length; m++) {
                    for (int n = 0; n < map[m].length; n++) {
                        switch (map[m][n]) {
                            case (0):
                                emojisedString = emojisedString + space;
                                break;
                            case (1):
                                emojisedString = emojisedString + selectedEmoji;
                                break;
                        }
                    }
                    emojisedString = emojisedString + "\n"; //'new line' character
                }
            }

        // where the new emojified string is written to render view
        renderView.setText(emojisedString);

    }
}
