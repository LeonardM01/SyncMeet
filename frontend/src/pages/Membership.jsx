import { MobileNavbar, Navbar } from "@/components";
import { Button } from "@/components/ui/Button";
import { Card } from "@/components/ui/card";
import { Input } from "@/components/ui/input";

const Membership = () => {
  return (
    <div className="min-h-screen overflow-x-hidden">
      <Navbar />
      <MobileNavbar />

      <section className="min-w-screen min-h-screen relative flex bg-[url('/assets/membership/images/background.webp')] bg-cover bg-center bg-no-repeat">
        <img
          src="/assets/membership/images/feature-cloud.webp"
          className="max-xl:hidden max-w-[1061px] w-full h-min object-cover md:pt-44 py-16"
          height={377}
          width={661}
          alt="an image depicting a cloud with features on it"
        />

        <div className="md:pt-44 py-16 lg:px-32 md:px-16 px-6 w-fit h-fit">
          <h1 className="heading1-bold max-w-xl mb-4">
            Access <span className="text-orange">Elite</span> Calendar{" "}
            <span className="text-orange">Features</span>
          </h1>

          <h2 className="heading2 max-w-2xl mb-16">
            Enhance your daily organization with advanced tools and personalized
            support!
          </h2>

          <form
            className="flex-start h-full flex-col"
            onSubmit={(e) => e.preventDefault()}
          >
            <div className="flex-start w-full gap-x-8">
              <Input
                type="email"
                autoComplete="email"
                className="!bg-light-400 text-black max-w-[326px] !outline-none !border-0 !ring-0 !ring-offset-0 h-[47px]"
              />
              <Button
                type="submit"
                className="button-bold flex-center bg-orange rounded-lg h-full px-5 py-3 line-clamp"
              >
                Notify Me
              </Button>
            </div>

            <small className="pt-4 paragraph self-start text-light-400">
              Stay Updated: Don&apos;t Miss Exclusive Offers and Updates
            </small>
          </form>
        </div>
      </section>

      <section className="flex-center flex-col mt-20">
        <div className="flex-center flex-col">
          <small className="text-orange pb-[6px]">
            START WITH A 7-DAY TRIAL
          </small>
          <h3 className="heading1-bold max-w-xl mb-4 text-center">
            Pricing Plans
          </h3>
          <p className="heading4 text-center pt-3">
            Choose the best plan that suits your needs
          </p>
        </div>

        <div className="mt-16 mb-24 flex max-xl:flex-col gap-16">
          <Card className="bg-orange-gradient h-fit self-center rounded-[20px] p-0.5">
            <div className="bg-dark-400 h-fit rounded-[20px] py-12 px-[75px] flex-center flex-col ">
              <p className="button">BASIC</p>
              <h2 className="pt-3 text-[31px] leading-[120%]">$5 / month</h2>

              <Button className="!bg-transparent border button border-orange rounded-lg w-full mt-10 min-w-[320px]">
                START SMALL
              </Button>

              <ul className="flex self-start flex-col gap-y-[15px] mt-14">
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Event tracking
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Sharing with friends
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Reoccurring events
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  2 uses of automatic schedule sync
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Limited event scheduling assistant
                </li>
              </ul>
            </div>
          </Card>
          <p className="heading1-bold self-center max-xl:hidden">OR</p>
          <Card className="bg-orange-gradient rounded-[20px] p-0.5 h-fit">
            <div className="bg-dark-400 rounded-[20px] py-12 px-[75px] h-fit flex-center flex-col ">
              <p className="button">ADVANCED</p>
              <h2 className="pt-3 text-[31px] leading-[120%]">$12 / month</h2>

              <Button className="bg-orange rounded-lg w-full mt-10 button-bold min-w-[320px]">
                INVEST NOW!
              </Button>

              <ul className="flex self-start flex-col gap-y-[15px] mt-14">
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Event tracking
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Sharing with friends
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Reoccurring events
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Reminders (e-mail and SMS)
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Unlimited uses of automatic schedule sync
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Smart event scheduling assistant
                </li>
                <li className="flex-start gap-x-4">
                  <img
                    src="/assets/membership/icons/orange-checkmark.svg"
                    width={20}
                    height={20}
                    alt="a checkmark icon"
                  />
                  Future mobile integration
                </li>
              </ul>
            </div>
          </Card>
        </div>
      </section>
    </div>
  );
};

export default Membership;
