package mdd.logistics.controller.user.vo;

import mdd.logistics.domain.User;

import java.util.Date;

public class RegisterVo extends User{

         private String userName;
        /**
         * 账号
         */
        private String  account;

        private transient String  password;
        /**
         * 手机号
         */
        private String  mobile;

        private String  tel;

        private String  coName;

        private String  coAddress;

        private Integer  type;

        private Integer  isDeleted;

        private Date createTime;

        private String mark;

        private Integer status;
}
