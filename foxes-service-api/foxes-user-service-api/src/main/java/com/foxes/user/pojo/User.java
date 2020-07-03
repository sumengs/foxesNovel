package com.foxes.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User implements Serializable {
    /**
     * id 主键
     */
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 头像地址
     */
    @Column(name = "image")
    private String image;

    /**
     * 是否为会员，1为会员，0为普通用户
     */
    @Column(name = "is_member")
    private Integer isMember;

    /**
     * 会员过期时间
     */
    @Column(name = "member_time")
    private Date memberTime;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 手机号是否验证
     */
    @Column(name = "is_phone_check")
    private Integer isPhoneCheck;

    /**
     * 邮箱地址
     */
    @Column(name = "email")
    private String email;

    /**
     * 邮箱号是否验证
     */
    @Column(name = "is_email_check")
    private Integer isEmailCheck;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * QQ号
     */
    @Column(name = "qq")
    private String qq;

    /**
     * qq号是否验证
     */
    @Column(name = "is_qq_check")
    private Integer isQqCheck;

    /**
     * wx号
     */
    @Column(name = "wx")
    private String wx;

    /**
     * wx号是否验证
     */
    @Column(name = "is_wx_check")
    private Integer isWxCheck;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id 主键
     *
     * @return id - id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id 主键
     *
     * @param id id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取头像地址
     *
     * @return image - 头像地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置头像地址
     *
     * @param image 头像地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取是否为会员，1为会员，0为普通用户
     *
     * @return is_member - 是否为会员，1为会员，0为普通用户
     */
    public Integer getIsMember() {
        return isMember;
    }

    /**
     * 设置是否为会员，1为会员，0为普通用户
     *
     * @param isMember 是否为会员，1为会员，0为普通用户
     */
    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    /**
     * 获取会员过期时间
     *
     * @return member_time - 会员过期时间
     */
    public Date getMemberTime() {
        return memberTime;
    }

    /**
     * 设置会员过期时间
     *
     * @param memberTime 会员过期时间
     */
    public void setMemberTime(Date memberTime) {
        this.memberTime = memberTime;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取手机号是否验证
     *
     * @return is_phone_check - 手机号是否验证
     */
    public Integer getIsPhoneCheck() {
        return isPhoneCheck;
    }

    /**
     * 设置手机号是否验证
     *
     * @param isPhoneCheck 手机号是否验证
     */
    public void setIsPhoneCheck(Integer isPhoneCheck) {
        this.isPhoneCheck = isPhoneCheck;
    }

    /**
     * 获取邮箱地址
     *
     * @return email - 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取邮箱号是否验证
     *
     * @return is_email_check - 邮箱号是否验证
     */
    public Integer getIsEmailCheck() {
        return isEmailCheck;
    }

    /**
     * 设置邮箱号是否验证
     *
     * @param isEmailCheck 邮箱号是否验证
     */
    public void setIsEmailCheck(Integer isEmailCheck) {
        this.isEmailCheck = isEmailCheck;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取QQ号
     *
     * @return qq - QQ号
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号
     *
     * @param qq QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取qq号是否验证
     *
     * @return is_qq_check - qq号是否验证
     */
    public Integer getIsQqCheck() {
        return isQqCheck;
    }

    /**
     * 设置qq号是否验证
     *
     * @param isQqCheck qq号是否验证
     */
    public void setIsQqCheck(Integer isQqCheck) {
        this.isQqCheck = isQqCheck;
    }

    /**
     * 获取wx号
     *
     * @return wx - wx号
     */
    public String getWx() {
        return wx;
    }

    /**
     * 设置wx号
     *
     * @param wx wx号
     */
    public void setWx(String wx) {
        this.wx = wx;
    }

    /**
     * 获取wx号是否验证
     *
     * @return is_wx_check - wx号是否验证
     */
    public Integer getIsWxCheck() {
        return isWxCheck;
    }

    /**
     * 设置wx号是否验证
     *
     * @param isWxCheck wx号是否验证
     */
    public void setIsWxCheck(Integer isWxCheck) {
        this.isWxCheck = isWxCheck;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", image=").append(image);
        sb.append(", isMember=").append(isMember);
        sb.append(", memberTime=").append(memberTime);
        sb.append(", phone=").append(phone);
        sb.append(", isPhoneCheck=").append(isPhoneCheck);
        sb.append(", email=").append(email);
        sb.append(", isEmailCheck=").append(isEmailCheck);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", qq=").append(qq);
        sb.append(", isQqCheck=").append(isQqCheck);
        sb.append(", wx=").append(wx);
        sb.append(", isWxCheck=").append(isWxCheck);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getIsMember() == null ? other.getIsMember() == null : this.getIsMember().equals(other.getIsMember()))
            && (this.getMemberTime() == null ? other.getMemberTime() == null : this.getMemberTime().equals(other.getMemberTime()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getIsPhoneCheck() == null ? other.getIsPhoneCheck() == null : this.getIsPhoneCheck().equals(other.getIsPhoneCheck()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getIsEmailCheck() == null ? other.getIsEmailCheck() == null : this.getIsEmailCheck().equals(other.getIsEmailCheck()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getIsQqCheck() == null ? other.getIsQqCheck() == null : this.getIsQqCheck().equals(other.getIsQqCheck()))
            && (this.getWx() == null ? other.getWx() == null : this.getWx().equals(other.getWx()))
            && (this.getIsWxCheck() == null ? other.getIsWxCheck() == null : this.getIsWxCheck().equals(other.getIsWxCheck()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getIsMember() == null) ? 0 : getIsMember().hashCode());
        result = prime * result + ((getMemberTime() == null) ? 0 : getMemberTime().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getIsPhoneCheck() == null) ? 0 : getIsPhoneCheck().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getIsEmailCheck() == null) ? 0 : getIsEmailCheck().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getIsQqCheck() == null) ? 0 : getIsQqCheck().hashCode());
        result = prime * result + ((getWx() == null) ? 0 : getWx().hashCode());
        result = prime * result + ((getIsWxCheck() == null) ? 0 : getIsWxCheck().hashCode());
        return result;
    }
}