import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Auth0Provider } from "@auth0/auth0-react";

// import { Navbar } from "../components";
import { Home, Dashboard } from "../pages";
import { Auth, DefaultLayout } from "../layouts";

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