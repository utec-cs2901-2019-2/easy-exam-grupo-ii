<template>
    <div>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Problem!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form @submit="onSubmit" @reset="onReset">
                            <b-form-group id="input-group-1" label="What type of problem would you like to submit?" label-for="input-3">
                                <multiselect
                                        v-model="problem.topics_id"
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
                            <b-button type="submit" variant="primary" class="mx-1 float-right">Next</b-button>
                        </b-form>
                    </b-col>
                    <b-col>
                        <h2>Vizualizador</h2>
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
    import Multiselect from "vue-multiselect"
    import axios from "axios"

    export default {
        data() {
            return {
                problem: {
                    title: '',
                    body: '',
                    topics_id: [
                    ],
                    image: null,
                },
                types: [],
                tags: [],
                customToolbar: [
                    [{ header: [2, 3, 4, false] }],
                    ["bold", "italic", "underline"],
                    [{ list: "ordered" }, { list: "bullet" }],
                    ["code-block"],
                ]
            }
        },
        mounted() {
            const type = axios.get("http://localhost:3000/types");
            const tag = axios.get("http://localhost:3000/tags");
            axios.all([type, tag]).then(axios.spread((...responses) => {
                const rtype = responses[0];
                const rtag = responses[1];
                this.types = rtype.data;
                this.tags = rtag.data
            }))
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                console.log(JSON.stringify(this.problem));
                const p_post = axios.post("http://localhost:3000/problem", {
			id: 6,
                    title: this.problem.title,
                    body: this.problem.body,
                    topics_id: this.problem.topics_id,
                    image: this.problem.image
                });
                p_post.then(resp => {
                    console.log(resp.data)
                });
                p_post.catch(error => {console.log(error)})

            },
            onReset(evt) {
                evt.preventDefault();
                this.problem.title = '';
                this.problem.topics = null;
                this.problem.body = '';
                this.problem.image = null;
            }
        },
        computed: {
            validation() {
                return this.problem.title.length > 4
            }
        },
        components: {
            VueEditor,
            Multiselect
        }
    }
</script>
<style src="../static/css/vue-multiselect/vue-multiselect.min.css"></style>

