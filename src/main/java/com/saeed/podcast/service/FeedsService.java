package com.saeed.podcast.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.saeed.podcast.RssXmlRepository;
import com.saeed.podcast.model.Episode;
import com.saeed.podcast.model.response.EpisodeResponse;
import com.saeed.podcast.model.response.FeedResponse;
import com.saeed.podcast.repository.FeedDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedsService {

    private final RssXmlRepository rssXmlRepository;
    private final FeedDBRepository feedDBRepository;

    public FeedResponse addNewFeed(String url) {
        SyndFeed xml = rssXmlRepository.getRssXml(url);
        String feedId = UUID.randomUUID().toString();

        FeedResponse feedResponse = FeedResponse.builder()
            .feedId(feedId)
            .title(xml.getTitle())
            .description(xml.getDescription())
            .imageUrl(xml.getImage().getUrl())
            .imageAlt(xml.getImage().getTitle())
            .build();
        feedDBRepository.storeFeed(feedResponse);

        List<Episode> episodes = xml.getEntries()
            .stream()
            .map(Episode::entryToEpisode)
            .toList();
        feedDBRepository.storeEpisodes(feedId, episodes);

        return feedResponse;
    }

    public List<FeedResponse> getAllFeeds() {
        return feedDBRepository.getAllFeeds();
    }

    public List<EpisodeResponse> getEpisodesFromFeed(String feedId) {
        return feedDBRepository.getAllEpisodesForFeed(feedId)
            .stream()
            .map(EpisodeResponse::fromEpisode)
            .toList();
    }
}
