import { userService } from '../services';
//import { router } from '../router';
/*
const user = JSON.parse(localStorage.getItem('user'));
const state = {
    user :null
}
*/
const actions = {
    login( {username, password}) {
        userService.login(username, password);

    }
};


export const account = {
    namespaced: true,
    actions,
};