import React from "react";
import "./App.css";
import Home from "./Home/Home";
import Profile from "./js/Profile";
import LogIn from "./Authentication/LogIn";
import Register from "./Authentication/Register";
import Calendar from "./Home/Calendar";
import { CreateEvent } from "./Create/CreateEvent";
import CreateUser from "./Create/CreateUser";
import EventDescription from "./js/EventDescription";
import { Routes, Route } from "react-router-dom";
import SelectTravel from "./Travel/SelectTravel";

const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<LogIn />} />
        <Route
          path="/:username/:travel/profile/:username"
          element={<Profile />}
        />
        <Route path="/login" element={<LogIn />} />
        <Route path="/register" element={<Register />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route
          path="/:username/:userid/:travelid/createEvent"
          element={<CreateEvent />}
        />
        <Route
          path="/:username/:userid/:travelid/createUser"
          element={<CreateUser />}
        />
        <Route
          path="/:userid/:travelid/:travelname/event/:event"
          element={<EventDescription />}
        />
        <Route path="/selectTravel" element={<SelectTravel />} />
        <Route path="/:userid/:travelid/:travelname" element={<Home />} />
      </Routes>
    </div>
  );
};

export default App;
