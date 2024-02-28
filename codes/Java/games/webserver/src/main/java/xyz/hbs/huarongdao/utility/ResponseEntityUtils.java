package xyz.hbs.huarongdao.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;

public class ResponseEntityUtils {

    public static <T> ResponseEntity<T> badRequest(T body, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("message", UriUtils.encode(message, "UTF-8"));

        headers.add("Access-Control-Expose-Headers", "message");

        return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<T> internalServerError(T body, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("message", UriUtils.encode(message, "UTF-8"));

        headers.add("Access-Control-Expose-Headers", "message");

        return new ResponseEntity<>(body, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
