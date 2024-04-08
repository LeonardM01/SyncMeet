import { Suspense } from "react";

import { PageLoader } from "../components";
import background from "/src/backgrounds/background.svg";

const DefaultLayout = ({ children }) => {
  return (
      <Suspense fallback={<PageLoader/>}>
              <div
                  className="min-h-screen max-w-screen bg-cover text-white font-nunito flex flex-col"
                  style={{backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url(${background})`}}
              >
                  <main className="mx-auto w-full h-full flex-grow">{children}</main>
              </div>
      </Suspense>
);
};

export default DefaultLayout;
