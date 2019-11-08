<template>
    <div class="container m-4">
        <Problem v-if="showProb">
        </Problem>
        <Solution v-if="showSol" @finished="true">
        </Solution>
    </div>
</template>
<script>
    import Problem from "../components/Problem";
    import Solution from "../components/Solution";
    export default {
        name: "Submit",
        data() {
            return {
                showProb: true,
                showSol: true
            }
        },
        components: {
            Problem,
            Solution,
        },
        methods: {
            finished(){
                this.showSol = false
            }
        }
    }
</script>

<style scoped>

</style>