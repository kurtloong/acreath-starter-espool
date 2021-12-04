package com.github.kurtloong.acreathstarterespool.config;

import com.github.kurtloong.acreathstarterespool.core.ElasticsearchNode;
import org.apache.http.HttpHost;

import java.util.List;

public class RestClientClusterConfiguration extends RestClientConfiguration{

    private List<ElasticsearchNode> clusterNodes;

    public RestClientClusterConfiguration(){

    }

    public RestClientClusterConfiguration(List<ElasticsearchNode> clusterNodes){
        this.clusterNodes = clusterNodes;
    }

    public void setHosts(List<ElasticsearchNode> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    @Override
    public HttpHost[] getHttpHosts(){
        HttpHost[] hostArray = new HttpHost[clusterNodes.size()];
        for (int i=0; i<clusterNodes.size(); i++) {
            hostArray[i] = new HttpHost(clusterNodes.get(i).getHost(), clusterNodes.get(i).getPort(), "http");
        }
        return hostArray;
    }

}