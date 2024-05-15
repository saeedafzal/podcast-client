package com.saeed.podcast.model.dto;

import com.saeed.podcast.model.response.FeedResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedResponseRowMapper implements RowMapper<FeedResponse> {

    @Override
    public FeedResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return FeedResponse.builder()
            .feedId(rs.getString("feed_id"))
            .title(rs.getString("title"))
            .description(rs.getString("description"))
            .imageUrl(rs.getString("image_src"))
            .imageAlt(rs.getString("image_alt"))
            .build();
    }
}
