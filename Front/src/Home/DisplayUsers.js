import React from "react";
import personSrc from "../img/person.png";
import { Link, useParams } from "react-router-dom";

function DisplayUsers({ users, preferences }) {
  const currentLoggedIn = JSON.parse(localStorage.getItem("id"));
  const { user, travel, travelName } = useParams();
  function CreateUser({ username, personid, spent }) {
    console.log(username);

    return (
      <Link
        to={`/${user}/${travel}/${travelName}/profile/${username}`}
        state={{ personid: personid }}
      >
        <div className="user">
          {preferences.displayIcon ? (
            <img className="user-icon" src={personSrc} alt="profile" />
          ) : null}
          <br />
          <h4
            className="caption"
            style={{ color: currentLoggedIn === user.name ? "blue" : "black" }}
          >
            {username}
          </h4>
          <br />
          {preferences.displayMoney ? (
            <h4 className="caption">
              {spent >= 0 ? `₩${Math.round(spent)}` : `-₩${-Math.round(spent)}`}
            </h4>
          ) : null}
        </div>
      </Link>
    );
  }

  function CreateType({ type }) {
    return (
      <div className="home-type">
        <h4 className="type">{type}</h4>
        <div style={{ verticalAlign: "center", alignItems: "center" }}>
          {users
            .filter(
              (user) =>
                (user.role === true && type === "Manager") ||
                (user.difference > 0 && type === "Depositors") ||
                (user.difference < 0 && type === "Recipients")
            )
            .map((user) => (
              <CreateUser
                username={user.name}
                spent={user.difference}
                personid={user.id}
                key={user.id}
              />
            ))}
        </div>
      </div>
    );
  }

  return (
    <div className="users">
      <CreateType users={users} type="Manager" />
      <CreateType users={users} type="Depositors" />
      <CreateType users={users} type="Recipients" />
    </div>
  );
}

export default DisplayUsers;
