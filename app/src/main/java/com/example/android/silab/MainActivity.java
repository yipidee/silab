package com.example.android.silab;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private EmojiInputDialog kb;                        //global variable for emoji dropdown
    private TextView renderView;                        //global variable for rendered display area
    private TextView emojiPattern;
    private static DialogDisplayer dd;
    private static Typeface mFont;                      //WhatsApp-like emoji set

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate dialog displayer helper for this activity
        dd = new DialogDisplayer(this);

        // load custom font from assets
        setCustomTypeface();

        //FragmentManager fm = getFragmentManager();
        kb = new EmojiInputDialog();

        emojiPattern = (TextView)findViewById(R.id.pattern_display);
        emojiPattern.setText(getString(R.string.emoji_string_textview));
        emojiPattern.setTypeface(mFont);
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
        if(Build.VERSION.SDK_INT>=19) { //If greater than KitKat use custom font
            mFont = Typeface.createFromAsset(getAssets(), "fonts/NotoColorEmoji.ttf");
        }else{ //else use default font
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

        // reference to share button
        Button share = (Button)findViewById(R.id.share_button);

        //variable to hold emojified string
        String emojified = "";

        //handle to the user input
        EditText userInput = (EditText) findViewById(R.id.user_text);
        String userString = userInput.getText().toString();

        if(emojiPattern.getText().equals(getString(R.string.emoji_string_textview))){
            dd.displayDialog(R.string.error_no_emoji_chosen);
        }else {

            if (!userString.equals("")) { //check for no user input
                emojified = Emojifier.emojify(userString, kb.getEmojiPattern());

                // render text to view
                renderView.setText(emojified);

                //enable share button
                share.setEnabled(true);
            } else {
                share.setEnabled(false);
                dd.displayDialog(R.string.error_no_input);
            }
        }
    }

    // share as text with WhatsApp, prompt other apps if WhatsApp not installed
    public void shareText(View v){
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

    public void openInput(View v){
        kb.show(getFragmentManager(),"emojiinput");
    }

    public static DialogDisplayer getDD(){
        return dd;
    }

    public static Typeface getFont(){
        return mFont;
    }
}
