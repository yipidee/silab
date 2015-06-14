package com.example.android.silab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    TextView renderView;                        //global variable for rendered display area

    Boolean isRendered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SilabHelper sh = new SilabHelper();         //helper for list and map creation

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get emoji list from helper
        List<String> emojiList = sh.getEmojiList();

        //Create string array adapter for dropdown list (emoji)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, // current context
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

        String space = "\u0020"; // single space..
        String selectedEmoji = (String)spinner.getSelectedItem(); // store selected emoji

        String emojisedString = ""; //what will become the final product

        //handle to the render view
        renderView = (TextView)findViewById(R.id.render_textview);

        //handle to the user input
        EditText userInput = (EditText)findViewById(R.id.user_text);

        /*//For test Purposes make user input currently available characters
        //TODO remove this test data
        userInput.setText("b a cf");
        */

        String userString = "";
        userString = userInput.getText().toString();
        userString=userString.toUpperCase();

        char[] userCharArray = userString.toCharArray();

        if(!userString.equals("")) { //check for no user input
            for (int i=0; i<userCharArray.length; i++) {

                char mapToUse = userCharArray[i];  // Get each character in order

                if (charMap.containsKey(mapToUse)) { //check if there is a map for this character

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
                    // 'new line' for between characters, except for last character
                    if(i!=userString.length()-1) emojisedString = emojisedString + "\n";
                }else{
                    //Creates an error dialog to inform the user about characters that can't be drawn
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.error_no_map);
                    builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing? Just let the dialog close and get garbage collected
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
            // where the new emojified string is written to render view
            renderView.setText(emojisedString);
            isRendered=true;
        }else{
            //Creates an error dialog to inform the user that they haven't input anything
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.error_no_input);
            builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing? Just let the dialog close and get garbage collected
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void share(View v){
        /* Bare bones intent to send rendered string.
         * Tested with:
         *   Hangouts:   NG
         *   gmail:      OK
         */

        if(isRendered){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, renderView.getText());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}
