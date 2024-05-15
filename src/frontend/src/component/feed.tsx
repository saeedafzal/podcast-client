import { $router } from "../stores/router.ts";

interface FeedProps {
    feed: DashboardFeed;
}

const Feed = ({ feed }: FeedProps) => {
    const gotoFeed = () => $router.open(`/${feed.feedId}`);

    return <>
        <div onClick={gotoFeed}>
            <img src={feed.imageUrl} alt={feed.imageAlt} height={100} width={100}/>
            <h4>{feed.title}</h4>
            <div>{feed.description}</div>
        </div>
    </>;
};

export default Feed;