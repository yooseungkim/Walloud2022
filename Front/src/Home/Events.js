import React from "react";
import { Link, useLocation } from "react-router-dom";

function Events({ event }) {
  const user = useLocation().state.user_id;
  const travel = useLocation().state.travel_id;
  const travelName = useLocation().state.travelName;
  console.log(useLocation().state);
  return (
    <div style={{ display: "flex" }}>
      <Link
        className="event"
        to={`${event.place}`}
        state={{
          event: event,
          user: user,
          travel: travel,
          travelName: travelName,
        }}
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
