package tr.com.migros.tyildirim.order.exception;

/**
 * @author Taner YILDIRIM
 */
public class BusinessException extends Exception {

    public BusinessException(String message){
        super(message);
    }
}
