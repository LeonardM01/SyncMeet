import {Navbar} from "../components";

const Home = () => {
    return (
        <>
            <Navbar/>
            <div
                className={"bg-background2 h-screen bg-contain bg-right bg-no-repeat grid grid-rows-1 items-center ms-20"}
            >
                <div>
                    <div className={"heading1-bold leading-tight mb-6 w-[627px] h-[234px]"}>
                        Elevate Your Scheduling Experience
                    </div>
                    <div className={"heading2 mb-12 w-[627px] h-[74px]"}>
                        Effortlessly track, share and harmonize events with friends!
                    </div>
                    <button
                        className={"bg-orange heading4-bold rounded-lg w-[309px] h-[51px]"}
                    >Start synchronizing now!
                    </button>
            </div>
            </div>
        </>
    );
};

export default Home;
