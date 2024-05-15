import { atom } from "nanostores";

export const $episodes = atom<Episode[]>([]);

export function getEpisodesFromFeed(feedId: string) {
    fetch(`http://localhost:8080/api/feeds/episodes/${feedId}`)
        .then(res => res.json())
        .then(res => $episodes.set(res))
        .catch(e => console.error(`Error getting episodes from feed ${feedId}:`, e));
}