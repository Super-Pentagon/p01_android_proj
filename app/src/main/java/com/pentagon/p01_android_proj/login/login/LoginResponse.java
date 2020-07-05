package com.pentagon.p01_android_proj.login.login;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public class LoginResponse {

    /**
     * success : true
     * code : 20000
     * message : 成功
     * data : {"buyer":{"bid":"1279433140783333377","username":"圆号","password":"efe6398127928f1b2e9ef3207fb82663","tel":"","address":"","avatar":"http://bpic.588ku.com/element_pic/01/58/79/95574840a476616.jpg","name":"","state":0}}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * buyer : {"bid":"1279433140783333377","username":"圆号","password":"efe6398127928f1b2e9ef3207fb82663","tel":"","address":"","avatar":"http://bpic.588ku.com/element_pic/01/58/79/95574840a476616.jpg","name":"","state":0}
         */

        private BuyerBean buyer;

        public BuyerBean getBuyer() {
            return buyer;
        }

        public void setBuyer(BuyerBean buyer) {
            this.buyer = buyer;
        }

        public static class BuyerBean {
            /**
             * bid : 1279433140783333377
             * username : 圆号
             * password : efe6398127928f1b2e9ef3207fb82663
             * tel :
             * address :
             * avatar : http://bpic.588ku.com/element_pic/01/58/79/95574840a476616.jpg
             * name :
             * state : 0
             */

            private String bid;
            private String username;
            private String password;
            private String tel;
            private String address;
            private String avatar;
            private String name;
            private int state;

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
