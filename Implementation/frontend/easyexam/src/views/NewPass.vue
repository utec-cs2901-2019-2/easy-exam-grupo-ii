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
                            <form>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="password" class="form-control" placeholder="Introduce password">
                                </div>

                                <div class="form-group">
                                    <label>Repeat Password</label>
                                    <input type="password" class="form-control" placeholder="Repeat password">
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
    export default {
        name: "NewPass"
    }
</script>

<style scoped>

</style>