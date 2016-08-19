import com.jecyhw.fjsnw.FjsnwDiseaseItem;
import com.jecyhw.fjsnw.FjsnwDiseaseItems;
import com.jecyhw.fjsnw.FjsnwDiseases;
import com.jecyhw.request.Page;
import com.jecyhw.request.exception.RequestFailedException;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jecyhw on 16-8-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.jecyhw.SpringAopConfig.class)
public class FjsnwDiseaseTest {

    @Autowired
    FjsnwDiseases diseases;

    @org.junit.Test
    public void test() {
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url("http://www.fjktp.cn:81/inquiry//uploadfile/jpg/F611743086A14488A46BB66B9A56DA7C.jpg").get().build();
        try {
            Response response = httpClient.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream("1.jpg");
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
        } catch (IOException e) {
        }
        for (Page disease : diseases) {
            try {
                for (FjsnwDiseaseItem diseaseItem : new FjsnwDiseaseItems(disease)) {
                    diseaseItem.toFjsnwBean();
                }

            } catch (RequestFailedException e) {
                e.printStackTrace();
            }

        }
    }
}
