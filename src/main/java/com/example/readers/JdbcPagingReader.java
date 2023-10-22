package com.example.readers;

import com.example.model.MlbPlayerData;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class JdbcPagingReader{

    @Value("${batch.input.path}")
    private String fetchSize;

    @Value("${batch.input.path}")
    private String pageSize;
    @Autowired
    private DataSource dataSource;


    public ItemReader<MlbPlayerData> getJdbcPagingReader(){
//        this.setDataSource(dataSource);
//        this.setFetchSize(Integer.parseInt(fetchSize));
//        this.setRowMapper(new BeanPropertyRowMapper<MlbPlayerData>(){
//            {
//                setMappedClass(MlbPlayerData.class);
//            }
//        });
//        this.setPageSize(Integer.parseInt(pageSize));
//
//        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
//        mySqlPagingQueryProvider.setSelectClause("*");
//        mySqlPagingQueryProvider.setFromClause("from mlb_player_data");
//
//        Map<String, Order> orderByName = new HashMap<>();
//        orderByName.put("name", Order.ASCENDING);
//
//        mySqlPagingQueryProvider.setSortKeys(orderByName);
//        this.setQueryProvider(mySqlPagingQueryProvider);
        return null;
    }
}
