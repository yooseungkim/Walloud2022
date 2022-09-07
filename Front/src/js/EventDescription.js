import React from "react";
import { useLocation, useParams } from "react-router-dom";
import { eventList } from "./Var";

const EventDescription = ({}) => {
  const description = useLocation().state.event;
  console.log(description);
  return (
    <div>
      <h2 id="headers">place: {description.place}</h2>
      <h2 id="headers">name: {description.name}</h2>
      <h2 id="headers">price: {description.price}</h2>
      <h2 id="headers">date: {description.date}</h2>
      <div style={{ display: "flex" }}>
        {description.participants.map((participant, id) => (
          <h3 style={{ margin: "0 auto" }} key={id}>
            {" "}
            {participant}{" "}
          </h3>
        ))}
      </div>
    </div>
  );
};

export default EventDescription;
