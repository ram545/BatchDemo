package com.example.writers;

import com.example.model.MlbPlayerData;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcWriter extends JdbcBatchItemWriter<MlbPlayerData> {

    public JdbcWriter(DataSource dataSource) {
        JdbcBatchItemWriter<MlbPlayerData> writer = new JdbcBatchItemWriter<>();
        this.setDataSource(dataSource);
        this.setSql("INSERT INTO `batch_jobs`.`mlb_player_data` (`name`,`age`,`height`,`position`,`team`,`weight`)" +
                " VALUES (:name, :age, :height, :position, :team, :weight)");
        this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<MlbPlayerData>());
        this.afterPropertiesSet();
    }
}