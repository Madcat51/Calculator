package site.madcat.calculator;

public class Calculator {
    private String displayText;
    private String resultText;
    private boolean firstOperation;
    private boolean lastPoint;
    private boolean clearDisplay;
    private float operand1 = 0;
    private float operand2 = 0;
    private float result = 0;
    private char prevSign;

    public Calculator() {
        this.displayText = "0";
        lastPoint = false;
        clearDisplay = true;
        firstOperation = true;
        resultText = "";
    }

    public void addSymbolToDisplayText(String symbol) {
        if (clearDisplay == true) {
            displayText = symbol;
            clearDisplay = false;
        } else {
            displayText = displayText + symbol;
        }
    }

    /*back-Убрать последний знак */
    public void backSymbol() {
        char lastSymbol = displayText.charAt(displayText.length() - 1);
        if (displayText.length() > 1) {
            displayText = displayText.substring(0, displayText.length() - 1);
        } else {
            displayText = "0";
            clearDisplay = true;
        }
        if (lastSymbol == '.') {     //если была точка то ее можно опять ставить
            lastPoint = false;
        }
    }

    /*Изменение знака +/- */
    public void displaySign() {
        if (displayText.charAt(0) == '-') {
            displayText = displayText.substring(1);
        } else {
            displayText = "-" + displayText;
        }
    }

    /*Вывод запятой */
    public void displayPoint(String point) {
        if (lastPoint == false) {
            displayText = displayText + ".";
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
        displayText = "0";
        resultText = "";
    }

    /*Кнопка действия */
    public void computation(char actionSign) {
        if (firstOperation == true) {
            if (prevSign == '=') {
                resultText = resultText + "\n" + displayText + actionSign;
            } else {
                resultText = resultText + displayText + actionSign;
            }
            operand1 = Float.parseFloat(displayText);
            clearDisplay = true;
            firstOperation = false;
        } else {
            operand2 = Float.parseFloat(displayText);
            result = payment(prevSign);
            String paymentResult = Float.toString(result).replaceAll("\\.?0*$", "");
            resultText = resultText + displayText + "=" + paymentResult + "\n" + paymentResult + actionSign;
            //
            operand1 = result;
            clearDisplay = true;
        }
        prevSign = actionSign;
    }

    /*Кнопка равно*/
    public void equalsClick() {
        if (prevSign != '=') {
            operand2 = Float.parseFloat(displayText);
            result = payment(prevSign);
            String paymentResult = Float.toString(result).replaceAll("\\.?0*$", "");
            resultText = resultText + displayText + "=" + paymentResult;
            clearDisplay = true;
            prevSign = '=';
            firstOperation = true;
        }
        firstOperation = true;
        clearDisplay = true;
        displayText = "0";
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


    public String getResultText() {
        return resultText;
    }

    public String getDisplayText() {
        return displayText;
    }

}
