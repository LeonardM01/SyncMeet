/* eslint-disable no-undef */
/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,js,jsx}"],
  theme: {
    extend: {
      backgroundImage: {
        background: "url('/src/backgrounds/background.svg')",
        background2: "url('/src/backgrounds/background2.svg')",
      },
      fontFamily: {
        nunito: ["Nunito", "sans-serif"],
      },
      colors: {
        orange: "#e28413",
        light: "#fbf5f3",
        "dark-100": "#010112",
        "dark-300": "#000022",
        "light-400": "#fbf3f1",
        "dark-400": "#1c1c21",
      },
    },
  },
  plugins: [require("tailwindcss-animate")],
};
