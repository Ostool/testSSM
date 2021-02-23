package wky.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {
    public static String bCryptPasswordEncoder(String password){

        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        return bpe.encode(password);
    }

    public static void main(String[] args) {
        String psw = "123";
        System.out.println(bCryptPasswordEncoder(psw));
    }
}
