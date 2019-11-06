import { userService } from '../services';
import { router } from '../router';

const user = JSON.parse(localStorage.getItem('user'));
const state = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };


const actions = {
    login( {username, password}) {
        userService.login(username, password).then(
            user => {
                router.push('/');
                state.status = { loggedIn: true };
                state.user = user;
            }
        );

    }
};


export const account = {
    namespaced: true,
    actions,
};