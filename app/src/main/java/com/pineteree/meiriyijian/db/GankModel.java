package com.pineteree.meiriyijian.db;

import java.io.Serializable;
import java.util.List;

/**
 * @author tlf
 * @date
 */

public class GankModel {

    /**
     * error : false
     * results : [{"_id":"5a027569421aa90fe7253610","createdAt":"2017-11-08T11:09:29.236Z","desc":"免费专栏推荐：小米 MIUI 系统工程师 的《从源码角度看 Android》","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483816&idx=1&sn=9129a1fff64c6da7dde9143dc6995ec1&chksm=fdbf32ffcac8bbe943e81a23a487e3caac0dbb64e18509e61b28666074234c58e820b1a0aeea#rd","used":true,"who":null},{"_id":"5a02d0d1421aa90fe2f02c37","createdAt":"2017-11-08T17:39:29.976Z","desc":"这可能是第二好的自定义 View 教程之绘制","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/7cea643df11b","used":true,"who":"LiuShilin"},{"_id":"5a0312a9421aa90fe7253615","createdAt":"2017-11-08T22:20:25.632Z","desc":"雕虫晓技(一) Android 组件化","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzI3MzMzNjgzMA==&mid=2247483674&idx=1&sn=fc93e05445a8fefcde0fdfb2c0145321&chksm=eb25918bdc52189d3ce82d7796ec95105927734d0cf47544126937d3d1255f41002f0cfb3afe&mpshare=1&scene=1&srcid=1106IA3DQP1u3IkOcCVwq1u2&key=5dba587a8b06ccca4ff30ff629f458ed05559774b37f3b9080982d26a7151db05c0324f15551f87029d3a11fee57d8fd51c5622f26b109f6f59a4c6c4ee811a840d7e9862b796b9dbdea8c14a1d32e9f&ascene=0&uin=NjM4NDkwNzgy&devicetype=iMac+MacBookAir7%2C2+OSX+OSX+10.12.4+build(16E195)&version=12010110&nettype=WIFI&fontScale=100&pass_ticket=wtm7nx0Fp2owsbSKcyoiIgV4pVu0Rw0GZicnXakuWo0tyipA8VCHAfpOkJGhp3yb","used":true,"who":"sloop"},{"_id":"5a03b2aa421aa90fe50c01e9","createdAt":"2017-11-09T09:43:06.493Z","desc":"我所理解的Android和iOS上的View","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/W9lRR4K3XRNTPvXf64qdhA","used":true,"who":"D_clock"},{"_id":"5a03ee2c421aa90fe7253619","createdAt":"2017-11-09T13:57:00.165Z","desc":"Kotlin 系列 - 从0到1 开发一个 App","images":["http://img.gank.io/35be6898-b5b8-4fa9-8b60-e49c6d6b998c"],"publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://caimuhao.com/2017/11/02/Learn-Kotlin-While-Developing-An-Android-App-Introduction/","used":true,"who":null},{"_id":"5a041483421aa90fe2f02c3f","createdAt":"2017-11-09T16:40:35.807Z","desc":"那些年遇到的奇葩后台写的奇葩接口","publishedAt":"2017-11-10T08:10:02.838Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/5a723f0b7c1e","used":true,"who":"阿韦"},{"_id":"5a000a51421aa90fef203504","createdAt":"2017-11-06T15:08:01.257Z","desc":" 使用CMake来进行Android NDK开发","publishedAt":"2017-11-08T11:00:50.559Z","source":"chrome","type":"Android","url":"http://blog.csdn.net/qq_34902522/article/details/78104610","used":true,"who":"宇宝守护神"},{"_id":"5a010bea421aa90fe7253604","createdAt":"2017-11-07T09:27:06.248Z","desc":"如何实现超萌动感小太阳？","publishedAt":"2017-11-08T11:00:50.559Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488057&idx=2&sn=d67b8d9c7da04e4efdec4084efe2098f","used":true,"who":"陈宇明"},{"_id":"5a0113a7421aa90fe7253605","createdAt":"2017-11-07T10:00:07.760Z","desc":"Android Gradle 自定义Task 详解","publishedAt":"2017-11-08T11:00:50.559Z","source":"web","type":"Android","url":"http://blog.csdn.net/zhaoyanjun6/article/details/76408024","used":true,"who":"赵彦军"},{"_id":"5a012636421aa90fe2f02c31","createdAt":"2017-11-07T11:19:18.496Z","desc":"教你如何在SDK开发使用美团Robust进行热更新","publishedAt":"2017-11-08T11:00:50.559Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/9284853d5762","used":true,"who":"Ou Bowu"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable {
        public ResultsBean(String _id, String createdAt, String desc, String publishedAt, String source, String type, String url, boolean used, Object who, List<String> images) {
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
            this.images = images;
        }

        /**
         * _id : 5a027569421aa90fe7253610
         * createdAt : 2017-11-08T11:09:29.236Z
         * desc : 免费专栏推荐：小米 MIUI 系统工程师 的《从源码角度看 Android》
         * publishedAt : 2017-11-10T08:10:02.838Z
         * source : web
         * type : Android
         * url : https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483816&idx=1&sn=9129a1fff64c6da7dde9143dc6995ec1&chksm=fdbf32ffcac8bbe943e81a23a487e3caac0dbb64e18509e61b28666074234c58e820b1a0aeea#rd
         * used : true
         * who : null
         * images : ["http://img.gank.io/35be6898-b5b8-4fa9-8b60-e49c6d6b998c"]
         */


        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
