import com.tojaoomy.test.EsDataOperation;
import com.tojaoomy.test.EsIndexOperation;
import com.tojaoomy.test.EsQueryOperation;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 玉书
 * @date 2022/2/17
 */
public class EsConfig {

    private static String EsHost = "127.0.0.1";

    private static int EsPort = 9300;

    private String EsClusterName = "dockerCluster";

    private static final RestHighLevelClient client = client();

    public static RestHighLevelClient client() {
        RestClientBuilder builder= RestClient.builder(new HttpHost("10.221.41.119", 9200, "http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    public static void checkIndex() {
        EsIndexOperation es = new EsIndexOperation(client);
        System.out.println("--------index exists = " + es.checkIndex("record"));
    }

    public static void createIndex() {
        Map<String, Object> columnMap = new HashMap<>() ;
        // ID
        Map<String, Object> id = new HashMap<>();
        id.put("type", "integer");
        columnMap.put("id", id);
        // 姓名
        Map<String, Object> name = new HashMap<>();
        name.put("type", "keyword");
        columnMap.put("name", name);
        // 年龄
        Map<String, Object> age = new HashMap<>();
        age.put("type", "integer");
        columnMap.put("age", age);
        // 创建
        Map<String, Object> createTime = new HashMap<>();
        createTime.put("type", "long");
        columnMap.put("createTime", createTime);

        EsIndexOperation es = new EsIndexOperation(client);

        es.createIndex("record", columnMap);
    }

    public static void deleteIndex() {
        EsIndexOperation es = new EsIndexOperation(client);
        es.deleteIndex("record");
    }

    public static void insertIndex() {
        EsDataOperation es = new EsDataOperation(client);
        Map<String,Object> dataMap = new HashMap<>() ;
        dataMap.put("id", 1);
        dataMap.put("name", "上海-张三");
        dataMap.put("age", 12);
        dataMap.put("createTime", 1611378102795L);
        es.insert("record",dataMap);
    }

    public static void countIndex() {
        EsQueryOperation queryOperation = new EsQueryOperation(client);
        System.out.println("----- count : " + queryOperation.count("record"));
    }

    public static void main(String[] args) throws Exception {
//        createIndex();
//        checkIndex();
//        deleteIndex();
//        checkIndex();
//        insertIndex();
        countIndex();
    }
}