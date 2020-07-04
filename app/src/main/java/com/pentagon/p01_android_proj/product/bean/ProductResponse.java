package com.pentagon.p01_android_proj.product.bean;

/**
 * @author: Create by huangyuanhao on 2020-07-04
 * @email: 814484626@qq.com
 */
public class ProductResponse {


    /**
     * success : true
     * code : 20000
     * message : 成功
     * data : {"product":{"pid":"12345678900","price":1,"sid":"778","des":"卫龙好吃","purl":"http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg","thumbnailurl":"http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg","pname":"卫龙","monthlysales":10}}
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
         * product : {"pid":"12345678900","price":1,"sid":"778","des":"卫龙好吃","purl":"http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg","thumbnailurl":"http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg","pname":"卫龙","monthlysales":10}
         */

        private ProductBean product;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * pid : 12345678900
             * price : 1.0
             * sid : 778
             * des : 卫龙好吃
             * purl : http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg
             * thumbnailurl : http://img06.fn-mart.com/C/show/detail/image/20151029/30fa36023164d13aa4bab697b6af2d66.jpg
             * pname : 卫龙
             * monthlysales : 10
             */

            private String pid;
            private double price;
            private String sid;
            private String des;
            private String purl;
            private String thumbnailurl;
            private String pname;
            private int monthlysales;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getPurl() {
                return purl;
            }

            public void setPurl(String purl) {
                this.purl = purl;
            }

            public String getThumbnailurl() {
                return thumbnailurl;
            }

            public void setThumbnailurl(String thumbnailurl) {
                this.thumbnailurl = thumbnailurl;
            }

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public int getMonthlysales() {
                return monthlysales;
            }

            public void setMonthlysales(int monthlysales) {
                this.monthlysales = monthlysales;
            }
        }
    }
}
