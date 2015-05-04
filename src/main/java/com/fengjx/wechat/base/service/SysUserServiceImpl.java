
package com.fengjx.wechat.base.service;

import com.fengjx.common.mail.service.MyEmailService;
import com.fengjx.common.mail.vo.SendMailVo;
import com.fengjx.common.system.init.FreeMarkerUtil;
import com.fengjx.common.utils.CommonUtils;
import com.fengjx.common.utils.DesEncrypt;
import com.fengjx.common.utils.LoggerUtil;
import com.fengjx.common.utils.WebUtil;
import com.fengjx.wechat.base.dao.SysUserMapper;
import com.fengjx.wechat.base.model.SysUser;
import com.fengjx.wechat.config.AppConfig;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private MyEmailService emailService;

    public SysUser signin(String username, String pwd)
    {
        SysUser user = new SysUser();
        user.setUsername(username);
        user = this.sysUserMapper.selectByCondition(user);
        if ((null != user) && (user.getPwd().equals(DigestUtils.md5Hex(pwd)))) {
            return user;
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void register(SysUser user)
            throws Exception
    {
        if (validUsername(user.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        if (validEmail(user.getEmail())) {
            throw new RuntimeException("邮箱已被占用");
        }
        user.setId(CommonUtils.getPrimaryKey());
        user.setPwd(DigestUtils.md5Hex(user.getPwd()));
        user.setInTime(new Date());
        user.setIsValid("0");
        user.setScore(Integer.valueOf(0));
        user.setValidUid(CommonUtils.getPrimaryKey());
        this.sysUserMapper.insert(user);
        sendRegisterMail(user);
    }

    public void sendRegisterMail(SysUser user) throws Exception {
        SendMailVo mail = new SendMailVo();
        mail.setType("html");
        mail.setToUser(user.getEmail());
        mail.setSubject("邮箱验证");
        Map root = new HashMap();
        root.put("userEmail", user.getEmail());
        root.put(
                "validUrl",
                AppConfig.DOMAIN_PAGE + "/activate?ser="
                        + WebUtil.urlEncode(DesEncrypt.encrypt(user.getValidUid())));

        mail.setContent(FreeMarkerUtil.process(root, "register-valid-mail.ftl"));
        this.emailService.send(mail);
    }

    public boolean validUsername(String username)
    {
        SysUser user = new SysUser();
        user.setUsername(username);
        int total = this.sysUserMapper.getCount(user);
        return total > 0;
    }

    public boolean validEmail(String email)
    {
        SysUser user = new SysUser();
        user.setEmail(email);
        int total = this.sysUserMapper.getCount(user);
        return total > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean activate(String ser)
    {
        String uid = null;
        try {
            uid = DesEncrypt.decrypt(ser);
        } catch (Exception e) {
            LoggerUtil.warn(LOGGER, "账号激活失败，ser = " + ser, e);
            return false;
        }
        SysUser user = new SysUser();
        user.setValidUid(uid);
        user = this.sysUserMapper.selectByCondition(user);
        if ((null == user) || ("1".equals(user.getIsValid()))) {
            return false;
        }
        user.setIsValid("1");
        this.sysUserMapper.updateByPrimaryKeySelective(user);
        return true;
    }
}
