import { Suspense } from "react";

import { PageLoader } from "../components";

const DefaultLayout = ({ children }) => {
  return (
    <Suspense fallback={<PageLoader />}>
      <div className="min-h-screen w-screen bg-dark-400 text-white font-nunito">
        <main className="mx-auto w-full h-full">{children}</main>
      </div>
    </Suspense>
  );
};

export default DefaultLayout;
