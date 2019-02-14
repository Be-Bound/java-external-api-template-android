package com.bebound.template;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bebound.sdk.BeBound;
import com.bebound.template.request.getcharacter.bebound.BeBoundRequestGetCharacter;
import com.bebound.template.request.getcharacter.http.HttpRequestGetCharacter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonClassic;
    private Button buttonBeBound;

    // It will manage the permission.
    private PermissionsDelegate permissionsDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        buttonClassic = findViewById(R.id.button_classic);
        buttonBeBound = findViewById(R.id.button_bebound);
        permissionsDelegate = new PermissionsDelegate(this);

        buttonClassic.setOnClickListener(view -> {
            // NOTE: When buttonClassic is clicked and EditText is not empty, a request is sent.
            if (editText.getText().length() == 0){
                Toast.makeText(this, getString(R.string.toast_et_empty), Toast.LENGTH_LONG).show();
            } if (Integer.valueOf(editText.getText().toString()) > 64) {
                Toast.makeText(this, getString(R.string.toast_range_not_valid), Toast.LENGTH_LONG).show();
            } else {
                HttpRequestGetCharacter.send(this, Integer.valueOf(editText.getText().toString()));
            }
        });
        buttonBeBound.setOnClickListener(view -> {
            // NOTE: When buttonClassic is clicked and EditText is not empty, a request is sent.
            if (editText.getText().length() == 0){
                Toast.makeText(this, getString(R.string.toast_et_empty), Toast.LENGTH_LONG).show();
            }  if (Integer.valueOf(editText.getText().toString()) > 64) {
                Toast.makeText(this, getString(R.string.toast_range_not_valid), Toast.LENGTH_LONG).show();
            }else {
                BeBoundRequestGetCharacter.sendRequest(Integer.valueOf(editText.getText().toString()));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionsDelegate.hasPermissions()) {
            buttonClassic.setClickable(true);
            buttonBeBound.setClickable(true);
        } else {
            permissionsDelegate.requestPermissions();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, grantResults)) {
            buttonClassic.setClickable(true);
            buttonBeBound.setClickable(true);
        }
    }

    public void showToast(String text){
        runOnUiThread (new Thread(() -> Toast.makeText(BeBound.getContext(), text, Toast.LENGTH_LONG).show()));
    }

}
