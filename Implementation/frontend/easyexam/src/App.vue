<template>
  <div id="app">
   <NavBar></NavBar>
    <router-view/>
  </div>
</template>
<script>
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
