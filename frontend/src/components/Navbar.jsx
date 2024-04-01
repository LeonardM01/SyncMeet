import { useAuth0 } from "@auth0/auth0-react";

const Navbar = () => {
  const { loginWithRedirect } = useAuth0();

  return (
    <nav className="flex-between w-full bg-black-400 px-32 pt-3">
      <img
        src="/assets/general/icons/logo.svg"
        width={115}
        height={87}
        alt="logo"
      />

      <section className="button w-fit flex-center gap-x-14">
        <a href="/docs">Docs</a>
        <a href="/membership">Membership</a>
        <a href="/Blog">Blog</a>
        <button
          className="button-bold border border-orange rounded-lg px-7 py-2.5"
          onClick={() => loginWithRedirect()}
        >
          Sign Up
        </button>
      </section>
    </nav>
  );
};

export default Navbar;
