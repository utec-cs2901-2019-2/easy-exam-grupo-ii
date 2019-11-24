<template>
    <div>
        <Problem v-if="showProblem">
        </Problem>
        <Solution v-if="showSolution">
        </Solution>
    </div>
</template>
<script>
    import Problem from "../components/Problem";
    import Solution from "../components/Solution";
    import { mapState } from 'vuex'
    export default {
        created(){
        this.items = JSON.parse(sessionStorage.getItem('items'))
        this.$store.state.user.username = this.items.map(items =>items.username).toString()
        this.$store.state.user.token = this.items.map(items =>items.token).toString()
        this.$store.state.user.credits = parseInt(this.items.map(items =>items.credits))
        this.$store.state.user.id = parseInt(this.items.map(items => items.id))
        this.$store.state.isLogged = true;
        },

        name: "Submit",
        data() {
            return {
            }
        },
        components: {
            Problem,
            Solution,
        },
        computed:{
            ...mapState ({
                showProblem: state => state.submit.show.problem,
                showSolution: state => state.submit.show.solution
            })
        }
    }
</script>

<style scoped>
</style>
