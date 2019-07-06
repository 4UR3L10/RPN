/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/
/*
Output:
1-N.B  ')'
2-B but Missing a #
3-200
4-N.B  '('
5-185
*/

package rpn;

public class RPN
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Array.
        String s[] =
        {
            "5 + ) * ( 2",
            " 2 + ( - 3 * 5 ) ",
            "(( 2 + 3 ) * 5 ) * 8 ",  
            "5 * 10 + ( 15 - 20 ) )  - 25",
            " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9"   
        };
        
        //Going throught the array.
        for (int i = 0; i < s.length; i++)
        {
            // Instance of Arithmetic class and passing index[i] each time.
            Arithmetic a = new Arithmetic(s[i]);
                        
            if (a.isBalance())
            {
                System.out.println("Expression " + s[i] + " is balanced\n");
                a.postfixExpression();
                System.out.println("The post fixed expression is " + a.getPostfix());
                a.evaluateRPN();                
            } 
            else
            {
                System.out.println("Expression is not balanced\n");
            }            
            
            // Just to do the output easier to read.
            System.out.println("------------------------------------------------------------------------------------------------------");            
        }
    }
}

/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/