<template>
    <div class="cotainer m-4">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card">
                    <header class="card-header">
                        <a href="/register" class="float-right btn btn-outline-primary mt-1">Sign up</a>
                        <h4 class="card-title mt-2">Login</h4>
                    </header>
                    <article class="card-body">
                        <form action="" method="">
                            <div class="form-group">
                                <label>Email address</label>
                                <input type="email" class="form-control" placeholder="">
                                <small class="form-text text-muted">We'll never share your Email Address with anyone else.</small>
                            </div>

                            <div class="form-group">
                                <label>Password</label>
                                <input type="email" class="form-control" placeholder="">

                            </div>


                            <div class="form-row justify-content-center">
                                <label>
                                    <input type="checkbox" name="remember">  Remember Me
                                </label>
                            </div>
                            <div class="form-row justify-content-center">
                                <a href="#" class="btn btn-link"> Forgot Your Password?</a>
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

</template>


<script>

export default {
  name: 'login',
  components: {
  },
  data(){
  },
  computed:{
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