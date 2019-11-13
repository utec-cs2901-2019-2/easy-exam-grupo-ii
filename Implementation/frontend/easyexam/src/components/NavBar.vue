<template>
    <div>
        <b-navbar toggleable="lg" v-if="getIsLogged" type="dark" variant="dark">
            <b-navbar-brand href="/">EasyExam</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>

                    <b-nav-item><router-link to="/dashboard"> Home </router-link></b-nav-item>
                    <b-nav-item><router-link to = "/submit">Submit</router-link></b-nav-item>
                    <b-nav-item><router-link to = "/generate">Generate</router-link></b-nav-item>
                    <b-nav-item><router-link to = "/feedback">Feedback</router-link></b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>User</em>
                        </template>
                        <b-dropdown-item> <router-link to="/profile">Profile</router-link></b-dropdown-item>
                        <b-dropdown-item href="/">Sign Out</b-dropdown-item>
                       <!-- <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>-->
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <b-navbar toggleable="lg" v-if="!getIsLogged" type="dark" variant="dark">
            <b-navbar-brand href="/">EasyExam</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>Sign Up</em>
                        </template>
                        <b-dropdown-item><router-link to ="/login">Sign In</router-link></b-dropdown-item>
                        <b-dropdown-item><router-link to ="/register">Sign Up</router-link></b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
import {mapMutations} from 'vuex'
import {mapGetters} from 'vuex'
export default {
    name: "NavBar",
    computed:{
    ...mapMutations(['logout']),
        ...mapGetters(['getIsLogged'])
    },
    methods: {
        logout() {

        }
    }
}
</script>

<style scoped>

</style>