import { Navbar } from "../components";
import backgroundImage from "/SyncMeet/frontend/public/assets/general/backgrounds/homepage.svg"
import heroImage from "/SyncMeet/frontend/public/assets/general/backgrounds/hero.svg"

const Home = () => {

  const buttonStyle = {
    width: '309px',
    height: '51px',
    fontSize: '18px'
  };

  return (
    <div
      className={"bg-bottom-right bg-right bg-no-repeat h-screen overflow-x-hidden md:px-8 lg:px-32 sm:px-0"}
      style={{
          backgroundImage: `url(${heroImage}), url(${backgroundImage})`,
          backgroundRepeat: 'no-repeat',
          backgroundSize: '948.72px 1031.89px, cover',
      }}
    >
      <Navbar />
      <div className="my-56 lg:mx-32 md:mx-16 sm:mx-10"> 
        <section className="heading1 max-w-xl my-6">
          Elevate Your Scheduling Experience
        </section>
        <section className="heading2 max-w-xl my-6">
          Effortlessly track, share and harmonize events with friends!
        </section>
        <button className="button border border-orange bg-orange rounded-lg px-7 py-2.5 my-6"  style={buttonStyle}>
          Start synchronizing now!
        </button>
      </div>
    </div>
  );
};

export default Home;
