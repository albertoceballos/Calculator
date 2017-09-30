package com.example.aac088.calculator;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by aac088 on 9/29/2017.
 */

public class Calculate {
    boolean  check_error = false;

    public String  standardizeDouble(double num){
        int a = (int) num;
        if(a==num){
            return Integer.toString(a);
        }else{
            return Double.toString(num);
        }
    }

    public boolean isCharPi(char c){
        if(c=='π'){
            return true;
        }else{
            return false;
        }
    }

    public boolean isNumPi(double num){
        if(num == Math.PI){
            return true;
        }else{
            return false;
        }
    }

    public boolean isNum(char c){
        if(Character.isDigit(c) || isCharPi(c)){
            return true;
        }else{
            return false;
        }
    }

    public String numToString(double num){
        if(isNumPi(num)){
            return "π";
        }else{
            return standardizeDouble(num);
        }
    }

    public double stringToNum(String s){
        if(isCharPi(s.charAt(0))){
            return Math.PI;
        }else{
            return Double.parseDouble(s);
        }
    }

    public boolean isOperator(char c){
        char operator[] = {'+','-','*','/','^','~','s','c','t','@','!','%',')','('};

        Arrays.sort(operator);
        if(Arrays.binarySearch(operator,c) > -1){
            return true;
        }else{
            return false;
        }
    }

    public int priority(char c){
        switch (c){
            case '+':case '-': return 1;
            case '*':case '/': return 2;
            case '~': return 3;
            case '@':case '!':case'^':return 4;
            case 's':case 'c':case't':return 5;
        }
        return 0;
    }

    public boolean isOneMath(char c){
        char  operator[] = { 's', 'c', 't', '@', '('};
        Arrays.sort(operator);
        if(Arrays.binarySearch(operator,c) > -1){
            return true;
        }else{
            return false;
        }
    }

    public String standardize(String s){
        String s1 = "";
        s = s.trim();
        s = s.replaceAll("\\s+"," ");
        int open = 0, close = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') open++;
            if (c==')')close++;
        }
        for( int i=0;i< (open-close);i++){
            s += ')';
        }
        for(int i=0;i<s.length();i++){
            if(i>0 && isOneMath(s.charAt(i)) && (s.charAt(i-1)==')' || isNum(s.charAt(i-1)))){
                s1 = s1 + "*";
            }
            if((i==0 || (i>0 && !isNum(s.charAt(i-1)))) && s.charAt(i) == '-' && isNum(s.charAt(i+1))){
                s1 = s1 + "~";
            }
            else if(i>0 && (isNum(s.charAt(i-1)) || s.charAt(i-1)==')') && isCharPi(s.charAt(i))){
                s1 = s1 + "*" + s.charAt(i);
            }
            else{
                s1 = s1+s.charAt(i);
            }
        }
        return s1;
    }

    public String[] processString(String sMath){
        String s1 = "", elementMath[] = null;
        sMath = standardize(sMath);
        Calculate calculate = new Calculate();
        for(int i =0;i<sMath.length();i++){
            char c = sMath.charAt(i);
            if(i<sMath.length()-1 && isCharPi(c) && !calculate.isOperator(sMath.charAt(i+1))){
                check_error = true;
                return null;
            }
            else
                if(!calculate.isOperator(c))
                    s1=s1+c;
            else s1= s1 + " " + c + " ";
        }

        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+", " ");
        elementMath = s1.split(" ");
        return  elementMath;
    }

    public String[] postfix(String[] elementMath){
        Calculate calculate = new Calculate();
        String s1 = "", E[];
        Stack<String> S = new Stack<>();
        for(int i =0 ;i<elementMath.length;i++){
            char c = elementMath[i].charAt(0);

            if(!calculate.isOperator(c)){
                s1 = s1 + elementMath[i] + " ";
            }else{
                if(c=='('){
                    S.push(elementMath[i]);
                }else{
                    if(c == ')'){
                        char c1;
                        do{
                            c1= S.peek().charAt(0);
                            if(c1 != '('){
                                s1 = s1 + S.peek() + " ";
                            }
                            S.pop();
                        }while(c1 != '(');
                    }else{
                        while(!S.isEmpty() && calculate.priority(S.peek().charAt(0)) >= calculate.priority(c)){
                            s1 = s1 + S.pop() + " ";
                        }
                        S.push(elementMath[i]);
                    }
                }
            }
        }
        while (!S.isEmpty()){
            s1 = s1 + S.pop() + " ";
        }
        E=s1.split(" ");
        return E;
    }

    public String valueMath(String[] elementMath){
        Stack<Double> S = new Stack<>();
        Calculate calculate = new Calculate();
        double num = 0.0;
        for(int i =0;i<elementMath.length;i++){
            char c = elementMath[i].charAt(0);
            if(isCharPi(c)){
                S.push(Math.PI);
            }else{
                if(!calculate.isOperator(c)){
                    S.push(Double.parseDouble(elementMath[i]));
                }else{
                    double num1 = S.pop();
                    switch (c){
                        case '~': num = -num1; break;
                        case 's':num = Math.sin(num1); break;
                        case 'c':num = Math.cos(num1);break;
                        case 't':num = Math.tan(num1);break;
                        case '%':num = num1/100;break;
                        case '@': {
                            if(num1 >= 0){
                                num = Math.sqrt(num1); break;
                            }
                            else check_error = true;
                        }
                        case '!':{
                            if(num1 >= 0 && (int)num1==num1){
                                num = 1;
                                for(int j=1;j<=(int)num1;j++){
                                    num = num*j;
                                }
                            }else check_error = true;
                        }
                        default: break;
                    }
                    if(!S.isEmpty()){
                        double num2 = S.peek();
                        switch (c){
                            case '+': num = num2+num1;S.pop();break;
                            case '-':num = num2 - num1;S.pop();break;
                            case '*': num = num2 * num1;S.pop();break;
                            case '/':{
                                if(num1 != 0) num = num2 / num1;
                                else check_error = true;
                                S.pop();break;
                            }
                            case '^': num= Math.pow(num2,num1);S.pop();break;
                        }
                    }
                    S.push(num);
                }
            }
        }
        return numToString(S.pop());
    }

}
