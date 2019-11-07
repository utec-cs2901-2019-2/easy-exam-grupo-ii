<template>
    <div id="login">
    <div class="cotainer m-4">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <header class="card-header">
                        <a href="/register" class="float-right btn btn-outline-primary mt-1">Sign Up</a>
                        <h4 class="card-title mt-2">Login</h4>
                    </header>
                    <article class="card-body">
                        <form action="" @submit="sendpost">
                            <div class="form-group">
                                <label>Email address</label>
                                <input v-model="email" type="email" class="form-control" placeholder="">
                                <small class="form-text text-muted">We'll never share your Email Address with anyone else.</small>
                            </div>

                            <div class="form-group">
                                <label>Password</label>
                                <input v-model="password" type="password" class="form-control" placeholder="">

                            </div>


                            <div class="form-row justify-content-center">
                                <label>
                                    <input type="checkbox" name="remember">  Remember Me
                                </label>
                            </div>
                            <div class="form-row justify-content-center">
                                <a href="/recover" class="btn btn-link"> Forgot Your Password?</a>
                            </div>
                            <p></p>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Login
                                </button>
                            </div>
                        </form>
                    </article>
                    <div class="border-top card-body text-center">Don't have an account? <a href="/register">Sign up</a></div>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>


<script>
    import axios from 'axios'


export default {
  name: 'login',
  components: {
  },
  data(){
      return {
          email:'',
          password: ''
      }
  },
  computed:{
  },
    methods:{
      sendpost: function () {
          axios.post('/htttp://localhost:9898/api/v1/login', {
              email: this.email,
              password: this.password
          })
              .then((response) => {
                  console.log(response);
              }, (error) => {
                  console.log(error);
              })
      }
    },
}
</script>

<style scoped>

.my-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}

.my-form .row
{
    margin-left: 0;
    margin-right: 0;
}

.login-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}
</style>