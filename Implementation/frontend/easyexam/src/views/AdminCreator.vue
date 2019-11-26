<template>
    <div class="container">
        <br>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <header class="card-header">
                        <h4 class="card-title mt-2">Create Admin User</h4>
                    </header>
                    <article class="card-body">
                        <b-alert v-model="emailCheck" variant="danger" dismissible>
                            User already register
                        </b-alert>
                        <b-alert
                        :show="dismissCountDown"
                        dismissible
                        variant="success"
                        @dismissed="dismissCountDown=0"
                        @dismiss-count-down="countDownChanged"
                        >
                        User Created
                        </b-alert>
                        <b-form @submit.prevent="loadpost">
                            <label>Name <font color="red">*</font></label>
                            <div class="form-row">
                                <div class="col form-group">
                                    <input v-model="fullname" type="text" class="form-control" placeholder="Full Name" :class="{ 'is-invalid': submitted && !fullname }">
                                    <div v-show="submitted && !fullname" class="invalid-feedback">Full Name is required</div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Email address <font color="red">*</font></label>
                                <input @keyup="emailCheck = false" v-model="email" type="email" class="form-control" placeholder="University / job email address, e.g.name@utec.edu" :class="{ 'is-invalid': submitted && !email }">
                                <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                                <div v-show="submitted && !email" class="invalid-feedback">Email is required</div>
                            </div>


                            <div class="form-row">

                                <div class = "form-group col-md-6">
                                    <label> Address <font color="red">*</font></label>
                                    <input v-model="address" type="text" class = "form-control" placeholder="Address" :class="{ 'is-invalid': submitted && !address }">
                                    <div v-show="submitted && !address" class="invalid-feedback">Address is required</div>
                                </div>
                                <div class = "form-group col-md-6">
                                    <label> Phone <font color="red">*</font></label>
                                    <input v-model="phone" type="text" class = "form-control" placeholder="Phone" :class="{ 'is-invalid': submitted && !phone }">
                                    <div v-show="submitted && !phone" class="invalid-feedback">Phone is required</div>
                                </div>

                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input @keyup="checkLenght" v-model="password" class="form-control" type="password" placeholder="New Password" :class="{ 'is-invalid': submitted && !password || passLenght}">
                                <small class="form-text text-muted">Make sure it al least 6 characters.</small>
                                <div v-show="submitted && !password || passLenght" class="invalid-feedback">Full password is required</div>
                            </div>

                            <div class="form-group">
                                <label>Confirm Password <font color="red">*</font></label>
                                <input @keyup ="validatePassword" v-model="confirm_password" class="form-control" type="password" placeholder="Retype your password" :class="{ 'is-invalid': submitted && !confirm_password || passwordCheck}">
                                <div v-show="submitted && !confirm_password || passwordCheck" class="invalid-feedback">Password not match</div>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block"> Register  </button>
                            </div>

                        </b-form>
                    </article>
                </div>
            </div>
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
          submitted: false,
          fullname: '',
          email: '',
          password: '',
          confirm_password: '',
          address: '',
          phone: '',
          passwordCheck: false,
          passLenght:false,
          emailCheck: false,
          dismissSecs: 10,
          dismissCountDown: 0,
          showDismissibleAlert: false
      }
  },
  computed:{
  },
    methods:{
        loadpost () {
            this.submitted = true;

            if (this.fullname && this.email && this.password && this.confirm_password &&
                this.password == this.confirm_password && 
                this.address && this.phone) {
                    console.log(this.fullname, this.password, this.address, this.phone, this.email )

                    axios.post('http://'+ this.$store.state.clientURL + '/admin/v1/register-admin', {
                        fullname:this.fullname,
                        address: this.address,
                        phone: this.phone,
                        user : {
                            email: this.email,
                            password: this.password,
                            role: {
                                id:0,
                                name:''
                            }
                        }
                    })
                        .then((response) => {
                            if (response.data.message == "success") {
                                this.dismissCountDown = this.dismissSecs
                                this.submitted = false
                                this.fullname= ''
                                this.email= ''
                                this.password= ''
                                this.confirm_password= ''
                                this.address= ''
                                this.phone= ''
                                this.passwordCheck= false
                                this.passLenght=false
                                this.emailCheck= false

                            } else {
                                this.emailCheck = true
                                this.email = ''
                            }
                        }, (error) => {
                            console.log(error)
                        })
                }
        },
        validatePassword () {
            if (this.password != this.confirm_password) {
                this.passwordCheck =  true;
            } else {
                this.passwordCheck =  false;
            }
        },
        checkLenght(){
            if (this.password.length<6){
                this.passLenght= true;
            } else {
                this.passLenght =  false;
            }
        },
        countDownChanged(dismissCountDown) {
            this.dismissCountDown = dismissCountDown
        }
    },
}
</script>
