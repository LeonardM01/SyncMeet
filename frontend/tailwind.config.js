/* eslint-disable no-undef */
/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,jsx}"],
  theme: {
    extend: {
      fontFamily: {
        nunito: ["Nunito", "sans-serif"],
      },
      colors: {
        orange: "#e28413",
        light: "#fbf5f3",
        "dark-100": "#010112",
        "dark-300": "#000022",
        "light-400": "#FBF3F1",
        "dark-400": "#1c1c21",
      },
    },
    keyframes: {
      slideInFromBottom: {
        "0%": { transform: "translateY(100%)", opacity: "0" },
        "100%": { transform: "translateY(0)", opacity: "1" },
      },
      slideOutFromBottom: {
        "0%": { transform: "translateY(0)", opacity: "1" },
        "100%": { transform: "translateY(100%)", opacity: "0" },
      },
    },
    animation: {
      slideInFromBottom: "slideInFromBottom 0.5s ease-in forwards",
      slideOutFromBottom: "slideOutFromBottom 0.5s ease-out",
    },
  },
  plugins: [require("tailwindcss-animate")],
};
