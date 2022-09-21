import React from "react";
import { Link } from "react-router-dom";
import moment from "moment/moment";

function Events({ event }) {
  

  return (
    <div style={{ display: "flex" }}>
      <Link
        className="event"
        to={`event/${event.place}`}
        state={{ event: event }}
      >
        <span>{event.name}</span>
      </Link>
      <span className="event">{event.payer}</span>
      <span className="event">{event.price}</span>
      <span className="event">{moment(event.date).utc().format('YYYY-MM-DD')}</span>
    </div>
  );
}

export default Events;
