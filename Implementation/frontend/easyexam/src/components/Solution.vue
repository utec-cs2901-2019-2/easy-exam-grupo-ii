<template>
    <div>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Solution!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form @submit="onSubmit" @reset="onReset">
                            <b-form-group id="input-group-1" label="What type of problem would you like to submit?" label-for="input-3">
                                <multiselect
                                        v-model="problem.type"
                                        placeholder="Pick a value"
                                        label="name"
                                        track-by="id"
                                        :options="types"
                                        :multiple="false"
                                        :searchable="false"
                                        :close-on-select="true"
                                        required
                                >
                                </multiselect>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Problem Solution" label-for="input-2">
                                <vue-editor
                                        v-model="solution.description"
                                        placeholder="Please enter your solution here"
                                        type="text"
                                        :editor-toolbar="customToolbar"
                                        required
                                >
                                </vue-editor>
                                <b-input-group class="mt-3">
                                    <b-form-input v-model="solution.alt_body"></b-form-input>
                                    <b-form-input v-model="solution.alt_value"></b-form-input>
                                    <b-button variant="outline-secondary" @click ="createAlternative">Add</b-button>
                                </b-input-group>
                                <b-table caption-top :items="solution.alternatives" :fields="fields">
                                    <template v-slot:cell(edit)="row">
                                        <b-button size="sm" class="mr-1">Edit</b-button>
                                        <b-button size="sm" variant="danger" @click="deleteAlternative(index)">X</b-button>
                                    </template>
                                </b-table>
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
    import { mapState, mapMutations } from 'vuex'
    import axios from "axios"
    import Multiselect from "vue-multiselect"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    export default {
        mixins: [validationMixin],
        name: "Solution",
        data() {
            return {
                tags: [],
                fields: [
                    {key:'body', label: 'Alternative'},
                    {key:'value', label: 'Correct'},
                    {key:'edit', label: ''}
                ],

            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                const p_post = axios.post("http://localhost:3000/problem", {
                    id: new Date(),
                    title: this.problem.title,
                    type: this.problem.type,
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
            },
            ...mapMutations([
                'createAlternative',
                'deleteAlternative'
            ]),
            showUpdateForm (alt_id){
                this.solution.updt_id= alt_id;
                this.solution.updt_body = this.solution.alternatives [alt_id].body;
                this.solution.updt_value = this.solution.alternatives [alt_id].value;
            }
        },
        mounted() {
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                solution: state => state.submit.form.solution,
                types: state => state.submit.types,
                customToolbar: state => state.submit.editor
            })
        },
        components: {
            VueEditor,
            Multiselect

        },
        validations: {
            solution: {
                description: {
                    required,
                    minLength: minLength(10)
                }
            },
            problem: {
                topics: {
                    required
                }
            }
        }
    }
</script>

<style scoped>

</style>