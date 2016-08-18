package com.jecyhw.request;

import com.jecyhw.request.exception.RequestFailedException;
import org.aspectj.lang.annotation.Aspect;

import java.util.Iterator;

/**
 * Created by jecyhw on 16-8-18.
 * 爬取一张网页内容的接口
 */
public interface Page {
    /**
     * @return 返回抓取网页的html文本
     */
    String html() throws RequestFailedException;
}
