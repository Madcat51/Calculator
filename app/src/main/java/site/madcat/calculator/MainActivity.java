package site.madcat.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Calculator calculator = new Calculator(); // создание объекта;
    private TextView resultView;
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
    private ScrollView scrollResult;
    private boolean lastPoint = false;//признак присутствия в вводимом числе запятой
    private boolean clearDisplay = true; //если true то следующее нажатие очищает экран
    private String displayText;
    private boolean firstOperation = true;//признак первой операции


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(); //Инициализация переменных

        clickKey();  //Обработка нажатия на экран
    }

    /*Инициализация переменных*/
    public void initViews() {
        resultView = (TextView) findViewById(R.id.result_text_view);
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
        scrollResult = findViewById(R.id.result_scroll_view);
    }

    /*Обработка нажатия на экран*/
    public void clickKey() {
        keyOne.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("1");
            afterGetResult();
        });
        keyTwo.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("2");
            afterGetResult();
        });
        keyThree.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("3");
            afterGetResult();
        });
        keyFour.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("4");
            afterGetResult();
        });
        keyFive.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("5");
            afterGetResult();
        });
        keySix.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("6");
            afterGetResult();
        });
        keySeven.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("7");
            afterGetResult();
        });
        keyEight.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("8");
            afterGetResult();
        });
        keyNine.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("9");
            afterGetResult();
        });
        keyZero.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("0");
            afterGetResult();
        });
        keyDoubleZero.setOnClickListener(view -> {
            calculator.addSymbolToDisplayText("00");
            afterGetResult();
        });
        keyPoint.setOnClickListener(view -> {
            calculator.displayPoint(".");
            afterGetResult();
        });
        keyPlusMinus.setOnClickListener(view -> {
            calculator.displaySign();
            afterGetResult();
        });
        keyBack.setOnClickListener(view -> {
            calculator.backSymbol();
            afterGetResult();
        });
        keyClear.setOnClickListener(view -> {
            calculator.clear();
            afterGetResult();
        });
        keyPlus.setOnClickListener(view -> {
            calculator.computation('+');
            afterGetResult();
        });
        keyMinus.setOnClickListener(view -> {
            calculator.computation('-');
            afterGetResult();
        });
        keyDivision.setOnClickListener(view -> {
            calculator.computation('/');
            afterGetResult();
        });
        keyMultiplay.setOnClickListener(view -> {
            calculator.computation('*');
            afterGetResult();
        });
        keyEquals.setOnClickListener(view -> {
            calculator.equalsClick();
            afterGetResult();
        });
    }

    public void afterGetResult() {
        displayView.setText(calculator.getDisplayText().toString());
        resultView.setText(calculator.getResultText().toString());
        scrollResult.scrollTo(0, scrollResult.getBottom());
    }

}
