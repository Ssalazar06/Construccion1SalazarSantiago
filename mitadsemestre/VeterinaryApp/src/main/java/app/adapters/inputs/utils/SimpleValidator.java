package app.adapters.inputs.utils;

import org.springframework.stereotype.Component;

@Component
public class SimpleValidator {
    public static String stringValidator(String value, String element) throws Exception{
        if(value ==null || value.equals("")){
            throw new Exception(element + " no tiene un valor valido");
        }
        return value;
    }

    public static long longValidator(String value, String element) throws Exception{
        try{
            return Long.parseLong(stringValidator(value, element));
        }
        catch(Exception e){
            throw new Exception(element + " debe ser un valor numérico");
        }
    }
    public int intValidator(String value, String element) throws Exception{
        try{
            return Integer.parseInt(stringValidator(value, element));
        }
        catch(Exception e){
            throw new Exception(element + " debe ser un valor numérico");
        }
    }

    public double doubleValidator(String value, String element) throws Exception{
        try{
            return Double.parseDouble(stringValidator(value, element));
        }
        catch(Exception e){
            throw new Exception(element + " debe ser un valor numérico");
        }
    }
}
