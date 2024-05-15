import { useEffect } from "react";
import { $episodes, getEpisodesFromFeed } from "../stores/episodes_store.ts";
import { useStore } from "@nanostores/react";

interface EpisodesProps {
    feedId: string;
}

const EpisodeList = () => {
    const episodes = useStore($episodes);

    return <>
        {
            episodes.map((episode, i) => {
                return <div key={i}>
                    <h3>{episode.title}</h3>
                    <p>{episode.description}</p>
                    <small>{episode.pubDate.toString()}</small>
                    <div>
                        <audio controls controlsList="nodownload" preload="none" src={episode.url} style={{width: "100%"}}/>
                    </div>
                </div>
            })
        }
    </>;
};

const Episodes = ({ feedId }: EpisodesProps) => {
    const episodes = useStore($episodes);

    useEffect(() => getEpisodesFromFeed(feedId), []);

    return <>
        {
            episodes.length === 0 ?
                <div>There are no episodes.</div> :
                <EpisodeList/>
        }
    </>;
};

export default Episodes;