import { app, BrowserWindow } from "electron";

function createWindow() {
  // Create the browser window.
  const mainWindow = new BrowserWindow({
    width: 1280,
    height: 720,
    icon: "./public/favicon.ico",
    title: "Syncmeet",
    autoHideMenuBar: true,
    webPreferences: {
      nodeIntegration: false,
      contextIsolation: true, // Be cautious with security implications
    },
  });

  // and load the index.html of the app.
  mainWindow.loadURL("https://syncmeet.space");
}

app.whenReady().then(createWindow);

app.on("window-all-closed", () => {
  // eslint-disable-next-line no-undef
  if (process.platform !== "darwin") {
    app.quit();
  }
});

app.on("activate", () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    createWindow();
  }
});
