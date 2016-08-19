package com.jecyhw.fjsnw;

import com.jecyhw.bean.FjsnwBean;
import com.jecyhw.request.Page;
import com.jecyhw.request.Request;
import com.jecyhw.request.exception.RequestFailedException;
import com.jecyhw.response.filter.Filter;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by jecyhw on 16-8-19.
 */
public class FjsnwDiseaseItem implements Page {
    static final Logger logger = Logger.getLogger(FjsnwDiseaseItem.class);

    final String itemUrl;

    public FjsnwDiseaseItem(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    @Override
    public String html() throws RequestFailedException {
        logger.info("当前抓取的链接:" + itemUrl);
        return Request.get(itemUrl);
    }

    final Filter<FjsnwBean> fjsnwDiseaseItemFilter = new FjsnwDiseaseItemFilter();

    class FjsnwDiseaseItemFilter implements Filter<FjsnwBean> {

        @Override
        public FjsnwBean filter() {
            FjsnwBean bean = new FjsnwBean();
            try {
                Document doc = Jsoup.parse(html());
                Elements trs = doc.select("tr");
                bean.setDiseaseName(trs.get(0).select("td:eq(1)").first().text());
                bean.setHarmPlant(trs.get(1).select("td:eq(1)").first().text());
                bean.setDiseaseReason(trs.get(2).select("td:eq(1)").first().text());
                bean.setDiseaseDescription(trs.get(4).select("td:eq(0)").first().text());
                bean.setCureMethod(trs.get(6).select("td:eq(0)").first().text());
                bean.setReference(trs.get(8).select("td:eq(0)").first().text());
            } catch (RequestFailedException e) {
                e.printStackTrace();
            }
            return bean;
        }
    }

    public FjsnwBean toFjsnwBean() {
        return fjsnwDiseaseItemFilter.filter();
    }
}
