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
  },
  plugins: [require("tailwindcss-animate")],
};
