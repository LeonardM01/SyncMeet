import { PageLoader } from "@/components";
import { useAuth0 } from "@auth0/auth0-react";

const Auth = ({ children }) => {
  const { isAuthenticated, isLoading, loginWithRedirect } = useAuth0();

  if (isLoading) return <PageLoader />;

  if (!isAuthenticated) {
    return loginWithRedirect();
  }

  return <>{children}</>;
};

export default Auth;
