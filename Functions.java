public class Functions {
    public static double Linear( double x){
        /*f(x)= 2x */
        return 2 * x; 
    }

    public static double Quadratic( double x){
       /* f(x)= x^2 */
        return x * x; 
    }

    public static double Exponential( double x){
        /* f(x)= 2^x */
        return Math.pow(2,x); 
    }
    
    public static double Logarithmic( double x){
        /* f(x)= log2(x) */
        if (x <= 0) {
            return Double.NaN;
        }
        return Math.log(x) / Math.log(2); 
    }

}
