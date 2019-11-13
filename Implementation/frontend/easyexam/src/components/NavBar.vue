<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
            <b-navbar-brand href="/">EasyExam</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    
                    <router-link to="/dashboard">Home</router-link>
                    <router-link to = "/submit">Submit</router-link>
                    <router-link to = "/generate">Generate</router-link>
                    <b-nav-item href="/feedback">Feedback</b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">

                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>User</em>
                        </template>
                        <b-dropdown-item href="/profile">Profile</b-dropdown-item>
                        <b-dropdown-item @click="logouts">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
import {mapMutations} from 'vuex'
export default {
    name: "NavBar",
    computed:{
    ...mapMutations(['logout'])
    },
    methods: {
        logout() {

        }
    }
}
</script>

<style scoped>

</style>