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
                    <form>
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
    export default {
        name: "Recover.vue"
    }


</script>

<style scoped>

</style>