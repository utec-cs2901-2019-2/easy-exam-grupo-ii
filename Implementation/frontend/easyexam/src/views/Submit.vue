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
                name: 'Hola'
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
<<<<<<< HEAD

</style>
=======
</style>
>>>>>>> bdd8f2b35231bec2825c85a6bd073c2f33feb84a
