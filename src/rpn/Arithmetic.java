/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/
package rpn;

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Scanner;


public class Arithmetic
{
    private Stack stk;
    private String expression;
    private String postfix;
    private int length;

    Arithmetic(String s)
    {
        expression = s;
        postfix = "";
        length = expression.length();
        stk = new Stack();
    }    
       
    // Determine if parentheses are balanced.
    boolean isBalance()
    {
        int index = 0;
        boolean fail = false;

        try
        {
            while (index < length && !fail)
            {
                char ch = expression.charAt(index);

                switch (ch)
                {
                    case Constants.LEFT_NORMAL:
                        stk.push(new Character(ch));
                        break;
                    case Constants.RIGHT_NORMAL:
                         stk.pop();
                        break;
                    default:

                    break;
                }  // end of swtich
                index++;
            }  // end of while
        }  // end of try
        catch (EmptyStackException e)
        {
            System.out.println(e.toString());
            fail = true;
        }
        
        if (stk.empty() && !fail)
        {
            return true;
        } 
        else
        {            
            return false;            
        }        
    }

    // Convert expression to postfix notation
    void postfixExpression()
    {
        stk.clear(); // Re-using the stack object
        Scanner scan = new Scanner(expression);
        char current;
        // The algorithm for doing the conversion.... Follow the bullets
        while (scan.hasNext())
        {
            String token = scan.next();           

            if (isNumber(token)) // Bullet # 1
            {
                postfix = postfix + token + " ";
            } 
            else
            {
                current = token.charAt(0);

                if (isParentheses(current)) // Bullet # 2 begins 
                {
                    if (stk.empty() || current == Constants.LEFT_NORMAL)
                    {
                        stk.push(current);
                    } 
                    else if (current == Constants.RIGHT_NORMAL)
                    {
                        try
                        {   
                            boolean endLoop = false;
                            while(!endLoop)
                            {
                                String hello = stk.pop().toString();
                                
                                if(hello.equalsIgnoreCase("("))
                                {                                    
                                    endLoop = true;                                    
                                }
                                else
                                {
                                    postfix = postfix + hello + " ";
                                }                              
                            }
                        } 
                        catch (EmptyStackException e)
                        {                          
                           // System.out.println(e.toString());
                        }
                    }
                }// Bullet # 2 ends
                else if (isOperator(current))// Bullet # 3 begins
                {
                    if (stk.empty())
                    {
                        stk.push(new Character(current));
                    } 
                    else
                    {
                        try
                        {
                            // Remember the method peek simply looks at the top
                            // element on the stack, but does not remove it.

                            char top = (Character) stk.peek();
                            boolean higher = hasHigherPrecedence(top, current);

                            while (top != Constants.LEFT_NORMAL && higher)
                            {
                                postfix = postfix + stk.pop() + " ";
                                top = (Character) stk.peek();
                            }
                            stk.push(new Character(current));
                        } 
                        catch (EmptyStackException e)
                        {
                            stk.push(new Character(current));
                        }
                    }
                }// Bullet # 3 ends
            }
        } // Outer loop ends

        try
        {
            while (!stk.empty()) // Bullet # 4
            {       
                postfix = postfix + stk.pop() + " ";
            }
        } 
        catch (EmptyStackException e)
        {                                  
            System.out.println(e.toString());
        }
    }

    boolean isNumber(String str)
    {
        //define this method
        try
        {
          Double.parseDouble(str);
          return true;
        }
        catch(NumberFormatException e)
        {
          return false;
        }       
    }

    boolean isParentheses(char current)
    {
        // define this method;
        if (current == '(' || current == ')')
        {
            return true;
        }
        else
        {
            return false;
        }        
    }

    boolean isOperator(char ch)
    {
        // define this method        
        if (ch == '+' || ch == '-' || ch == '*' || ch == '*' || ch == '/')
        {
            return true;
        }
        else
        {
            return false;
        }   
    }

    boolean hasHigherPrecedence(char top, char current) 
    {
        // define this method
        if(top == '*' || top == '/')
        {
            return true;
        }       
        
        if((top == '+' || top == '-') && (current == '+' || current == '-'))
        {
            return true;
        }        
       
        return false;         
    }

    String getPostfix()
    {
        // define method
        return postfix;
    }  
    
    double operation(double t1, double t2, char operator)
    {      
        if (operator == '+')
        {
            return t2 + t1;
        }

        if (operator == '-')
        {
            return t2 - t1;
        }
        
        if (operator == '*')
        {
            return t2 * t1;
        }       
        
        return t2 / t1;       
    }
        
    void evaluateRPN() 
    {        
        stk.clear(); // Re-using the stack object.
        Stack intStack = new Stack();
        double result = 0.0;      
               
        Scanner scan = new Scanner(postfix);
     
        // Reading the expression.
        while (scan.hasNext())
        {
            String token = scan.next();
            
            // If Number check if it is and push in case of true.
            if (isNumber(token)) 
            {    
              stk.push(token);        
            } 
            // Else must be an operator.
            else
            {
                // Getting the two values and the operator conver to char.
                try
                {
                    double t1 = Double.valueOf(stk.pop().toString());  
                    double t2 = Double.valueOf(stk.pop().toString());                  
                    char current = token.charAt(0);
                    stk.push(operation(t1, t2, current)); // Does the calculation and push it to the stack.                   
                } 
                catch (EmptyStackException e)
                {                                          
                    System.out.println(e.toString() + ": NO ENOUGH NUMBERS IN THE STACK TO DO CALCULATIONS");
                }
            }
        }        
        
        try
        {
            System.out.println("The result is: " + stk.pop().toString());
            stk.clear();
        } 
        catch (EmptyStackException e)
        {
            System.out.println(e.toString() + " STACK EMPTY WHEN TRIYING TO GET THE RESULT FROM THE STACK");
        }
    }
}
/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/