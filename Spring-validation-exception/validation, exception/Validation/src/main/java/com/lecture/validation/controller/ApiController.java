package com.lecture.validation.controller;

import com.lecture.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public ResponseEntity user(@RequestBody @Valid User user, BindingResult bingingResult) {
        if (bingingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bingingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();


                System.out.println("field : " + field.getField());
                System.out.println(message);
                sb.append("field :" + field.getField());
                sb.append("message : " + message);

            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        System.out.println(user);

        return ResponseEntity.ok(user);
    }


//    옛날 방식.
//    @PostMapping("/user2")
//    public ResponseEntity user2(@RequestBody User user){
//        System.out.println(user);
//        if(user.getPhoneNumber() == "xxx-xxxx-xxxx"){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }
//        return ResponseEntity.ok(user);
//    }
}
