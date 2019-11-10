<template>
    <div>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Solution!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form @submit="onSubmit" @reset="onReset">
                            <b-form-group id="input-group-2" label="Problem Solution" label-for="input-2">
                                <vue-editor
                                        v-model="solution.description"
                                        placeholder="Please enter your solution here"
                                        type="text"
                                        :editor-toolbar="customToolbar"
                                        required
                                >
                                </vue-editor>
                            </b-form-group>
                            <b-form-group id = "input-group-img" label="Select an image (optional)">
                                <b-form-file
                                        v-model="solution.image"
                                        :state="Boolean(solution.image)"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            <b-button variant="success" on-clik class = "m-2 float-left" @click = "goBack" >Back</b-button>
                            <b-button type="submit" variant = "primary" class = "m-2 float-right">Submit</b-button>
                            <b-button type="reset" variant = "danger" class="m-2 float-left" >Reset</b-button>
                        </b-form>
                    </b-col>
                    <b-col>
                        <h2>Visualizador</h2>
                        <b-card class="mt-3" header="Form Data Result">
                            <pre class="m-0">{{ solution }}</pre>
                        </b-card>
                    </b-col>
                </b-row>
            </b-container>
        </b-card>

    </div>
</template>

<script>
    import { VueEditor } from "vue2-editor"
    import { mapState } from 'vuex'
    import axios from "axios"
    export default {
        name: "Solution",
        data() {
            return {
                customToolbar: [
                    [{ header: [2, 3, 4, false] }],
                    ["bold", "italic", "underline"],
                    [{ list: "ordered" }, { list: "bullet" }],
                    ["code-block"],
                ]
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                const p_post = axios.post("http://localhost:3000/problem", {
                    id: 3,
                    title: this.problem.title,
                    body: this.problem.body,
                    topics: this.problem.topics_id,
                    rutaImage: this.problem.image,
                    descriptionSolution: this.solution.description,
                    pathImageSolution: this.solution.image
                });
                p_post.then(resp => {
                    console.log(resp.data)
                });
                p_post.catch(error => {
                    console.log(error)
                });
                this.$store.commit('updateViewNext')

            },
            onReset(evt) {
                evt.preventDefault();
                this.solution.description = '';
            },
            goBack () {
                this.$store.commit('updateViewBack')
            }
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                solution: state => state.submit.form.solution
            }),
        },
        components: {
            VueEditor
        },
    }
</script>

<style scoped>

</style>