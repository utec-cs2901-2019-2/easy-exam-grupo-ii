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
<<<<<<< HEAD:Implementation/frontend/easyexam/src/views/ResetPassword.vue
                            <form action="" @submit="loadNewPass" @submit.prevent="">
=======
                            <form action="" @submit="loadNewPass" @submit.prevent="loadNewPass">
>>>>>>> 52a022c0669d63270a23ce0f8f8f2fac3204bdea:Implementation/frontend/easyexam/src/views/NewPass.vue
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" name="pass" id="pass1" @keyup="checkPass" class="form-control" placeholder="Introduce password">
                                </div>

                                <div class="form-group">
                                    <label>Repeat Password</label>
                                    <input type="password" name="repass" id="repass1" @keyup="checkPass" class="form-control" placeholder="Repeat password">
                                    <span id="confirm-message2" class="confirm-message"> hola</span>
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
                token: ''
            }
        },
        computed:{
        },
        methods:{
            loadNewPass: function () {
                axios.post('http://localhost:9898/api/v1/reset-password', {
                    password: this.password,
                    token: this.$route.query.token
                })
                    .then((response) => {
                        console.log(response);
                    }, (error) => {
                        console.log(error);
                    })
<<<<<<< HEAD:Implementation/frontend/easyexam/src/views/ResetPassword.vue

=======
            },
            checkPass: function () {
                var pass = document.getElementById('pass1');
                var repass = document.getElementById('repass1');
                var message = document.getElementById('confirm-message2');
                var accept = "#66cc66";
                var fail = "#F74B00";
                if(pass.value === repass.value){
                    repass.style.backgroundColor = accept;
                    message.style.color =accept;
                }
                else{
                    repass.style.backgroundColor = fail;
                    message.style.color = fail;

                }
>>>>>>> 52a022c0669d63270a23ce0f8f8f2fac3204bdea:Implementation/frontend/easyexam/src/views/NewPass.vue
            }
        },
    }

</script>

<style scoped>

</style>