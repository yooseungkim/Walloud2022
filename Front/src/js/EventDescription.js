import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";

const EventDescription = () => {
  console.log("event description");
  const description = useLocation().state.event;
  const { user, travel, travelName } = useParams();
  console.log(useParams());
  console.log(description);
  return (
    <div>
      <Link to={`/${user}/${travel}/${travelName}`}>
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2 id="headers">{description.place}</h2>
      <div>
        <Link
          to={`/${user}/${travel}/${travelName}/profile/${description.name}`}
        >
          <h3 className="link-text" id="headers">
            {description.name}
          </h3>
        </Link>
        <h4 id="headers">₩{description.price}</h4>
        <h4 id="headers">{description.date}</h4>
      </div>
      <div style={{ display: "flex" }}>
        {description.participants.map((participant, id) => (
          <Link
            to={`/${user}/${travel}/${travelName}/profile/${participant}`}
            style={{ margin: "auto" }}
          >
            <h3 className="link-text" key={id}>
              {" "}
              {participant}{" "}
            </h3>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default EventDescription;
