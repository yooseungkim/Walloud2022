import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import moment from "moment";

const EventDescription = () => {
  console.log("event description");
  const description = useLocation().state.event;
  console.log("evnet : ", description);
  const { user, travel, travelName } = useParams();
  return (
    <div>
      <Link to={`/${user}/${travel}/${travelName}`}>
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2 id="headers">{description.name}</h2>
      <div>
        <Link
          to={`/${user}/${travel}/${travelName}/profile/${description.payer}`}
        >
          <h3 className="link-text" id="headers">
            {description.payer}
          </h3>
        </Link>
        <h4 id="headers">{description.price}</h4>
        <h4 id="headers">{moment(description.date).utc().format('YYYY-MM-DD')}</h4>
      </div>
      <div style={{ display: "flex" }}>
        {/* {description.participants.map((participant, id) => (
          <Link
            to={`/${user}/${travel}/${travelName}/profile/${participant}`}
            style={{ margin: "auto" }}
          >
            <h3 className="link-text" key={id}>
              {" "}
              {participant}{" "}
            </h3>
          </Link>
        ))} */}
      </div>
    </div>
  );
};

export default EventDescription;
