package model.exception;

public class InvalidInputStringException extends RuntimeException {
    private final String errorMsg = "Неверный формат входной строки!";

    @Override
    public String getMessage(){
        return '\n' + errorMsg;
    }
}
