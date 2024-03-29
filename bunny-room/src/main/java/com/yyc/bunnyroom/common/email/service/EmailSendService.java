package com.yyc.bunnyroom.common.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;


@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtil redisUtil;

    private int authNumber;



    public boolean checkAuthNum(String email, String authNum){
        if (redisUtil.getData(authNum) == null){
            return false;
        }
        else if (redisUtil.getData(authNum).equals(email)){
            return true;
        }
        else {
            return false;
        }
    }



    // 임의의 6자리 양수를 반환합니다.
    public void makeRandomNumber(){
        Random random = new Random();
        String randomNumber ="";

        // 6개의 램덤 양수를 만드는 매소드
        for (int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(random.nextInt(10)); // 0부터의9의 랜덤 수를 작성하는 메소드
        }

        authNumber = Integer.parseInt(randomNumber); // 위 메소드에서 생성된 6가지 랜덤수를 저장
    }



    // mail을 어디서 보내는지, 어디로 보내는지, 인증번호를 html형식으로 어떻게 보내는지 작성함
    public String joinEmail(String email){
        makeRandomNumber();
        String setFrom = "kjsintellij2312@gmail.com"; // email-config에서 설정한 이메일을 입력
        String toMail = email;
        String title= "회원 가입 인증 이메일 입니다.";  // 이메일의 제목
        String content =
                "YYC Bunny_Room을 방문해주셔서 감사합니다." +      //  html 형식으로 작성
                        "<br><br>" +
                        "발급하신 인증번호는 <h1>"+authNumber+"</h1> "; // 이메일 내용 삽입
        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

    //이메일을 전송하는 메소드
    public void mailSend(String setFrom, String toMail, String title, String content){
        MimeMessage message = mailSender.createMimeMessage(); //JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message , true , "UTF-8"); //이메일 메시지와 관련된 설정을 수행합니다.
            helper.setFrom(setFrom);//이메일의 발신자 주소 설정
            helper.setTo(toMail);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content,true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);
        } catch (MessagingException e){ //이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류
            // 이러한 경우 MessagingException이 발생
            e.printStackTrace(); //e.printStackTrace()는 예외를 기본 오류 스트림에 출력하는 메서드
        }
        redisUtil.setDataExpire(Integer.toString(authNumber), toMail, 60*3L);
    }

}
