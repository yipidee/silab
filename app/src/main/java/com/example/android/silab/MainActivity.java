package com.example.android.silab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.List;

/* The main activity for silab, renders layout, gets input, sets output etc.
 *
 * Currently implemented
 *  - rendering emoji string
 *  - sharing image of rendered string
 *
 * To be implemented
 *  - TODO add multiple emoji fonts for image option
 *
 */
public class MainActivity extends Activity {

    private Hashtable<Character, int[][]> charMap;      //global variable for character maps
    private EmojiInputDialog kb;                        //global variable for emoji dropdown
    private TextView renderView;                        //global variable for rendered display area
    private TextView emojiPattern;
    private Typeface mFont;                             //WhatsApp-like emoji set

    private Boolean isRendered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SilabHelper sh = new SilabHelper();         //helper for list and map creation

        // load custom font from assets
        setCustomTypeface();

        //FragmentManager fm = getFragmentManager();
        kb = new EmojiInputDialog();

        emojiPattern = (TextView)findViewById(R.id.pattern_display);
        emojiPattern.setText(getString(R.string.emoji_string_textview));
        emojiPattern.setTypeface(mFont);

        // character map hashtable from helper
        charMap = sh.getCharMap();
    }

    // this is automatically created, didn't touch this function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // this is automatically created, didn't touch this function
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

    protected void setCustomTypeface(){
        if(Build.VERSION.SDK_INT>=19) { //If greater than KitKat use cutsom font
            mFont = Typeface.createFromAsset(getAssets(), "fonts/NotoColorEmoji.ttf");
        }else{ //else use deafult font
            mFont = Typeface.DEFAULT;
        }
    }

    // Function that creates emojified string
    public void render(View v) {
        /* This is where the magic happens. Takes user string and selected emoji
         * and renders the character using the emoji.
         */

        //handle to the render view
        renderView = (TextView) findViewById(R.id.render_textview);
        renderView.setTypeface(mFont);

        //Variables to contain user input and output
        String emojified = "";

        //handle to the user input
        EditText userInput = (EditText) findViewById(R.id.user_text);
        String userString = userInput.getText().toString();

        if(emojiPattern.getText().equals(getString(R.string.emoji_string_textview))){
            displayDialog(R.string.error_no_emoji_chosen);
        }else {
            userString = userString.toUpperCase();

            char[] userCharArray = userString.toCharArray();

            if (userCharArray.length != 0) { //check for no user input
                emojified = makeEmojisedString(userCharArray, kb.getEmojiPattern());
            } else {
                displayDialog(R.string.error_no_input);
            }
            // render text to view
            renderView.setText(emojified);
            isRendered = true;
        }
    }

    // Share function
    // Presents user with option to share text or image
    public void share(View v){
        if(isRendered) {
           shareText();
        }
    }

    // share as text with WhatsApp
    protected void shareText(){
        Intent shareIntent = new Intent();
        try{
            Uri urluri = Uri.parse("whatsapp://send?text="+renderView.getText().toString()+"");
            shareIntent.setAction(Intent.ACTION_VIEW);
            shareIntent.setData(urluri);
            startActivity(shareIntent);
        }catch(ActivityNotFoundException e){
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT,renderView.getText().toString());
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Choose your share app!"));
        }
    }

    protected void displayDialog(int errorMessage) {
        // Displays a error dialog, with an ok button to close
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errorMessage);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing? Just let the dialog close and get garbage collected
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected Boolean mapExists(char key) {
        return charMap.containsKey(key);
    }

    protected String makeEmojisedString(char[] input, List<String> selectedEmoji) {

        String space = "\u205F\u202F";  // unicode space

        //Variable to store emojised string
        String emojisedString = "";

        for (int i = 0; i < input.length; i++) {

            char mapToUse = input[i];  // Get each character in order

            if (mapExists(mapToUse)) { //check if there is a map for this character

                int[][] map = charMap.get(mapToUse);  //The map for the input character

                //This is what turns the map into the emoji based string.(as long as there's a map)
                int count = 0; //index for emoji pattern
                for (int m = 0; m < map.length; m++) {
                    for (int n = 0; n < map[m].length; n++) {
                       switch (map[m][n]) {
                            case (0):
                                emojisedString = emojisedString + space;
                                break;
                            case (1):
                                emojisedString = emojisedString + selectedEmoji.get(count%selectedEmoji.size());
                                count++;
                                break;
                        }
                    }
                    emojisedString = emojisedString + "\n"; //'new line' character
                }
                // 'new line' for between characters, except for last character
                if (i != input.length - 1) emojisedString = emojisedString + "\n";
            } else {
                displayDialog(R.string.error_no_map);
            }
       }
        // return emojified string
        return emojisedString;
    }

    public void openInput(View v){
        kb.show(getFragmentManager(),"test");
    }
}
