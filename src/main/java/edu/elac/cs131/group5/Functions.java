public class Functions {
    public static double linear( double x){
        // f(x)= 2x 
        return 2 * x; 
    }

    public static double quadratic( double x){
       // f(x)= x^2 
        return x * x; 
    }

    public static double exponential( double x){
        // f(x)= 2^x 
        return Math.pow(2,x); 
    }
    
    public static double logarithmic( double x){
        // f(x)= log2(x) 
        if (x <= 0) {
            return Double.NaN;
        }
        return Math.log(x) / Math.log(2); 
    }

    /* functionName = "linear", "quadratic", "exponential", "logarithmic"
     * minX = minimum x value start of the graph
     * maxX = maximum x value end of the graph
     numPoints = number of points to generate       
    */
    public static double[][] GraphPoints(String functionName, double minX, double maxX, int numPoints) {
        double[][] points = new double[numPoints][2];
        double step = (maxX - minX) / (numPoints - 1);
        
        for (int i = 0; i < numPoints; i++) {
            double x = minX + i * step;
            double y;
            
            switch (functionName.toLowerCase()) {
                case "linear":
                    y = linear(x);
                    break;
                case "quadratic":
                    y = quadratic(x);
                    break;
                case "exponential":
                    y = exponential(x);
                    break;
                case "logarithmic":
                    y = logarithmic(x);
                    break;
                default:
                    y=0;
            }
            points[i][0] = x;
            points[i][1] = y;
        }
        return points;
    }
    /*  Test individual functions
        public static void main(String[] args) {

        System.out.println("f(x) = 2x: f(2) = " + linear(2));
        System.out.println("f(x) = x²: f(3) = " + quadratic(3));
        System.out.println("f(x) = 2^x: f(5) = " + exponential(5));
        System.out.println("f(x) = log₂(x): f(8) = " + logarithmic(8));
        System.out.println();
        }
    */    
}