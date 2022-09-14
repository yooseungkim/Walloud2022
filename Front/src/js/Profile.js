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
      <h2>{profile.name}</h2>
      <h3>Account : {profile.account}</h3>
      <h3 style={{ color: profile.type === "Depositors" ? "red" : "blue" }}>
        Spent: {profile.spent}₩
      </h3>
      <h3 id="headers">Participated Events: </h3>
      {displayEvents()}
      <br />
    </div>
  );
};

export default Profile;
