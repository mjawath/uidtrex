/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

/**
 *
 * @author nnjj
 */
public class MathUtil {
    public static Double multiply(Double d1,Double d2){
        if(d1==null || d2== null){
        return 0d;
        }
//        BigDecimal t=new BigDecimal(d1).multiply(d2);
        Double t=d1*d2;
        return t;
    }
    
}
