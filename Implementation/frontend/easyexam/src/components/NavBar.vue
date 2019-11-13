<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info">
            <b-navbar-brand href="/">EasyExam</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="/home">Home</b-nav-item>
                    <b-nav-item href="/submit">Submit</b-nav-item>
                    <b-nav-item href="/generate">Generate</b-nav-item>
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