<template>
  <div id="app">
   <NavBar v-if="getIsLogged"></NavBar>
    <router-view/>
  </div>
</template>
<script>
<<<<<<< HEAD
  import NavBar from "./components/NavBar";
  import {mapGetters} from 'vuex'
  export default {
    name: 'app',
    components: {
      NavBar
    },
    computed:{
      ...mapGetters(['getIsLogged'])
    }
  }
</script>
<style scoped>
</style>

=======
import NavBar from "./components/NavBar";
import {mapGetters} from 'vuex'
export default {
  name: 'app',
  components: {
    NavBar
  },
  computed:{
  ...mapGetters(['getIsLogged'])
  }
}
</script>
<style scoped>
</style>
>>>>>>> 0aa5e6ebc54282ac4bed110517f5aaa2acf7c253
