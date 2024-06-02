import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Auth0Provider } from "@auth0/auth0-react";

import { Home, Dashboard, Membership } from "@/pages";
import { Auth, DefaultLayout } from "@/layouts";
import Blog from "./Blog";

const App = () => {
  return (
    <Auth0Provider
      domain={import.meta.env.VITE_AUTH_DOMAIN}
      clientId={import.meta.env.VITE_AUTH_KEY}
      authorizationParams={{
        redirect_uri: window.location.origin,
      }}
    >
      <DefaultLayout>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/membership" element={<Membership />} />
            <Route path="/blog" element={<Blog />} />
            <Route
              path="/dashboard"
              element={
                 <Auth>
                  <Dashboard />
                 </Auth>
              }
            />
          </Routes>
        </BrowserRouter>
      </DefaultLayout>
    </Auth0Provider>
  );
};

export default App;
