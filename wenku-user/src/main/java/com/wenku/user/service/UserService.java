package com.wenku.user.service;

import com.wenku.define.Result;
import com.wenku.mail.MailService;
import com.wenku.user.dao.UserACodeDao;
import com.wenku.user.dao.UserEmailDao;
import com.wenku.user.model.*;
import com.wenku.user.util.UserUtils;
import com.wenku.util.DigestUtils;
import com.wenku.util.MVCUtils;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.runtime.UserAccessorProperty;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.wenku.user.dao.UserDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,value = "tsUser")
public class UserService{

    @Resource
    private UserDao userDao;

    @Resource
    private UserEmailDao userEmailDao;

    @Resource
    private UserACodeDao userACodeDao;

    @Resource
    private MailService mailService;

    @Transactional("tsUser")
    public int insert(User pojo){
        return userDao.insert(pojo);
    }

    @Transactional("tsUser")
    public int insertSelective(User pojo){
        return userDao.insertSelective(pojo);
    }

    @Transactional("tsUser")
    public int insertList(List<User> pojos){
        return userDao.insertList(pojos);
    }

    @Transactional("tsUser")
    public int update(User pojo){
        return userDao.update(pojo);
    }

    public User findOne(long id){
        return userDao.findById(id);
    }


    /**
     * 后台验证注册信息，防止攻击
     * @param email
     * @param password
     * @return
     */
    public Result validateEmailRegister(String email,String password){
        if (StringUtils.isBlank(email)){
            return Result.fail("邮箱不能为空！");
        }
        if (StringUtils.isBlank(password)){
            return Result.fail("密码不能为空！");
        }
        if (password.length()<6||password.length()>20){
            return Result.fail("密码不符合规范");
        }
        if (!email.contains("@")||email.length()>32){
            return Result.fail("邮箱不符合规范");
        }
        if (isEmailExists(email)){
            return  Result.fail("该邮箱已存在");
        }
        return Result.success();
    }

    /**
     *
     * @param loginName
     * @param password
     * @return
     */
    public Result validateLogin(String loginName,String password){
        if (StringUtils.isBlank(loginName)||StringUtils.isBlank(password)){
            return Result.fail("用户名和密码都不能为空！");
        }
        User user = findUserByEmail(loginName);
        if (user == null){
            return Result.fail("用户名不存在！");
        }
        String ecPass = encryptPassword(user,password);
        if (!ecPass.equals(user.getPass())){
            return Result.fail("用户名或密码错误！");
        }
        return Result.success();
    }


    public String encryptPassword(User user,String password){
        try {
            return DigestUtils.md5(password,user.getSalt().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 用邮箱注册用户
     * @param email
     * @param password
     * @return
     */
    @Transactional("tsUser")
    public Result registerEmail(String email, String password){
        Result result = validateEmailRegister(email,password);
        if (!result.isSuccess()){
            return result;
        }
        User user = User.newUserByEmail(email,password);
        userDao.insert(user);
        UserEmail userEmail = new UserEmail(user.getId(),user.getEmail());
        userEmailDao.insert(userEmail);
        String activeCode = UserUtils.genActiveCode(email);
        UserACode userACode = new UserACode(user.getId(), activeCode);
        userACodeDao.insert(userACode);
        sendActivateEmail(user);
        return result;
    }

    /**
     * 发送激活邮件
     * @param user
     */
    @Transactional("tsUser")
    public void sendActivateEmail(User user){
        String host = MVCUtils.getHostAndContextPath();
        UserACode userACode = userACodeDao.findFirstByUid(user.getId());
        String url = host+"/register/activate?mail="+user.getEmail();
        url+="&activateCode="+userACode.getAcode();
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，请尽快激活！</br>");
        sb.append("<a href=\"" + url + "\">");
        sb.append(url);
        sb.append("</a>");
        mailService.sendMail("搜文酷-用户激活，请激活您在搜文酷注册的新用户",sb.toString(),user.getEmail());
    }

    public User findUserByEmail(String email){
        UserEmail userEmail = userEmailDao.findFirstByEmail(email);
        if (userEmail!=null){
            return userDao.findById(userEmail.getUid());
        }
        return null;
    }

    public Long findUserIdByLoginName(String loginName){
        UserEmail userEmail = userEmailDao.findFirstByEmail(loginName);
        return userEmail==null?null:userEmail.getUid();
    }

    @Transactional("tsUser")
    public ActivateResult activeMail(String mail,String activeCode){
        if (StringUtils.isBlank(mail)||StringUtils.isBlank(activeCode)){
            return new ActivateResult(false,false,"没有邮件或激活码");
        }
        User user = findUserByEmail(mail);
        if (user==null){
            return new ActivateResult(false,false,"无法找到邮箱:"+mail+" 注册的用户！");
        }
        if (user.getStatus().equals(UserStatus.active)){
            return new ActivateResult(true,false,"邮箱已激活，请登录！");
        }
        DateTime current = new DateTime();
        DateTime regTime = new DateTime(user.getUpdateDate());
        if (regTime.plusHours(48).isBefore(current)){
            return new ActivateResult(false,true,"激活码已过期，请重新激活！");
        }
        UserACode userACode = userACodeDao.findFirstByUid(user.getId());
        if (!activeCode.equals(userACode.getAcode())){
            return new ActivateResult(false,true,"激活码不正确！");
        }
        user.setStatus(UserStatus.active);
        userDao.update(user);
        userACodeDao.deleteByUid(user.getId());
        return new ActivateResult(true,false,"用户激活成功！");

    }

    /**
     * 判断邮箱
     * @param email
     * @return
     */
    public Boolean isEmailExists(String email){
        if (StringUtils.isBlank(email)){
            return false;
        }
        UserEmail userEmail = userEmailDao.findFirstByEmail(email);
        return userEmail!= null;
    }


}
