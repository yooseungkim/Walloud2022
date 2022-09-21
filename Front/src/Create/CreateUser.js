import React from "react";
import { Link, useLocation, useParams } from "react-router-dom";

const CreateUser = () => {
  const { travel, travelName, user } = useParams();
  // const user = useLocation().state.user;
  // const travel = useLocation().state.travel;
  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
        <h1 className="home">Divide by N</h1>
      </Link>
      <h2>Add User</h2>
      <label htmlFor="email">Email</label>
      <input id="email" type="email" />
<<<<<<< HEAD
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
=======
      <Link to={`/${user}/${travel}`} state={{ user: user, travel: travel }}>
>>>>>>> c14cd8ed2c77ddbdc59fe27c74ec1096b69b3258
        <button>Submit</button>
      </Link>
    </div>
  );
};

export default CreateUser;
