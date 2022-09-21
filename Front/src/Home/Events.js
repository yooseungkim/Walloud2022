import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";

function Events({ event }) {
  const { user, travel, travelName } = useParams();
  console.log(useParams());
  return (
    <div style={{ display: "flex" }}>
      <Link
        className="event"
        to={`/${user}/${travel}/${travelName}/${event.place}`}
        state={{ event: event }}
      >
        <span className="link-text">{event.place}</span>
      </Link>
      <span className="event">{event.name}</span>
      <span className="event">{event.price}</span>
      <span className="event">{event.date}</span>
    </div>
  );
}

export default Events;
