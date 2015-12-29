package Challenges;
import java.util.*;
import java.util.regex.*;

/**
 * Created by wentaod on 12/27/15.
 */
public class Calculator {
    Matcher matcher;
    HashMap<Character, Integer> precedence;

    public int calculate(String exp) {
        if(exp == null || exp.isEmpty())
            return 0;

        precedence = new HashMap<>();
        precedence.put('+', 2);
        precedence.put('-', 2);
        precedence.put('*', 3);
        precedence.put('/', 3);

        matcher = Pattern.compile("(\\d+)|(\\D)").matcher(exp);

        return calc();
    }

    int calc() {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        while(matcher.find()) {
            String token = matcher.group();
            char c = token.charAt(0);
            if(c == ' ')
                continue;
            else if(c == '(') {
                operands.push(calc()); // enter next level recursion
            }
            else if(c == ')') { // exit current level recursion
                break;
            }
            else if(c < '0' || c > '9') { //operators
                if(!operators.isEmpty() && precedence.get(operators.peek()) >= precedence.get(c)) // lower or equal rank operator
                    operands.push(reduce(operands, operators, precedence.get(c)));
                operators.push(c);
            }
            else //operands
                operands.push(Integer.valueOf(token));
        }
        return reduce(operands, operators, 0);
    }

    // reduce 2 operands and 1 operator each step, until all oprators are consumed
    // or operators stack top has lower precedence.
    int reduce(Stack<Integer> operands, Stack<Character> operators, int prc) {
        while(!operators.isEmpty() && precedence.get(operators.peek()) >= prc) {
            char op = operators.pop();
            int i2 = operands.pop();
            int i1 = operands.pop();
            int result = 0;
            if (op == '+')
                result = i1 + i2;
            else if (op == '-')
                result = i1 - i2;
            else if (op == '*')
                result = i1 * i2;
            else
                result = i1 / i2;
            operands.push(result);
        }
        return operands.pop();
    }

    static public void main(String args[]) {
        Calculator calc = new Calculator();
        System.out.println(calc.calculate("3 + 5*6 / (12 + 3*2 -  12 + 3/3-1)"));
    }
}
