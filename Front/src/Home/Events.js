import React from "react";
import { Link, useParams } from "react-router-dom";
import moment from "moment/moment";

function Events({ event }) {
 const {user, travel, travelName} = useParams();
  return (
    <div style={{ display: "flex" }}>
      <Link
        className="event"
        to={`/${user}/${travel}/${travelName}/${event.name}`}
        state={{ event: event }}
      >
        <span className="link-text">{event.name}</span>
      </Link>
      <span className="event">{event.payer}</span>
      <span className="event">{event.price}</span>
      <span className="event">{moment(event.date).utc().format('YYYY-MM-DD')}</span>
    </div>
  );
}

export default Events;
