import { Navbar } from "../components";

const Home = () => {
  return (
    <div
      className={
        "bg-bottom-right bg-right bg-no-repeat h-screen overflow-x-hidden md:px-8 lg:px-32 sm:px-0"
      }
      style={{
        backgroundImage: `url(' '), url('/assets/general/backgrounds/homepage.svg)`,
        backgroundRepeat: "no-repeat",
        backgroundSize: "948.72px 1031.89px, cover",
      }}
    >
      <Navbar />

      <section className="min-w-screen relawtive">
        <div className="my-56 lg:mx-32 md:mx-16 sm:mx-10">
          <img
            src="/assets/general/backgrounds/hero.svg"
            className="absolute right-0i"
            alt="an image depicting a calendar"
          />

          <h1 className="heading1 max-w-xl my-6">
            Elevate Your Scheduling Experience
          </h1>

          <h2 className="heading2 max-w-xl my-6">
            Effortlessly track, share and harmonize events with friends!
          </h2>

          <a
            href="/membership"
            className="heading4-bold bg-orange rounded-lg px-7 py-4 my-6 max-w-[309px]"
          >
            Start synchronizing now!
          </a>
        </div>
      </section>
    </div>
  );
};

export default Home;
