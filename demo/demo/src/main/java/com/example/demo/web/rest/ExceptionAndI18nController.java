package com.example.demo.web.rest;

import com.example.demo.service.testApiService;
import com.example.demo.util.BodyResponseDTO;
import com.example.demo.util.MessageUtils;
import com.example.demo.util.RestResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/test")
public class ExceptionAndI18nController {

    private final MessageUtils messageUtils;
    private final testApiService testApiService;

    public static final String ACCOUNT_SID = "ACd87d2XXXXXX";
    public static final String AUTH_TOKEN = "000d06XXXXXXX";
    public static final String SERVICE_SID = "VA759XXXXXX";
    private final String rawNumber = "+19388888119";
    private final String myNumber = "+84586231178";
    private final String sieuNumber = "+84358747744";
    private final String sieuNumber1 = "+84358747744";

    public ExceptionAndI18nController(MessageUtils messageUtils, com.example.demo.service.testApiService testApiService) {
        this.messageUtils = messageUtils;
        this.testApiService = testApiService;
    }

    @GetMapping("/")
    public ResponseEntity<BodyResponseDTO<Object>> getAllABC(){
        return RestResponseWrapper.getSuccess( testApiService.testException(), messageUtils);
    }


    @GetMapping(value = "/generateOTP")
    public ResponseEntity<String> generateOTP(){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.verify.v2.Service service = com.twilio.rest.verify.v2.Service.creator("kiennt_98_vn_Verify_Service").create(); // Replace YOUR_SERVICE_SID with your actual Verify Service SID
        Verification verification = Verification.creator(
                SERVICE_SID,
                String.valueOf(new PhoneNumber(myNumber)),
                "sms")
                .create();


        System.out.println(verification.getStatus());

        log.info("OTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<>("Your OTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @GetMapping("/verifyOTP")
    public ResponseEntity<?> verifyUserOTP(@RequestParam String codeVer) throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            com.twilio.rest.verify.v2.Service service = com.twilio.rest.verify.v2.Service.creator("kiennt_98_vn_Verify_Service").create();
            VerificationCheck verificationCheck = VerificationCheck.creator(
                    SERVICE_SID)
                    .setTo("+84586231178")
                    .setCode(codeVer)
                    .create();

            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("This user's verification has been completed successfully", HttpStatus.OK);
    }

    // Find your Account Sid and Token at twilio.com/user/account


//    Random rand = new Random();
//    int otp = rand.nextInt(999999);
    // Send the SMS OTP using the Twilio API
//            Message message = Message.creator(
//                    new PhoneNumber(recipient),
//                    new PhoneNumber("+19388888119"),
//                    "Xin chào kĩ sư nhé, tôi kiên đây, nếu kĩ sư nhận đc mail này thì cap màn hình nhắn tôi nhé test mã OTP hehehe: " + otp)
//                    .create();
//
//            System.out.println("SMS OTP sent to " + recipient + " with message SID: " + message.getSid());
}
