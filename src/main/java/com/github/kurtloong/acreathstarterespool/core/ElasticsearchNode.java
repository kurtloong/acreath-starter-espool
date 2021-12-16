package com.github.kurtloong.acreathstarterespool.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticsearchNode {

    private String host;
    private Integer port;

}
