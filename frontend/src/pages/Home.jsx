import { MobileNavbar, Navbar } from "@/components";
import { Button } from "@/components/ui/Button";

const Home = () => {
  return (
    <div className="h-screen overflow-x-hidden sm:overflow-y-hidden bg-[url('/assets/home/images/background.webp')] bg-cover bg-no-repeat">
      <Navbar />
      <MobileNavbar />

      <section className="min-w-screen relative">
        <img
          src="/assets/home/images/calendars.webp"
          className="absolute right-0 top-0 max-xl:hidden"
          alt="an image depicting a calendar"
        />

        <div className="md:pt-44 pt-16 lg:px-32 md:px-16 px-6 w-full h-full">
          <h1 className="heading1 max-w-xl mb-8">
            Elevate Your Scheduling Experience
          </h1>

          <h2 className="heading2 max-w-xl mb-16">
            Effortlessly track, share and harmonize events with friends!
          </h2>

          <Button
            href="/membership"
            className="heading4-bold bg-orange rounded-lg px-10 py-4 h-fit w-full md:max-w-[310px]"
          >
            Start synchronizing now!
          </Button>
        </div>
      </section>
    </div>
  );
};

export default Home;
