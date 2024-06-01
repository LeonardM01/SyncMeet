import {useState} from 'react';
import {Navbar} from "/src/components";
import {Card, CardHeader, CardTitle, CardContent, CardFooter} from '@/components/ui/card';

const Blog = () => {
    const [isExpanded, setIsExpanded] = useState(false);

    const handleClick = () => {
        if (isExpanded) {
            setIsExpanded(false);
        } else {
            setIsExpanded(true);
        }
    };

    return (
        <div>
            <Navbar/>

            <div className={`w-full bg-[url('/assets/blog/images/blog_background_circles.svg')] bg-cover bg-center bg-no-repeat h-[240px] 
                            ${isExpanded ? 'hidden' : ''}`}
            >
                <div className="mx-6 flex justify-between px-32 mt-10">
                    <h1 className="text-6xl font-bold mt-20">Blog</h1>
                    <div className="heading4 ml-12 text-right mt-20">
                        <p>Get more info about our mission.</p>
                        <p>Follow us weekly.</p>
                    </div>
                </div>
            </div>

            <div
                className={`w-full bg-[url('/assets/blog/images/blog_background_small.svg')] bg-cover bg-center bg-no-repeat rounded-t-3xl
                            ${isExpanded ? 'slide-in-from-bottom' : 'slide-out-to-bottom'}`}
            >
                {isExpanded ? (
                    <div
                        className="mx-6 pt-6 px-32 flex flex-col items-start bg-[url('/assets/blog/images/blog_background_big.svg')]"
                        onClick={handleClick}
                    >
                        <div className="flex flex-row items-start mb-8 mt-16">
                            <img src="/assets/blog/images/blog_card_big.svg"
                                 alt="big blog image"
                                 className="w-[556px] h-[212px] rounded-lg mb-6 mr-20"/>
                            <div>
                                <h2 className="heading2 font-bold mb-4">This is a title of our blog post</h2>
                                <p className="text-light-400 heading4 mb-4 w-[402px]">
                                    Short description about the post. About 2 sentences, maybe even first 2 of the post.
                                </p>
                                <div className="flex items-center mb-4 mt-9">
                                    <span className="bg-orange text-white button rounded px-2 py-1 mr-2">#dev</span>
                                    <span className="bg-orange text-white button rounded px-2 py-1 mr-2">#syncmeet</span>
                                </div>
                                <p className="paragraph mt-9"><span className="text-white">29.06.2024. by</span> <span
                                    className="text-orange">Syncmeet team</span></p>
                            </div>
                        </div>
                        <div className="text-white">
                            <p className="heading3 mt-8 mb-16">
                                Lorem ipsum dolor sit amet consectetur.
                                Ipsum nibh scelerisque odio consequat arcu egestas.
                                Nullam posuere consequat facilisis dolor dignissim tortor velit eget.
                                Enim faucibus porttitor lacus consectetur id dolor nunc. Rutrum luctus dolor ut volutpat habitasse.
                                Phasellus nam in lorem id a nisl. Velit interdum ultricies in suspendisse amet egestas id.
                                Non vel pellentesque sit quis.
                            </p>
                            <h3 className="heading2-bold mb-8">Another chapter title</h3>
                            <p className="heading3 mb-8">
                                Et mi aliquet maecenas scelerisque eget aliquet maecenas non.
                                Vitae metus magnis gravida in amet auctor mattis. Nibh diam nulla cursus quis tempor consectetur nunc arcu.
                                Non diam mus sed ornare. Sit quisque dolor pellentesque tincidunt netus risus.
                                Sed molestie sit lacus sollicitudin volutpat eget faucibus massa varius.
                            </p>
                            <p className="heading3 mb-8 pb-24">
                                Tortor aliquam cursus ante sed justo duis.
                                Vivamus interdum venenatis vitae odio id.
                                Nam lacus massa accumsan nisi enim id pellentesque.
                                Sit neque nunc egestas enim elementum egestas.
                                Sit imperdiet nunc nisl ultricies ut rhoncus sagittis ut.
                                Odio aliquam mauris commodo amet tellus. Imperdiet a viverra nec pellentesque sed.
                                Sed enim cras est suscipit morbi egestas lacus eleifend urna. Viverra nibh.
                            </p>
                        </div>
                    </div>
                ) : (
                    <div className="justify-between flex-between mx-6 py-16 px-32 grid grid-cols-4 gap-16">
                        {[...Array(16)].map((_, id) => (
                            <Card
                                key={id}
                                onClick={handleClick}
                            >
                                <CardHeader className="p-0 bg-white rounded-t-lg ">
                                    <img
                                        src="/assets/blog/images/blog_card_small.svg"
                                        alt="small blog image"
                                        className="w-full h-[212px] object-cover rounded-t-lg"
                                    />
                                    <CardTitle className="px-6 py-3 heading4 font-bold mb-2 text-black">This is title</CardTitle>
                                </CardHeader>
                                <CardContent className={"bg-white"}>
                                    <span className="bg-orange body text-white rounded px-2 py-1 mr-2">#dev.</span>
                                    <span className="bg-orange body text-white rounded px-2 py-1 mr-2">#syncmeet</span>
                                </CardContent>
                                <CardFooter className={"bg-white rounded-b-lg"}>
                                    <p className="small text-black mb-2">29.06.2024</p>
                                </CardFooter>
                            </Card>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default Blog;