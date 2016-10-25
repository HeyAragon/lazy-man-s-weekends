package com.qianfeng.aragon.lazy_man_weekend.bean;


import java.util.List;

/**
 * Created by aragon on 2016/10/25.
 */
public class LazyManBean {


    /**
     * biz_phone :
     * address : 湖北省武汉市武昌区徐东大街秦园东路福星惠誉水岸星城A区G17栋4901室
     * show_free : true
     * want_status : 0
     * poi : CANON HALL成人钢琴俱乐部
     * category : 文艺生活
     * collected_num : 46
     * title : 成人钢琴【18课时】深度体验，遇见最美好的自己【188元抢购】！
     * leo_id : 1351342007
     * sell_status : 2
     * front_cover_image_list : ["http://image.lanrenzhoumo.com/leo/img/20160903092309_853b031a43495200d111d6f5239398a3.jpg","http://image.lanrenzhoumo.com/leo/img/20160903092647_84fe8390074c44a142116cc82b836d4d.png","http://image.lanrenzhoumo.com/leo/img/20160903092515_5764da0b73126a2e066caee6bd341cda.jpg","http://image.lanrenzhoumo.com/leo/img/20160903092334_5ef232056812ae4ef241fe373aae7c5a.jpg","http://image.lanrenzhoumo.com/leo/img/20160903092351_1bd3d2af3a42343ff2eb3f2dba852ac0.jpg"]
     * viewed_num : 562
     * consult_phone :
     * jump_type : leo_detail_api
     * tags : ["亲子","情侣","单身"]
     * price : 188
     * time_info : 截止至2017年10月11日
     * time_desc : 9月3日 ~ 2017年10月11日 21:00
     * jump_data :
     * distance : 0
     * item_type : leo
     * time : {"start":"2016-09-03 00:00:00","end":"2017-10-11 21:00:00"}
     * price_info : 188
     * category_id : 14
     * poi_name : CANON HALL成人钢琴俱乐部
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String address;
        private String poi;
        private String category;
        private int collected_num;
        private String title;
        private int leo_id;
        private float price;
        private String time_info;
        private int distance;
        private List<String> front_cover_image_list;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPoi() {
            return poi;
        }

        public void setPoi(String poi) {
            this.poi = poi;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCollected_num() {
            return collected_num;
        }

        public void setCollected_num(int collected_num) {
            this.collected_num = collected_num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getLeo_id() {
            return leo_id;
        }

        public void setLeo_id(int leo_id) {
            this.leo_id = leo_id;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getTime_info() {
            return time_info;
        }

        public void setTime_info(String time_info) {
            this.time_info = time_info;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<String> getFront_cover_image_list() {
            return front_cover_image_list;
        }

        public void setFront_cover_image_list(List<String> front_cover_image_list) {
            this.front_cover_image_list = front_cover_image_list;
        }
    }
}
