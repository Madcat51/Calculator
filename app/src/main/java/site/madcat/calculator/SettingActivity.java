package site.madcat.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
    public static final String POSITION_SWITCH = "SWITCH_POS";
    private Switch themes_dark_switch;
    private Button key_apply_setting;
    private boolean checkDarkThemes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        themes_dark_switch.setChecked(getIntent().getBooleanExtra(POSITION_SWITCH, true));
        clickKey();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void initViews() {
        key_apply_setting = findViewById(R.id.key_apply_setting_button);
        themes_dark_switch = findViewById(R.id.themes_dark_switch);
    }

    public void clickKey() {
        key_apply_setting.setOnClickListener(view -> {
            Intent intent = new Intent();
            if (themes_dark_switch.isChecked()) {
                checkDarkThemes = true;
            } else {
                checkDarkThemes = false;
            }
            intent.putExtra("dark", checkDarkThemes);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}