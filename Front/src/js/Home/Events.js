import React from "react";
import { Link } from "react-router-dom";

function Events({ event }) {
  return (
    <div style={{ display: "flex" }}>
      <Link
        className="event"
        to={`event/${event.place}`}
        state={{ event: event }}
      >
        <span>{event.place}</span>
      </Link>
      <span className="event">{event.name}</span>
      <span className="event">{event.price}</span>
      <span className="event">{event.date}</span>
    </div>
  );
}

export default Events;
