package com.example.huarongpuzzlegame.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends Result {
        private final T data;

        Success(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    // Error sub-class
    final static class Error extends Result {
        private final Exception error;

        Error(Exception error) {
            this.error = error;
        }

        Exception getError() {
            return error;
        }
    }
}
