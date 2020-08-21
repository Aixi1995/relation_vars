package elasticsearch;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 16:18
 */
public class IndexService {

    @Autowired
    private ClientService clientService;

    RestHighLevelClient client = clientService.getClient();

    /**
     * get index settings
     *
     * @param index index name
     * @return settings
     */
    public Map<String, Settings> getSettings(String index) {
        GetSettingsRequest request = new GetSettingsRequest().indices(index);
        try {
            GetSettingsResponse response = client.indices().getSettings(request, RequestOptions.DEFAULT);
            return response.getIndexToSettings().get(index).getAsGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * update index.num_of_replicas
     *
     * @param numOfReplicas numOfReplicas
     * @param indices       name
     * @return update successful?
     */
    public boolean updateNumOfReplicas(int numOfReplicas, String... indices) {
        UpdateSettingsRequest request = new UpdateSettingsRequest(indices);
        Map<String, Object> settings = new HashMap<>();
        settings.put("index.number_of_replicas", numOfReplicas);
        request.settings(settings);
        try {
            org.elasticsearch.action.support.master.AcknowledgedResponse response = client.indices().putSettings(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * get index mappings
     *
     * @param index index name
     * @return mappings as map
     */
    public Map<String, Object> getMappings(String index) {
        GetMappingsRequest request = new GetMappingsRequest().indices(index);
        try {
            GetMappingsResponse response = client.indices().getMapping(request, RequestOptions.DEFAULT);
            return response.mappings().get(index).getSourceAsMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * put mappings to indices
     *
     * @param indices names
     * @return put successful?
     */
    public boolean putMappings(String... indices) {
        PutMappingRequest request = new PutMappingRequest(indices);
        String mappings = "";
        request.source(mappings, XContentType.JSON);
        try {
            org.elasticsearch.action.support.master.AcknowledgedResponse response = client.indices().putMapping(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existIndices(String... index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createIndices(String index) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(index);
            String wholeSource = "";
            request.source(wholeSource, XContentType.JSON);
            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * aliases exist?
     *
     * @param aliases alias names
     * @return exist?
     */
    public boolean existAliases(String aliases) {
        GetAliasesRequest request = new GetAliasesRequest(aliases);
        try {
            return client.indices().existsAlias(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * add alias to index
     *
     * @param index index name
     * @param alias alias name
     * @return add successful?
     */
    public boolean addAlias(String index, String alias) {
        AliasActions aliasAction = new AliasActions(AliasActions.Type.ADD).index(index).alias(alias);
        IndicesAliasesRequest request = new IndicesAliasesRequest().addAliasAction(aliasAction);
        try {
            org.elasticsearch.action.support.master.AcknowledgedResponse response = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * index contain alias?
     *
     * @param index index name
     * @param alias alias name
     * @return contain?
     */
    public boolean existAliasFromIndex(String index, String alias) {
        GetAliasesRequest request = new GetAliasesRequest(alias);
        try {
            GetAliasesResponse response = client.indices().getAlias(request, RequestOptions.DEFAULT);
            return Optional.ofNullable(response.getAliases().get(index)).isPresent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * get alias by index name
     *
     * @param indices index names
     * @return aliases
     */
    public Map getAliasByIndex(String... indices) {
        GetAliasesRequest request = new GetAliasesRequest().indices(indices);
        try {
            GetAliasesResponse response = client.indices().getAlias(request, RequestOptions.DEFAULT);
            return response.getAliases();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete alias from index
     *
     * @param index index name
     * @param alias alias name
     * @return delete successful?
     */
    public boolean delAlias(String index, String alias) {
        DeleteAliasRequest request = new DeleteAliasRequest(index, alias);
        try {
            AcknowledgedResponse response = client.indices().deleteAlias(request, RequestOptions.DEFAULT);
            return response.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * refresh index
     *
     * @param indices index names
     */
    public void refreshIndices(String... indices) {
        RefreshRequest request = new RefreshRequest(indices);
        try {
            client.indices().refresh(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
