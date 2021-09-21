package site.madcat.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
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
    private float operand1 = 0;
    private float operand2 = 0;
    private float result = 0;
    private char prevSign;//знак  действия предыдущий


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews(); //Инициализация переменных
        Calculator calculator = new Calculator(displayView); // создание объекта
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
        keyClear.setOnClickListener(view -> clear());
        keyPlus.setOnClickListener(view -> computation('+'));
        keyMinus.setOnClickListener(view -> computation('-'));
        keyDivision.setOnClickListener(view -> computation('/'));
        keyMultiplay.setOnClickListener(view -> computation('*'));
        keyEquals.setOnClickListener(view -> equalsClick());
    }

    /*back-Убрать последний знак */
    public void backSymbol() {
        displayText = displayView.getText().toString();
        char lastSymbol = displayText.charAt(displayText.length() - 1);
        if (displayText.length() > 1) {
            displayView.setText(displayText.substring(0, displayText.length() - 1));
        } else {
            displayView.setText("0");
            clearDisplay = true;
        }
        if (lastSymbol == '.') {     //если была точка то ее можно опять ставить
            lastPoint = false;
        }
    }

    /*Вывод цифры на экран в display_text_view)*/
    public void displayOnScreen(String symbol) {
        if (clearDisplay == true) {
            displayView.setText(symbol);
            clearDisplay = false;
        } else {
            displayView.setText(displayView.getText() + symbol);
        }
    }

    /*Изменение знака +/- */
    public void displaySign() {
        displayText = displayView.getText().toString();
        if (displayText.charAt(0) == '-') {
            displayView.setText(displayText.substring(1));
        } else {
            displayView.setText("-" + displayText);
        }
    }

    /*Вывод запятой */
    public void displayPoint(String point) {
        if (lastPoint == false) {
            displayView.setText(displayView.getText() + ".");
            lastPoint = true; //после удаления запятой кнопкой back не работает, поправлю
        }
    }

    /*Сброс здесь сбрасываем на начало*/
    public void clear() {
        clearDisplay = true;
        operand1 = 0;
        operand2 = 0;
        result = 0;
        String prevSign = "";
        lastPoint = false;
        firstOperation = true;
        clearDisplay = true;
        displayView.setText("0");
        resultView.setText("");
    }

    /*Кнопка действия */
    public void computation(char actionSign) {
        if (firstOperation == true) {//расчет если стоит признак первой операции - либо только загрузили/сбросили, либо нажали "="
            if (prevSign == '=') {
                resultView.setText(resultView.getText().toString() + "\n" + displayView.getText() + actionSign);//для слуая когда предыдущая операция была "="
            } else {
                resultView.setText(resultView.getText().toString() + displayView.getText() + actionSign); //для загрузили/сбросили
            }
            operand1 = Float.parseFloat(displayView.getText().toString());
            clearDisplay = true;
            firstOperation = false;
        } else {//здесь выполняем расчет по нажатию кнопки с мат. операциями +-/*
            operand2 = Float.parseFloat(displayView.getText().toString());

            result = payment(prevSign);  //выполняем математические операции. вызываем метод и передаем туда предыдущее действие
            String paymentResult = Float.toString(result).replaceAll("\\.?0*$", "");
            resultView.setText(resultView.getText().toString() + displayView.getText() + "=" + paymentResult + "\n" + paymentResult + actionSign);
            scrollResult.scrollTo(0, scrollResult.getBottom());
            operand1 = result;
            clearDisplay = true;
        }
        prevSign = actionSign;
    }

    /*Кнопка равно*/
    private void equalsClick() {
        if (prevSign != '=') {
            operand2 = Float.parseFloat(displayView.getText().toString());

            result = payment(prevSign); //выполняем математические операции. вызываем метод и передаем туда предыдущее действие
            String paymentResult = Float.toString(result).replaceAll("\\.?0*$", "");
            resultView.setText(resultView.getText().toString() + displayView.getText() + "=" + paymentResult);
            clearDisplay = true;
            prevSign = '=';
            firstOperation = true;
        }
        scrollResult.scrollTo(0, scrollResult.getBottom());
        firstOperation = true;
        clearDisplay = true;
        displayView.setText("0");
    }

    /*математические операции*/
    private float payment(char sign) {
        switch (sign) {
            case ('+'): {
                return (operand1 + operand2);
            }
            case ('-'): {
                return (operand1 - operand2);
            }
            case ('/'): {                            // деление на 0 надо написать
                return (operand1 / operand2);
            }
            case ('*'): {
                return (operand1 * operand2);
            }
        }
        return 0;
    }

}
