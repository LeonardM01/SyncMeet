import { useState } from 'react';
import { MobileNavbar, Navbar, PageLoader } from "@/components";
import { useAuth0 } from "@auth0/auth0-react";
import { Card, CardHeader, CardTitle, CardContent, CardFooter } from '@/components/ui/card';

const Blog = () => {
    const { isLoading } = useAuth0();
    const [isExpanded, setIsExpanded] = useState(false);

    if (isLoading) return <PageLoader />;

    const handleCardClick = () => {
        setIsExpanded(true);
        window.scrollTo({ top: 0, behavior: 'smooth' }); 
    };

    return (
        <div className="min-h-screen overflow-x-hidden relative">
            <Navbar />
            <MobileNavbar />
            <section 
                className={`relative w-full bg-[url('/assets/blog/images/background.svg')] bg-cover bg-center bg-no-repeat h-[240px] ${isExpanded ? 'hidden' : ''}`} 
            >
                <div className="mx-6 flex justify-between lg:px-32 md:px-16 sm:px-12 mt-12">
                    <h1 className="text-6xl font-bold">Blog</h1>
                    <div className="text-lg lg:ml-12 text-right">
                        <p className="text-left">Get more info about our mission.</p>
                        <p>Follow us weekly.</p>
                    </div>
                </div>
            </section>
            <section 
                className={`relative w-full bg-[url('/assets/blog/images/background_cards.svg')] bg-cover bg-center bg-no-repeat rounded-t-[30px] ${isExpanded ? 'section-2-expanded h-[100vh]' : 'section-2'}`}
                style={{ top: isExpanded ? '-240px' : '0' }}
            >
                {isExpanded && (
                    <div className="mx-6 pt-6 lg:px-32 md:px-12 sm:px-6 flex flex-col items-start">
                        <div className="flex flex-col lg:flex-row lg:items-start mb-8 mt-16 mx-20">
                            <img src="/assets/blog/images/card_expanded.svg" alt="Blog" className="w-[556px] h-[212px] lg:w-1/3 rounded-lg shadow-lg mb-6 lg:mb-0 lg:mr-8" />
                            <div className="text-gray-200">
                                <h2 className="text-4xl font-bold mb-4">This is a title of our blog post</h2>
                                <p className="text-lg mb-4">Short description about the post. About 2 sentences, maybe even first 2 of the post.</p>
                                <div className="flex items-center mb-4">
                                    <span className="bg-orange text-white px-2 py-1 rounded mr-2">#dev</span>
                                    <span className="bg-orange text-white px-2 py-1 rounded mr-2">#syncmeet</span>
                                </div>
                                <p className="text-sm text-gray-400">29.06.2024. by <span className="text-orange-400">Syncmeet team</span></p>
                            </div>
                        </div>
                        <div className="text-gray-200 mx-20">
                            <p className="mt-8 mb-16 text-2xl">
                                Lorem ipsum dolor sit amet consectetur. Ipsum nibh scelerisque odio consequat arcu egestas. Nullam posuere consequat facilisis dolor dignissim tortor velit eget. Enim faucibus porttitor lacus consectetur id dolor nunc. Rutrum luctus dolor ut volutpat habitasse. Phasellus nam in lorem id a nisl. Velit interdum ultricies in suspendisse amet egestas id. Non vel pellentesque sit quis.
                            </p>
                            <h3 className="text-3xl font-bold mb-8">Another chapter title</h3>
                            <p className="mb-8 text-2xl">
                                Et mi aliquet maecenas scelerisque eget aliquet maecenas non. Vitae metus magnis gravida in amet auctor mattis. Nibh diam nulla cursus quis tempor consectetur nunc arcu. Non diam mus sed ornare. Sit quisque dolor pellentesque tincidunt netus risus. Sed molestie sit lacus sollicitudin volutpat eget faucibus massa varius.
                            </p>
                            <p className="mb-8 text-2xl pb-24">
                                Tortor aliquam cursus ante sed justo duis. Vivamus interdum venenatis vitae odio id. Nam lacus massa accumsan nisi enim id pellentesque. Sit neque nunc egestas enim elementum egestas. Sit imperdiet nunc nisl ultricies ut rhoncus sagittis ut. Odio aliquam mauris commodo amet tellus. Imperdiet a viverra nec pellentesque sed. Sed enim cras est suscipit morbi egestas lacus eleifend urna. Viverra nibh.
                            </p>
                        </div>
                    </div>
                )}
                {!isExpanded && (
                    <div className="justify-between flex-between mx-6 py-16 lg:px-32 md:px-12 sm:px-6 grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-16">
                        {[...Array(10)].map((_, idx) => (
                            <Card 
                                key={idx}
                                onClick={handleCardClick}
                            >
                                <CardHeader className="p-0">
                                <img 
                                    src="/assets/blog/images/card_image.svg" 
                                    alt="card image" 
                                    className="w-full h-[212px] object-cover rounded-t-lg" 
                                />
                                    <CardTitle className="px-6 py-3 text-3xl font-bold mb-2 text-black">Title {idx + 1}</CardTitle>
                                </CardHeader>
                                <CardContent>
                                    <span className="bg-orange text-white px-2 py-1 rounded mr-2">Content for card {idx + 1}.</span>
                                    <span className="bg-orange text-white px-2 py-1 rounded mr-2">Content for card {idx + 1}.</span>
                                </CardContent>
                                <CardFooter>
                                    <p className="text-sm text-gray-600 mb-2">29.06.2024</p>
                                </CardFooter>
                            </Card>
                        ))}
                    </div>
                )}
            </section>
        </div>
    );
};

export default Blog;