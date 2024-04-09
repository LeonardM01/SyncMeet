import { Suspense } from "react";

import { PageLoader } from "../components";

const DefaultLayout = ({ children }) => {
  return (
      <Suspense fallback={<PageLoader/>}>
              <div
                  className="bg-background bg-gray-600 bg-blend-multiply bg-cover bg-no-repeat min-h-screen max-w-screen text-white font-nunito flex flex-col"
              >
                  <main className="mx-auto w-full h-full flex-grow">{children}</main>
              </div>
      </Suspense>
);
};

export default DefaultLayout;
