<template>
    <div>
        <div class="container">
    <br>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <a href="/login" class="float-right btn btn-outline-primary mt-1">Log in</a>
                    <h4 class="card-title mt-2">Recover my account</h4>
                </header>
                
                <article class="card-body">
                    <b-alert variant="success" v-model="sended" dismissible>Email sended</b-alert>
                    <b-alert v-model="emailCheck" variant="danger" dismissible>
                        User dont register
                    </b-alert>
                    <b-form action="" @submit="loadRecover" @submit.prevent="">
                        <div class="form-group">
                            <label>Email</label>
                            <input @keyup="emailCheck = false" v-model="email" type="email" class="form-control" placeholder="University / job email address, e.g.name@utec.edu"  :class="{ 'is-invalid': submitted && !email }">
                            <div v-show="submitted && !email" class="invalid-feedback">Email is required</div>
                        </div>

                        <div class="form-group">
                            <b-button variant="primary" type="submit" class="btn btn-primary btn-block">
                                <b-spinner v-show="spinner" small></b-spinner>
                                 Recover my account
                            </b-button>
                        </div>

                    </b-form>
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
        name: 'recover',
        components: {
        },
        data(){
            return {
                email: '',
                submitted: false,
                sended: false,
                emailCheck: false,
                spinner: false
            }
        },
        computed:{
        },
        methods:{
            loadRecover () {
                this.submitted = true;
                if (this.email && !this.spinner) {
                    this.spinner = true;
                    axios.post('http://'+ this.$store.state.clientURL + '/api/v1/forgot-password', {
                        email: this.email,
                    })
                        .then((response) => {
                            console.log(response.data.message);
                            if (response.data.message == "success") {
                                this.sended = true;
                            }
                            if (response.data.message == "fail") {
                                this.emailCheck = true;
                            }
                            this.spinner = false;
                        }, (error) => {
                            console.log(error);
                            this.spinner = false;
                        })
                    this.email = ""  
                    this.submitted = false;
                }
            }
        },
    }


</script>

<style scoped>

</style>