<template>
    <div>
        <b-navbar toggleable="lg" v-if="getIsLogged" type="dark" variant="dark">
            <b-navbar-brand ><router-link to="/" style="font-family: italic; font-size: x-large; margin-left: 7%; margin-right: 7%; color: white;">EasyExam</router-link></b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav style="margin-left: 1%">
                <b-navbar-nav style="font-size: medium">
                    <!-- <i class="fas fa-home fa-1x" style="color:  white;"></i> -->
                    <b-nav-item><router-link to="/dashboard" style="color: white; margin-right: 15px">Home</router-link></b-nav-item>
                    <b-nav-item><router-link to = "/submit" style="color: white; margin-right: 15px">Submit</router-link></b-nav-item>
                    <b-nav-item><router-link to = "/generate" style="color: white; margin-right: 15px">Generate</router-link></b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto" style="margin-right: 5%">

                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>User</em>
                        </template>
                        <b-dropdown-item> <router-link to="/profile" style="color: black">Profile</router-link></b-dropdown-item>
                        <b-dropdown-item href="/" style="color: black">Sign Out</b-dropdown-item>
                       <!-- <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>-->
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
        <b-navbar toggleable="lg" v-if="!getIsLogged" type="dark" variant="dark" style="margin-bottom: 25px">
            <b-navbar-brand href="/" style="font-family: italic; font-size: xx-large; margin-left: 5%">EasyExam</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto" style="margin-right: 6%">
                    <b-nav-item><router-link to ="/login" style="color: white">Sign In</router-link></b-nav-item>
                    <button style="border-radius: 15px; color: white; background: #2f3135; border-color: white;"><b-nav-item><router-link to ="/register" style="color: white">Sign Up</router-link></b-nav-item></button>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
//import {mapMutations} from 'vuex'
import {mapGetters} from 'vuex'
export default {
    name: "NavBar",
    computed:{
   // ...mapMutations(['logout']),
        ...mapGetters(['getIsLogged'])
    },
    methods: {
        //logout() {        }
    }
}
</script>

<style scoped>

</style>