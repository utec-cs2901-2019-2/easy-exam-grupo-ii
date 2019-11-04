//import { authHeader } from '../_helpers';

export const userService = {
    login
};



function login(username, password) {
    const requestOptions = {
        method: 'POST',
        mode: 'cors',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    };

    var state = {}


    return fetch('http://localhost:9898/api/v1/login', requestOptions)
        .then(res => res.json().then(data => {
                state = data
                console.log(state);
            })
        );
}