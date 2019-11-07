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
                    <form action="" @submit="loadRecover">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" placeholder="University / job email address, e.g.name@utec.edu">
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> Recover my account  </button>
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
        name: 'recover',
        components: {
        },
        data(){
            return {
                email: ''
            }
        },
        computed:{
        },
        methods:{
            loadRecover: function () {
                axios.post('http://localhost:9898/api/v1/recover', {
                    email: this.email,
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

</style>