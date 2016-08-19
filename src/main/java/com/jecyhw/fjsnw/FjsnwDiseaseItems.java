package com.jecyhw.fjsnw;

import com.jecyhw.request.Page;
import com.jecyhw.request.exception.RequestFailedException;
import com.jecyhw.response.filter.Filter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jecyhw on 16-8-19.
 */
public class FjsnwDiseaseItems implements Iterable<FjsnwDiseaseItem> {
    final List<String> urls;
    int n;
    public FjsnwDiseaseItems(Page disease) throws RequestFailedException {
        urls = new DiseaseItemsFilter(disease.html()).filter();
        n = 0;
    }

    @Override
    public Iterator<FjsnwDiseaseItem> iterator() {
        return new FjsnwDiseaseItemIterator();
    }

    class FjsnwDiseaseItemIterator implements Iterator<FjsnwDiseaseItem> {

        @Override
        public boolean hasNext() {
            return n < urls.size();
        }

        @Override
        public FjsnwDiseaseItem next() {
            return new FjsnwDiseaseItem(urls.get(n++));
        }
    }

    class DiseaseItemsFilter implements Filter<List<String>> {
        final String diseaseHtml;
        DiseaseItemsFilter(String diseaseHtml) {
            this.diseaseHtml = diseaseHtml;
        }
        @Override
        public List<String> filter() {
            List<String> result = new ArrayList<>();
            Document doc = Jsoup.parse(diseaseHtml);
            Elements urls = doc.select("div.czllist td:eq(0) > a");
            for (Element url : urls) {
                result.add(FjsnwConstant.ROOT_URL + url.attr("href"));
            }
            return result;
        }
    }
}
