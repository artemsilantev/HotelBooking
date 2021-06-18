package eu.senla.hotelBooking.controllers.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseBody {

    public static ResponseEntity<?> successOperation() {
        return new ResponseEntity<>(
                new HashMap<String, String>() {{
                    put("status","success");
                }},
                HttpStatus.OK
        );
    }

}
