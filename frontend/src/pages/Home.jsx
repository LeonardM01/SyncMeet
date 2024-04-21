import { MobileNavbar, Navbar, PageLoader } from "@/components";
import { Button } from "@/components/ui/Button";
import { useAuth0 } from "@auth0/auth0-react";

const Home = () => {
  const { loginWithRedirect, user, isLoading } = useAuth0();

  if (isLoading) return <PageLoader />;

  return (
    <div className="h-screen overflow-x-hidden bg-[url('/assets/home/images/background.webp')] bg-cover bg-no-repeat">
      <Navbar />
      <MobileNavbar />

      <section className="min-w-screen relative">
        <img
          src="/assets/home/images/calendars.webp"
          className="absolute right-0 top-0 max-xl:hidden"
          alt="an image depicting a calendar"
        />

        <div className="md:pt-44 py-16 lg:px-32 md:px-16 px-6 w-full h-full">
          <h1 className="heading1 max-w-xl mb-8">
            Elevate Your Scheduling Experience
          </h1>

          <h2 className="heading2 max-w-xl mb-16">
            Effortlessly track, share and harmonize events with friends!
          </h2>

          {user ? (
            <a
              href="/dashboard"
              className="heading4-bold bg-orange rounded-lg px-10 py-4 h-fit w-full md:max-w-[310px]"
            >
              Start synchronizing now!
            </a>
          ) : (
            <Button
              onClick={() => loginWithRedirect()}
              className="heading4-bold bg-orange rounded-lg px-10 py-4 h-fit w-full md:max-w-[310px]"
            >
              Start synchronizing now!
            </Button>
          )}
        </div>
      </section>
    </div>
  );
};

export default Home;
