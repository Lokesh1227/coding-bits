package model;

public class ErrorCode {
    public static String ERROR_INSTRUCTION_NOT_LOADED = "ERROR ! Unable to load instructions. Please check path of welcome file";
    public static String ERROR_NOT_RECOGNIZED = "Command not recognized ";
    public static String ERROR_NOT_SUPPORTED = "This command is not support ";

    public static String ERROR_PARAM_MISMATCH = "Invalid number of parameters.";
    public static String ERROR_INVALID_PARAMS = "Command parameters are invalid. Please verify and try again! ";
    public static String ERROR_CANVAS_NOT_READY = "Canvas is not ready yet !! Please create a canvas to begin !";
    public static String ERROR_STRAIGHT_LINE_SUPPORTED = "Only straight lines are supported";
    public static String ERROR_POINT_OUT_OF_BOUND = "One of the point is out of canvas range. Please try again !!";
}
