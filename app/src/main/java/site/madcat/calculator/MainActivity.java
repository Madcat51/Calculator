package site.madcat.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private boolean clearDisplay = true;
    private boolean lastPoint = false;
    private TextView displayView;
    private Button keyOne;
    private Button keyTwo;
    private Button keyThree;
    private Button keyFour;
    private Button keyFive;
    private Button keySix;
    private Button keySeven;
    private Button keyEight;
    private Button keyNine;
    private Button keyZero;
    private Button keyDoubleZero;
    private Button keyBack;
    private Button keyPlusMinus;
    private Button keyClear;
    private Button keyPlus;
    private Button keyMinus;
    private Button keyDivision;
    private Button keyMultiplay;
    private Button keyEquals;
    private Button keyPoint;
    private String displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(); //Инициализация переменных
        clickKey();  //Обработка нажатия на экран


    }

    /*Инициализация переменных*/
    public void initViews() {
        displayView = (TextView) findViewById(R.id.display_text_view);
        keyOne = findViewById(R.id.key_one_button);
        keyTwo = findViewById(R.id.key_two_button);
        keyThree = findViewById(R.id.key_three_button);
        keyFour = findViewById(R.id.key_four_button);
        keyFive = findViewById(R.id.key_five_button);
        keySix = findViewById(R.id.key_six_button);
        keySeven = findViewById(R.id.key_seven_button);
        keyEight = findViewById(R.id.key_eight_button);
        keyNine = findViewById(R.id.key_nine_button);
        keyZero = findViewById(R.id.key_zero_button);
        keyDoubleZero = findViewById(R.id.key_double_zero_button);
        keyBack = findViewById(R.id.key_back_button);
        keyPlusMinus = findViewById(R.id.key_plus_minus_button);
        keyClear = findViewById(R.id.key_clear_button);
        keyPlus = findViewById(R.id.key_plus_button);
        keyMinus = findViewById(R.id.key_minus_button);
        keyDivision = findViewById(R.id.key_division_button);
        keyMultiplay = findViewById(R.id.key_multiplay_button);
        keyEquals = findViewById(R.id.key_equals_button);
        keyPoint = findViewById(R.id.key_point_button);
    }

    /*Обработка нажатия на экран*/
    public void clickKey() {
        keyOne.setOnClickListener(view -> displayOnScreen("1"));
        keyTwo.setOnClickListener(view -> displayOnScreen("2"));
        keyThree.setOnClickListener(view -> displayOnScreen("3"));
        keyFour.setOnClickListener(view -> displayOnScreen("4"));
        keyFive.setOnClickListener(view -> displayOnScreen("5"));
        keySix.setOnClickListener(view -> displayOnScreen("6"));
        keySeven.setOnClickListener(view -> displayOnScreen("7"));
        keyEight.setOnClickListener(view -> displayOnScreen("8"));
        keyNine.setOnClickListener(view -> displayOnScreen("9"));
        keyZero.setOnClickListener(view -> displayOnScreen("0"));
        keyDoubleZero.setOnClickListener(view -> displayOnScreen("00"));
        keyPoint.setOnClickListener(view -> displayPoint("."));
        keyPlusMinus.setOnClickListener(view -> displaySign());
        keyBack.setOnClickListener(view -> backSymbol());
//        keyClear.setOnClickListener(view -> );
//        keyPlus.setOnClickListener(view -> );
//        keyMinus.setOnClickListener(view -> );
//        keyDivision.setOnClickListener(view -> );
//        keyMultiplay.setOnClickListener(view -> );
//        keyEquals.setOnClickListener(view -> );
    }

    /*Вывод цифры на экран*/
    public void displayOnScreen(String symbol) {
        if (clearDisplay == true) {
            displayView.setText(symbol);
            clearDisplay = false;
        } else {
            displayView.setText(displayView.getText() + symbol);
        }
    }

    /*Изменение знака*/
    public void displaySign() {
        displayText = displayView.getText().toString();
        if (displayText.charAt(0) == '-') {
            displayView.setText(displayText.substring(1));
        } else {
            displayView.setText("-" + displayText);
        }
    }

    /*Вывод запятой*/
    public void displayPoint(String point) {
        if (lastPoint == false) {
            displayView.setText(displayView.getText() + ".");
            lastPoint = true; //после удаления запятой не работает
        }
    }

    /*Убрать последний знак*/
    public void backSymbol() {
  //  char lastSymbol =displayText.charAt(displayText.length() - 1);
        displayText = displayView.getText().toString();
        if (displayText.length() >=1) {

            displayView.setText(displayText.substring(0,displayText.length() - 1));
        } else  {
      //      displayView.setText(0);//поправить не работает
        }
  /*      if (lastSymbol=='.'){     //если была точка то ее можно опять ставить
            lastPoint = false;
        }*/

    }


}
