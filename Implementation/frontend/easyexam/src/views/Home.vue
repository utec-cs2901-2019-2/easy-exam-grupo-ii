<template>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="container">
          <div class="masthead clearfix">
            <div class="container inner">
              <h3 class="masthead-brand">Welcome To EasyExam</h3>
            </div>
          </div>

          <div class="inner cover text-center">
            <img src="@/static/img/exam.jpg" alt="..." class="img-thumbnail w-25">
            <h1 class="cover-heading">Easy Exam</h1>
            <p class="lead">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Corporis provident placeat atque doloribus, molestiae natus eaque in vitae illo officia, modi laboriosam fugiat soluta iure consectetur voluptatem eligendi doloremque ratione?</p>
            <p class="lead text-center">
              <a href="/login" class="btn btn-lg btn-default">Get Started</a>
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
  color: #333;
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
  background-color: #fff;
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
  background-color: rgb(49, 160, 132);
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
  padding: 30px;
}


/*
 * Header
 */
.masthead-brand {
  margin-top: 10px;
  margin-bottom: 10px;
}

.masthead-nav > li {
  display: inline-block;
}
.masthead-nav > li + li {
  margin-left: 20px;
}


@media (min-width: 768px) {
  .masthead-brand {
    float: left;
  }
  .masthead-nav {
    float: right;
  }
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
  background: #18d26e;
}

/*
 * Affix and center
 */

@media (min-width: 768px) {
  /* Pull out the header and footer */
  .masthead {
    position: fixed;
    top: 0;
  }
  /* Start the vertical centering */
  .site-wrapper-inner {
    vertical-align: middle;
  }

}
</style>