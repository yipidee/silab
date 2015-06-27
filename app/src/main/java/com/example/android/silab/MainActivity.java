package com.example.android.silab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private EmojiInputDialog kb;                   //global variable for emoji dropdown
    private TextView renderView;                        //global variable for rendered display area
    private TextView emojiPattern;

    Boolean isRendered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SilabHelper sh = new SilabHelper();         //helper for list and map creation

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragmentManager fm = getFragmentManager();
        kb = new EmojiInputDialog();

        emojiPattern = (TextView)findViewById(R.id.pattern_display);
        emojiPattern.setText(getString(R.string.emoji_string_textview));

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

    // Function that creates emojified string
    public void render(View v) {
        /* This is where the magic happens. Takes user string and selected emoji
         * and renders the character using the emoji.
         */

        //Variables to contain user input and output
        String userString = "";
        String emojified = "";

        //handle to the user input
        EditText userInput = (EditText) findViewById(R.id.user_text);
        userString = userInput.getText().toString();
        userString = userString.toUpperCase();

        char[] userCharArray = userString.toCharArray();

        if (userCharArray.length != 0) { //check for no user input
            emojified = makeEmojisedString(userCharArray);
        } else {
            displayDialog(R.string.error_no_input);
        }
        // render text to view
        renderView.setText(emojified);
        isRendered = true;
    }

    // Share function
    // Presents user with option to share text or image
    public void share(View v){
        if(isRendered) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.message_share);
            builder.setPositiveButton(R.string.button_string, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shareText();
                }
            });
            builder.setNegativeButton(R.string.button_image, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    shareImage();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    // Function that creates and shares images of emojified string
    protected void shareImage() {
        /* Bare bones intent to send renderView as image.
         * Tested with:
         *   Hangouts:  OK
         *   gmail:     OK (cropped)
         *   Facebook:
         *   FB mess:   OK
         *   Line:      OK
         *   WA:        OK (cropped)
         */

        // Create bitmap
        Bitmap bm = Bitmap.createBitmap(renderView.getDrawingCache());

        // file to store PNG image of renderView content
        // this is only a file object, it doesn't create a file on disc
        File f = new File(Environment.getExternalStorageDirectory() + File.separator, "tmp_emojified.png");

        try {
            f.createNewFile(); // create the file
        } catch (IOException e) {
            // file creation fails for whatever reason
            displayDialog(R.string.error_file_open);
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // catchs error thrown when problem with file IO
            e.printStackTrace();
        }

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        shareIntent.setType("image/png");
        startActivity(Intent.createChooser(shareIntent, "Choose your share app!"));
    }

    protected void shareText(){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,renderView.getText());
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Choose your share app!"));
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

    protected String makeEmojisedString(char[] input) {

        int count = 0; //index for emoji pattern

        String space = " ";  // simple ASCII space
        List<String> selectedEmoji = kb.getEmojiPattern();//(String) spinner.getSelectedItem(); // store selected emoji

        //Variable to store emojised string
        String emojisedString = "";

        //handle to the render view
        renderView = (TextView) findViewById(R.id.render_textview);
        renderView.setDrawingCacheEnabled(true);

        for (int i = 0; i < input.length; i++) {

            char mapToUse = input[i];  // Get each character in order

            if (mapExists(mapToUse)) { //check if there is a map for this character

                int[][] map = charMap.get(mapToUse);  //The map for the input character

                //This is what turns the map into the emoji based string.(as long as there's a map)
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
