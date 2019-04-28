package com.kharazmi.helpdesk.Controller;

import com.auth0.jwt.JWT;
import com.kharazmi.helpdesk.Model.*;
import com.kharazmi.helpdesk.Repository.*;
import com.kharazmi.helpdesk.Service.EmailSenderService;
import com.kharazmi.helpdesk.Service.EmailService;
import com.kharazmi.helpdesk.Util.*;
import com.kharazmi.helpdesk.kavenegar.sdk.KavenegarApi;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController extends Functions {
    @Autowired
    AdminModelRepository adminModelRepository;
    @Autowired
    RoleModelRepository roleModelRepository;
    @Autowired
    UserModelRepository userModelRepository;
    @Autowired
    TicketModelRepository ticketModelRepository;
    @Autowired
    EmailService emailService ;
    @Autowired
    EmailVerfyModelRepository emailVerifyRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    VerifyphonenumberModelRepository verifyphonenumberModelRepository;

    //its just a test!
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody String test () {
        return "yo! its fucking working :D";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ImportDemoData", produces = "application/json;charset=UTF-8")
    public void loginUser() throws Exception {


        RoleModel roleModel = new RoleModel();
        roleModel.setName("SuperAdmin");

        RoleModel roleModel1 = new RoleModel();
        roleModel1.setName("Admin");
        RoleModel roleModel2 = new RoleModel();
        roleModel2.setName("Support");
        roleModelRepository.save(roleModel);
        roleModelRepository.save(roleModel1);
        roleModelRepository.save(roleModel2);
    }


    //email address needed
        @RequestMapping(value = "/forgotPassUsingEmail" , method = RequestMethod.POST , produces = "application/json", consumes = "application/json")
    @ResponseBody
    public JsonModelResponse forgot (@RequestBody UserJsonModelForget user) {
        JsonModelResponse jsonModelResponse = new JsonModelResponse();
        if (Validator.isEmailValid(user.getEmail())) {
            Optional<UserModel> userModel = userModelRepository.findUserByEmail(user.getEmail());
            if (userModel.isPresent()) {
                int random = random();
                String message = "your verify code is " + random;
                UserModel usersave = userModelRepository.findByEmail(user.getEmail());
//                usersave.setIsEmailVerify((byte) random);
                usersave.setAuthCode(String.valueOf(random));
                userModelRepository.save(usersave);
                emailService.sendForgetMessage(user.getEmail(), "Forgot Password", message);
                jsonModelResponse.setResponse("message send");
                return jsonModelResponse;

            } else {
                jsonModelResponse.setResponse("user not found");
                return jsonModelResponse;
            }
        }else {
            jsonModelResponse.setResponse("wrong email");
            return jsonModelResponse;
        }
    }
    //farmoon started
    @RequestMapping(value = "/login" , method = RequestMethod.POST , produces = "application/json", consumes = "application/json")
    public @ResponseBody String login (@RequestBody JsonModel LoginData) throws JSONException {
        JSONObject j = new JSONObject();
        UserModel um = new UserModel();
        Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder();
        String securePass = encoderMD5.encodePassword(LoginData.getPassword(), null);
        String[] captcha= Functions.captchaGen();
        if (LoginData.getPassword()==null || LoginData.getUserName()==null){
            j.accumulate("message","Username or password is nulled.");
            return j.toString();
        }
        else if(LoginData.getCaptcha()!=null){
            if(LoginData.getUserName().matches(Varibles.EmailRegex)){
                um = userModelRepository.findByEmail(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    if(um.getLoginCaptchaText().equals(LoginData.getCaptcha()) && um.getIsEmailVerify()==1){
                        //jsonModelResponse.setStatus("step2ok");
                        //jsonModelResponse.setToken(token);
                        um.setStatus(1);
                        userModelRepository.save(um);
                        String token = createToken(um.getUserName(),Varibles.JWTkey, Varibles.JWTexpire);
                        j.accumulate("message","login request approved.");
                        j.accumulate("token",token);
                        j.accumulate("info",infoObjectCreator(um));
                        return j.toString();
                        //return jsonModelResponse;
                    }
                }
            }
            else if(LoginData.getUserName().matches(Varibles.PhoneNumberRegex)){
                um = userModelRepository.findByPhoneNumber(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    if(um.getLoginCaptchaText().equals(LoginData.getCaptcha()) && um.getIsEmailVerify()==1){
                        //jsonModelResponse.setStatus("step2ok");
                        String token = createToken(um.getUserName(),Varibles.JWTkey, Varibles.JWTexpire);
                        //jsonModelResponse.setToken(token);
                        um.setStatus(1);
                        userModelRepository.save(um);
                        j.accumulate("message","login request approved.");
                        j.accumulate("token",token);
                        j.accumulate("info",infoObjectCreator(um));
                        return j.toString();
                       // return jsonModelResponse;
                    }
                }
            }
            else {
                um = userModelRepository.findByUserName(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    if(um.getLoginCaptchaText().equals(LoginData.getCaptcha()) && um.getIsEmailVerify()==1){
                       // jsonModelResponse.setStatus("step2ok");
                        String token = createToken(um.getUserName(),Varibles.JWTkey, Varibles.JWTexpire);
                      //  jsonModelResponse.setToken(token);
                        um.setStatus(1);
                        j.accumulate("message","login request approved.");
                        j.accumulate("token",token);
                        j.accumulate("info",infoObjectCreator(um));
                        userModelRepository.save(um);
                        return j.toString();
                       // return jsonModelResponse;
                    }
                }
            }
            j.accumulate("message","login request not approved. captcha is regenrated.");
            j.accumulate("captcha",captcha[1]);
            um.setLoginCaptchaText(captcha[0]);
            userModelRepository.save(um);
            return j.toString();
        }
        else if(LoginData.getCaptcha()==null){
            j.accumulate("captcha",captcha[1]);
            j.accumulate("message","wait for captcha code");
            if(LoginData.getUserName().matches(Varibles.EmailRegex)){
                um = userModelRepository.findByEmail(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    um.setLoginCaptchaText(captcha[0]);
                    userModelRepository.save(um);
                    //run jwt token creator and send that
                }
                else{
                    //run step 2 with false start
                }
            }
            else if(LoginData.getUserName().matches(Varibles.PhoneNumberRegex)){
                um = userModelRepository.findByPhoneNumber(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    um.setLoginCaptchaText(captcha[0]);
                    userModelRepository.save(um);
                    //run jwt token creator and send that
                }
                else{
                    //run step 2 with false start
                }

            }
            else {
                um = userModelRepository.findByUserName(LoginData.getUserName());
                if(securePass.equals(um.getPassword())){
                    um.setLoginCaptchaText(captcha[0]);
                    userModelRepository.save(um);

                    //run jwt token creator and send that
                }
                else{
                    //run step 2 with false start
                }

            }
            return j.toString();
        }
        j.accumulate("massage","bad request.");
        return j.toString();
    }
//farmoon ended
    //email address and verify code needed
    @RequestMapping(value = "/changePassEmailVerifyCode", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    JsonModelResponse displayForgotPasswordPage(@RequestBody UserJsonModelForget userModel) {
        JsonModelResponse jsonModelResponse = new JsonModelResponse();
        Varibles varibles =new Varibles();
        if (Validator.isEmailValid(userModel.getEmail())) {
            UserModel user =  userModelRepository.findByEmail(userModel.getEmail());
            if (user != null){
//                if (user.getIsEmailVerify() == (byte) Integer.parseInt(userModel.getVerify()) ) {
                if (user.getAuthCode().equals(userModel.getVerify())) {
                    if (varibles.validate(userModel.getPassword())) {
                        Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
                        String password = md5Encoder.encodePassword( userModel.getPassword(),null);
                        user.setPassword(password);
                        userModelRepository.save(user);
                        jsonModelResponse.setResponse("password changed");
                        return jsonModelResponse;
                    }
                    else{
                        jsonModelResponse.setResponse("password incorrect");
                        return jsonModelResponse;
                    }
                } else {
                    jsonModelResponse.setResponse("verified error");
                    return jsonModelResponse;
                }
            }else {
                jsonModelResponse.setResponse("user notFound");
                return jsonModelResponse;
            }
        }else {
            jsonModelResponse.setResponse("wrong email");
            return jsonModelResponse;
        }
    }

    @RequestMapping(value = "/verifyQuestionEmail" , method = RequestMethod.POST , produces = "application/json" , consumes = "applicatin/json")
    @ResponseBody
    public JsonModelResponse AuthQuestionVerifyEmail (@RequestBody UserJsonModelForget user){
        JsonModelResponse response = new JsonModelResponse();
        if (Validator.isEmailValid(user.getEmail())) {
            UserModel userModel = userModelRepository.findByEmail(user.getEmail());
            if (userModel != null){
                if (user.getQuestionAnswer1().equals(userModel.getAnswerOne()) && user.getQuestionAnswer2().equals(userModel.getAnswerTwo()) && user.getQuestionAnswer3().equals(userModel.getAnswerThree())){
                    response.setResponse("verified");
                    return response;
                }else {
                    response.setResponse("false Question");
                    return response;
                }
            }else {
                response.setResponse("not found");
                return response;
            }
        }else {
            response.setResponse("wrong email");
            return response;
        }
    }

    @PostMapping(value="/register",  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public String registerUserByEmail(@RequestBody UserJsonModelForget model)
    {
        UserModel user = new UserModel();
        user.setEmail(model.getEmail());
        user.setFirstName(model.getFirstName());
        user.setFamilyName(model.getFamilyName());
        user.setIsEmailVerify((byte) 0);

        Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
        String password = md5Encoder.encodePassword(model.getPassword(),null);
        user.setPassword(password);

        user.setUserName(model.getUserName());
        user.setStatus(model.getStatus());
        user.setPhoneNumber(model.getPhoneNumber());
        user.setLastLogin(model.getLastLogin());

        List<Integer> questions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            questions.add(i);
        }
        Collections.shuffle(questions);

        user.setQuestionOneId(questions.get(0));
        user.setQuestionTwoId(questions.get(1));
        user.setQuestionThreeId(questions.get(2));

        user.setAnswerOne(model.getQuestionAnswer1());
        user.setAnswerTwo(model.getQuestionAnswer2());
        user.setAnswerThree(model.getQuestionAnswer3());

        if (!Validator.isEmailValid(user.getEmail())) return "Email Not Valid!";
        if (!Validator.isPhoneNumberValid(user.getPhoneNumber())) return "Phone Not Valid!";
        if(!Validator.isPasswordValid(model.getPassword())) return "Password Not Valid";

        UserModel exUser = userModelRepository.findByEmail(user.getEmail());

        if (exUser != null) return "Already Registered With This Email!";
        else
        {
            userModelRepository.save(user);

            EmailVerfyModel confirmationToken = new EmailVerfyModel();
            confirmationToken.setUserId(user.getId());
            confirmationToken.setToken(UUID.randomUUID().toString());

            emailVerifyRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Verify Your Account Now!");
            mailMessage.setFrom("exmpleEmail@gmail.com");
            mailMessage.setText("http://localhost:8080/api/v1/user/confirm?token="+confirmationToken.getToken());

            emailSenderService.sendEmail(mailMessage);
        }
        return "Confirmation Email Sent!";
    }

    @RequestMapping(value="/confirm", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserByEmail(@RequestParam("token") String token)
    {
        EmailVerfyModel confirmationTokenModel = emailVerifyRepository.findByToken(token);
        if(token != null)
        {
            UserModel user = userModelRepository.findID(confirmationTokenModel.getUserId());
            user.setIsEmailVerify((byte) 1);
            user.setStatus(1);
            userModelRepository.save(user);
        }
        else return "Invalid Token!";
        return "Registration Successful!";
    }

    @PostMapping(value="/cannotsignin",  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public JsonModelResponse canNotSignIn(@RequestBody UserJsonModelForget user) {
        JsonModelResponse response = new JsonModelResponse();
        UserModel userModel = userModelRepository.findByEmail(user.getEmail());
        if (userModel != null){
            if (user.getQuestionAnswer1().equals(userModel.getAnswerOne()) && user.getQuestionAnswer2().equals(userModel.getAnswerTwo()) && user.getQuestionAnswer3().equals(userModel.getAnswerThree())){
                String newPass = generateNewPassword(userModel);
                SendMail(user.getEmail(),
                        "alimajari1379@gmail.com",
                        "Your new password",
                        "Your password has been reset to \"" + newPass + "\". Remember to reset this password as soon as you can!"
                );
                response.setResponse("new password has been set");
                return response;
            }else {
                response.setResponse("false security answers");
                return response;
            }
        }else {
            response.setResponse("user not found");
            return response;
        }
    }

    @PostMapping(value="/sendticket",  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public JsonModelResponse sendTicket(@RequestBody UserJsonModelForget user) {
        JsonModelResponse response = new JsonModelResponse();
        UserModel userModel = userModelRepository.findByEmail(user.getEmail());
        if (userModel != null){
            TicketModel ticket = new TicketModel();
            ticket.setSenderEmail(user.getEmail());
            ticket.setText(user.getTicketText());
            ticketModelRepository.save(ticket);
            int ticketId = ticket.getId();

            SendMail(user.getEmail(),
                    "alimajari1379@gmail.com",
                    "Ticket sent!",
                    "Your ticket has been sent with factor number of \"573" + (ticketId) + "\"."
            );
            response.setResponse("ticket sent");
            return response;
        }else {
            response.setResponse("user not found");
            return response;
        }
    }

    /*kasra code*/
    @RequestMapping(method = RequestMethod.GET,value = "/forgetPasswordBy/phonenumber")
    public JsonModelResponse forgetpasswordbyphonenumber(@RequestBody UserJsonModelForget userJsonModelForget) {
        UserModel userModel = userModelRepository.findByPhoneNumber(userJsonModelForget.getPhoneNumber());
        JsonModelResponse jsonModelResponse = new JsonModelResponse();
        if(userModel!=null) {
            String verification;
            verification = String.valueOf((Double.doubleToLongBits(Math.random())) % 1000);
            userModel.setAuthCode(verification);
            userModelRepository.save(userModel);

            KavenegarApi kavenegarApi = new KavenegarApi("59414E53626943415A2B6D695A706A7542744134557550526A6268574C41586A");
            String sender = "1000596446";
            String receptor = userModel.getPhoneNumber();
            kavenegarApi.verifyLookup(receptor, verification, "verify");
            int id = userModel.getId();


            jsonModelResponse.setResponse("sms has been send");
            return jsonModelResponse;
            /*redirect to url:/forgetPasswordBy/phonenumber/Verify/{id} that verify code that has send by sms*/
        }
        else{
            jsonModelResponse.setResponse("phonenumber is not valid");
            return jsonModelResponse;
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/forgetPasswordBy/phonenumber/verify/{id}")
    public JsonModelResponse verify(@RequestBody UserJsonModelForget jsonModelForget,@PathVariable int id){
        UserModel userModel=userModelRepository.findID(id);
        JsonModelResponse jsonModelResponse=new JsonModelResponse();
        if(userModel!=null) {
            if ((userModel.getAuthCode()).equals(jsonModelForget.getVerificationCode())) {
                userModel.setAuthCode("0");

                Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder();
                //String securePass = encoderMD5.encodePassword(jsonModelForget.getPassword(), null);
                String securePass = encoderMD5.encodePassword(jsonModelForget.getPassword(), null);
                // userModel.setPassword(securePass);
                userModel.setPassword(jsonModelForget.getPassword());
                userModelRepository.save(userModel);
                jsonModelResponse.setResponse("password changed and verified");
                return jsonModelResponse;
                /*redirect to url:/forgetPasswordBy/phonenumber//ChangePassword/{id}*/
            } else {
                jsonModelResponse.setResponse("Not verified try again");
                return jsonModelResponse;
                /*redirect to url:/forgetPasswordBy/phonenumber/verify/{id}*/
            }
        }
        else{
            jsonModelResponse.setResponse("User not found");
            return jsonModelResponse;
        }
    }


    //hamed farahi codes:


    @RequestMapping(value = "/register/verify", method = RequestMethod.POST)
    public ResponseEntity<String> GetPhoneNumberAndVerify(@RequestBody UserJsonModelForget userJsonModelForget) throws JSONException {
        String phoneNumber = userJsonModelForget.getPhoneNumber();

        if (verifyphonenumberModelRepository.findPhoneNumber(phoneNumber) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "this phone number already exist").toString());
        } else {
            if (Validator.isPhoneNumberValid(phoneNumber)) {
                int verify = random();


                verifyphonenumberModelRepository.save(generateVerifyModel(phoneNumber, verify));

                sendVerifyCodeApi(phoneNumber, verify);


                return ResponseEntity.ok(getJsonResponse( "verify code send").toString());
            }
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "Invalid regex phone number").toString());
    }


    @RequestMapping(value = "/register/verify/submit", method = RequestMethod.POST)
    public ResponseEntity<String> checkPhoneNumberAndVerify(@RequestBody UserJsonModelForget userJsonModelForget) throws JSONException {

        if (verifyphonenumberModelRepository.findPhoneNumber(userJsonModelForget.getPhoneNumber()) != null) {

            VerifyphonenumberModel verifyphonenumberModel = verifyphonenumberModelRepository.findPhoneNumber(userJsonModelForget.getPhoneNumber());
            if (Integer.toString(verifyphonenumberModel.getVerifyCode()).equals(userJsonModelForget.getVerificationCode())) {
                verifyphonenumberModel.setStatus(1);
                verifyphonenumberModelRepository.save(verifyphonenumberModel);
                JSONObject jsonObject = new JSONObject();
                List<Integer> questions = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    questions.add(i);
                }
                Collections.shuffle(questions);

                jsonObject.put("question1",questions.get(0));
                jsonObject.put("question2",questions.get(1));
                jsonObject.put("question3",questions.get(2));
                return ResponseEntity.ok(jsonObject.toString());

            } else {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "your phone number and verify code not match").toString());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "this phone number not exist").toString());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/user", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> addUser(@RequestBody UserJsonModelForget userJsonModelForget) throws JSONException, NoSuchAlgorithmException {

        boolean jsonDataValidation = jsonDataValidation(userJsonModelForget.getPhoneNumber(), userJsonModelForget.getPassword(), userJsonModelForget.getEmail());
        if (jsonDataValidation) {
            if (verifyphonenumberModelRepository.findPhoneNumber(userJsonModelForget.getPhoneNumber()).getStatus() == 1) {
                if (userModelRepository.findByUserName(userJsonModelForget.getUserName()) == null) {

                    userModelRepository.save(castJsonToUserModel(userJsonModelForget));

                    return ResponseEntity.ok().body(getJsonResponse("ok").toString());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "this user name already exist").toString());
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "such phone number not exist").toString());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getJsonResponse( "your input is not valid").toString());
    }


















//    @RequestMapping(method = RequestMethod.POST,value="/forgetPasswordBy/phonenumber/ChangePassword/{id}")
//    public void changepassword(@RequestBody UserJsonModelForget jsonModelForget,@PathVariable int id) {
//        UserModel userModel = userModelRepository.findID(id);
//        Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder();
//
//        //String securePass = encoderMD5.encodePassword(jsonModelForget.getPassword(), null);
//        String securePass = encoderMD5.encodePassword(jsonModelForget.getPassword(), null);
//       // userModel.setPassword(securePass);
//        userModel.setPassword(jsonModelForget.getPassword()+1);
//        userModelRepository.save(userModel);
//
//        JsonModelResponse jsonModelResponse = new JsonModelResponse();
//        jsonModelResponse.setResponse("password changed");
//    }

   /* ******** */
}
