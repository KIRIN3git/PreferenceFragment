package com.example.shinji.preferencefragment;

import java.util.Set;
import java.util.prefs.Preferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定画面の表示
        Button settings = (Button)this.findViewById(R.id.settings);
        settings.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Preferencesx.class);
                startActivityForResult(intent, 0);
            }
        });

        setPreferenceValues();
    }

    /*
     * Preferenceの値をセット
     */
    private void setPreferenceValues() {
        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);

        // チェックボックス
        TextView tvCheckBoxValue = (TextView)this.findViewById(R.id.checkbox_value);
        boolean checkboxValue = spf.getBoolean("checkbox_preference", false);
        tvCheckBoxValue.setText(String.valueOf(checkboxValue));

        // スイッチ
        TextView tvSwitchValue = (TextView)this.findViewById(R.id.switch_value);
        boolean switchValue = spf.getBoolean("switch_preference", false);
        tvSwitchValue.setText(String.valueOf(switchValue));

        // エディットテキスト
        TextView tvEditTextValue = (TextView)this.findViewById(R.id.edittext_value);
        String edittextValue = spf.getString("edittext_preference", "");
        tvEditTextValue.setText(edittextValue);

        // リスト
        TextView tvListValue = (TextView)this.findViewById(R.id.list_value);
        String listValue = spf.getString("list_preference", "0");
        tvListValue.setText(listValue);

        // マルチセレクト
        TextView tvMultiValue = (TextView)this.findViewById(R.id.multi_value);
        Set<String> multiValues = spf.getStringSet("multi_preference", null);
        String multiValue = "";
        if (multiValues != null) {
            String[] multiValueArray = multiValues.toArray(new String[] {});
            for (int i = 0; i < multiValueArray.length; i++) {
                multiValue += multiValueArray[i];
            }
        }
        tvMultiValue.setText(multiValue);
    }

    /*
     * Activityの戻り処理
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                setPreferenceValues();
                break;
        }
    }
}
