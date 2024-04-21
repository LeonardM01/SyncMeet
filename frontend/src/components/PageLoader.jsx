const PageLoader = () => {
  return (
    <div className="h-screen flex-center w-screen">
      <div className="loader bg-white p-3 rounded-full flex justify-center items-center">
        <div className="spin-circle border-4 border-orange border-t-transparent w-8 h-8 rounded-full animate-spin"></div>
      </div>
    </div>
  );
};

export default PageLoader;
