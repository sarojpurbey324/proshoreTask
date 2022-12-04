package com.proshore.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QueryAndParamsDTO {
    private String query;
    private Map<String, Object> params;
    private List<MapSqlParameterSource> mapSqlParams;

    public QueryAndParamsDTO(String query, Map<String, Object> params) {
        this.query = query;
        this.params = params;
    }
}
