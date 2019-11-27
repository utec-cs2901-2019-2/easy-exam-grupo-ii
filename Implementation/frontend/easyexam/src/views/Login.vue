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
                          <b-alert
                            :show="dismissCountDown"
                            dismissible
                            variant="danger"
                            @dismissed="dismissCountDown=0"
                            @dismiss-count-down="countDownChanged"
                            >
                            Email or password incorrect
                            </b-alert>

                        <form @submit.prevent="sendpost">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" v-model="email" name="email" class="form-control" :class="{ 'is-invalid': submitted && !email }" />
                                <div v-show="submitted && !email" class="invalid-feedback">Email is required</div>
                            </div>
                            <div class="form-group">
                                <label htmlFor="password">Password</label>
                                <input type="password" v-model="password" name="password" class="form-control" :class="{ 'is-invalid': submitted && !password }" />
                                <div v-show="submitted && !password" class="invalid-feedback">Password is required</div>
                            </div>
                            <div class="form-row justify-content-center">
                                <label>
                                    <input type="checkbox" name="remember">  Remember Me
                                </label>
                            </div>
                            <div class="form-row justify-content-center">
                                <a href="/forgot-password" class="btn btn-link"> Forgot Your Password?</a>
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
import { mapState } from 'vuex'

export default {
  name: 'login',
  components: {
  },
  data(){
      return {
          email:'',
          password: '',
          submitted: false,
          dismissSecs: 5,
          dismissCountDown: 0,
      }
  },
  computed:{
    ...mapState ({
            username: state => state.user.username,
            token: state => state.user.token
        })
  },
    methods:{
      sendpost() {
          this.submitted = true;
          const { email, password } = this;
          
          if (email && password) {
              axios.post('http://'+ this.$store.state.clientURL + '/api/v1/login', {
                email: email,
                password: password
            })
            .then(response => {
                if (response.data.message == "success") {
                    this.$store.state.user.username = response.data.result.username
                    this.$store.state.user.token = response.data.result.token
                    this.$store.state.user.credits = response.data.result.credits
                    this.$store.state.user.id = response.data.result.id
                    this.$store.state.user.role.id = response.data.result.role.id
                    this.$store.state.user.role.name = response.data.result.role.name
                    this.$store.state.isLogged = true;
                    this.$router.push('/dashboard')
                    this.$session.set('user', this.$store.state.user.username)
                    this.$session.set('token', this.$store.state.user.token )
                    this.$session.set('isLogged', this.$store.state.isLogged)
                    this.$session.set('user_id',this.$store.state.user.id )
                } else {
                    this.dismissCountDown = this.dismissSecs
                }
            }, error => {
                console.log(error);
                this.dismissCountDown = this.dismissSecs
            })
          }
      },
      countDownChanged(dismissCountDown) {
        this.dismissCountDown = dismissCountDown
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
