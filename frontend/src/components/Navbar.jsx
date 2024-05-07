import { useAuth0 } from "@auth0/auth0-react";

const Navbar = () => {
  const { loginWithRedirect, user } = useAuth0();

  return (
    <nav className="flex-between z-10 w-full bg-black-400 lg:px-32 md:px-16 sm:px-12 pt-3 max-sm:hidden">
      <a href="/">
        <img
          src="/assets/general/icons/logo.svg"
          width={115}
          height={87}
          alt="logo"
        />
      </a>

      <section className="button w-fit flex-center gap-x-14">
        <a href="/membership">Membership</a>
        <a href="/Blog">Blog</a>
        {user ? (
          <a
            href="/dashboard"
            className="button-bold bg-orange  hover:bg-orange/80 rounded-lg px-5 py-2.5 line-clamp"
          >
            Dashboard
          </a>
        ) : (
          <button
            className="button-bold border border-orange rounded-lg px-5 py-2.5 line-clamp"
            onClick={() => loginWithRedirect()}
          >
            Sign Up
          </button>
        )}
      </section>
    </nav>
  );
};

export default Navbar;
