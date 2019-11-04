<template>
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Login</div>
                    <div class="card-body">
                        <form id="login-form" @submit.prevent="processForm">
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="email-address" v-model="username" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" v-model="password" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 offset-md-4">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="remember"> Remember Me
                                        </label>
                                <a href="#" class="btn btn-link">
                                    Forgot Your Password?
                                </a>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-3 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Login
                                </button>

                                    <a href="/register" class="btn btn-primary ml-4">Register</a>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</template>


<script>

import { account } from '../store/account.module'

export default {
  name: 'login',

  data: () => ({
      username: '',
      password: ''
  }),

  methods: {
      processForm: function() {
        const { username, password } = this;
        account.actions.login({username, password });
        console.log({ email: username, password: password });
      }
  }
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