
package com.fengjx.modules.sys.security;

/**
 * 用户和密码（包含验证码）令牌类
 * 
 * @author fengjx
 * @version 2015-10-17
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    // 验证码
    private String captcha;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host,
            String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
