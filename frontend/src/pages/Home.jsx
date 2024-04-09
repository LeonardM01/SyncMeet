import background2 from "@/backgrounds/background2.svg";
import {Navbar} from "../components";

const Home = () => {
    return (
        <>
            <Navbar/>
            <div
                className={"h-screen bg-contain bg-right grid grid-rows-1 items-center ms-20"}
                style={{
                    backgroundImage: `url(${background2})`,
                    backgroundRepeat: 'no-repeat',
                }}>
                <div>
                    <div className={"heading1-bold leading-tight mb-6"}>
                        Elevate Your
                        <br/>
                        Scheduling
                        <br/>
                        Experience
                    </div>
                    <div className={"heading2 mb-12"}>
                        Effortlessly track, share and harmonize
                        <br/>
                        events with friends!
                    </div>
                    <button
                        className={"bg-orange heading4-bold rounded-lg"}
                        style={{width: '309px', height: '51px'}}
                    >Start synchronizing now!
                    </button>
            </div>
            </div>
        </>
    );
};

export default Home;
