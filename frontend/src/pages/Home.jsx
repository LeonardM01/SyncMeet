import { Navbar } from "../components";
import backgroundImage from "/SyncMeet/frontend/public/assets/general/backgrounds/homepage.svg"
import heroImage from "/SyncMeet/frontend/public/assets/general/backgrounds/hero.svg"

const Home = () => {

  const backgroundImageStyle = {
    backgroundImage: `url(${heroImage}), url(${backgroundImage})`,
    backgroundSize: '948.72px 1031.89px, cover',
    backgroundPosition: 'bottom right, center',
    backgroundRepeat: 'no-repeat',
    height: '100vh',
    overflowX: 'hidden',
  };

  const buttonStyle = {
    width: '309px',
    height: '51px',
    fontSize: '18px'
  };


  return (
    <div style={backgroundImageStyle} className="md:px-8 lg:px-32 sm:px-0">
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
