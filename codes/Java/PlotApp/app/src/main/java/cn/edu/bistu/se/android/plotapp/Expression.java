package cn.edu.bistu.se.android.plotapp;

/**
 * Created by hbs on 2015-11-2.
 */
public class Expression {

    enum Token
    {
        T_Eoe,//end of exp
        T_Number,//数字
        T_Plus,// +
        T_Minus,// _
        T_Mul, //*
        T_Div,// /
        T_Mod, //%
        T_Pow,// ^幂
        T_LB,//左括号
        T_RB,//右括号
        T_ID,//标识符
        T_Unknown //其他
    };


    protected String strExp;


    protected Expression()
    {


    }




    private double dVal;
    private String strVal;




    private int nExpIndex;

    private int nErrorCount;

    public int ErrorCount()
    {
        return nErrorCount;
    }


    private void SetError(String str)
    {
        nErrorCount++;
    }

    /// <summary>
    /// 返回下一个token
    /// </summary>
    /// <returns>下一个token</returns>
    private Token GetNextToken()
    {
        char c;
        while (nExpIndex >= 0 && nExpIndex < strExp.length())
        {
            switch (strExp.charAt(nExpIndex))
            {
                case ' ':
                case '\t':
                case '\f':
                case '\r':
                case '\n':
                    nExpIndex++;
                    break;

                case '+':
                    nExpIndex++;
                    return Token.T_Plus;

                case '-':
                    nExpIndex++;
                    return Token.T_Minus;

                case '*':
                    nExpIndex++;
                    return Token.T_Mul;

                case '/':
                    nExpIndex++;
                    return Token.T_Div;

                case '%':
                    nExpIndex++;
                    return Token.T_Div;

                case '^':
                    nExpIndex++;
                    return Token.T_Pow;

                case '(':
                    nExpIndex++;
                    return Token.T_LB;

                case ')':
                    nExpIndex++;
                    return Token.T_RB;


                //解析数字，数字的合法形式为[0-9]+('.'[0-9]+)?,即要么是整数，要么是小数
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    dVal = 0;

                    boolean bPoint = false;
                    int nPoint = 0;
                    while (true)
                    {
                        c = strExp.charAt(nExpIndex++);

                        if (bPoint)
                        {
                            nPoint++;
                            double dtemp = (double)(c - '0');
                            for (int i = 0; i < nPoint; i++)
                                dtemp /= 10;
                            dVal = dVal + dtemp;
                        }
                        else
                            dVal = dVal * 10 + (c - '0');

                        if (nExpIndex >= strExp.length())
                            break;

                        c = strExp.charAt(nExpIndex);

                        if (c == '.')
                        {

                            if (bPoint)
                            {
                                //两个小数点
                                String str = nExpIndex+":小数点多余1个";

                                SetError(str);

                                nExpIndex++;
                                break;
                            }
                            else
                            {
                                if (nExpIndex >= strExp.length())
                                {
                                    String str = nExpIndex+":小数点后面为空";

                                    SetError(str);
                                    break;
                                }

                                nExpIndex++;
                                c = strExp.charAt(nExpIndex);

                                if (c < '0' || c > '9')
                                {
                                    String str = nExpIndex+":小数点后面不是数字";

                                    SetError(str);
                                    break;
                                }


                                bPoint = true;

                                continue;
                            }
                        }

                        if (c < '0' || c > '9')
                            break;

                    }
                    return Token.T_Number;


                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '_':
                    strVal = "";
                    c = strExp.charAt(nExpIndex);
                    do
                    {

                        strVal += c;

                        nExpIndex++;

                        if (nExpIndex >= strExp.length())
                            break;

                        c = strExp.charAt(nExpIndex);
                    } while ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_');
                    return Token.T_ID;

                default:
                {
                    String str = nExpIndex+":其他错误";

                    SetError(str);

                    nExpIndex++;
                    return Token.T_Unknown;
                }
            }
        }

        return Token.T_Eoe;
    }

    void skip(Token t)
    {
        if (tok != t)
        {
            String str =nExpIndex+"该位置并不是'{1}'"+t;

            SetError(str);
        }
        tok = this.GetNextToken();
    }

    private Token tok;

    protected double evalf(String str)
    {
        strExp = str;
        nExpIndex = 0;
        nErrorCount = 0;

        double dResult = 0;
        tok = GetNextToken();
        if (tok != Token.T_Eoe)
            dResult = sum();

        return dResult;



    }
    private double power()
    {
        double left = unary();

        double right = 0;
        while (tok == Token.T_Pow)
        {
            tok = GetNextToken();
            right = unary();

            left = Math.pow(left, right);
        }
        return left;
    }

    private double prod()
    {
        double left = power();

        double right = 0;
        while (tok == Token.T_Mul || tok == Token.T_Div || tok == Token.T_Mod)
        {
            Token t = tok;
            tok = GetNextToken();
            right = power();
            if (t == Token.T_Mul)
                left = left * right;
            else if (t == Token.T_Div)
                left = left / right;
            else
                left = left % right;
        }
        return left;
    }

    private double sum()
    {

        double left = prod();


        double right = 0;

        while (tok == Token.T_Plus || tok == Token.T_Minus)
        {
            Token t = tok;
            tok = GetNextToken();
            right = prod();
            if (t == Token.T_Plus)
                left = left + right;
            else
                left = left - right;
        }
        return left;

    }

    private double unary()
    {
        double dv = 0;
        switch (tok)
        {
            case T_Number:

                dv = this.dVal;
                tok = GetNextToken();
                break;

            //字符串
            case T_ID:
                switch (this.strVal)
                {
                    case "PI":
                    case "pi":
                    case "Pi":
                        dv = Math.PI;
                        break;


                    case "sin":
                        tok = GetNextToken();
                        skip(Token.T_LB);
                        dv = sum();
                        skip(Token.T_RB);
                        dv = Math.sin(dv);
                        break;

                    case "cos":
                        tok = GetNextToken();
                        skip(Token.T_LB);
                        dv = sum();
                        skip(Token.T_RB);
                        dv = Math.cos(dv);
                        break;

                    case "tg":
                        tok = GetNextToken();
                        skip(Token.T_LB);
                        dv = sum();
                        skip(Token.T_RB);
                        dv = Math.tan(dv);
                        break;

                    case "ctg":
                        skip(Token.T_LB);
                        dv = sum();
                        skip(Token.T_RB);
                        dv = 1 / Math.tan(dv);
                        break;

                    default:
                    {
                        String str =strVal+":非法函数或变量";

                        SetError(str);
                    }
                    break;

                }

                break;

            //左括号
            case T_LB:
                skip(Token.T_LB);
                dv = sum();
                skip(Token.T_RB);

                break;

            //一元减
            case T_Minus:
                tok = GetNextToken();
                dv = -unary();
                break;

            //一元加
            case T_Plus:
                tok = GetNextToken();
                dv = -unary();
                break;

        }

        return dv;
    }
}
