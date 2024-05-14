package com.saeed.podcast.repository;

import com.saeed.podcast.model.dto.FeedResponseRowMapper;
import com.saeed.podcast.model.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDBRepository {

    private static final String INSERT_SQL = "insert into feeds (title, description, image_src, image_alt) values (?, ?, ?, ?)";
    private static final String SELECT_SQL = "select * from feeds";

    private final JdbcTemplate jdbcTemplate;

    public void storeFeed(FeedResponse feedResponse) {
        jdbcTemplate.update(
            INSERT_SQL,
            feedResponse.getTitle(),
            feedResponse.getDescription(),
            feedResponse.getImageUrl(),
            feedResponse.getImageAlt()
        );
    }

    public List<FeedResponse> getAllFeeds() {
        return jdbcTemplate.query(SELECT_SQL, new FeedResponseRowMapper());
    }
}
