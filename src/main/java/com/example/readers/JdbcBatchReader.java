package com.example.readers;

import com.example.constants.SqlQueries;
import com.example.model.MlbPlayerData;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcBatchReader extends JdbcCursorItemReader<MlbPlayerData> {

    public JdbcBatchReader(DataSource dataSource){
        this.setDataSource(dataSource);
        this.setRowMapper(new BeanPropertyRowMapper<MlbPlayerData>(){
            {
                setMappedClass(MlbPlayerData.class);
            }
        });
        this.setSql(SqlQueries.Get_By_Name_Query);
    }
}
