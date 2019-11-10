<template>
    <div>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Problem!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form @reset="onReset">
                            <b-form-group id="input-group-2" label="Problem Title:" label-for="input-1">
                                <b-form-input
                                        id="input-1"
                                        v-model="problem.title"
                                        type="text"
                                        :state="validation"
                                        required
                                        placeholder="Please enter your problem title here"
                                ></b-form-input>
                                <b-form-invalid-feedback :state="validation">
                                    You must need to enter a title.
                                </b-form-invalid-feedback>
                                <b-form-valid-feedback :state="validation">
                                    Looks Good.
                                </b-form-valid-feedback>
                            </b-form-group>

                            <b-form-group id="input-group-3" label="Problem Body">
                                <vue-editor
                                        v-model="problem.body"
                                        placeholder="Please enter your problem body here"
                                        type="text"
                                        required
                                        :editorToolbar="customToolbar"
                                >
                                </vue-editor>
                            </b-form-group>
                            <b-form-group id = "input-group-img" label="Select an image (optional)">
                                <b-form-file
                                        v-model="problem.image"
                                        :state="Boolean(problem.image)"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            <b-form-group label="Tags:">
                                <multiselect
                                        v-model="problem.topics_id"
                                        placeholder="Search a tag"
                                        label="name"
                                        track-by="id"
                                        :options="tags"
                                        :multiple="true"
                                >
                                </multiselect>
                            </b-form-group>
                            <b-button type="reset" variant="danger">Reset</b-button>
                            <b-button variant="primary" class="mx-1 float-right" @click="goNext">Next</b-button>
                        </b-form>
                    </b-col>
                    <b-col>
                        <h2>Visualizador</h2>
                        <b-card class="mt-3" header="Form Data Result">
                            <pre class="m-0">{{ problem }}</pre>
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
    import Multiselect from "vue-multiselect"
    import axios from "axios"

    export default {
        data() {
            return {
                tags: [],
            }
        },
        mounted() {
            const tag = axios.get("http://localhost:3000/tags");
            tag.then(response => (this.tags = response.data));
        },
        methods: {
            goNext() {
                this.$store.commit('updateViewNext')
            },
            onReset(evt) {
                evt.preventDefault();
                this.problem.title = '';
                this.problem.body = '';
                this.problem.image = null;
                this.problem.topics_id = null
            }
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                customToolbar: state => state.submit.editor
            }),
            validation() {
                return (this.problem.title.length > 4)
            }
        },
        components: {
            VueEditor,
            Multiselect
        }
    }
</script>
<style src="../static/css/vue-multiselect/vue-multiselect.min.css"></style>

