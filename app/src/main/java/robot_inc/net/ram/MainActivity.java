package robot_inc.net.ram;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<RadioButton> buttons;
    TextView out;
    EditText input;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        out = (TextView) findViewById(R.id.out);
        buttons = new ArrayList<RadioButton>();
        buttons.add((RadioButton) findViewById(R.id.rb1));
        buttons.add((RadioButton) findViewById(R.id.rb2));
        buttons.add((RadioButton) findViewById(R.id.rb3));
        buttons.add((RadioButton) findViewById(R.id.rb4));
        buttons.add((RadioButton) findViewById(R.id.rb5));
        buttons.add((RadioButton) findViewById(R.id.rb6));
        for (RadioButton button : buttons) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClick(buttonView);
                }
            });
        }
        input = (EditText) findViewById(R.id.input);
        input.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    for (RadioButton button : buttons) {
                        if (button.isChecked()) {
                            Double chitAmount = Double.parseDouble(button.getText().toString());
                            Double output = (((chitAmount * 3) / 100 + chitAmount) - Double.parseDouble(input.getText().toString())) / 20;
                            out.setText(String.valueOf(output));
                            break;
                        }
                    }
                }
                return true;
            }
        });
    }
    private void processRadioButtonClick(CompoundButton buttonView) {
        for (RadioButton button : buttons) {
            if (button != buttonView)
                button.setChecked(false);
        }
    }
}