import { useState } from "react";
import { MobileNavbar, Navbar, PageLoader } from "@/components";
import { useAuth0 } from "@auth0/auth0-react";
import {
  Card,
  CardHeader,
  CardTitle,
  CardContent,
  CardFooter,
} from "@/components/ui/card";

const Blog = () => {
  const { isLoading } = useAuth0();
  const [isExpanded, setIsExpanded] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const cardsPerPage = 8; // adjust this value to change the number of cards per page
  const totalCards = 23; // adjust this value to change the total number of cards

  if (isLoading) return <PageLoader />;

  const handleCardClick = (state) => {
    setIsExpanded(state);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const indexOfLastCard = currentPage * cardsPerPage;
  const indexOfFirstCard = indexOfLastCard - cardsPerPage;
  const currentCards = [...Array(totalCards)].slice(indexOfFirstCard, indexOfLastCard);

  return (
    <div className="min-h-screen overflow-x-hidden relative">
      <Navbar />
      <MobileNavbar />

      <img
        src="/assets/blog/images/background.svg"
        alt="background"
        onClick={() => handleCardClick(false)}
        className="w-full absolute"
      />
      <section className="w-full pb-20 pt-12">
        <article className="mx-6 flex max-md:flex-col gap-y-5 justify-between lg:px-32 md:px-16 sm:px-12">
          <h1 className="text-6xl font-bold">Blog</h1>
          <div className="text-lg lg:ml-12 text-right">
            <p className="text-left">Get more info about our mission.</p>
            <p className="max-md:text-left">Follow us weekly.</p>
          </div>
        </article>
      </section>

      <section className="relative">
        {isExpanded && (
          <div className="pt-6 absolute h-full w-full lg:px-32 md:px-12 sm:px-6 flex flex-col items-start rounded-t-[30px] bg-[#2D2D2D] animate-slideInFromBottom overflow-y-auto">
            <div className="flex flex-col lg:flex-row lg:items-start mb-8 mt-16 mx-20">
              <img
                src="/assets/blog/images/card_expanded.svg"
                alt="Blog"
                className="w-full rounded-lg shadow-lg mb-6 lg:mb-0 lg:mr-8"
              />
              <div className="text-gray-200">
                <h2 className="text-4xl font-bold mb-4">
                  This is a title of our blog post
                </h2>
                <p className="text-lg mb-4">
                  Short description about the post. About 2 sentences, maybe
                  even first 2 of the post.
                </p>
                <div className="flex items-center mb-4">
                  <span className="bg-orange text-white px-2 py-1 rounded mr-2">
                    #dev
                  </span>
                  <span className="bg-orange text-white px-2 py-1 rounded mr-2">
                    #syncmeet
                  </span>
                </div>
                <p className="text-sm text-gray-400">
                  29.06.2024. by{" "}
                  <span className="text-orange-400">Syncmeet team</span>
                </p>
              </div>
            </div>
            <div className="text-gray-200 mx-20">
              <p className="mt-8 mb-16 text-2xl">
                Lorem ipsum dolor sit amet consectetur. Ipsum nibh scelerisque
                odio consequat arcu egestas. Nullam posuere consequat facilisis
                dolor dignissim tortor velit eget. Enim faucibus porttitor lacus
                consectetur id dolor nunc. Rutrum luctus dolor ut volutpat
                habitasse. Phasellus nam in lorem id a nisl. Velit interdum
                ultricies in suspendisse amet egestas id. Non vel pellentesque
                sit quis.
              </p>
              <h3 className="text-3xl font-bold mb-8">Another chapter title</h3>
              <p className="mb-8 text-2xl">
                Et mi aliquet maecenas scelerisque eget aliquet maecenas non.
                Vitaemetus magnis gravida in amet auctor mattis. Nibh diam
                nulla cursus quis tempor consectetur nunc arcu. Non diam mus sed
                ornare. Sit quisque dolor pellentesque tincidunt netus risus.
                Sed molestie sit lacus sollicitudin volutpat eget faucibus massa
                varius.
              </p>
              <p className="mb-8 text-2xl pb-24">
                Tortor aliquam cursus ante sed justo duis. Vivamus interdum
                venenatis vitae odio id. Nam lacus massa accumsan nisi enim id
                pellentesque. Sit neque nunc egestas enim elementum egestas. Sit
                imperdiet nunc nisl ultricies ut rhoncus sagittis ut. Odio
                aliquam mauris commodo amet tellus. Imperdiet a viverra nec
                pellentesque sed. Sed enim cras est suscipit morbi egestas lacus
                eleifend urna. Viverra nibh.
              </p>
            </div>
          </div>
        )}

        <div className="justify-between w-full rounded-t-[30px] bg-[#2D2D2D] flex-between py-16 lg:px-32 md:px-12 sm:px-6 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-16">
          {currentCards.map((_, idx) => (
            <Card key={idx} onClick={() => handleCardClick(true)}>
              <CardContent className="bg-light-400 p-0 rounded-[10px] cursor-pointer">
                <CardHeader className="p-0 bg-light-400 rounded-[10px]">
                  <img
                    src="/assets/blog/images/card_image.svg"
                    alt="card image"
                    className="w-full h-[212px] object-cover rounded-t-[10px]"
                  />
                  <CardTitle className="px-2 py-3 text-3xl font-bold mb-2 text-black">
                    Title {idx + 1 + (currentPage - 1) * cardsPerPage}
                  </CardTitle>
                </CardHeader>

                <div className="px-2 paragraph text-white flex gap-x-2">
                  <span className="bg-orange px-2 py-1 rounded">#dev</span>
                  <span className="bg-orange px-2 py-1 rounded">#syncmeet</span>
                </div>

                <CardFooter className="px-2 pt-2 pb-2">
                  <p className="body text-gray-600 mb-2">29.06.2024</p>
                </CardFooter>
              </CardContent>
            </Card>
          ))}
        </div>

        <div className="flex justify-center mb-8">
          {[...Array(Math.ceil(totalCards / cardsPerPage))].map((_, idx) => (
            <button
              key={idx}
              className={`px-4 py-2 rounded-lg ${
                idx + 1 === currentPage? "bg-orange-400 text-white" : "bg-gray-200 text-gray-600"
              }`}
              onClick={() => handlePageChange(idx + 1)}
            >
              {idx + 1}
            </button>
          ))}
        </div>
      </section>
    </div>
  );
};

export default Blog;