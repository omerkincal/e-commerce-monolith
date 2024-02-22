package com.example.ecommercewebapp.library.datasourceloadoptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote  UrlEncoder ile query string'i çevirmeniz gerekiyor.
 * <h1>Example</h1>
 * <h4>Not So</h4>
 * <p>?filter=[["storage","contains","mehmet"],["count","lessThan","3"],["stockSubType","equals","asad"],["created","between","15.11.2023","17.11.2023"]]</p>
 * <br>
 * <h4>Do This</h4>
 * <p>?filter=%5B%5B%22storage%22%2C%22contains%22%2C%22mehmet%22%5D%2C%5B%22count%22%2C%22lessThan%22%2C%223%22%5D%2C%5B%22stockSubType%22%2C%22equals%22%2C%22asad%22%5D%2C%5B%22created%22%2C%22between%22%2C%2215.11.2023%22%2C%2217.11.2023%22%5D%5D</p>
 * <br>
 * <a href="https://www.urlencoder.org/">urlencoder</a>
 * @see DataSourceLoadOptionsUtil
 * @author Ahmet Emin Hergüner
 */
@Getter
@NoArgsConstructor
public class DataSourceLoadOptions {
    @Nullable
    private List<List<String>> filter;

    public DataSourceLoadOptions(@Nullable String filter) throws JsonProcessingException {
        if(StringUtils.hasLength(filter)){
            ObjectMapper objectMapper = new ObjectMapper();
            this.filter = objectMapper.readValue(filter, List.class);
        }
        else{
            this.filter = null;
        }
    }

    public void setFilter(@Nullable String filter) throws JsonProcessingException {
        if(StringUtils.hasLength(filter)){
            ObjectMapper objectMapper = new ObjectMapper();
            this.filter = objectMapper.readValue(filter, List.class);
        }
        else{
            this.filter = null;
        }
    }

    public void addFilter(List<String> addfilter) {
        if(filter == null){
            filter = new ArrayList<>();
        }
        filter.add(addfilter);
    }

    public void deleteFilter(List<String> deleteFilter) {
        if(filter == null){
            filter = new ArrayList<>();
        }
        filter.remove(deleteFilter);
    }
}
