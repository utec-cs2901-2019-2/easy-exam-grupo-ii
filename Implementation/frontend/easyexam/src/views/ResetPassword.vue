<template>
    <div>
        <div class="container">
            <br>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <header class="card-header">
                            <a href="/" class="float-right btn btn-outline-primary mt-1">Cancel</a>
                            <h4 class="card-title mt-2">Recover my account</h4>
                        </header>
                        <article class="card-body">
                            <form @submit.prevent="loadNewPass">
                                <div class="form-group">
                                    <label>Password</label>
                                    <input v-model="password" class="form-control" type="password" placeholder="New Password" :class="{ 'is-invalid': submitted && !password }">
                                    <div v-show="submitted && !password" class="invalid-feedback">Password is required</div>
                                </div>

                                <div class="form-group">
                                    <label>Confirm Password</label>
                                    <input @keyup ="validatePassword" v-model="confirm_password" class="form-control" type="password" placeholder="Retype your password" :class="{ 'is-invalid': submitted && !confirm_password || passwordCheck}">
                                    <div v-show="submitted && !confirm_password || passwordCheck" class="invalid-feedback">Password not match</div>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block"> Change Password  </button>
                                </div>

                            </form>
                        </article> <!-- card-body end .// -->

                    </div> <!-- card.// -->

                </div> <!-- col.//-->
            </div>
        </div>

    </div>
</template>

<script>
    import axios from 'axios'


    export default {
        name: 'newpass',
        components: {
        },
        data(){
            return {
                password: '',
                confirm_password: '',
                token: '',
                submitted: false,
                passwordCheck: false
            }
        },
        computed:{
        },
        methods:{
            loadNewPass: function () {
                this.submitted = true;
                if (this.password && this.confirm_password && 
                    this.password == this.confirm_password) {
                    axios.post('http://'+ this.$store.state.clientURL + '/api/v1/reset-password', {
                        password: this.password,
                        token: this.$route.query.token
                    })
                        .then((response) => {
                            console.log(response);
                            this.$router.push('/login');
                        }, (error) => {
                            console.log(error);
                        })
                }
            },
            validatePassword () {
                if (this.password != this.confirm_password) {
                    this.passwordCheck =  true;
                } else {
                    this.passwordCheck =  false;
                }
            }
        },
    }

</script>

<style scoped>

</style>