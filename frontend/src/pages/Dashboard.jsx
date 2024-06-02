import { useState, useEffect, useRef  } from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import "../styles/theme.css";
import {
  Command,
  CommandSeparator,
  CommandInput,
  CommandList,
  CommandEmpty,
  CommandGroup,
  CommandItem,
} from "@/components/ui/command";

const localizer = momentLocalizer(moment);

const Dashboard = () => {
  const events = [];

  const [searchTerm, setSearchTerm] = useState("");
  const [showCommandDialog, setShowCommandDialog] = useState(false);

  const commandDialogRef = useRef(null); // Dodajmo ref za CommandDialog

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.ctrlKey && event.key === "k") {
        event.preventDefault(); 
        setShowCommandDialog(true);
      }
    };

    const handleClickOutside = (event) => {
      if (commandDialogRef.current && !commandDialogRef.current.contains(event.target)) {
        setShowCommandDialog(false);
      }
    };
  
    document.addEventListener("keydown", handleKeyDown);
    document.addEventListener("click", handleClickOutside); 
  
    return () => {
      document.removeEventListener("keydown", handleKeyDown);
      document.removeEventListener("click", handleClickOutside);
    };
  }, [commandDialogRef]);
  

  return (
    <div className="flex h-screen">
      <div className="flex flex-col flex-1">

        {/* Search Bar */}
        <div className="p-4 mb-2 flex justify-end">
          <input
            type="text"
            value={searchTerm}
            onChange={handleSearchChange}
            placeholder="Search"
            className="w-150 px-4 py-2 rounded border-gray-300 focus:outline-none focus:border-indigo-500 text-black"
          />
        </div>

        <div className="mx-4 mb-4 relative">
          {/* Calendar*/}
          <Calendar
            localizer={localizer}
            events={events}
            startAccessor="start"
            endAccessor="end"
            step={60}
            timeslots={1}
            style={{ height: "90vh" }}
            min={new Date(1970, 1, 1, 7, 0, 0)}
            max={new Date(1970, 1, 1, 23, 0, 0)}
          /> 

          {showCommandDialog && (
          <Command 
            ref={commandDialogRef} // Dodajmo ref na CommandDialog
            className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-3/4 " 
            style={{ width: "511px", height: "348px" }}
          >
            <CommandInput placeholder="Type a command or search..." />
            <CommandList>
              <CommandEmpty>No results found.</CommandEmpty>
              <CommandGroup heading="Suggestions">
                <CommandItem>Calendar</CommandItem>
                <CommandItem>Search Emoji</CommandItem>
                <CommandItem>Calculator</CommandItem>
                <CommandItem>
                  Add friend&nbsp;
                  <span className="text-gray-400">@username</span>
                </CommandItem>
              </CommandGroup>
              <CommandSeparator />
              <CommandGroup heading="Settings">
                <CommandItem>Profile</CommandItem>
                <CommandItem>Billing</CommandItem>
                <CommandItem>Settings</CommandItem>
              </CommandGroup>
            </CommandList>
          </Command>
          )}
        </div>
      </div>

      {/* Sidebar */}
      <div className="w-64 bg-light-400 p-4 text-black">
        <div className="flex flex-col space-y-4 mt-10">
          <div className="flex justify-center space-x-2">
            <img src="/assets/dashboard/vector.svg" alt="Leonard Martinis" />
            <h2 className="text-xl font-semibold">Leonard Martinis</h2>
          </div>
          <div className="border-b border-gray-300 w-full my-2"></div>
          <p className="pb-6">No upcoming meetings</p>
          <button className="bg-orange text-white py-2 px-4 rounded">Create shared event</button>
          <div className="mt-8">
            <h3 className="text-lg font-semibold">Friends</h3>
            <div className="flex flex-col space-y-2 mt-2">
              <div className="flex items-center space-x-2">
                <img src="/assets/dashboard/profile_icon.svg" alt="Tin UgIesic" className="w-8 h-8 rounded-full" />
                <span>Tin Uglesic</span>
              </div>
            </div>
          </div>
          <div className="mt-8">
            <h3 className="text-lg font-semibold">Tips</h3>
            <div className="flex flex-col space-y-2 mt-2">
              <p>Open command bar: <span className="font-mono p-1 bg-gray-300 rounded">CTRL+K</span></p>
              <p>Create new event: <span className="font-mono p-1 bg-gray-300 rounded">CTRL+N</span></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
