<template>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">


          <div class="inner cover text-center">
            <div class="container inner">
              <h1 class="cover-heading">Welcome To EasyExam!</h1>
              <p class="lead"> <small>Your smartest exam generation tool!</small></p>
            </div>

          <div class="inner cover text-center">
            <img src="@/static/img/exam.jpg" alt="..." class="img-thumbnail w-25">
            <p class="lead">EasyExam is an online platform aiming to help teachers to generate problems for the exams that they wish to take their students. EasyExam works on a 3x1 system in which each the user sends 1 problem for every 3 they will recieve, it is in order to encourage the collaboration within the platform. The users can download the generated exams in PDF-Latex format.</p>
            <p class="lead text-center">
              <a href="/login" class="btn btn-lg btn-light text-dark">Get Started</a>
            </p>
          </div>

        </div>

      </div>

    </div>

</template>

<script>

// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'
import {mapMutations,mapState} from "vuex";

export default {
  name: 'home',
  components: {
  },
  data(){
    return{
    nombre:'HolaMundo'
    }
  },
  computed:{
    ...mapState['nombre'],
    ...mapMutations['changeNombre'],
  }
}
</script>

<style scoped>
/* Links */
a,
a:focus,
a:hover {
  color: #fff;
}

/* Custom default button */
.btn-default,
.btn-default:hover,
.btn-default:focus {
  color: #46dbff;
  text-shadow: none; /* Prevent inheritence from `body` */
  background-color: #fff;
  border: 1px solid #fff;
}


/*
 * Base structure
 */

html,
body {
  height: 100%;
  background-image: url("/src/static/img/bg.jpg");
  background: #3070ff;
}
body {
  color: #fff;
  text-align: center;
  text-shadow: 0 1px 3px rgba(241, 233, 233, 0.884);
}

/* Extra markup and styles for table-esque vertical and horizontal centering */
.site-wrapper {
  display: table;
  width: 100%;
  height: 600px;
  background-color: rgba(49, 160, 132, 0.03);
  /*height: 100%; */
  /*min-height: 100%;*/
}
.site-wrapper-inner {
  display: table-cell;
  vertical-align: top;
}
.cover-container {
  margin-right: auto;
  margin-left: auto;
}

/* Padding for spacing */
.inner {
  padding: 10px;
}

/*
 * Cover
 */

.cover {
  padding: 0 20px;
}
.cover .btn-lg {
  font-family: "Montserrat", sans-serif;
  font-weight: 500;
  letter-spacing: 1px;
  display: inline-block;
  padding: 8px 32px;
  border-radius: 50px;
  transition: 0.5s;
  margin: 10px;
  color: #fff;
  background: rgba(24, 210, 110, 0);
}
</style>