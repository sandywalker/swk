package com.wenku.convert;

import aspose.pdf.Row;
import com.wenku.queue.ProcQueue;
import com.wenku.queue.ProcState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sandy on 16/09/2017.
 */
@Component
@Transactional(readOnly = true)
public class ConvertDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private static final String TBL_NAME = "doc_proc_queue";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TBL_NAME).usingGeneratedKeyColumns("id");
    }

    @Transactional
    public void newProcQueue(ProcQueue procQueue){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("uid",procQueue.getUid());
        parameters.addValue("did",procQueue.getDid());
        parameters.addValue("state",procQueue.getState().ordinal());
        parameters.addValue("create_time",procQueue.getCreateTime());
        parameters.addValue("update_time",procQueue.getUpdateTime());
        parameters.addValue("msg",procQueue.getMsg());
        Number newId = jdbcInsert.executeAndReturnKey(parameters);
        procQueue.setId(newId.longValue());
    }

    @Transactional
    public void updateDocPageCount(Long did,Integer pageCount){
        this.jdbcTemplate.update("update doc_upload set page_count = ? where id = ?",pageCount,did);
    }

    @Transactional
    public void updateQueueState(Long id, ProcState state,String message){
        this.jdbcTemplate.update("update " + TBL_NAME + " set state = ? , msg = ? where id = ?",state.ordinal(),message,id);
    }

    public ProcQueue findOne(Long id){
        String sql = "select * from " + TBL_NAME + " where id = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id},new QueueRowMapper());
    }

    public List<ProcQueue> findQueueByState(ProcState state){
        String sql = "select * from " + TBL_NAME + " where state = ?";
        return this.jdbcTemplate.query(sql, new Object[]{state.ordinal()},new QueueRowMapper());
    }

    private static class QueueRowMapper implements RowMapper<ProcQueue>{

        @Override
        public ProcQueue mapRow(ResultSet resultSet, int i) throws SQLException {
            ProcQueue queue = new ProcQueue();
            queue.setId(resultSet.getLong("id"));
            queue.setUid(resultSet.getLong("uid"));
            queue.setDid(resultSet.getLong("did"));
            queue.setState( ProcState.values()[resultSet.getInt("state")]);
            queue.setCreateTime(resultSet.getDate("create_time"));
            queue.setUpdateTime(resultSet.getDate("update_time"));
            queue.setMsg(resultSet.getString("msg"));
            queue.setFileType(resultSet.getString("file_type"));
            return queue;
        }
    }


}
