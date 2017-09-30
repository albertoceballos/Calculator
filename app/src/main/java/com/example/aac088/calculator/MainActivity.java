package com.example.aac088.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView screenAns, screenMath;

    private Button btnSin, btnCos, btnTan, btnFactorial, btnRaise;
    private Button btnClear, btnBackSpace, btnLeftParentesis, btnRightParentesis,btnSquare;
    private Button btn7,btn9,btn8,btnDiv,btnMod;
    private Button btn4,btn5,btn6,btnMult,btnInverse;
    private Button btn1,btn2,btn3,btnRest,btnAdd,btnResult;
    private Button btn0,btnDot,btnPi;

    private StringBuilder textMath = new StringBuilder("");
    private StringBuilder textAns = new StringBuilder("0");
    private StringBuilder screenTextMath = new StringBuilder("");
    double num1 =0,num2=0,ans=0;

    int checkSubmit =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"Lato-Regular.ttf",true);

        screenAns = (TextView) findViewById(R.id.screen);
        screenMath = (TextView) findViewById(R.id.preview);

        btn0 = (Button) findViewById(R.id.zero_btn);
        btn0.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.one_btn);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.two_btn);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.three_btn);
        btn3.setOnClickListener(this);

        btn4 = (Button) findViewById(R.id.four_btn);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.five_btn);
        btn5.setOnClickListener(this);

        btn6 = (Button) findViewById(R.id.six_btn);
        btn6.setOnClickListener(this);

        btn7 = (Button) findViewById(R.id.seven_btn);
        btn7.setOnClickListener(this);

        btn8 = (Button) findViewById(R.id.eight_btn);
        btn8.setOnClickListener(this);

        btn9 = (Button) findViewById(R.id.nine_btn);
        btn9.setOnClickListener(this);

        btnSin = (Button) findViewById(R.id.sin_btn);
        btnSin.setOnClickListener(this);

        btnCos = (Button) findViewById(R.id.cos_btn);
        btnCos.setOnClickListener(this);

        btnTan = (Button) findViewById(R.id.tan_btn);
        btnTan.setOnClickListener(this);

        btnFactorial = (Button) findViewById(R.id.fact_btn);
        btnFactorial.setOnClickListener(this);

        btnRaise = (Button) findViewById(R.id.exp_btn);
        btnRaise.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.clear_btn);
        btnClear.setOnClickListener(this);

        btnBackSpace = (Button) findViewById(R.id.back_btn);
        btnBackSpace.setOnClickListener(this);

        btnLeftParentesis = (Button) findViewById(R.id.left_parentesis_btn);
        btnLeftParentesis.setOnClickListener(this);

        btnRightParentesis = (Button) findViewById(R.id.right_parentesis_btn);
        btnRightParentesis.setOnClickListener(this);

        btnSquare = (Button) findViewById(R.id.sqrt_btn);
        btnSquare.setOnClickListener(this);

        btnDot = (Button) findViewById(R.id.dot_btn);
        btnDot.setOnClickListener(this);

        btnPi = (Button) findViewById(R.id.pi_btn);
        btnPi.setOnClickListener(this);

        btnDiv = (Button) findViewById(R.id.div_btn);
        btnDiv.setOnClickListener(this);

        btnMod = (Button) findViewById(R.id.percent_btn);
        btnMod.setOnClickListener(this);

        btnMult = (Button) findViewById(R.id.mult_btn);
        btnMult.setOnClickListener(this);

        btnInverse = (Button) findViewById(R.id.inverse_btn);
        btnInverse.setOnClickListener(this);

        btnRest = (Button) findViewById(R.id.minus_btn);
        btnRest.setOnClickListener(this);

        btnAdd = (Button) findViewById(R.id.add_btn);
        btnAdd.setOnClickListener(this);

        btnResult = (Button) findViewById(R.id.equals_btn);
        btnResult.setOnClickListener(this);
    }

    public void error(){
        screenAns.setText("MATH ERROR!");
        textAns = textMath = screenTextMath = new StringBuilder("");
    }

    public void submit(String[] elementMath){
        Calculate calculate = new Calculate();
        if(textMath.length()>0){
            try{
                if(!calculate.check_error) elementMath = calculate.processString(textMath.toString());
                if(!calculate.check_error) elementMath = calculate.postfix(elementMath);
                if(!calculate.check_error) textAns = new StringBuilder(calculate.valueMath(elementMath));
                if(calculate.check_error) textAns = new StringBuilder("MATH ERROR!");
                screenAns.setText(textAns);

                screenTextMath = new StringBuilder();
                textMath = new StringBuilder();
                checkSubmit=1;
            }catch (Exception e){
                error();
            }
        }
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        String elementMath[] = null;

        if(id == R.id.zero_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("0");
                screenTextMath.append("0");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.one_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("1");
                screenTextMath.append("1");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.two_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("2");
                screenTextMath.append("2");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.three_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("3");
                screenTextMath.append("3");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.four_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("4");
                screenTextMath.append("4");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.five_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("5");
                screenTextMath.append("5");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.six_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("6");
                screenTextMath.append("6");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.seven_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("7");
                screenTextMath.append("7");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.eight_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("8");
                screenTextMath.append("8");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.nine_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("9");
                screenTextMath.append("9");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.dot_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append(".");
                screenTextMath.append(".");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.pi_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("π");
                screenTextMath.append("π");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.add_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("+");
                screenTextMath.append("+");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.minus_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("-");
                screenTextMath.append("-");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.mult_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("*");
                screenTextMath.append("*");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.div_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("/");
                screenTextMath.append("/");
            }
            screenMath.setText(screenTextMath);
        }else if(id == R.id.fact_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("!");
                screenTextMath.append("!");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.exp_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("^(");
                screenTextMath.append("^(");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.sqrt_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("@");
                screenTextMath.append("√");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.sin_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("s(");
                screenTextMath.append("Sin(");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.cos_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("c(");
                screenTextMath.append("Cos(");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.tan_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("t(");
                screenTextMath.append("Tan(");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.left_parentesis_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append("(");
                screenTextMath.append("(");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.right_parentesis_btn){
            if(screenTextMath.length()<48){
                if(checkSubmit==1){
                    screenTextMath = new StringBuilder("");
                    textMath = new StringBuilder("");
                    checkSubmit=0;
                }
                textMath.append(")");
                screenTextMath.append(")");
            }
            screenMath.setText(screenTextMath);
        }
        else if(id == R.id.percent_btn){
            if(screenTextMath.length()==0) screenTextMath = new StringBuilder("0");
            screenTextMath = new StringBuilder("("+screenTextMath+")%");
            screenMath.setText(screenTextMath.toString());
            if(checkSubmit==0) submit(elementMath);
            textMath = new StringBuilder(textAns+ "/100");
            submit(elementMath);
        }
        else if(id == R.id.inverse_btn){
            if(screenTextMath.length()==0) screenTextMath = new StringBuilder("0");
            screenTextMath = new StringBuilder("1/("+screenTextMath+")");
            screenMath.setText(screenTextMath);
            if(checkSubmit==0) submit(elementMath);
            textMath = new StringBuilder("1/"+ textAns);
            submit(elementMath);
        }
        else if(id==R.id.equals_btn){
            submit(elementMath);
        }
        else if(id==R.id.clear_btn){
            textMath=new StringBuilder("");
            screenTextMath = new StringBuilder("");
            textAns = new StringBuilder("0");
            screenAns.setText(textAns);
            screenMath.setText("|");
        }
        else if(id==R.id.back_btn){
            if(screenMath.length()>0){
                char c = textMath.charAt(textMath.length()-1);
                if(textMath.length() > 1 && c=='(' && textMath.charAt(textMath.length()-2) == '^'){
                    screenTextMath= new StringBuilder(screenTextMath.substring(0,screenTextMath.length()-2));
                    textMath = new StringBuilder(textMath.substring(0,textMath.length()-2));
                }
                else if(textMath.length() >1 && c=='(' && (textMath.charAt(textMath.length()-2)=='s' || textMath.charAt(textMath.length()-2)=='c' || textMath.charAt(textMath.length()-2)=='t')){
                    textMath = new StringBuilder(textMath.substring(0,textMath.length()-2));
                    screenTextMath = new StringBuilder(screenTextMath.substring(0,screenTextMath.length()-4));
                }
                else{
                    textMath = new StringBuilder(textMath.substring(0,textMath.length()-1));
                    screenTextMath = new StringBuilder(screenTextMath.substring(0,screenTextMath.length()-1));
                }
            }
            screenMath.setText(screenTextMath);
        }
    }
}
