<template>
  <div id="app">
   <NavBar v-if="getIsLogged"></NavBar>
    <router-view/>
  </div>
</template>
<script>
  import NavBar from "./components/NavBar";
  export default {
    name: 'app',
    components: {
      NavBar
    }
  }
</script>
<style scoped>
</style>

<<<<<<< HEAD
<script>
import {mapGetters} from 'vuex'
export default {
  computed:{
    ...mapGetters(['getIsLogged'])
  }
}
</script>
=======
>>>>>>> 326f8857a5c6ebb8c1a9c2b8b3283a00ef85df76

