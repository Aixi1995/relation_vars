package elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/21 17:47
 * @desc
 */
@Service
public class ClientService {

    private RestHighLevelClient client = null;

    @Value("${elasticsearch.hostname}")
    private String hostname;

    @Value("${elasticsearch.port}")
    private Integer port;


    public RestHighLevelClient getClient() {
        if (client == null) {
            client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost(hostname, port, "http")
            ));
        }
        return client;
    }
}
