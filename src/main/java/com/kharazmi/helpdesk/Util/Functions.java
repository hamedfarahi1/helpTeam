package com.kharazmi.helpdesk.Util;

import com.kharazmi.helpdesk.Model.RoleModel;
import com.kharazmi.helpdesk.Model.UserModel;
import com.kharazmi.helpdesk.Model.VerifyphonenumberModel;
import com.kharazmi.helpdesk.Repository.AdminModelRepository;
import com.kharazmi.helpdesk.Repository.RoleModelRepository;
import com.kharazmi.helpdesk.Repository.UserModelRepository;
import com.kharazmi.helpdesk.Service.EmailSenderService;
import com.kharazmi.helpdesk.kavenegar.sdk.KavenegarApi;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.StandardEmitterMBean;
import javax.security.auth.Subject;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import static com.kharazmi.helpdesk.Util.Validator.isEmailValid;
import static com.kharazmi.helpdesk.Util.Validator.isPasswordValid;
import static com.kharazmi.helpdesk.Util.Validator.isPhoneNumberValid;

public class Functions {

    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    UserModelRepository userModelRepository;
    @Autowired
    AdminModelRepository adminModelRepository;

    public int random() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(9)) * 10000 + r.nextInt(10000));
    }

    public String generateNewPassword(UserModel user) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String pass = RandomStringUtils.random( 8, characters );
        user.setPassword(pass);
        userModelRepository.save(user);
        return pass;
    }
    protected void sendVerifyCodeApi(String phoneNumber, int verify) {
        KavenegarApi api = new KavenegarApi(Varibles.KaveNegarApiKey);
        String sender = "1000596446";
        String receptor = phoneNumber;
        String message = "your verify code is  " + verify;
        api.send(sender, receptor, message);
    }
    public void SendMail(String to, String from, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        emailSenderService.sendEmail(mailMessage);
    }

    //farmon started
    public static String createToken(String userName,String secretKey, long expired){
        String JWT = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return JWT;
    }
    public static String[] captchaGen(){
        String[] captcha = new String[2];
        captcha[0] = Captcha.generateText().substring(0,4); //define captcha text as 0
        captcha[1] = Base64.getEncoder().encodeToString(Captcha.generateImage(captcha[0])); //define captcha image as 1
        return captcha;
    }
    public static JSONObject infoObjectCreator(UserModel um) throws JSONException {
        JSONObject j1 = new JSONObject();
        j1.accumulate("userName",um.getUserName());
        j1.accumulate("email",um.getEmail());
        j1.accumulate("firstName",um.getFirstName());
        j1.accumulate("familyName",um.getFamilyName());
        j1.accumulate("id",um.getId());
        return j1;
    }
    //farmoon ended

    public boolean jsonDataValidation(String phoneNumber,String password ,String email){
        return isPasswordValid(phoneNumber) && isPhoneNumberValid(password) && isEmailValid(email);
    }
    protected JSONObject getJsonResponse(String description) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description",description);
        return jsonObject;
    }

    protected VerifyphonenumberModel generateVerifyModel(String phoneNumber, int verify) {
        VerifyphonenumberModel verifyphonenumberModel = new VerifyphonenumberModel();
        verifyphonenumberModel.setPhoneNumber(phoneNumber);
                /*
                0 status for invalid status and not verified
               */
        verifyphonenumberModel.setStatus(0);
        verifyphonenumberModel.setVerifyCode(verify);

        return verifyphonenumberModel;
    }

    protected UserModel castJsonToUserModel(@RequestBody UserJsonModelForget userJsonModelForget) throws NoSuchAlgorithmException {

        UserModel userModel = new UserModel();
        userModel.setFirstName(userJsonModelForget.getFirstName());
        userModel.setFamilyName(userJsonModelForget.getFamilyName());
        userModel.setUserName(userJsonModelForget.getUserName());
        userModel.setPhoneNumber(userJsonModelForget.getPhoneNumber());
        userModel.setEmail(userJsonModelForget.getEmail());
        Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
        String password = md5Encoder.encodePassword(userJsonModelForget.getPassword(),null);
        userModel.setPassword(password);
        userModel.setAnswerOne(userJsonModelForget.getQuestionAnswer1());
        userModel.setAnswerTwo(userJsonModelForget.getQuestionAnswer2());
        userModel.setAnswerThree(userJsonModelForget.getQuestionAnswer3());


        userModel.setQuestionOneId(1);
        userModel.setQuestionTwoId(2);
        userModel.setQuestionThreeId(3);

        userModel.setStatus(0);
        userModel.setAuthCode(" ");
        userModel.setIsEmailVerify((byte) 0);
        userModel.setLoginCaptchaText("  ");
        userModel.setLastLogin(new Timestamp(System.currentTimeMillis()));

        return userModel;
    }

    public String sendEmailToOfflineAdmin(JSONObject jsonObject) throws JSONException {

        String adminEmail=adminModelRepository.getOne(Integer.parseInt(jsonObject.getString("adminId"))).getEmail();
        String adminName=adminModelRepository.getOne(Integer.parseInt(jsonObject.getString("adminId"))).getFirstName();
        String userName=adminModelRepository.getOne(Integer.parseInt(jsonObject.getString("adminId"))).getFirstName()
                        +"   "+adminModelRepository.getOne(Integer.parseInt(jsonObject.getString("adminId"))).getFamilyName();
        String Subject="Offline Admin";

        SendMail(adminEmail,"example@gmail.com",Subject,"you have a ticket");

        return "";

    }
}
