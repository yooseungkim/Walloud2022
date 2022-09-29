import axios from "axios";
import React, { useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

const UserInvite = ({friend, onDel}) => {

  return (
    <li>
      <div>
        <span>
          <strong>이메일 : {friend.email}</strong>
          <button onClick = {() => onDel(friend.id)}>삭제</button>
        </span>
      </div>
    </li>
  )
}

const CreateUser = () => {

  // multi input 만들기
  const { travel, travelName, user } = useParams();
  const navigate = useNavigate();
  const no = useRef(2);
  const inviteRef = useRef();
  const [inviteList,setInvite] = useState([]);
  const [form, setFrom] = useState({
    email : ""
  });
  
  const changeInput = (e) => {
    const {value, name} = e.target
    setFrom({
      ...form,
      [name] : value
    })
  }

  const onSubmit = (e) => {
    e.preventDefault();
    if(!email) {
      alert("Write any email!");
      return;
    }
    onAdd(form)

    setFrom({
      email : ''
    })
    inviteRef.current.focus()
  }
  
  const {email} = form;

  const onAdd = (form) => {
    form.id = no.current++;
    setInvite(inviteList.concat(form));
  }
  
  const onDel = (id) => {
    setInvite(inviteList.filter(e => e.id !== id))
  }

  const sendInvite = async() => {
    let sended = [];
    await inviteList.map((invite_elem) => {
      console.log(invite_elem.email);
      axios.post(`/api/${user}/${travel}/createUser`,{
        user_email : invite_elem.email
      }).then((res) => {
        switch(res.data) {
          case 200:
            sended.push(invite_elem.id);
            console.log(sended);
            break;
          case -1:
            alert("check the email (" + invite_elem.email + ") is correct");
            break;
          case -2:
            alert("Network Error");
            break;
          case -3:
            alert("User is already existed.");
            break;
          default:
            afterError(sended);
            break;
        }
      }).catch((error) => {
        console.log(error);
        afterError(sended);
      })
    })
  }

  const afterError = (sended) => {
    sended.map((sended_elem) => {
      onDel(sended_elem);
    })
  }

  return (
    <div>
      <Link
        to={`/${user}/${travel}/${travelName}`}
        state={{ user: user, travel: travel, travelName: travelName }}
      >
      
       <h1 className="home">{travelName}</h1>
     </Link>
     <h2>Add User</h2>
      <h3>총인원 : {inviteList.length}</h3>
      <form className="formadd" onSubmit={onSubmit}>
        <span><label htmlFor="email">email</label> : <input type="email" id="email" onChange={changeInput} value={email} name="email" ref={inviteRef}/> <button type="submit">추가</button></span>
      </form>
      <ul>
        {inviteList && inviteList.map(friend => <UserInvite key={friend.id} friend={friend} onDel = {onDel}/>)}
      </ul>
      <button onClick = {sendInvite}>Submit</button>

    </div>
  )

}

// const CreateUser = () => {
//   const { travel, travelName, user } = useParams();
//   const [email, setEmail] = useState("");
//   const navigate = useNavigate();
  
//   const onChange = (event) => {
//     setEmail(event.currentTarget.value);
//   }

//   const onClick = async() => {
//     await axios.post(`/api/${user}/${travel}/createUser`,{
//       user_email : email
//     }).then((res) => {
//       switch(res.data) {
//         case -1:
//           alert("check the email is correct");
//           break;
//         case -2:
//           alert("Network Error");
//           break;
//         case 200:
//           alert("Successfully Created");
//           navigate(`/${user}/${travel}/${travelName}`);
//           break;
//         default:
//           throw res;
//       }
//     }).catch((error) => {
//       console.log(error);
//     })
//   }

//   return (
//     <div>
//       <Link
//         to={`/${user}/${travel}/${travelName}`}
//         state={{ user: user, travel: travel, travelName: travelName }}
//       >
//         <h1 className="home">{travelName}</h1>
//       </Link>
//       <h2>Add User</h2>

//       <label htmlFor="email">Email</label>
//       <input id="email" type="email" onChange={onChange} />
//       <button onClick={onClick}>Submit</button>
//     </div>
//   );
// };

export default CreateUser;
