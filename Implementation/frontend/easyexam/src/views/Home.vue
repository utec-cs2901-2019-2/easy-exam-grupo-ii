<template>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">
          <div class="inner cover text-center">

            <div class="container inner">
                <h1 class="cover-heading" style="font: oblique bold 350% cursive;">Welcome To EasyExam!</h1>
              <p class="lead" style="font: oblique bold 150% cursive;"> <small>Your smartest exam generation tool!</small></p>
            </div>
              <hr width="95%" size=200 color="#2f3135">
              <b-row>
                  <b-col md="6">
              <div class="inner cover text-center" style="margin: 3%">
                <p class="lead">EasyExam is an online platform aiming to help teachers to generate problems for the exams that they wish to take their students. EasyExam works on a 3x1 system in which each the user sends 1 problem for every 3 they will recieve, it is in order to encourage the collaboration within the platform. The users can download the generated exams in PDF-Latex format.</p>
                <p class="lead text-center">
                    <b-button variant="dark" href="/login" style="background-color: black">Get Started</b-button>
                    <b-button class="m-2" variant="dark" href="#Learn-More">Learn More</b-button>
                </p>
              </div>
                  </b-col>
                  <b-col md="6">
              <HomeCarrousel style="margin: 3%"></HomeCarrousel>
                  </b-col>
              </b-row>
        </div>
          <hr width="95%" size=200 color="#2f3135">
          <div id="Learn-More" style="margin-top: 3%">
              <b-embed
                      type="iframe"
                      aspect="16by9"
                      src="https://www.youtube.com/embed/fL308_-Kbt0?rel=0"
                      allowfullscreen
              ></b-embed>
          </div>
          <Footer/>

      </div>

    </div>

</template>

<script>

// @ is an alias to /src
import HomeCarrousel from "../components/HomeCarrousel";
import Footer from "../components/Footer";


export default {
  created(){
        this.items = JSON.parse(localStorage.getItem('items'))
        this.$store.state.user.username = this.items.map(items =>items.username).toString()
        this.$store.state.user.token = this.items.map(items =>items.token).toString()
        this.$store.state.user.credits = parseInt(this.items.map(items =>items.credits))
        this.$store.state.user.id = parseInt(this.items.map(items => items.id))
        this.$store.state.isLogged = true;
        },

  name: 'home',
  components: {
      Footer,
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
