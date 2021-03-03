package com.xx.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xiexing
 * @description 商户用户信息表
 * @date 2020-55-01 09:55:40
 */
@Entity 
@Table(name = "power_user.t_mng_user")
@DynamicUpdate 
@DynamicInsert 
public class MngUser implements Serializable {
    private static final long serialVersionUID = 1L; 

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户类型(1平台自动生成，2管理人员添加)
     */
    private String userType;

    /**
     * 员工编号
     */
    private String userCode;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态(1:正常，2:停用，3:临时锁定，9:删除)
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属平台(取T_PLAT_PROJECT.PLAT_CODE)
     */
    private String ownerPlat;

    /**
     * 所属平台客户ID
     */
    private String ownerId;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 最近登录IP
     */
    private String loginIp;

    /**
     * 最近登录时间
     */
    private Date loginTime;

    /**
     * CRM系统userid
     */
    private String crmUserId;

    /**
     * 预留信息
     */
    private String personalInfo;

    /**
     * 最后修改密码时间
     */
    private Date updatePwdTime;

    /**
     * 强制修改密码 1- 强制修改 0-不强制修改
     */
    private String forcePwdUpdate;

    /**
     * 登陆类型 P-账密登陆 T-账密+token登陆
     */
    private String loginType;

    /**
     * 证件类型 01-身份证；02-护照；03-港澳通行证；05-其它人员、其它业务员；06-代理商、商户；07-代理商业务员；08-军官证；09-港澳居民来往内地通行证（回乡证）；10-台湾同胞来往内地通行证（台胞证）；11-警官证；12-士兵证；13-户口簿；14-临时身份证；15-外国人居留证；99-其他证件
     */
    private String certType;

    /**
     * 证件号码
     */
    private String certNo;

    /**
     * 用户昵称
     */
    private String userAlias;

    /**
     * 是否开通手机pos，1是0否
     */
    private String mbpFlag;


    @Id
    @GeneratedValue(generator = "system_generator")
    @GenericGenerator(name = "system_generator",strategy = "com.jlpay.manage.tools.sequence.impl.JLSequenceGenerator",parameters = {@org.hibernate.annotations.Parameter(name = "seqName",value = "power_user.t_mng_user")})
    @Column(name = "USER_ID")
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "USER_TYPE")
    public String getUserType(){
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "USER_CODE")
    public String getUserCode(){
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name = "LOGIN_NAME")
    public String getLoginName(){
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "LOGIN_PWD")
    public String getLoginPwd(){
        return loginPwd;
    }
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    @Column(name = "USER_NAME")
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "MOBILE")
    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "EMAIL")
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "STATUS")
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "REMARK")
    public String getRemark(){
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "OWNER_PLAT")
    public String getOwnerPlat(){
        return ownerPlat;
    }
    public void setOwnerPlat(String ownerPlat) {
        this.ownerPlat = ownerPlat;
    }

    @Column(name = "OWNER_ID")
    public String getOwnerId(){
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CREATE_USER")
    public String getCreateUser(){
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "UPDATE_USER")
    public String getUpdateUser(){
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "LOGIN_IP")
    public String getLoginIp(){
        return loginIp;
    }
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Column(name = "LOGIN_TIME")
    public Date getLoginTime(){
        return loginTime;
    }
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "CRM_USER_ID")
    public String getCrmUserId(){
        return crmUserId;
    }
    public void setCrmUserId(String crmUserId) {
        this.crmUserId = crmUserId;
    }

    @Column(name = "PERSONAL_INFO")
    public String getPersonalInfo(){
        return personalInfo;
    }
    public void setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
    }

    @Column(name = "UPDATE_PWD_TIME")
    public Date getUpdatePwdTime(){
        return updatePwdTime;
    }
    public void setUpdatePwdTime(Date updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    @Column(name = "FORCE_PWD_UPDATE")
    public String getForcePwdUpdate(){
        return forcePwdUpdate;
    }
    public void setForcePwdUpdate(String forcePwdUpdate) {
        this.forcePwdUpdate = forcePwdUpdate;
    }

    @Column(name = "LOGIN_TYPE")
    public String getLoginType(){
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Column(name = "CERT_TYPE")
    public String getCertType(){
        return certType;
    }
    public void setCertType(String certType) {
        this.certType = certType;
    }

    @Column(name = "CERT_NO")
    public String getCertNo(){
        return certNo;
    }
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    @Column(name = "USER_ALIAS")
    public String getUserAlias(){
        return userAlias;
    }
    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    @Column(name = "MBP_FLAG")
    public String getMbpFlag(){
        return mbpFlag;
    }
    public void setMbpFlag(String mbpFlag) {
        this.mbpFlag = mbpFlag;
    }

}