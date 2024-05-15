package com.saeed.podcast.repository;

import com.saeed.podcast.model.Episode;
import com.saeed.podcast.model.dto.EpisodeRowMapper;
import com.saeed.podcast.model.dto.FeedResponseRowMapper;
import com.saeed.podcast.model.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedDBRepository {

    private static final String INSERT_EPISODE_SQL = "insert into episodes (feed_id, title, description, pub_date, url, audio_type) values (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SQL = "insert into feeds (feed_id, title, description, image_src, image_alt) values (?, ?, ?, ?, ?)";
    private static final String SELECT_EPISODE_SQL = """
        select * from episodes e
        join feeds f on e.feed_id = f.feed_id
        where f.feed_id = ?
        """;
    private static final String SELECT_SQL = "select * from feeds";

    private final JdbcTemplate jdbcTemplate;

    public void storeEpisodes(String feedId, List<Episode> episodes) {
        log.info("Storing {} episodes...", episodes.size());

        jdbcTemplate.batchUpdate(INSERT_EPISODE_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Episode episode = episodes.get(i);
                ps.setString(1, feedId);
                ps.setString(2, episode.getTitle());
                ps.setString(3, episode.getDescription());
                ps.setDate(4, episode.getPubDate());
                ps.setString(5, episode.getUrl());
                ps.setString(6, episode.getType());
            }

            @Override
            public int getBatchSize() {
                return episodes.size();
            }
        });
    }

    public void storeFeed(FeedResponse feedResponse) {
        jdbcTemplate.update(
            INSERT_SQL,
            feedResponse.getFeedId(),
            feedResponse.getTitle(),
            feedResponse.getDescription(),
            feedResponse.getImageUrl(),
            feedResponse.getImageAlt()
        );
    }

    public List<FeedResponse> getAllFeeds() {
        return jdbcTemplate.query(SELECT_SQL, new FeedResponseRowMapper());
    }

    public List<Episode> getAllEpisodesForFeed(String feedId) {
        return jdbcTemplate.query(SELECT_EPISODE_SQL, new EpisodeRowMapper(), feedId);
    }
}
