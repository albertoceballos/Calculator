package com.example.aac088.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String display="";
    private String currentOp="";
    private String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen=(TextView) findViewById(R.id.display);
        screenUpdate();
    }

    public void screenUpdate(){
        screen.setText(display);
    }

    public void onClickNumber(View v){
        if(result != ""){
            clear();
            screenUpdate();
        }
        Button b = (Button) v;
        display+=b.getText();
        screenUpdate();
    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/': return true;
            default: return false;
        }

    }

    public void onClickOperator(View v){
        if(display == "") return;

        Button b = (Button) v;
        if(result !=""){
            String _display=result;
            clear();
            display = _display;
        }

        if(currentOp != ""){
            Log.d("CalcX", ""+display.charAt(display.length() -1));
            if(isOperator(display.charAt(display.length()-1))){
                display=display.replace(display.charAt(display.length()-1),b.getText().charAt(0));
                screenUpdate();
                return;
            }else{
                getResult();
                display=result;
                result="";
            }
            currentOp=b.getText().toString();
        }
        display+=b.getText();
        currentOp=b.getText().toString();
        screenUpdate();
    }

    private void clear(){
        display="";
        currentOp="";
        result="";
    }

    public void onClickClear(View v){
        clear();
        screenUpdate();
    }

    public double operations(String a, String b, String operations){
        switch (operations){
            case "+": return Double.valueOf(a)+Double.valueOf(b);
            case "-": return Double.valueOf(a)-Double.valueOf(b);
            case "*": return Double.valueOf(a)*Double.valueOf(b);
            case "/": try{
                return Double.valueOf(a)/Double.valueOf(b);
            }catch(Exception e){
                Log.d("Calc",e.getMessage());
            }
            default:  return -1;
        }

    }

    private boolean getResult(){
        if(currentOp=="") return false;
        String[] operation = display.split(Pattern.quote(currentOp));
        if(operation.length < 2) return false;
        result = String.valueOf(operations(operation[0],operation[1],currentOp));
        return true;
    }

    public void onClickEqual(View v){
        if(display=="")return;
        if(!getResult())return;
        screen.setText(display + "\n" + String.valueOf(result));
    }


}
