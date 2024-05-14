import { useStore } from "@nanostores/react";
import { $router } from "../stores/router.ts";
import Dashboard from "../views/dashboard.tsx";

const Layout = () => {
    const page = useStore($router);

    if (!page) {
        return <div>404 - Page not found.</div>;
    }

    switch (page.route) {
        case "dashboard":
            return <Dashboard/>
    }
};

export default Layout;