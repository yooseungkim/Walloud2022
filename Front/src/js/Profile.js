import React from "react";
import { Link, useParams } from "react-router-dom";
import { users } from "./Var";

const Profile = () => {
  const { user, travel, travelName, username } = useParams();
  console.log(useParams());
  const profile = users.filter((user) => user.name === username)[0];
  const displayEvents = () => {
    const events = profile.events;

    return (
      <div style={{ display: "flex" }}>
        {events.map((event, index) => (
          <div>
            <Link
              to={`/${user}/${travel}/${travelName}/${event}`}
              state={{ event: event }}
            >
              <h3 className="link-text" key={index}>
                {event}
              </h3>
            </Link>
          </div>
        ))}
      </div>
    );
  };
  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
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
