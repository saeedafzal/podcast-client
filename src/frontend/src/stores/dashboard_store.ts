import { atom } from "nanostores";

export const $feedsLoading = atom<boolean>(true);
export const $feeds = atom<DashboardFeed[]>([]);

export function initialiseFeeds(): void {
    fetch("http://localhost:8080/api/feeds")
        .then(res => res.json())
        .then(res => {
            console.log(res);
            $feeds.set(res);
            $feedsLoading.set(false);
        })
        .catch(e => console.error("Error getting feeds from API:", e));
}

export function addNewFeed(url: string, callback: () => void): void {
    fetch("http://localhost:8080/api/feeds", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ url })
    }).then(res => {
        return res.json();
    }).then(res => {
        console.log(res);
        $feeds.set([...$feeds.get(), res]);
        callback();
    }).catch(e => {
        console.error("Error adding feed from url:", url, e);
    });
}