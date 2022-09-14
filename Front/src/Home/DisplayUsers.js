import React from "react";
import personSrc from "../img/person.png";
import { Link } from "react-router-dom";

function DisplayUsers({ users, preferences, travel }) {
  const currentLoggedIn = JSON.parse(localStorage.getItem("id"));
  function CreateUser({ user }) {
    return (
      <div className="user">
        <Link to={`profile/${user.name}`}>
          {preferences.displayIcon ? (
            <img className="user-icon" src={personSrc} alt="profile" />
          ) : null}
        </Link>
        <br />
        <span
          style={{ color: currentLoggedIn === user.name ? "blue" : "black" }}
        >
          {user.name}
        </span>
        <br />
        {preferences.displayMoney ? <span>{user.spent}</span> : null}
      </div>
    );
  }

  function CreateType({ type }) {
    const onClickDescription = (event) => {
      if (type === "Depositors") {
        console.log("Depositors have to send money");
      } else if (type === "Manager") {
        console.log("Manager is the one who spent the most money");
      } else if (type === "Recipients") {
        console.log(
          "Recipients are the ones who spent more money; in some cases, there can be no Recipients"
        );
      }
    };
    return (
      <div>
        <h4 className="type" onClick={() => onClickDescription({ type })}>
          {type}
        </h4>
        <div>
          {users
            .filter((user) => user.type === type)
            .map((user) => (
              <CreateUser user={user} key={user.index} />
            ))}
        </div>
      </div>
    );
  }

  return (
    <div className="users">
      <CreateType users={users} type="Depositors" />
      <hr />
      <CreateType users={users} type="Manager" />
      <hr />
      <CreateType users={users} type="Recipients" />
    </div>
  );
}

export default DisplayUsers;
