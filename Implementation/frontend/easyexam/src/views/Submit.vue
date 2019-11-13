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
