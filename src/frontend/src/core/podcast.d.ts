interface DashboardFeed {
    feedId: string;
    title: string;
    description: string;
    imageUrl: string;
    imageAlt: string;
}

interface Episode {
    title: string;
    description: string;
    pubDate: Date;
    url: string;
    type: string;
}