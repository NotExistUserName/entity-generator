package com.xx.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiexing
 * @description 菜单表
 * @date 2020-55-01 09:55:40
 */
@Entity 
@Table(name = "power_user.t_menu")
@DynamicUpdate 
@DynamicInsert 
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L; 

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * URL
     */
    private String url;

    /**
     * 顺序
     */
    private String forder;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 样式
     */
    private String fstyle;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date utime;

    /**
     * 操作人ID
     */
    private String opUserId;

    /**
     * 操作人ID
     */
    private String opUserName;

    /**
     * 是否可用Y:可用，N:不可用，D:删除
     */
    private String isEnabled;

    /**
     * 系统
     */
    private String projectCode;

    /**
     * 菜单级别
     */
    private BigDecimal menuLevel;

    /**
     * 菜单ID链
     */
    private String path;


    @Id
    @GeneratedValue(generator = "system_generator")
    @GenericGenerator(name = "system_generator",strategy = "com.jlpay.manage.tools.sequence.impl.JLSequenceGenerator",parameters = {@org.hibernate.annotations.Parameter(name = "seqName",value = "power_user.t_menu")})
    @Column(name = "MENU_ID")
    public String getMenuId(){
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Column(name = "URL")
    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "FORDER")
    public String getForder(){
        return forder;
    }
    public void setForder(String forder) {
        this.forder = forder;
    }

    @Column(name = "PARENT_ID")
    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "MENU_NAME")
    public String getMenuName(){
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Column(name = "FSTYLE")
    public String getFstyle(){
        return fstyle;
    }
    public void setFstyle(String fstyle) {
        this.fstyle = fstyle;
    }

    @Column(name = "CTIME")
    public Date getCtime(){
        return ctime;
    }
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Column(name = "UTIME")
    public Date getUtime(){
        return utime;
    }
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Column(name = "OP_USER_ID")
    public String getOpUserId(){
        return opUserId;
    }
    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    @Column(name = "OP_USER_NAME")
    public String getOpUserName(){
        return opUserName;
    }
    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    @Column(name = "IS_ENABLED")
    public String getIsEnabled(){
        return isEnabled;
    }
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Column(name = "PROJECT_CODE")
    public String getProjectCode(){
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Column(name = "MENU_LEVEL")
    public BigDecimal getMenuLevel(){
        return menuLevel;
    }
    public void setMenuLevel(BigDecimal menuLevel) {
        this.menuLevel = menuLevel;
    }

    @Column(name = "PATH")
    public String getPath(){
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}