package hu.example.fodorsz.myfirstapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MyActivity extends Activity {

    public static final String EXTRA_MESSAGE = "hu.example.fodorsz.myfirstapp.MESSAGE";
    private int CAPTURE_PICTURE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present!
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                searchClicked();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog searchClicked() {
        return new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title_search))
                .setMessage(getString(R.string.dialog_message_search))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void showFragmentsActivity(View view) {
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    public void intentForPicture(View view) {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(captureIntent, CAPTURE_PICTURE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_PICTURE_REQUEST) {
            if (resultCode == RESULT_OK) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                BitmapDrawable background = new BitmapDrawable(getResources(), (Bitmap) data.getExtras().get("data"));

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.setBackgroundDrawable(background);
                } else {
                    layout.setBackground(background);
                }
            }
        }
    }
}
