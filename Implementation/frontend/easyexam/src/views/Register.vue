<template>
    <div class="container">
        <br>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <header class="card-header">
                        <a href="/login" class="float-right btn btn-outline-primary mt-1">Log in</a>
                        <h4 class="card-title mt-2">Sign up</h4>
                    </header>
                    <article class="card-body">
                        <form action="" @submit="loadpost">
                            <label>Name</label>
                            <div class="form-row">
                                <div class="col form-group">
                                    <input v-model="firstName" type="text" class="form-control" placeholder="First Name">
                                </div>

                                <div class="col form-group">
                                    <input v-model="lastName" type="text" class="form-control" placeholder="Last Name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Email address</label>
                                <input v-model="email" type="email" class="form-control" placeholder="University / job email address, e.g.name@utec.edu">
                                <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>


                            <div class="form-row">

                                <div class = "form-group col-md-6">
                                    <label> Institution </label>
                                    <input v-model="institution" type="text" class = "form-control" placeholder="University / job name">
                                </div>

                                <div class="form-group col-md-6">
                                    <label>Country / region</label>
                                    <select id="inputState" class="form-control">
                                        <option> Choose...</option>
                                    </select>

                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label>Birthdate</label>
                                <b-form-input v-model = 'birthdate' type="date"></b-form-input>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input v-model="password" class="form-control" type="password" placeholder="New Password">
                            </div>

                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input class="form-control" type="password" placeholder="Retype your password">
                            </div> <!-- form-group end.// -->

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block"> Register  </button>
                            </div>

                            <small class="text-muted">By clicking the 'Sign Up' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>

                        </form>
                    </article> <!-- card-body end .// -->

                    <div class="border-top card-body text-center">Have an account? <a href="/login">Log In</a></div>
                </div> <!-- card.// -->

            </div> <!-- col.//-->
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'register',
  components: {
  },
  data(){
      return{
          firstName: '',
          lastName: '',
          email: '',
          password: '',
          institution: '',
          gender:'',
          birthdate: ''
      }
  },
  computed:{
  },
    methods:{
        loadpost: function () {
            axios.post('http://localhost:9898/api/v1/register', {
                firstName:this.firstName,
                lastName: this.lastName,
                user : {
                    email: this.email,
                    password: this.password
                },
                institution: this.institution,
                gender: this.gender
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