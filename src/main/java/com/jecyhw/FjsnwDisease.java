package com.jecyhw;

import com.jecyhw.request.Page;
import com.jecyhw.request.PageIterable;
import com.jecyhw.request.bean.RequestInfo;
import com.jecyhw.request.exception.RequestFailedException;
import com.jecyhw.request.params.Post;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by jecyhw on 16-8-18.
 */
public class FjsnwDisease implements PageIterable {
    static final Logger logger = Logger.getLogger(FjsnwDisease.class);
    static final String rootUrl = "http://www.fjsnw.cn/disease.shtml";
    int startPageNumber = 0;
    int endPageNumber = 61;

    public FjsnwDisease() {
        logger.info("当前爬取的病害数据网址为:" + rootUrl);
    }

    @Override
    public String html() throws RequestFailedException{
        logger.info("当前抓取页:" + startPageNumber);
        OkHttpClient httpClient = new OkHttpClient();
        Post post = new FjsnwListPostParams(startPageNumber);
        Request request = new Request.Builder().url(rootUrl).post(post.requestBody()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RequestFailedException(rootUrl, post.toString(), response.toString());
            }
        } catch (IOException e) {
            throw new RequestFailedException(rootUrl, post.toString(), e);
        }
    }

    @Override
    public Iterator<Page> iterator() {
        return new FjsnwListDiseaseIterator();
    }

    class FjsnwListDiseaseIterator implements Iterator<Page> {

        @Override
        public boolean hasNext() {
            return startPageNumber < endPageNumber;
        }

        @Override
        public FjsnwDisease next() {
            startPageNumber++;
            return FjsnwDisease.this;
        }
    }

    static class FjsnwListPostParams implements Post{
        final String diseaseName;
        final String cause;
        final String method;
        final int pageNum;
        final int isManage;
        final String inputPn;

        public FjsnwListPostParams(int pageNum) {
            this.diseaseName = "";
            this.cause = "";
            this.method = "list";
            this.pageNum = pageNum;
            this.isManage = 0;
            this.inputPn = "";
        }

        @Override
        public RequestBody requestBody() {
            return new FormBody.Builder().add("diseasename", this.diseaseName)
                    .add("cause", this.cause)
                    .add("method", this.method)
                    .add("pageNum", String.valueOf(this.pageNum))
                    .add("isManage", String.valueOf(this.isManage))
                    .add("inputpn", this.inputPn)
                    .build();
        }

        @Override
        public String toString() {
            return "FjsnwListPostParams{" +
                    "diseaseName='" + diseaseName + '\'' +
                    ", cause='" + cause + '\'' +
                    ", method='" + method + '\'' +
                    ", pageNum=" + pageNum +
                    ", isManage=" + isManage +
                    ", inputPn='" + inputPn + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws RequestFailedException {
        for (Page disease : new FjsnwDisease()) {
            disease.html();
        }
    }
}
