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

<script>
import {mapGetters} from 'vuex'
export default {
  computed:{
    ...mapGetters(['getIsLogged'])
  }
}
</script>

