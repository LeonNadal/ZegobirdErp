package com.xiaozhai.zegobirderp.bean;

/**
 * Created by nadal on 2017/11/10.
 */

public class LoginResultBean {

    /**
     * ResultCode : 0000
     * Message : 操作成功！
     * Data : {"StaffId":"1","UserName":"admin","UserTrueName":"mdb-A","DeptName":"技术部",
     * "SignToken":"c68570e4-2c71-4a57-8c6c-fe1397ba82fb","ExpireTime":"2017-11-07 10:58:33"}
     */

    private String ResultCode;
    private String Message;
    private DataBean Data;

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    @Override
    public String toString() {
        return "LoginResultBean{" +
                "ResultCode='" + ResultCode + '\'' +
                ", Message='" + Message + '\'' +
                ", Data=" + Data +
                '}';
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * StaffId : 1
         * UserName : admin
         * UserTrueName : mdb-A
         * DeptName : 技术部
         * SignToken : c68570e4-2c71-4a57-8c6c-fe1397ba82fb
         * ExpireTime : 2017-11-07 10:58:33
         */

        private String StaffId;
        private String UserName;
        private String UserTrueName;
        private String DeptName;
        private String SignToken;
        private String ExpireTime;

        @Override
        public String toString() {
            return "DataBean{" +
                    "StaffId='" + StaffId + '\'' +
                    ", UserName='" + UserName + '\'' +
                    ", UserTrueName='" + UserTrueName + '\'' +
                    ", DeptName='" + DeptName + '\'' +
                    ", SignToken='" + SignToken + '\'' +
                    ", ExpireTime='" + ExpireTime + '\'' +
                    '}';
        }

        public String getStaffId() {
            return StaffId;
        }

        public void setStaffId(String StaffId) {
            this.StaffId = StaffId;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserTrueName() {
            return UserTrueName;
        }

        public void setUserTrueName(String UserTrueName) {
            this.UserTrueName = UserTrueName;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getSignToken() {
            return SignToken;
        }

        public void setSignToken(String SignToken) {
            this.SignToken = SignToken;
        }

        public String getExpireTime() {
            return ExpireTime;
        }

        public void setExpireTime(String ExpireTime) {
            this.ExpireTime = ExpireTime;
        }
    }
}
