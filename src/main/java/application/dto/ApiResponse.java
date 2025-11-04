package application.dto;

public class ApiResponse {
    private boolean success;
    private Object data;
    private String message;

    public ApiResponse() {}

    public ApiResponse(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(true, data, null);
    }

    public static ApiResponse ok(Object data, String message) {
        return new ApiResponse(true, data, message);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
