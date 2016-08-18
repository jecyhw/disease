package com.jecyhw;

import com.jecyhw.request.params.Post;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-18.
 */
public class FjsnwDisease implements Iterable<FjsnwDisease> {
    static final Logger logger = Logger.getLogger(FjsnwDisease.class);
    static final String rootUrl = "http://www.fjsnw.cn/disease.shtml";
    int startPageNumber = 0;
    int endPageNumber = 61;

    public void getPage() {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(rootUrl).post(new FjsnwListPostParams(startPageNumber).requestBody()).build();
        logger.info("当前抓取页:" + startPageNumber);
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {

            } else {
                logger.error(":" + startPageNumber);
                logger.error(response.message());
            }
        } catch (IOException e) {
            logger.error("page:" + startPageNumber);
            logger.error(e.getMessage());
        }
    }

    @Override
    public Iterator<FjsnwDisease> iterator() {
        return new FjsnwListDiseaseIterator();
    }

    class FjsnwListDiseaseIterator implements Iterator<FjsnwDisease> {

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

    public static void main(String[] args) {
        for (FjsnwDisease disease : new FjsnwDisease()) {
            disease.getPage();
        }
    }
}
