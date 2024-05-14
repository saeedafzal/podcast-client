import React from "react";
import ReactDOM from "react-dom/client";
import Layout from "./component/layout.tsx";

// Root element.
const root = document.getElementById("root");
if (!root) {
    throw Error("Root element not found.");
}

// React.
ReactDOM.createRoot(root).render(
    <React.StrictMode>
        <Layout/>
    </React.StrictMode>
);