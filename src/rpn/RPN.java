/*
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
            " 2 + ( - 3 * 5 ) ", //problemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm.
            "(( 2 + 3 ) * 5 ) * 8 ",   //problemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm.
            "5 * 10 + ( 15 - 20 ) )  - 25",
            " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9"   //problemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm.
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

                // Supply the necessary codes to determine the validity of your answers
            } else
            {
                System.out.println("Expression is not balanced\n");
            }
            
            //testingggg delete
            System.out.println("------------------------------------------------------------------------------------------------------");
            //testingggg delete
        }
    }
}