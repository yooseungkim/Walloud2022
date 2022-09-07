import React from "react";
import { useParams } from "react-router-dom";
import { users } from "./Var";

const Profile = () => {
  const { username } = useParams();
  console.log(username);
  const profile = users.filter((user) => user.name === username)[0];

  const displayEvents = () => {
    const events = profile.events;

    return (
      <div style={{ display: "flex" }}>
        {events.map((event, index) => (
          <h3 style={{ display: "block", margin: "0 auto" }} key={index}>
            {event}
          </h3>
        ))}
      </div>
    );
  };
  return (
    <div>
      <h1 id="headers">{profile.name}</h1>
      <h2 id="headers">Account : {profile.account}</h2>
      <h3
        id="headers"
        style={{ color: profile.type === "Depositors" ? "red" : "blue" }}
      >
        Spent: {profile.spent}₩
      </h3>
      <h4 id="headers">Participated Events: </h4>
      {displayEvents()}
    </div>
  );
};

export default Profile;
