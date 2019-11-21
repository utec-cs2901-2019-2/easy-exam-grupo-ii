<template>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">


          <div class="inner cover text-center">
            <div class="container inner">
              <h1 class="cover-heading">Welcome To EasyExam!</h1>
              <p class="lead"> <small>Your smartest exam generation tool!</small></p>
            </div>
              <HomeCarrousel/>
              <b-card class="text-center">
              <div class="inner cover text-center">
                <p class="lead">EasyExam is an online platform aiming to help teachers to generate problems for the exams that they wish to take their students. EasyExam works on a 3x1 system in which each the user sends 1 problem for every 3 they will recieve, it is in order to encourage the collaboration within the platform. The users can download the generated exams in PDF-Latex format.</p>
                <p class="lead text-center">
                    <b-button variant="primary" href="/login">Get Started</b-button>
                    <b-button variant="primary" href="/pruebahtml">PruebaHtml</b-button>
                    <b-button class="m-2" variant="primary" href="#Learn-More">Learn More</b-button>
                </p>
              </div>
              </b-card>
        </div>
          <div id="Learn-More">
              <b-embed
                      type="iframe"
                      aspect="16by9"
                      src="https://www.youtube.com/embed/fL308_-Kbt0?rel=0"
                      allowfullscreen
              ></b-embed>
          </div>

      </div>

    </div>

</template>

<script>

// @ is an alias to /src
import HomeCarrousel from "../components/HomeCarrousel";


export default {
    created(){
    this.items = JSON.parse(localStorage.getItem('items'))
    this.$store.state.user.username = this.items.map(items => items.username)
    this.$store.state.user.token = this.items.map(items => items.token)
    this.$store.state.user.credits = this.items.map(items => items.credits)
    this.$store.state.user.id = this.items.map(items => items.id)
    this.$store.state.isLogged = true;
    },

  name: 'home',
  components: {
      HomeCarrousel
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Hind+Madurai|Jomolhari&display=swap');
/* Links */
a,
a:focus,
a:hover {
  color: #fff;
}


/*
 * Base structure
 */

html,body {
    height: 100%;
    background: #3070ff;
    color: #fff;
    text-align: center;
    text-shadow: 0 1px 3px rgba(241, 233, 233, 0.884);
    font-family: 'Jomolhari', serif;
    font-family: 'Hind Madurai', sans-serif;
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
