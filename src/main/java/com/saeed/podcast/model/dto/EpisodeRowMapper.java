package com.saeed.podcast.model.dto;

import com.saeed.podcast.model.Episode;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EpisodeRowMapper implements RowMapper<Episode> {

    @Override
    public Episode mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Episode.builder()
            .title(rs.getString("title"))
            .description(rs.getString("description"))
            .pubDate(rs.getDate("pub_date"))
            .url(rs.getString("url"))
            .type(rs.getString("audio_type"))
            .build();
    }
}
