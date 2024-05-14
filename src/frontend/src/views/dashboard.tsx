import { useStore } from "@nanostores/react";
import { $feeds, $feedsLoading, addNewFeed, initialiseFeeds } from "../stores/dashboard_store.ts";
import { FormEvent, useEffect, useRef } from "react";
import Feed from "../component/feed.tsx";

const FeedsLoading = () => (<div>Loading feeds...</div>);
const NoFeeds = () => (<div>No feeds. Please add a feed using the "Add Feed" button.</div>);

const FeedList = () => {
    const feeds = useStore($feeds);

    return <>
        {
            feeds.map((feed, i) => <Feed key={i} feed={feed}/>)
        }
    </>;
};

const Dashboard = () => {
    const dialogRef = useRef<HTMLDialogElement>(null);
    const feedsLoading = useStore($feedsLoading);
    const feeds = useStore($feeds);

    const formHandler = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const url = formData.get("url-input")!.toString();
        console.log("Adding feed for url", url);
        addNewFeed(url, () => dialogRef.current!.close());
    };

    useEffect(() => {
        if (feedsLoading) initialiseFeeds();
    }, []);

    return <>
        <button onClick={() => dialogRef.current!.showModal()}>Add Feed</button>
        <h3>Feeds</h3>
        {
            feedsLoading ?
                <FeedsLoading/> :
                feeds.length === 0 ? <NoFeeds/> : <FeedList/>
        }

        <dialog ref={dialogRef}>
            <form onSubmit={formHandler}>
                <input
                    type="url"
                    placeholder="Enter url of RSS feed..."
                    name="url-input"
                    required/>
                <button>Add Feed</button>
            </form>
        </dialog>
    </>;
};

export default Dashboard;