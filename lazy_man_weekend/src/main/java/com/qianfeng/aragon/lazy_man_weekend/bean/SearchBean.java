package com.qianfeng.aragon.lazy_man_weekend.bean;

import java.util.List;

/**
 * Created by aragon on 2016/10/26.
 */
public class SearchBean {

    /**
     * name : all
     * icon_view : http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png
     * cn_name : 全部类目
     * icon_pressed : http://image.lanrenzhoumo.com/leo/img/20150820091224_670e5fe509fcc9505682cd1e3aac7ea4.png
     * children : []
     * description :
     */

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String name;
        private String icon_view;
        private String cn_name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon_view() {
            return icon_view;
        }

        public void setIcon_view(String icon_view) {
            this.icon_view = icon_view;
        }

        public String getCn_name() {
            return cn_name;
        }

        public void setCn_name(String cn_name) {
            this.cn_name = cn_name;
        }
    }
}
