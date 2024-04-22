import { useAuth0 } from "@auth0/auth0-react";

import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet";
import { Button } from "@/components/ui/Button";

const MobileNavbar = () => {
  const { loginWithRedirect } = useAuth0();

  return (
    <nav className="flex-between w-full bg-black-400 lg:px-32 md:px-16 sm:px-12 pt-3 sm:hidden">
      <img
        src="/assets/general/icons/logo.svg"
        width={115}
        height={87}
        alt="logo"
      />

      <Sheet>
        <SheetTrigger asChild className="border-0 mr-5">
          <Button
            variant="outline"
            className="p-1 h-fit w-fit border border-orange rounded-lg"
          >
            <img
              src="/assets/home/icons/hamburger.svg"
              width={35}
              height={35}
              alt="hambuerger menu icon"
            />
          </Button>
        </SheetTrigger>

        <SheetContent
          side="top"
          className="!bg-dark-400 !gap-y-6 flex-col heading4 pt-10 flex border-0 text-white"
        >
          <a href="/membership">Membership</a>
          <a href="/Blog">Blog</a>
          <button
            className="button-bold w-full border border-orange rounded-lg px-5 py-2.5 line-clamp"
            onClick={() => loginWithRedirect()}
          >
            Sign Up
          </button>
        </SheetContent>
      </Sheet>
    </nav>
  );
};

export default MobileNavbar;
