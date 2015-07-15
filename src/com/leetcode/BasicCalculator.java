package com.leetcode;

import java.util.Stack;

/**
 * Created by WentaoD on 6/23/15.
 * Implement a basic calculator to evaluate a simple expression string.
 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 You may assume that the given expression is always valid.
 */


public class BasicCalculator {

  static class Solution {
    String expression;
    int index;
    boolean isOperand;

    public int calculate(String s) {
      if(s==null || s.length()<1)
        return 0;
      expression = s + ')';
      index = 0;
      isOperand = false;
      return calc();
    }

    // calculate expression enclosed by the same level of (...)
    // for lower level, recurse.
    private int calc() {
      int result = 0;
      int operand = 0;
      char operator = '+';

      while(index < expression.length()) {
        char c = expression.charAt(index++);
        switch(c) {
          case '+':
          case '-':
            result = operator == '+' ? result + operand : result - operand;
            isOperand = false;
            operator = c;
            break;
          case '(': // Met (, go to deeper level.
            isOperand = false;
            operand = calc();
            break;
          case ')': // Met ), return to upper level.
            isOperand = false;
            return operator == '+' ? result + operand : result - operand;
          case ' ':
            break;
          default:
            if(isOperand)
              operand = operand * 10 + c - '0';
            else {
              operand = c - '0';
              isOperand = true;
            }
        }
      }

      return result;
    }

    public boolean verify(String expr, int res) {
      boolean result = calculate(expr) == res;
      if(!result)
        System.out.println("Wrong! " + expr + " not equal to " + res);
      return result;
    }

    public void test() {
      verify("16 -(12-15)+ 11", 30);
      verify("33", 33);
      verify("", 0);
      verify("11 -2", 9);
      verify("(0+(33) )", 33);
      verify("(1-2) -(3-2)", -2);
    }
  }

  /*Implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
    You may assume that the given expression is always valid.*/
  static class SolutionII {
    String expression;
    int index;

    enum TokenType {OPRAND, MUL, DIV, PLS, SUB};
    static class Token {
      TokenType type;
      String token;
      Token(TokenType t, String s) {type=t; token=s;};
    };

    // start with index pointing to the beginning of a token.
    // end with index pointing to beginning of next token.
    // Space is skipped.
    Token getNextToken() {
      boolean isOprand = false;
      StringBuilder sb = new StringBuilder();
      while(index < expression.length()) {
        char c = expression.charAt(index);
        if(c>='0' && c<='9') {
          isOprand = true;
          sb.append(c);
          index++;
        }
        else {
          if (isOprand)
            return new Token(TokenType.OPRAND, sb.toString());
          index++;
          switch (c) {
            case ' ':
              break;
            case '+':
              return new Token(TokenType.PLS, "+");
            case '-':
              return new Token(TokenType.SUB, "-");
            case '*':
              return new Token(TokenType.MUL, "*");
            case '/':
              return new Token(TokenType.DIV, "/");
          }
        }
      }
      return null;
    }

    public int calculate(String s) {
      if(s==null || s.length()<1)
        return 0;
      expression = "0+" + s + "+0";
      index = 0;
      return lower();
    }

    // handles + or - only.
    int lower() {
      int result = 0;
      int operand = 0;
      TokenType op = TokenType.PLS;

      while(true) {
        Token token = getNextToken();
        if(token==null) //end
          return result;

        switch(token.type) {
          case OPRAND:
            operand = Integer.valueOf(token.token);
            break;
          case PLS:
          case SUB:
            result = result + (op==TokenType.PLS ? operand : -operand);
            op = token.type;
            break;
          case MUL:
          case DIV:
            int higher = higher(operand, token.type);
            result = result + (op==TokenType.PLS ? higher : -higher);
            operand = 0;
            op = expression.charAt(index-1) == '+' ? TokenType.PLS : TokenType.SUB;
            break;
        }
      }
    }

    // handles * or / only.
    // operand is the first operand in */, op is * or /.
    // upon getting in, index points to an operand.
    // returns result of expression of */. Upon return, index points to an operand.
    int higher(int result, TokenType op) {
      int operand = 0;
      while(true) {
        Token token = getNextToken();
        if(token==null)
          return result;

        if(token.type == TokenType.OPRAND)
          operand = Integer.valueOf(token.token);
        else {
          if(op == TokenType.MUL)
            result = result * operand;
          else
            result = result / operand;

          switch (token.type) {
            case MUL:
            case DIV:
              op = token.type;
              break;
            case PLS:
            case SUB:
              return result;
          }
        }

      }
    }

      public boolean verify(String expr, int res) {
      int result = calculate(expr);
      if(result != res)
        System.out.println("Wrong! " + expr + " is calculated as " + result + " but we expect " + res);
      return result != res;
    }

    public void test() {
      verify("", 0);
      verify("9", 9);
      verify("16 -2- 15+ 11", 10);
      verify(" 3*5 *2 / 11  ", 2);
      verify("16 -2* 15+ 11*2", 8);
    }
  }


  public static void main(String args[]) {
    SolutionII sol = new SolutionII();
    sol.test();
  }
}
